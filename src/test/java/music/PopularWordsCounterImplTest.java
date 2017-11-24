package music;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static music.Const.DEV;
import static org.junit.Assert.*;

/**
 * @author Evgeny Borisov
 * @since 3.2
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Conf.class)
@ActiveProfiles(DEV)
public class PopularWordsCounterImplTest {
    @Autowired
    private PopularWordsCounter popularWordsCounter;

    @Autowired
    private JavaSparkContext sc;


    @Test
    public void topX() throws Exception {

        List<String> lines = Arrays.asList("java is the most popua, java java", "scala scala", "java groovy");
        JavaRDD<String> rdd = sc.parallelize(lines);

        List<String> topX = popularWordsCounter.topX(rdd, 1);
        Assert.assertEquals("java",topX.get(0));


    }

}



