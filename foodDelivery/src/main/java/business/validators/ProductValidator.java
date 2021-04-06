package business.validators;

import business.model.Products;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductValidator {
    private Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
    public boolean validate(String price, String quantity, String name){//Products t) {
        if(!price.matches("^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$")){
            System.out.println("Wrong product price format");
            return false;
        }
        float priceF = Float.valueOf(price);
        // TODO Auto-generated method stub
        if(priceF<=0) {
            System.out.println("Wrong product price format");
            return false;
            //throw new IllegalArgumentException("The Product's price must be a positive number");
        }
        if(!quantity.matches("[0-9]+")){
            System.out.println("Wrong product quantity format");
            return false;
        }
        Integer quantityI = Integer.valueOf(quantity);
        if(quantityI<=0){
            System.out.println("Wrong product quantity format");
            return false;
            //throw new IllegalArgumentException("Can't introduce a Product with negative quantity");
        }
        Matcher hasSpecial = special.matcher(name);
        // TODO Auto-generated method stub
        if(name.matches(".*\\d.*")) {
            System.out.println("Wrong product name format");
            return false;
            //throw new IllegalArgumentException("The Product's name can't contain numbers");
        }
        if(hasSpecial.find()){
            System.out.println("Wrong product name format");
            return false;
            //throw new IllegalArgumentException("The Product's name can't contain special characters");
        }
        return true;
    }
}
