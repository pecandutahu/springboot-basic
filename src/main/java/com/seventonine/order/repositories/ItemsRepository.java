package com.seventonine.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seventonine.order.models.Items;

public interface ItemsRepository extends JpaRepository<Items, Integer> {
    
}
