package com.isa.aem.freemarker;

public enum TemplateName {

    CURRENCY_MANAGER("currency-manager-converter"),
    CURRENCY_LOG("currency-converter"),
    GLOBAL_EXTREMUM("global-extremum"),
    LOCAL_EXTREMUM("local-extremum");

    public String name;

    public String getName() {
        return name;
    }

    TemplateName(String name) {
        this.name = name;
    }
}
