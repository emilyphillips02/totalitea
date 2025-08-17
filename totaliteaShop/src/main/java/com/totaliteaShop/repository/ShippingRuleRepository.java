package com.totaliteaShop.repository;

import com.totaliteaShop.model.ShippingRuleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShippingRuleRepository extends JpaRepository<ShippingRuleModel, Long> {
    List<ShippingRuleModel> findByMinWeightLessThanEqualAndMaxWeightGreaterThanEqual
            (Double minWeight, Double maxWeight);
    List<ShippingRuleModel> findByFreeShippingThresholdNotNull();
}
