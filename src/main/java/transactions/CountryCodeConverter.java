package transactions;

import music.AutowiredBroadcast;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.api.java.UDF1;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Evgeny Borisov
 * @since 3.2
 */

@RegisterUDF
public class CountryCodeConverter implements UDF1<Integer,String>, Serializable {

    @AutowiredBroadcast
    private Broadcast<UserConfig> broadcast;

    @Override
    public String call(Integer countryCode) throws Exception {
        return broadcast.value().getMap().get(countryCode);
    }
}


