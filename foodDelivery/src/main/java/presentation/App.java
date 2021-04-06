package presentation;

import business.CustomerModule;
import business.OrderModule;
import business.ProductModule;
import business.ShoppingCartModule;
import business.model.Customers;
import business.model.Orders;
import business.model.Products;
import business.model.ShoppingCarts;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import persistent.CustomerGateaway;
import persistent.ProductGateaway;
import persistent.ShoppingCartGateaway;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MainController controller = new MainController("controller");
        LoginView frame = controller.getLoginView();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
      /*
       UserView frame2 = new UserView("name2", new MainController());
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
        frame2.setVisible(true);
        OrderModule om = new OrderModule();
        List<Orders> orders = om.findAllWithCustomerId(1);
        for(Orders order:orders){
            System.out.println(order.getID());
        }
      CustomerModule cm = new CustomerModule();
        ProductModule pm = new ProductModule();
        ShoppingCartModule scm = new ShoppingCartModule();
        OrderModule om = new OrderModule();

        pm.deleteProduct("4");

        ShoppingCartGateaway scg = new ShoppingCartGateaway();

        ShoppingCarts sc = scg.findById(1);

        System.out.println(sc.getIdProduct());

        ShoppingCarts sc2 = new ShoppingCarts(-1,4,2,2);

        sc2.setID(scg.insert(sc2));

        sc.setQuantity(4);

        scg.update(sc);

        ArrayList<ShoppingCarts> carts = (ArrayList<ShoppingCarts>) scg.findAll();

        for(ShoppingCarts cart : carts){
            System.out.println(cart.getQuantity());
        }
        */
       // Customers c2 = new Customers(-1,"Oradea","Marius","1113245602134",false,"marius","bossu");

        //int id = cg.insert(c2);
     /*   c.setAddress("Cluj");
        //c2.setID(id);
        cg.update(c);
        //cg.delete(2);

        ArrayList<Customers> cs = (ArrayList<Customers>) cg.findAll();
        for(Customers customer : cs){
            System.out.println(customer.getName());
        }
        OrderGateaway og = new OrderGateaway();

        Orders o = og.findById(1);
        o.setIdCustomer(3);
        og.update(o);
        System.out.println(o.getDate().toString());

        Date dt = new Date();
        Orders o2 = new Orders(-1,dt,1);
        o2.setID(og.insert(o2));

        ArrayList<Orders> orders = (ArrayList<Orders>) og.findAll();

        for(Orders order : orders){
            System.out.println(order.getDate().toString());
        }

        og.delete(o2.getID());

        //pg.update(p);
       // System.out.println(p.getProductName());


        Products p2 = new Products(-1,"bruschete",12.5f, 10, "Bucatele de paine prajita cu rosii si ceapa verde");
        Products p3 = new Products(-1,"apa",5.5f, 20, "");
        p2.setID(pg.insert(p2));
        p3.setID(pg.insert(p3));
        //pg.delete(1);
        p3.setDescription("cea mai racoritoare apa pe care o vei gasi");
        pg.update(p3);
        ArrayList<Products>products = (ArrayList<Products>) pg.findAll();

        for(Products product: products){
            System.out.println(product.getProductName());
        }*/

    }

}
