package linkedIn;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.StructField;

import static org.apache.spark.sql.functions.*;

/**
 * @author Evgeny Borisov
 * @since 3.2
 */
public class Main {
    private static final String SALARY = "salary";
    private static final String AGE = "age";
    private static final String KEYWORDS = "keywords";
    private static final String KEYWORD = "keyword";

    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf();
        sparkConf.setMaster("local");
        sparkConf.setAppName("linked in");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        SQLContext sqlContext = new SQLContext(sc);


        DataFrame dataFrame = sqlContext.read().json("data/linkedIn/profiles.*");
        dataFrame.show();
        dataFrame.printSchema();

        StructField[] fields = dataFrame.schema().fields();
        for (StructField field : fields) {
            System.out.println(field.dataType());
        }


        dataFrame = dataFrame.withColumn(SALARY,
                col(AGE).multiply(10).multiply(size(col(KEYWORDS))));
        dataFrame.show();

        DataFrame keyWordDF = dataFrame.withColumn(KEYWORD, explode(col(KEYWORDS))).select(KEYWORD);


        Row row = keyWordDF.groupBy(KEYWORD).agg(count(KEYWORD).as("amount"))
                .sort(col("amount").desc()).first();
//        keyWordDF.groupBy(KEYWORD).count().show();

        String mostPopular = row.getAs(KEYWORD);
        System.out.println("mostPopular = " + mostPopular);

        dataFrame.filter(col(SALARY).leq(1200))
                .filter(array_contains(col(KEYWORDS),mostPopular)).show();


    }
}








