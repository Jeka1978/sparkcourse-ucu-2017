package transactions;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Evgeny Borisov
 * @since 3.2
 */
@Service
public class DataFrameBuilder {
    @Autowired
    private JavaSparkContext sc;

    @Autowired
    private SQLContext sqlContext;


    public DataFrame load() {
        JavaRDD<String> rdd = sc.textFile("data/transactions.csv");
        JavaRDD<Row> rowJavaRDD = rdd.map(line -> {
            String[] data = line.split(";");
            return RowFactory.create(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
        });

       return sqlContext.createDataFrame(rowJavaRDD,createSchema());

    }


    private static StructType createSchema() {
        return DataTypes.createStructType(new StructField[]{
                DataTypes.createStructField("country code", DataTypes.IntegerType, false),
                DataTypes.createStructField("amount", DataTypes.IntegerType, false),
                DataTypes.createStructField("account number", DataTypes.IntegerType, false)
        });
    }











}
