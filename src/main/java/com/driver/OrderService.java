package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository odres;

    public void addOrder(Order order){
        odres.addOrder(order);
    }
    public void addPartner(String partnerId){
        odres.addPartner(partnerId);
    }
    public void addOrderPartnerPair(String orderId, String partnerId){
        odres.addOrderPartnerPair(orderId,partnerId);
    }
    public Order getOrderById(String orderId){

        return odres.getOrderById(orderId);
    }
    public DeliveryPartner getPartnerById(String partnerId){

        return odres.getPartnerById(partnerId);
    }
    public Integer getOrderCountByPartnerId(String partnerId){

        return odres.getOrderCountByPartnerId(partnerId);
    }
    public List<String> getOrdersByPartnerId(String partnerId){

        return odres.getOrdersByPartnerId(partnerId);
    }
    public List<String> getAllOrders(){

        return odres.getAllOrders();
    }
    public Integer getCountOfUnassignedOrders(){

        return odres.getCountOfUnassignedOrders();
    }
    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId){

        return odres.getOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);
    }
    public String getLastDeliveryTimeByPartnerId(String partnerId){

        return odres.getLastDeliveryTimeByPartnerId(partnerId);
    }
    public void deletePartnerById(String partnerId){

        odres.deletePartnerById(partnerId);
    }
    public void deleteOrderById(String orderId){

        odres.deleteOrderById(orderId);
    }

}
