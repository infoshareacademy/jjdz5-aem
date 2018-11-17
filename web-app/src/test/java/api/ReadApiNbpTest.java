package api;

import com.isa.aem.api.ReadApiNbp;
import com.isa.aem.api.nbp.CurrencyRates;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ReadApiNbpTest {
    private ReadApiNbp client;

    @Before
    public void setUp() {
        client = new ReadApiNbp();
    }

    @Test
    public void shouldGetAllCurrency() {
        List<CurrencyRates> states = client.callREST();
        states.forEach(s-> System.out.println(s.getRates()));

        assertThat(states.size(), is(55));
    }
}
