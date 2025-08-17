package com.totaliteaShop.repository;

import com.totaliteaShop.model.ShippingRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShippingRuleRepository extends JpaRepository<ShippingRule, Long> {
    List<ShippingRule> findByMinWeightLessThanEqualAndMaxWeightGreaterThanEqual
            (Double minWeight, Double maxWeight);
    List<ShippingRule> findByFreeShippingThresholdNotNull();
}
