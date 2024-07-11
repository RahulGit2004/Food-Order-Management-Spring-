package com.example.Fms.service.impl;

import com.example.Fms.entity.model.Restaurant;
import com.example.Fms.entity.model.User;
import com.example.Fms.entity.request.DeleteRestaurantReq;
import com.example.Fms.entity.request.RestaurantRequest;
import com.example.Fms.entity.request.UpdateRestaurantReq;
import com.example.Fms.repository.RestaurantRepository;
import com.example.Fms.service.FoodItemService;
import com.example.Fms.service.RestaurantService;
import com.example.Fms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserService userService;


    // everyone can  create a restaurant (change it to owner only)
    @Override
    public Restaurant createRestaurant(RestaurantRequest restaurantRequest) {
        // user exists
       User user = userService.findByPhoneNumber(restaurantRequest.getOwnerPhone());
        if (user != null && user.getRole().equalsIgnoreCase("owner")) {
            // doing each restaurant have unique phone number.
            boolean existsPhone = restaurantRepository.existsByRestaurantPhone(restaurantRequest.getRestaurantPhone());
            if (!existsPhone) {
                Restaurant restaurant = new Restaurant();
                restaurant.setOwnerId(user.getId());
                restaurant.setRestaurantName(restaurantRequest.getRestaurantName());
                restaurant.setRestaurantAddress(restaurantRequest.getRestaurantAddress());
                restaurant.setActiveRestaurant(true);
                restaurant.setRestaurantPhone(restaurantRequest.getRestaurantPhone());
                return restaurantRepository.save(restaurant);
            } else {
                // when phone number already registered on another restaurant
                return new Restaurant();
            }
        } else {
            // when role of owner id is not owner. && owner id is not present in user repo.
            return new Restaurant();
        }
    }

    // todo update success but when i call list of restaurant this one not giving updated data (done)

    @Override
    public boolean updateRestaurant(UpdateRestaurantReq updateReq) {
        // finding correct restaurantId.
        boolean existsRestId = restaurantRepository.existsByRestaurantId(updateReq.getRestaurantId());
        if (existsRestId) {
            // checking owner is existing on restaurant repo.
            Restaurant restaurant = restaurantRepository.findByRestaurantPhone(updateReq.getOldRestaurantPhone());
            if (restaurant != null && restaurant.getRestaurantId().equals(updateReq.getRestaurantId())) {
                if (restaurant.getActiveRestaurant()) {
                    // updating restaurant details.
                    restaurant.setRestaurantName(updateReq.getRestaurantName());
                    restaurant.setRestaurantAddress(updateReq.getRestaurantAddress());
                    restaurant.setRestaurantPhone(updateReq.getNewRestaurantPhone());
                    restaurantRepository.save(restaurant);
                    return true;
                } else {
                    // when restaurant is already deleted.
                    return false;
                }
            } else {
                // when ownerId wrong.
                return false;
            }
        } else {
            // when restId is wrong.
            return false;
        }
    }

    @Override
    public boolean deleteRestaurant(DeleteRestaurantReq deleteRequest) {
        Restaurant restaurant = restaurantRepository.findByRestaurantPhone(deleteRequest.getRestaurantPhone());
        if (restaurant != null) {
            if (restaurant.getActiveRestaurant()) {
                if (restaurant.getOwnerId().equals(deleteRequest.getOwnerId())) {
                    restaurant.setActiveRestaurant(false);
                    restaurantRepository.delete(restaurant);
                    return true;
                } else {
                    // when phone number is miss-match
                    return false;
                }
            } else {
                // when restaurant already deleted.
                return false;
            }
        } else {
            // when ownerId is wrong.
            return false;
        }
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public void delete() {
         restaurantRepository.deleteAll();
    }

    @Override
    public Restaurant findByRestaurantId(Integer restaurantId) {
        return restaurantRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public Restaurant saveUpdates(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
}
