import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CurrentVariable {
    LocalDate localTime1;
    String dateCurrent;
    List<Currency> listCurrency=new ArrayList<>();
    List<Currency>  dateCurrency=new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentVariable that = (CurrentVariable) o;
        return Objects.equals(localTime1, that.localTime1) &&
                Objects.equals(dateCurrent, that.dateCurrent) &&
                Objects.equals(listCurrency, that.listCurrency) &&
                Objects.equals(dateCurrency, that.dateCurrency);
    }

    @Override
    public int hashCode() {

        return Objects.hash(localTime1, dateCurrent, listCurrency, dateCurrency);
    }
}
