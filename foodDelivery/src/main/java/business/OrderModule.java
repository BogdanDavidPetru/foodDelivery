package business;

import business.model.Orders;
import persistent.OrderGateaway;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OrderModule {
    private OrderGateaway dao;

    public OrderModule(){
        dao = new OrderGateaway();
    }

    public OrderGateaway getDao() {
        return dao;
    }

    public Orders insertOrder(int idCustomer){
        Orders order = new Orders(-1,new Date(), idCustomer);
        order.setID(dao.insert(order));
        return order;
    }

    public boolean deleteOrder(String ID){
        if(!ID.matches("[0-9]+")){
            System.out.println("Wrong ID format");
            return false;
        }
        int orderId = Integer.valueOf(ID);
        ShoppingCartModule scm = new ShoppingCartModule();
        if(!scm.deleteShopCartsWithSpecificOrderID(orderId))
            return false;
        return dao.delete(orderId);
    }

    public boolean deleteOrderWithSpecificCustomerId(int ID){
        List<Orders> orders = dao.findAll();
        for(Orders order:orders){
            if(order.getIdCustomer()==ID){
                if(!this.deleteOrder(order.getID().toString()))
                    return false;
            }
        }
        return true;
    }

    public List<Orders> findAllWithCustomerId(int customerID){
        List<Orders> allOrders = dao.findAll();
        List<Orders> orders = allOrders.stream()
                                        .filter(entry-> entry.getIdCustomer()==customerID)
                                        .collect(Collectors.toList());
        return orders;
    }

}
