package music;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Evgeny Borisov
 * @since 3.2
 */
@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    private JavaSparkContext sc;

    @Autowired
    private PopularWordsCounter popularWordsCounter;


    @Override
    public List<String> topX(String artist, int x) {
        JavaRDD<String> rdd = sc.textFile("data/songs/" + artist);
        return popularWordsCounter.topX(rdd, x);
    }
}







