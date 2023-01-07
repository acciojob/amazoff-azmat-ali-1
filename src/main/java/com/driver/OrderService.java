package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    public void AddOrder(Order order){
        orderRepository.AddOrder(order);
    }
    public void AddDelivery(String deliveryPartner){
        orderRepository.AddDelivery(deliveryPartner);
    }
    public void AddBoth(String delivery,String order){
        orderRepository.AddBoth(delivery,order);
    }

    public Order getByOrderId(String id){
        return orderRepository.getByOrderId(id);
    }
    public DeliveryPartner getByPartnerId(String id){
       return  orderRepository.getByPartnerId(id);
    }
    public int countOrderByPartner(String partner){
        return orderRepository.countOrderByPartner(partner);
    }
    public List<String> getOrderListBypartnerId(String partner){
        return orderRepository.getOrderListBypartnerId(partner);
    }
    public List<String> getAllOrder(){
        return orderRepository.getAllOrder();
    }
    public int getCountOfUnassignedOrders(){
        return orderRepository.getCountOfUnassignedOrders();
    }
    public int getOrdersLeftAfterGivenTimeByPartnerId(String time,String partnerId){
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);
    }
    public String getLastDeliveryTimeByPartnerId(String partnerId){
        return orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
    }
    public void deletePartnerById(String partnerId){
        orderRepository.deletePartnerById(partnerId);
    }
    public void deleteOrderById(String orderId){
        orderRepository.deleteOrderById(orderId);
    }
}
