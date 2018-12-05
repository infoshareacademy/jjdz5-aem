package com.isa.aem.model.selects;

import com.isa.aem.model.Activity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LOCAL")
public class LocalExtremumTracking {

    private static final String UNIQUE_ID = "id";
    private static final String NAME_OF_CURRENCY = "currencyName";
    private static final String AMOUNT_GIVEN_BY_USER = "amount";
    private static final String DATA_AND_TIME_WHEN_USER_SELECTED_OPTION = "dataAndTime";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = UNIQUE_ID)
    private Long id;

    @Column(name = NAME_OF_CURRENCY)
    private String currencyName;

    @Column(name = AMOUNT_GIVEN_BY_USER)
    private Double amount;

    @Column(name = DATA_AND_TIME_WHEN_USER_SELECTED_OPTION)
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "activity")
    private Activity activity;

    public LocalExtremumTracking(String currencyName,
                                 Double amount,
                                 LocalDateTime localDateTime,
                                 Activity activity) {
        this.currencyName = currencyName;
        this.amount = amount;
        this.localDateTime = localDateTime;
        this.activity = activity;
    }

    public LocalExtremumTracking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
