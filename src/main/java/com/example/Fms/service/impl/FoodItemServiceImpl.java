package com.example.Fms.service.impl;


import com.example.Fms.entity.model.FoodItem;
import com.example.Fms.entity.model.Restaurant;
import com.example.Fms.entity.model.User;
import com.example.Fms.entity.request.DeleteFoodItemReq;
import com.example.Fms.entity.request.FoodItemRequest;
import com.example.Fms.entity.request.UpdateItemReq;
import com.example.Fms.repository.FoodItemRepository;
import com.example.Fms.service.FoodItemService;
import com.example.Fms.service.RestaurantService;
import com.example.Fms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemServiceImpl implements FoodItemService {
    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    // todo item with same name will added multiple time in a restaurant.
    @Override
    public FoodItem createFoodItem(FoodItemRequest foodItemRequest) {
        User user = userService.findByPhoneNumber(foodItemRequest.getOwnerPhone());
        if (user != null && user.getRole().equalsIgnoreCase("owner")) {
            Restaurant restaurant = restaurantService.findByRestaurantId(foodItemRequest.getRestaurantId());
            if (restaurant != null) {
//                FoodItem foodItem = foodItemRepository.findByItemName(foodItemRequest.getFoodItemName());
//                if (foodItem != null &&) {
                    FoodItem foodItem = new FoodItem();
                    foodItem.setRestaurantId(foodItemRequest.getRestaurantId());
                    foodItem.setItemName(foodItemRequest.getFoodItemName());
                    foodItem.setDescription(foodItemRequest.getDescription());
                    foodItem.setPrice(foodItemRequest.getPrice());
                    foodItem.setAvailability(true);
                    restaurant.getFoodItemList().add(foodItem);
                    return foodItemRepository.save(foodItem);
//                } else {
//                    // when item is already present
//                    return new FoodItem();
//                }

            } else {
                // when restaurant id is not correct.
                return new FoodItem();
            }
        } else {
            return new FoodItem();
        }
    }

    @Override
    public boolean updateFoodItem(UpdateItemReq updateItemReq) {
        User user = userService.findByPhoneNumber(updateItemReq.getOwnerPhone());
        if (user != null && user.getRole().equalsIgnoreCase("owner")) {
            Restaurant restaurant = restaurantService.findByRestaurantId(updateItemReq.getRestaurantId());
            if (restaurant != null) {
                FoodItem foodItem = foodItemRepository.findByFoodId(updateItemReq.getFoodId());
                if (foodItem != null) {
                    restaurant.getFoodItemList().remove(foodItem);
                    foodItem.setItemName(updateItemReq.getFoodItemName());
                    foodItem.setDescription(updateItemReq.getDescription());
                    foodItem.setPrice(updateItemReq.getPrice());
                    restaurant.getFoodItemList().add(foodItem);
                    foodItemRepository.save(foodItem); // saving updates
                    restaurantService.saveUpdates(restaurant); // saving updates on restaurant.
                    return true;
                } else {
                    // when item Id is not correct
                    return false;
                }
            } else {
                // when rest id is wrong
                return false;
            }
        } else {
            // when not owner.
            return false;
        }

    }

    @Override
    public boolean deleteFoodItem(DeleteFoodItemReq foodItemReq) {
        User user = userService.findByPhoneNumber(foodItemReq.getOwnerPhone());
        if (user !=  null && user.getRole().equalsIgnoreCase("owner")) {
            FoodItem foodItem = foodItemRepository.findByFoodId(foodItemReq.getFoodId());
            if (foodItem != null && foodItem.getRestaurantId().equals(foodItemReq.getRestaurantId())) {
                foodItem.setAvailability(false);
                foodItemRepository.delete(foodItem);
                return true;
            } else {
                // when foodId is not present in provided restaurant.
                return false;
            }

        } else {
            // when not owner.
            return false;
        }
    }

    @Override
    public List<FoodItem> getFoodItems() {
        return foodItemRepository.findAll();
    }


}
