package music;

import org.apache.spark.SparkConf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static music.Const.DEV;

/**
 * @author Evgeny Borisov
 * @since 3.2
 */
@Profile(DEV)
@Configuration
public class DevConfig {



    @Bean
    public SparkConf sparkConf(){
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("songs");
        sparkConf.setMaster("local[*]");
        return sparkConf;
    }
}
