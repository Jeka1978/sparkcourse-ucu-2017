package linkedIn;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.functions;

/**
 * @author Evgeny Borisov
 * @since 3.2
 */
public class Main {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf();
        sparkConf.setMaster("local");
        sparkConf.setAppName("linked in");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        SQLContext sqlContext = new SQLContext(sc);


        DataFrame dataFrame = sqlContext.read().json("data/linkedIn/profiles.*");
        dataFrame.show();
        dataFrame.printSchema();


        dataFrame = dataFrame.withColumn("age+10", functions.col("age").plus(10));
        dataFrame.show();


    }
}
