package music;

import org.apache.spark.SparkConf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static music.Const.PROD;

/**
 * @author Evgeny Borisov
 * @since 3.2
 */
@Profile(PROD)
@Configuration
public class ProdConfig {
    @Bean
    public SparkConf sparkConf(){
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("songs");
        return sparkConf;
    }
}
