package com.example.Fms.entity.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeleteRestaurantReq {
    private Integer ownerId;
    private String restaurantPhone;
}
