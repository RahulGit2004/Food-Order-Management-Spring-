package com.example.Fms.entity.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateRestaurantReq {
//    private Integer ownerId;
    private Integer restaurantId;
    private String oldRestaurantPhone;
    private String restaurantName;
    private String restaurantAddress;
    private String newRestaurantPhone;
}
