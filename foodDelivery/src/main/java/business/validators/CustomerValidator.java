package business.validators;

import business.model.Customers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerValidator {
    private Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~]");
   // private Pattern cnpPattern = Pattern.compile("[(\d{13})]");
    public boolean validate(String address, String name, String pnc){//Customers t) {
        Matcher hasSpecial = special.matcher(address);//t.getAddress());
        if(hasSpecial.find()){
            System.out.println("Wrong address format");
            //throw new IllegalArgumentException("The Client's address can't contain special characters");
            return false;
        }
        hasSpecial = special.matcher(name);//t.getName());
        // TODO Auto-generated method stub
        if(name.matches(".*\\d.*")) {
            System.out.println("Wrong name format");
            //throw new IllegalArgumentException("The Client's name can't contain numbers");
            return false;
        }
        if(hasSpecial.find()){
            System.out.println("Wrong name format");
           // throw new IllegalArgumentException("The Client's name can't contain special characters");
            return false;
        }
        if(!pnc.matches("^[0-9]{13}$")){
            System.out.println("Wrong pnc format");
            //System.out.println("Yes");
            return false;
        }
        return true;
    }

}
