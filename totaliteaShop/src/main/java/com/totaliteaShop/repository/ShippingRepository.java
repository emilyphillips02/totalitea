package com.totaliteaShop.repository;

import com.totaliteaShop.model.ShippingRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ShippingRepository extends JpaRepository<ShippingRule, Long> {
    Optional<ShippingRule> findFirstByMinWeightLessThanEqualAndMaxWeightGreaterThanEqual(BigDecimal min, BigDecimal max);
}
