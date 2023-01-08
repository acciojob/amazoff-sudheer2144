package com.driver;

import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {

    HashMap<String,Order> orders=new HashMap<>();
    HashMap<String, DeliveryPartner> partners=new HashMap<>();
    HashMap<String,List<String>> pair=new HashMap<>();
    HashMap<String,Order> unassigned=new HashMap<>();

    public void addOrder(Order order){
        orders.put(order.getId(),order);
    }
    public void addPartner(String partnerId){
        partners.put(partnerId,new DeliveryPartner(partnerId));
    }
    public void addOrderPartnerPair(String orderId, String partnerId){
        List<String> ls=new ArrayList<>();
        if(pair.containsKey(partnerId)){
            ls=pair.get(partnerId);
        }
        ls.add(orderId);
        pair.put(partnerId,ls);
        partners.get(partnerId).setNumberOfOrders(pair.get(partnerId).size());
    }
    public Order getOrderById(String orderId){
        if(orders.containsKey(orderId)){
            return orders.get(orderId);
        }
        return null;
    }
    public DeliveryPartner getPartnerById(String partnerId){
        if(partners.containsKey(partnerId)){
            return partners.get(partnerId);
        }
        return null;
    }
    public Integer getOrderCountByPartnerId(String partnerId){

        return partners.get(partnerId).getNumberOfOrders();
    }
    public List<String> getOrdersByPartnerId(String partnerId){
        List<String> ls=new ArrayList<>();
        for(String order:pair.get(partnerId)){
            ls.add(orders.get(order).getId());
        }
        return ls;
    }
    public List<String> getAllOrders(){

        return new ArrayList<>(orders.keySet());
    }
    public Integer getCountOfUnassignedOrders(){

        return unassigned.size();
    }
    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId){
        int hh=Integer.parseInt(time.split(":")[0]);
        int mm=Integer.parseInt(time.split(":")[1]);
        int deliveryTime=hh*60 + mm;
        int count=0;
        for(String order:pair.get(partnerId)){
            if(orders.get(order).getDeliveryTime() > deliveryTime){
                count++;
            }
        }
        return count;
    }
    public String getLastDeliveryTimeByPartnerId(String partnerId){
        int time=orders.get(pair.get(partnerId).get(pair.get(partnerId).size()-1)).getDeliveryTime();
        String s=String.valueOf(time/60);
        String hh=s.length()==1 ? "0"+s:s;
        s = String.valueOf(time - Integer.parseInt(hh) * 60);
        String mm= s.length()==1 ? "0"+ s : s;
        return hh+":"+mm;
    }
    public void deletePartnerById(String partnerId){
        List<String> ls=pair.get(partnerId);
        for(String order:ls){
            Order odr=orders.get(order);
            unassigned.put(odr.getId(),odr);
        }
        pair.remove(partnerId);
    }
    public void deleteOrderById(String orderId){
        unassigned.put(orders.get(orderId).getId(),orders.get(orderId));
        orders.remove(orderId);
    }

}
