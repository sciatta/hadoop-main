package com.sciatta.dev.java.example.enums;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yangxiaoyu on 2021/4/10<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Pizza
 */
public class Pizza {
    private PizzaStatus status;
    private static final EnumSet<PizzaStatus> undelivered = EnumSet.of(PizzaStatus.ORDERED, PizzaStatus.READY);
    
    public Pizza(PizzaStatus status) {
        this.status = status;
    }
    
    enum PizzaStatus {
        ORDERED(0) {
            @Override
            boolean isOrdered() {
                return true;
            }
        },
        READY(1) {
            @Override
            boolean isReady() {
                return true;
            }
        },
        DELIVERED(2) {
            @Override
            boolean isDelivered() {
                return true;
            }
        };
        
        private int innerStatus;
        
        boolean isOrdered() {
            return false;
        }
        
        boolean isReady() {
            return false;
        }
        
        boolean isDelivered() {
            return false;
        }
        
        PizzaStatus(int innerStatus) {
            this.innerStatus = innerStatus;
        }
    }
    
    public static List<Pizza> getAllUndelivered(List<Pizza> pizzas) {
        return pizzas.stream().filter(t -> undelivered.contains(t.status)).collect(Collectors.toList());
    }
    
    public static boolean isDeliverable(Pizza pizza) {
        return pizza.status.isReady();
    }
    
    public static void main(String[] args) {
        List<Pizza> pizzas = new ArrayList<>();
        pizzas.add(new Pizza(PizzaStatus.ORDERED));
        pizzas.add(new Pizza(PizzaStatus.ORDERED));
        pizzas.add(new Pizza(PizzaStatus.READY));
        pizzas.add(new Pizza(PizzaStatus.ORDERED));
        pizzas.add(new Pizza(PizzaStatus.DELIVERED));
        
        System.out.println(Pizza.getAllUndelivered(pizzas).size());    // 4
    
        System.out.println(Pizza.isDeliverable(new Pizza(PizzaStatus.ORDERED)));    // false
    
        System.out.println(Pizza.isDeliverable(new Pizza(PizzaStatus.READY)));    // true
    }
}
