package music;

import org.apache.spark.api.java.JavaRDD;

import java.util.List;

/**
 * @author Evgeny Borisov
 * @since 3.2
 */
public interface PopularWordsCounter {
    List<String> topX(JavaRDD<String> lines, int x);
}
