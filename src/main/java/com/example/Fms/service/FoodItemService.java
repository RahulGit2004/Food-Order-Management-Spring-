package com.example.Fms.service;

import com.example.Fms.entity.model.FoodItem;
import com.example.Fms.entity.request.DeleteFoodItemReq;
import com.example.Fms.entity.request.FoodItemRequest;
import com.example.Fms.entity.request.UpdateItemReq;

import java.util.List;

public interface FoodItemService {
    FoodItem createFoodItem(FoodItemRequest foodItemRequest);

    boolean updateFoodItem(UpdateItemReq updateItemReq);

    boolean deleteFoodItem(DeleteFoodItemReq foodItemReq);

    List<FoodItem> getFoodItems();
}
