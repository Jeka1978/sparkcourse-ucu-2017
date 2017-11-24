package music;

import java.util.List;

/**
 * @author Evgeny Borisov
 * @since 3.2
 */
public interface MusicService {
    List<String> topX(String artist, int x);
}
