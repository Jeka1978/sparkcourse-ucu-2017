package taxi_lab;

import org.apache.spark.Accumulator;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.storage.StorageLevel;
import scala.Tuple2;

import static org.apache.spark.storage.StorageLevel.MEMORY_AND_DISK;

/**
 * @author Evgeny Borisov
 * @since 3.2
 */
public class Main {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("Taxi");
        sparkConf.setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);

        JavaRDD<String> rdd = sc.textFile("data/taxi/taxi_order.txt");
        rdd.persist(MEMORY_AND_DISK());
        long numberOfTrips = rdd.count();
        System.out.println("numberOfTrips = " + numberOfTrips);


        JavaRDD<Trip> tripRdd = rdd.map(String::toLowerCase).map(line -> {
            String[] data = line.split(" ");
            Trip trip = Trip.builder().id(data[0]).cityName(data[1])
                    .km(Integer.parseInt(data[2])).build();
            return trip;
        });

        tripRdd.persist(MEMORY_AND_DISK());

        JavaRDD<Trip> bostonTrips = tripRdd.filter(trip -> trip.getCityName().equals("boston"));
        bostonTrips.persist(MEMORY_AND_DISK());
        long numberOfLongTripsToBoston = bostonTrips.filter(trip -> trip.getKm() >= 10).count();
        System.out.println("numberOfLongTripsToBoston = " + numberOfLongTripsToBoston);


      /*  JavaPairRDD<String, Integer> pairRDD = bostonTrips.mapToPair(trip -> new Tuple2<>(trip.getCityName(), trip.getKm()));
        pairRDD.collect().forEach(System.out::println);
        pairRDD.reduceByKey(Integer::sum)*/

        Double totalBostonKm = bostonTrips.mapToDouble(Trip::getKm).sum();
        System.out.println("totalBostonKm = " + totalBostonKm);


        Counter counter = new Counter();
        Accumulator<Integer> longTrips = sc.accumulator(0);
        tripRdd.foreach(trip -> {
            if (trip.getKm() > 10) {
                longTrips.add(1);
            }
        });


        Integer value = longTrips.value();
        System.out.println("value = " + value);

    }
}






