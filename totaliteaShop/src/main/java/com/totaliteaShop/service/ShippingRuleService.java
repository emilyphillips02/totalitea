package com.totaliteaShop.service;

import com.totaliteaShop.model.ShippingRule;
import com.totaliteaShop.repository.ShippingRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ShippingRuleService {
    private final ShippingRuleRepository shippingRuleRepository;

    public ShippingRule createRule(ShippingRule rule){
        return shippingRuleRepository.save(rule);
    }
    public Optional<ShippingRule> getRuleById(Long id){
        return shippingRuleRepository.findById(id);
    }
    public List<ShippingRule> getAllRules(){
        return shippingRuleRepository.findAll();
    }
    public void deleteRuleById(Long id){
        shippingRuleRepository.deleteById(id);
    }
    public List<ShippingRule> getRulesForWeight (Double minWeight, Double maxWeight){
        return shippingRuleRepository .findByMinWeightLessThanEqualAndMaxWeightGreaterThanEqual(minWeight, maxWeight);
    }
    public List<ShippingRule> getFreeShippingRules(){
        return shippingRuleRepository .findByFreeShippingThresholdNotNull();
    }
}
