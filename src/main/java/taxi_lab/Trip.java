package taxi_lab;

import lombok.Builder;
import lombok.Data;

/**
 * @author Evgeny Borisov
 * @since 3.2
 */
@Data
@Builder
public class Trip {
    private String id;
    private String cityName;
    private int km;
}
