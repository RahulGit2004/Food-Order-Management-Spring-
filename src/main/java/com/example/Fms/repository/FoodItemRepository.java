package com.example.Fms.repository;

import com.example.Fms.entity.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Integer> {
    FoodItem findByFoodId(Integer foodId);
}

