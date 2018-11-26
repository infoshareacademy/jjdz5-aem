package com.isa.aem.model.selects;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "LOCAL")
public class LocalExtremeTracking extends Select {

    public LocalExtremeTracking(String currencyName, Double amount, LocalDateTime localDateTime) {
        super(currencyName, amount, localDateTime);
    }

    public LocalExtremeTracking() {
        super();
    }

}
