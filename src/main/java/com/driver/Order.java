package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id=id;
        this.deliveryTime = Converter(deliveryTime);
    }

    public int Converter(String deliveryTime) {
        String hhString = deliveryTime.substring(0,2);
        String mmString = deliveryTime.substring(3);
        int hh= Integer.parseInt(hhString);
        int mm = Integer.parseInt(mmString);
        return hh*60+mm;
    }


    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}