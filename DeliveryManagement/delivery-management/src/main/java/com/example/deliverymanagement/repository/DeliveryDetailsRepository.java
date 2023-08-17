package com.example.deliverymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.deliverymanagement.entity.DeliveryDetails;

public interface DeliveryDetailsRepository extends JpaRepository<DeliveryDetails, Long>{

}
