package com.example.Fms.entity.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "restaurant")
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

    private Integer ownerId;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer restaurantId;
    private String restaurantPhone;
    private Boolean activeRestaurant;
    private String restaurantName;
    private String restaurantAddress;
    @ElementCollection
    private List<FoodItem> foodItemList;



}
