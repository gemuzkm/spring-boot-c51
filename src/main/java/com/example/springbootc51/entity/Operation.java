package com.example.springbootc51.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="history")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "invalid value1")
    private Double value1;

    @NotNull(message = "invalid value2")
    private Double value2;

    @NotNull(message = "No operator is supported")
    private String operation;

    private Double result;

    @ManyToOne(cascade = {
            CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "user_id")
    private User user;

    public Operation() {
    }

    public Operation(Double value1, Double value2, String operation) {
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

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", value1=" + value1 +
                ", value2=" + value2 +
                ", operation='" + operation + '\'' +
                ", result=" + result +
                '}';
    }
}
