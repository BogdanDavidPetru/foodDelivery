package business;

import business.model.Customers;
import business.model.Products;
import business.validators.CustomerValidator;
import business.validators.ProductValidator;
import persistent.AbstractGateaway;
import persistent.CustomerGateaway;
import persistent.ProductGateaway;

import java.util.List;

public class ProductModule {
    private ProductGateaway dao;
    private ProductValidator validator;

    public ProductModule() {
        this.dao = new ProductGateaway();
        this.validator = new ProductValidator();
    }

    public Products findProductById(String ID){
        if(!ID.matches("[0-9]+")){
            System.out.println("Wrong ID format");
            return null;
        }
        Products product = dao.findById(Integer.valueOf(ID));
        if(product==null) {
            System.out.println("The product couldn't be found");
            return null;
        }
        return product;
    }
    public Products findProductByName(String name){
        Products product = dao.findByName(name);
        if(product==null) {
            System.out.println("Wrong name!");
            return null;
        }
        return product;
    }

    public Products insertProduct(String name, String price, String quantity, String description){
        if(dao.findByName(name)!=null){
            System.out.println("A product with same name already exists. Please enter a different name.");
            return null;
        }
        if(!validator.validate(price,quantity,name)){
            System.out.println("Please check the introduced information.");
            return null;
        }
        Products product = new Products(-1,name,Float.valueOf(price),Integer.valueOf(quantity),description);
        product.setID(dao.insert(product));
        return product;
    }
    public boolean updateProduct(Products product){
        Products alreadyExistent = dao.findByName(product.getProductName());
        if(alreadyExistent!=null && alreadyExistent.getID()!=product.getID()){
            System.out.println("A product with same name already exists. Please enter a different name.");
            return false;
        }
        if(!validator.validate(String.valueOf(product.getPrice()),String.valueOf(product.getQuantity()), product.getProductName())){
            System.out.println("Please check the introduced information.");
            return false;
        }
        return dao.update(product);
    }
    public boolean deleteProduct(String ID){
        if(!ID.matches("[0-9]+")){
            System.out.println("Wrong ID format");
            return false;
        }
        ShoppingCartModule scm = new ShoppingCartModule();
        scm.deleteShopCartsWithSpecificProductID(Integer.valueOf(ID));
        return dao.delete(Integer.valueOf(ID));
    }
    public List<Products> findAllProducts(){
        List<Products> products= dao.findAll();
        if(products.isEmpty()){
            System.out.println("No products were found");
            return null;
        }
        return products;
    }
    public ProductGateaway getDao() {
        return dao;
    }

    public ProductValidator getValidator() {
        return validator;
    }
}
