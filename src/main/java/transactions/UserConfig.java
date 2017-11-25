package transactions;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Evgeny Borisov
 * @since 3.2
 */
@Component
public class UserConfig implements Serializable{
    @Getter
    private Map<Integer, String> map = new HashMap<>();



    @PostConstruct
    public void initMap(){
        map.put(1, "Canada");
        map.put(2, "Ukraine");
    }

}
