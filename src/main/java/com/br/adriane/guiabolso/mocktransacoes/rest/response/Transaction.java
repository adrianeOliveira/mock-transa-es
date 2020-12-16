package com.br.adriane.guiabolso.mocktransacoes.rest.response;

public class Transaction {

    private String description;
    private Long date;
    private Integer value;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
                "\"description\": " + "\"" + description +"\"" +
                ", \"date\": " + "\"" + date + "\"" +
                ", \"value\": " + "\"" + value + "\"" +
                '}';
    }
}
