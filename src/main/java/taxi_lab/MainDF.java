package taxi_lab;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import static org.apache.spark.storage.StorageLevel.MEMORY_AND_DISK;

/**
 * @author Evgeny Borisov
 * @since 3.2
 */
public class MainDF {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("Taxi");
        sparkConf.setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        SQLContext sqlContext = new SQLContext(sc);

        JavaRDD<String> rdd = sc.textFile("data/taxi/taxi_order.txt");
        rdd.persist(MEMORY_AND_DISK());
        long numberOfTrips = rdd.count();
        System.out.println("numberOfTrips = " + numberOfTrips);


        JavaRDD<Trip> tripRdd = rdd.map(String::toLowerCase).map(line -> {
            String[] data = line.split(" ");
            Trip trip = Trip.builder().id(data[0]).cityName(data[1])
                    .km(Integer.parseInt(data[2])).build();
            return trip;
        });


        JavaRDD<Row> rowRDD = tripRdd.map(trip ->
                RowFactory.create(trip.getId(), trip.getCityName(), trip.getKm()));

        StructType schema = createSchema();

        DataFrame dataFrame = sqlContext.createDataFrame(rowRDD, schema);
        dataFrame.show();


    }

    private static StructType createSchema() {
        return DataTypes.createStructType(new StructField[]{
                    DataTypes.createStructField("id", DataTypes.StringType, false),
                    DataTypes.createStructField("city", DataTypes.StringType, false),
                    DataTypes.createStructField("km", DataTypes.IntegerType, false)
            });
    }
}
