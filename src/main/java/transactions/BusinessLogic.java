package transactions;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.apache.spark.sql.functions.*;

/**
 * @author Evgeny Borisov
 * @since 3.2
 */
@Service
public class BusinessLogic {

    @Autowired
    private DataFrameBuilder dataFrameBuilder;



    public void doWork(){
        DataFrame dataFrame = dataFrameBuilder.load();
        dataFrame=dataFrame.withColumn("country name",
                callUDF(CountryCodeConverter.class.getName(),col("country code")));
        dataFrame.show();

    }
}
