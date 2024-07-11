package com.example.Fms.service;

import com.example.Fms.entity.model.Restaurant;
import com.example.Fms.entity.request.DeleteRestaurantReq;
import com.example.Fms.entity.request.RestaurantRequest;
import com.example.Fms.entity.request.UpdateRestaurantReq;

import java.util.List;


public interface RestaurantService {
    Restaurant createRestaurant(RestaurantRequest restaurantRequest);

    boolean updateRestaurant(UpdateRestaurantReq updateReq);

    boolean deleteRestaurant(DeleteRestaurantReq deleteRequest);

    List<Restaurant> getRestaurants();

    void delete();

    Restaurant findByRestaurantId(Integer restaurantId);
    Restaurant saveUpdates(Restaurant restaurant);
}
