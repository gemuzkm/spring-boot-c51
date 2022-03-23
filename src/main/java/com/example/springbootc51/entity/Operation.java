package com.example.springbootc51.entity;

import javax.validation.constraints.NotNull;

public class Operation {
    private static long index = 1;
    private long id;

    @NotNull(message = "invalid value1")
    private Double value1;

    @NotNull(message = "invalid value2")
    private Double value2;

    @NotNull(message = "No operator is supported")
    private String operation;

    public Operation() {
    }

    public Operation(Double value1, Double value2, String operation) {
        id = index;
        index++;
        this.value1 = value1;
        this.value2 = value2;
        this.operation = operation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getValue1() {
        return value1;
    }

    public void setValue1(Double value1) {
        this.value1 = value1;
    }

    public Double getValue2() {
        return value2;
    }

    public void setValue2(Double value2) {
        this.value2 = value2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "value1=" + value1 +
                ", value2=" + value2 +
                ", operation='" + operation + '\'' +
                '}';
    }
}
