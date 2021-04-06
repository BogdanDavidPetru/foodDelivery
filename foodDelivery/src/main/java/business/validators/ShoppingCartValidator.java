package business.validators;

import business.model.Products;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShoppingCartValidator {

    private Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
    public boolean validate(String productName, String quantity) {
        Matcher hasSpecial = special.matcher(productName);
        // TODO Auto-generated method stub
        if(productName.matches(".*\\d.*")) {
            System.out.println("Wrong product name format");
            return false;
            //throw new IllegalArgumentException("The Product's name can't contain numbers");
        }
        if(hasSpecial.find()){
            System.out.println("Wrong product name format");
            return false;
            //throw new IllegalArgumentException("The Product's name can't contain special characters");
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
        return true;
    }
}
