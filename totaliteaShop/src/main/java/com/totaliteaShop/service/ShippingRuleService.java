package com.totaliteaShop.service;

import com.totaliteaShop.model.ShippingRuleModel;
import com.totaliteaShop.repository.ShippingRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ShippingRuleService {
    private final ShippingRuleRepository shippingRuleRepository;

    public ShippingRuleModel createRule(ShippingRuleModel rule){
        return shippingRuleRepository.save(rule);
    }
    public Optional<ShippingRuleModel> getRuleById(Long id){
        return shippingRuleRepository.findById(id);
    }
    public List<ShippingRuleModel> getAllRules(){
        return shippingRuleRepository.findAll();
    }
    public void deleteRuleById(Long id){
        shippingRuleRepository.deleteById(id);
    }
    public List<ShippingRuleModel> getRulesForWeight (Double minWeight, Double maxWeight){
        return shippingRuleRepository .findByMinWeightLessThanEqualAndMaxWeightGreaterThanEqual(minWeight, maxWeight);
    }
    public List<ShippingRuleModel> getFreeShippingRules(){
        return shippingRuleRepository .findByFreeShippingThresholdNotNull();
    }
}
