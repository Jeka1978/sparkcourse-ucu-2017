package taxi_lab;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Evgeny Borisov
 * @since 3.2
 */
@Data
public class Counter implements Serializable{
    private int count;
    public void inc(){
        count++;
    }
}
