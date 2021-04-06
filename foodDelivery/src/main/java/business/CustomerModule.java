package business;

import business.model.Customers;
import business.validators.CustomerValidator;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import persistent.AbstractGateaway;
import persistent.CustomerGateaway;

import java.util.List;

public class CustomerModule {
    private CustomerGateaway dao;
    private CustomerValidator validator;

    public CustomerModule() {
        this.dao = new CustomerGateaway();
        this.validator = new CustomerValidator();
    }

    public Customers findCustomerById(String ID){
        if(!ID.matches("[0-9]+")){
            System.out.println("Wrong ID format");
            return null;
        }
        Customers customer = dao.findById(Integer.valueOf(ID));
        if(customer==null) {
            System.out.println("The customer couldn't be found");
            return null;
        }
        return customer;
    }
   public Customers findCustomerByUsername(String username){
        Customers customer = dao.findByUser(username);
        if(customer==null) {
            System.out.println("Wrong username!");
            return null;
        }
        return customer;
    }

    public Customers insertCustomer(String name, String address, String pnc, String username, String password){
        if(dao.findByUser(username)!=null){
            System.out.println("A customer with same username already exists. Please enter a different username.");
            return null;
        }
        if(!validator.validate(address,name,pnc)){
            System.out.println("Please check the introduced information.");
            return null;
        }
        Customers customer = new Customers(-1,address,name,pnc,false,username,password);
        customer.setID(dao.insert(customer));
        return customer;
    }
    public boolean updateCustomer(Customers customer){
        Customers alreadyExistent = dao.findByUser(customer.getUsername());
        if(alreadyExistent!=null && alreadyExistent.getID()!=customer.getID()){
            System.out.println("A customer with same username already exists. Please enter a different username.");
            return false;
        }
        if(!validator.validate(customer.getAddress(), customer.getName(), customer.getPnc())){
            System.out.println("Please check the introduced information.");
            return false;
        }
        return dao.update(customer);
    }
    public boolean deleteCustomer(String ID){
        if(!ID.matches("[0-9]+")){
            System.out.println("Wrong ID format");
            return false;
        }
        OrderModule om = new OrderModule();
        om.deleteOrderWithSpecificCustomerId(Integer.valueOf(ID));
        return dao.delete(Integer.valueOf(ID));
    }
    public List<Customers> findAllCustomers(){
        List<Customers> customers= dao.findAll();
        if(customers.isEmpty()){
            System.out.println("No customers were found");
            return null;
        }
        return customers;
    }
    public AbstractGateaway<Customers> getDao() {
        return dao;
    }

    public CustomerValidator getValidator() {
        return validator;
    }
}
