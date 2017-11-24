package music;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * @author Evgeny Borisov
 * @since 3.2
 */
public class Main {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "Dev");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Conf.class);
        MusicService musicService = context.getBean(MusicService.class);
        List<String> beatles = musicService.topX("beatles", 3);
        System.out.println("beatles = " + beatles);
    }
}
