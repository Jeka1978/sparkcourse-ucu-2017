package transactions;

import music.bpp.AutowiredBroadcastBPP;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Evgeny Borisov
 * @since 3.2
 */
@Configuration
@ComponentScan
public class Conf {
    @Bean
    public JavaSparkContext sc(){
       return new JavaSparkContext(new SparkConf().setMaster("local").setAppName("bank"));
    }

    @Bean
    public SQLContext sqlContext(){
        return new SQLContext(sc());
    }

    @Bean
    public AutowiredBroadcastBPP autowiredBroadcastBPP() {
        return new AutowiredBroadcastBPP();
    }














}
