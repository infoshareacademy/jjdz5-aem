import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ContenerDateCurrency {
    String current;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    LocalDate dateMin;
    LocalDate dateMax;

    @Override
    public String toString() {
        return "ContenerDateCurrency{" +
                "current='" + current + '\'' +
                ", dateMin=" + dateMin +
                ", dateMax=" + dateMax +
                '}';
    }

    public ContenerDateCurrency(String current, LocalDate dateMin, LocalDate dateMax) {
        this.current = current;
        this.dateMin = dateMin;
        this.dateMax = dateMax;
    }



}
