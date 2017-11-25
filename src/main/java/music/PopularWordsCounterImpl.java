package music;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.Tuple2;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

/**
 * @author Evgeny Borisov
 * @since 3.2
 */


@Service
public class PopularWordsCounterImpl implements PopularWordsCounter, Serializable {

    @AutowiredBroadcast
    private Broadcast<UserConfig> broadcast;

    @Override
    public List<String> topX(JavaRDD<String> lines, int x) {


        return lines.map(String::toLowerCase)
                .flatMap(WordsUtil::getWords)
                .filter(word -> !this.broadcast.value().garbage.contains(word))
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey(Integer::sum)
                .mapToPair(Tuple2::swap)
                .sortByKey(false)
                .map(Tuple2::_2)
                .take(x);
    }
}








