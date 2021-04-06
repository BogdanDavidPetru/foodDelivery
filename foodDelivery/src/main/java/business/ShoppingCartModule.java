package business;

import business.model.Customers;
import business.model.Products;
import business.model.ShoppingCarts;
import business.validators.ShoppingCartValidator;
import persistent.ShoppingCartGateaway;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartModule {

    private ShoppingCartGateaway dao;
    private ShoppingCartValidator validator;

    public ShoppingCartModule() {
        dao = new ShoppingCartGateaway();
        validator = new ShoppingCartValidator();
    }

    public ShoppingCarts insertProduct(String productName, String quantity, Integer orderID){
        if(!validator.validate(productName,quantity)){
            System.out.println("Wrong information was given");
            return null;
        }
        ProductModule pm = new ProductModule();
        Products product = pm.findProductByName(productName);
        if(product==null){
            System.out.println("No product was found with this name");
            return null;
        }
        int quantityI = Integer.valueOf(quantity);
        int orderedQuantity=quantityI;
        if(product.getQuantity()<quantityI){
            System.out.println("The asked quantity was too much and so we provided you with our remainings.");
            orderedQuantity=product.getQuantity();
            product.setQuantity(0);
        }
        else{
            product.setQuantity(product.getQuantity()-quantityI);
        }
        pm.updateProduct(product);
        ShoppingCarts sc = new ShoppingCarts(-1,product.getID(),orderedQuantity,orderID);
        sc.setID(dao.insert(sc));
        return sc;
    }


    public boolean deleteShopCart(String ID){
        if(!ID.matches("[0-9]+")){
            System.out.println("Wrong ID format");
            return false;
        }
        return dao.delete(Integer.valueOf(ID));
    }
    public boolean deleteShopCartsWithSpecificOrderID(int ID){
        List<ShoppingCarts> shopCarts = dao.findAll();
        for(ShoppingCarts sc : shopCarts){
            if(sc.getIdOrder()==ID){
                if(!dao.delete(sc.getID()))
                    return false;
            }
        }
        return true;
    }
    public boolean deleteShopCartsWithSpecificProductID(int ID){
        List<ShoppingCarts> shopCarts = dao.findAll();
        for(ShoppingCarts sc : shopCarts){
            if(sc.getIdProduct()==ID){
                if(!dao.delete(sc.getID()))
                    return false;
            }
        }
        return true;
    }
   /* public boolean updateProduct(ShoppingCarts shoppingCart){
        if(!shoppingCart.getQuantity().toString().matches("[0-9]+")){
            System.out.println("Wrong product quantity format");
            return false;
        }
        if(shoppingCart.getQuantity()<=0){
            System.out.println("Wrong product quantity format");
            return false;
            //throw new IllegalArgumentException("Can't introduce a Product with negative quantity");
        }
        ProductModule pm = new ProductModule();
        Products product = pm.findProductById(shoppingCart.getID().toString());
        if(product==null){
            System.out.println("No product was found with this name");
            return false;
        }
        int orderedQuantity=shoppingCart.getQuantity();
        if(product.getQuantity()<shoppingCart.getQuantity()){
            System.out.println("The asked quantity was too much and so we provided you with our remainings.");
            orderedQuantity=product.getQuantity();
            product.setQuantity(0);
        }
        else{
            product.setQuantity(product.getQuantity()-shoppingCart.getQuantity());
        }
        pm.updateProduct(product);
        return dao.update(shoppingCart);
    }
*/
   public List<ShoppingCarts> findAllShopCarts(){
       List<ShoppingCarts> shopCarts= dao.findAll();
       if(shopCarts.isEmpty()){
           System.out.println("No shopCarts were found");
           return null;
       }
       return shopCarts;
   }
    public List<ShoppingCarts> findAllShopCartsWithOrderId(int orderId){
        List<ShoppingCarts> allShopCarts= dao.findAll();
        List<ShoppingCarts> shopCarts = allShopCarts.stream()
                .filter(entry -> entry.getIdOrder()==orderId)
                .collect(Collectors.toList());
      /*  List<ShoppingCarts> shopCarts=new ArrayList<ShoppingCarts>();
        for(ShoppingCarts sc:allShopCarts){
            if(sc.getIdOrder()==orderId){
                shopCarts.add(sc);
            }
        }*/
        return shopCarts;
    }
    public ShoppingCartGateaway getDao() {
        return dao;
    }

    public ShoppingCartValidator getValidator() {
        return validator;
    }
}
