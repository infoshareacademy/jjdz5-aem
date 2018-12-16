package com.isa.aem.freemarker;

public enum TemplateName {

    CURRENCY_MANAGER("currency-manager-converter"),
    CURRENCY_CONVERTER("currency-converter"),
    EXTREMUM("extremum"),
    ERROR("error");

    public String name;

    public String getName() {
        return name;
    }

    TemplateName(String name) {
        this.name = name;
    }
}
