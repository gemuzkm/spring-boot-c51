package com.example.springbootc51.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="history")
@Data
@NoArgsConstructor
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

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    public Operation(Double value1, Double value2, String operation) {
        this.value1 = value1;
        this.value2 = value2;
        this.operation = operation;
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
