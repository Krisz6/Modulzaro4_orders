package com.example.modulzaro4_orders.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;
    private String fromLocation;
    private String orderedFood;
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "ordered_by")
    @JsonBackReference
    private Person orderedBy;
}
