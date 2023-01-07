package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
@Component
public class OrderRepository {
    HashMap<String,Order>orderHashMap = new HashMap<>();
    HashMap<String,DeliveryPartner>deliveryPartnerHashMap  = new HashMap<>();
    HashMap<String, List<String>> orderdeliveryHashMap= new HashMap<>();

    public void AddOrder(Order order){
        orderHashMap.put(order.getId(),order);
    }
    public void AddDelivery(String deliveryPartner){
        DeliveryPartner deliveryPartner1 = new DeliveryPartner(deliveryPartner);
        deliveryPartnerHashMap.put(deliveryPartner,deliveryPartner1);
    }
    public void AddBoth(String delivery,String order){
        List<String> list = new ArrayList<>();
        if(orderdeliveryHashMap.containsKey(delivery)){
            list = orderdeliveryHashMap.get(delivery);
            orderdeliveryHashMap.put(delivery,list);
        }
        else{
            list.add(order);
            orderdeliveryHashMap.put(delivery,list);
        }
    }

    public Order getByOrderId(String id){
        if(orderHashMap.containsKey(id)){
            return orderHashMap.get(id);
        }
        return null;
    }
    public DeliveryPartner getByPartnerId(String id){
        if(deliveryPartnerHashMap.containsKey(id)){
            return deliveryPartnerHashMap.get(id);
        }
        return null;
    }
    public int countOrderByPartner(String partner){
        if(orderdeliveryHashMap.containsKey(partner)){
            List<String> list = orderdeliveryHashMap.get(partner);
            return list.size();
        }
        return 0;
    }
    public List<String> getOrderListBypartnerId(String partner){
        if(orderdeliveryHashMap.containsKey(partner)){
            List<String> list = orderdeliveryHashMap.get(partner);
            return list;
        }
        return null;
    }
    public List<String> getAllOrder(){
        List<String>list = new ArrayList<>();
        for (String i: orderHashMap.keySet()){
            list.add(i);
        }
        return list;
    }
    public int getCountOfUnassignedOrders(){
        List<String>list = getAllOrder();
        int count=0;
        for (String i: list){
            if(orderdeliveryHashMap.containsKey(i)==false){
                count++;
            }
        }
        return count;
    }
    public int getOrdersLeftAfterGivenTimeByPartnerId(String time,String partnerId){
        List<String>list = new ArrayList<>();
        if(orderdeliveryHashMap.containsKey(partnerId)){
            list = orderdeliveryHashMap.get(partnerId);
        }
        int count=0;
        for (String i: list){
            if(orderHashMap.containsKey(i)){
                Order order = getByOrderId(i);
                if(order.getDeliveryTime()>Integer.valueOf(time)){
                    count++;
                }
            }
        }
        return count;
    }
    public String getLastDeliveryTimeByPartnerId(String partnerId){
        String ordername="";
        List<String>list = new ArrayList<>();
        if(orderdeliveryHashMap.containsKey(partnerId)){
            list = orderdeliveryHashMap.get(partnerId);
        }
        int count=0;
        for (String i: list){
            if(orderHashMap.containsKey(i)){
                Order order = getByOrderId(i);
                if(order.getDeliveryTime()>=count){
                    count=order.getDeliveryTime();
                    ordername=i;
                }
            }
        }
        return ordername;
    }
    public void deletePartnerById(String partnerId){
        if(deliveryPartnerHashMap.containsKey(partnerId)){
            deliveryPartnerHashMap.remove(partnerId);
        }
        if(orderdeliveryHashMap.containsKey(partnerId)){
            orderdeliveryHashMap.remove(partnerId);
        }
    }
    public void deleteOrderById(String orderId){
        if(orderHashMap.containsKey(orderId)){
            orderHashMap.remove(orderId);
        }
        for (String i: orderdeliveryHashMap.keySet()){
            List<String> list = orderdeliveryHashMap.get(i);
            for (String j : list){
                if (j.equals(orderId)){
                    list.remove(orderId);
                    orderdeliveryHashMap.put(i,list);
                }
            }
        }
    }
}
