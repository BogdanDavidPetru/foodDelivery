package presentation;

import business.CustomerModule;
import business.OrderModule;
import business.ProductModule;
import business.ShoppingCartModule;
import business.model.Customers;
import business.model.Orders;
import business.model.Products;
import business.model.ShoppingCarts;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class MainController implements ActionListener {

    private LoginView loginView;

    private SignUpView signUpView;

    private ShoppingCartView shopCartView;

    private UserView userView;

    private AdminView adminView;

    private CheckOutView checkOutView;

    private String name;

    private HashMap<String,String> allCurrentOrders;
    //private Set<Customers> users;

    private CustomerModule cm;

    private OrderModule om;

    private ProductModule pm;

    private ShoppingCartModule scm;

    private Customers presentCustomer;

    public MainController(String name){
        this.name=name;
        loginView = new LoginView("LOGIN", this);

        signUpView = new SignUpView("SIGNUP", this);

        userView = new UserView("USER", this);

        adminView = new AdminView("ADMIN", this);

        shopCartView = new ShoppingCartView("SHOP CART", this);

        allCurrentOrders = new HashMap<>();

        checkOutView = new CheckOutView("CHECKOUT", this);

        cm = new CustomerModule();
        om=new OrderModule();
        pm = new ProductModule();
        scm = new ShoppingCartModule();

        presentCustomer = new Customers();
    }

    private <T extends JFrame> void makeViewVisible(T frame){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void loginUser(){
        String username = loginView.getUsername().getText();
        Customers customer = cm.findCustomerByUsername(username);
        if(customer==null){
            JOptionPane.showMessageDialog(loginView,"Error logging in");
        }
        else{
            String pass = loginView.getPassword().getText();
            if(!pass.contentEquals(customer.getPassword())){
                JOptionPane.showMessageDialog(loginView,"Error logging in");
            }
            else{
                presentCustomer=customer;
                makeViewVisible(userView);
                loginView.setVisible(false);
            }
        }
    }

    private void signUpView(){
        makeViewVisible(signUpView);
        loginView.setVisible(false);
    }
    private void loginAdmin(){
        String username = loginView.getUsername().getText();
        String pass = loginView.getPassword().getText();

        if(username.contentEquals("admin") && pass.contentEquals("admin")){
            makeViewVisible(adminView);
            loginView.setVisible(false);
        }
        else{
            JOptionPane.showMessageDialog(loginView,"Wrong admin user or password");
        }
    }
    private void validateSignUp(){
        Customers customer = cm.insertCustomer(signUpView.getCustomerName().getText(),signUpView.getAddress().getText(), signUpView.getPnc().getText(),signUpView.getUsername().getText(),signUpView.getPassword().getText());
        if(customer==null){
            JOptionPane.showMessageDialog(signUpView,"Error while signing up");
        }
        else{
            presentCustomer=customer;
            makeViewVisible(userView);
            signUpView.setVisible(false);
        }
    }

    private void showPersonalInfo(){
        String[] col= {"Name","Address","PNC","Username", "Password"};
        String [][] el= new String[2][5];
        String[] row1= {"Name","Address","PNC","Username", "Password"};
        el[0]=row1;
        String[] row= {presentCustomer.getName(),presentCustomer.getAddress(),presentCustomer.getPnc(),presentCustomer.getUsername(),presentCustomer.getPassword()};
        el[1]=row;
        JTable tabel=new JTable(el,col) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        TableColumnModel columnModel = tabel.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(50);
        columnModel.getColumn(2).setPreferredWidth(20);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(100);
        userView.addTable(tabel);
    }

    private void showProducts(){
        List<Products> allProducts = pm.findAllProducts();
        String[] col= {"Name","Price","Quantity","Description"};
        String [][] el= new String[allProducts.size()+1][4];
        String[] row1= {"Name","Price","Quantity","Description"};
        el[0]=row1;
        int i=1;
        for(Products product : allProducts){
            String[] row= {product.getProductName(),String.valueOf(product.getPrice()),product.getQuantity().toString(),product.getDescription()};
            el[i++]=row;
        }
        JTable tabel=new JTable(el,col) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        TableColumnModel columnModel = tabel.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(50);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(200);
        shopCartView.addProductsTable(tabel);
    }
    private void showCustomers(){
        List<Customers> allCustomers = cm.findAllCustomers();
        String[] col= {"ID","Name","Address","Loyal","PNC","Username", "Password"};
        String [][] el= new String[allCustomers.size()+1][6];
        String[] row1= {"ID","Name","Address","Loyal","PNC","Username", "Password"};
        el[0]=row1;
        int i=1;
        for(Customers customer : allCustomers){
            String[] row= {customer.getID().toString(),customer.getName(),customer.getAddress(),customer.getLoyal().toString(),customer.getPnc(),customer.getUsername(),customer.getPassword()};
            el[i++]=row;
        }
        JTable tabel=new JTable(el,col) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        TableColumnModel columnModel = tabel.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(80);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(50);
        columnModel.getColumn(5).setPreferredWidth(80);
        adminView.addCustomersTable(tabel);
    }
    private void showProductsAdmin(){
        List<Products> allProducts = pm.findAllProducts();
        String[] col= {"ID","Name","Price","Quantity","Description"};
        String [][] el= new String[allProducts.size()+1][5];
        String[] row1= {"ID","Name","Price","Quantity","Description"};
        el[0]=row1;
        int i=1;
        for(Products product : allProducts){
            String[] row= {product.getID().toString(),product.getProductName(),String.valueOf(product.getPrice()),product.getQuantity().toString(),product.getDescription()};
            el[i++]=row;
        }
        JTable tabel=new JTable(el,col) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        TableColumnModel columnModel = tabel.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(80);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(200);
        adminView.addProductsTable(tabel);
    }
    private void showShopCart(){
        String[] col= {"ProductName","Quantity"};
        String [][] el= new String[allCurrentOrders.size()+1][2];
        String[] row1= {"ProductName","Quantity"};
        el[0]=row1;
        int i=1;
        for(String key : allCurrentOrders.keySet()){
            String[] row= {key,allCurrentOrders.get(key)};
            el[i++]=row;
        }
        JTable tabel=new JTable(el,col) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        TableColumnModel columnModel = tabel.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(50);
        shopCartView.addCartTabble(tabel);
    }

    private void goToShop(){
        makeViewVisible(shopCartView);
        userView.setVisible(false);
    }
    private void updateName(){
        String newName = userView.getUpdateName().getText();
        if(!newName.equals("")){
            presentCustomer.setName(newName);
            if(!cm.updateCustomer(presentCustomer)){
                JOptionPane.showMessageDialog(userView,"Error while updating. Wrong data format");
            }
        }
        else{
            JOptionPane.showMessageDialog(userView,"Nothing was introduced");
        }
    }
    private void updateAddress(){
        String newAddress = userView.getUpdateAddress().getText();
        if(!newAddress.equals("")){
            presentCustomer.setAddress(newAddress);
            if(!cm.updateCustomer(presentCustomer)){
                JOptionPane.showMessageDialog(userView,"Error while updating. Wrong data format");
            }
        }
        else{
            JOptionPane.showMessageDialog(userView,"Nothing was introduced");
        }
    }
    private void updatePNC(){
        String newPNC = userView.getUpdatePNC().getText();
        if(!newPNC.equals("")){
            presentCustomer.setPnc(newPNC);
            if(!cm.updateCustomer(presentCustomer)){
                JOptionPane.showMessageDialog(userView,"Error while updating. Wrong data format");
            }
        }
        else{
            JOptionPane.showMessageDialog(userView,"Nothing was introduced");
        }
    }
    private void updateUsername(){
        String newUser = userView.getUpdateUser().getText();
        if(!newUser.equals("")){
            presentCustomer.setUsername(newUser);
            if(!cm.updateCustomer(presentCustomer)){
                JOptionPane.showMessageDialog(userView,"Error while updating. Wrong data format");
            }
        }
        else{
            JOptionPane.showMessageDialog(userView,"Nothing was introduced");
        }
    }
    private void updatePass(){
        String newPass = userView.getUpdatePass().getText();
        if(!newPass.equals("")){
            presentCustomer.setPassword(newPass);
            if(!cm.updateCustomer(presentCustomer)){
                JOptionPane.showMessageDialog(userView,"Error while updating. Wrong data format");
            }
        }
        else{
            JOptionPane.showMessageDialog(userView,"Nothing was introduced");
        }
    }
    private void insertIntoCart(){
        String product = shopCartView.getProductName().getText();
        String quantity  = shopCartView.getQuantity().getText();

        allCurrentOrders.put(product,quantity);
    }
    private void updateCart(){
        String product = shopCartView.getProductName().getText();
        if(allCurrentOrders.keySet().contains(product)){
            String quantity  = shopCartView.getQuantity().getText();
            allCurrentOrders.put(product,quantity);
        }
        else{
            JOptionPane.showMessageDialog(shopCartView,"Can't update this product as it doesn't exist in your cart.");
        }
    }
    private void deleteFromCart(){
        String product = shopCartView.getProductName().getText();
        if(allCurrentOrders.keySet().contains(product)){
           allCurrentOrders.remove(product);
        }
        else{
            JOptionPane.showMessageDialog(shopCartView,"Can't delete this product as it doesn't exist in your cart.");
        }
    }
    private void goToCheckOut(){
        Orders newOrder =om.insertOrder(presentCustomer.getID());
        for(String key : allCurrentOrders.keySet()){
            if(scm.insertProduct(key,allCurrentOrders.get(key),newOrder.getID())==null){
                JOptionPane.showMessageDialog(shopCartView,"Can't place order as some products have not been found in the menu");
            }
        }
        makeViewVisible(checkOutView);
        shopCartView.setVisible(false);
    }
    private void payCard(){
        checkOutView.addCard();
    }
    private void finishPayment(){
        JOptionPane.showMessageDialog(checkOutView,
                "Your order was successfully placed!");
    }
    private void updateNameAdmin(){
        String id = adminView.getIdUser().getText();
        Customers user = cm.findCustomerById(id);
        String newName = adminView.getUpdateName().getText();
        if(!newName.equals("")){
            user.setName(newName);
            if(!cm.updateCustomer(user)){
                JOptionPane.showMessageDialog(adminView,"Error while updating. Wrong data format");
            }
        }
        else{
            JOptionPane.showMessageDialog(adminView,"Nothing was introduced");
        }
    }
    private void updateAddressAdmin(){
        String id = adminView.getIdUser().getText();
        Customers user = cm.findCustomerById(id);
        String newAddress = adminView.getUpdateAddress().getText();
        if(!newAddress.equals("")){
            user.setAddress(newAddress);
            if(!cm.updateCustomer(user)){
                JOptionPane.showMessageDialog(adminView,"Error while updating. Wrong data format");
            }
        }
        else{
            JOptionPane.showMessageDialog(adminView,"Nothing was introduced");
        }
    }
    private void updatePNCAdmin(){
        String id = adminView.getIdUser().getText();
        Customers user = cm.findCustomerById(id);
        String newPNC = adminView.getUpdatePNC().getText();
        if(!newPNC.equals("")){
            user.setPnc(newPNC);
            if(!cm.updateCustomer(user)){
                JOptionPane.showMessageDialog(adminView,"Error while updating. Wrong data format");
            }
        }
        else{
            JOptionPane.showMessageDialog(adminView,"Nothing was introduced");
        }
    }
    private void updateUsernameAdmin(){
        String id = adminView.getIdUser().getText();
        Customers user = cm.findCustomerById(id);
        String newUser = adminView.getUpdateUser().getText();
        if(!newUser.equals("")){
            user.setUsername(newUser);
            if(!cm.updateCustomer(user)){
                JOptionPane.showMessageDialog(adminView,"Error while updating. Wrong data format");
            }
        }
        else{
            JOptionPane.showMessageDialog(adminView,"Nothing was introduced");
        }
    }
    private void updatePassAdmin(){
        String id = adminView.getIdUser().getText();
        Customers user = cm.findCustomerById(id);
        String newPass = adminView.getUpdatePass().getText();
        if(!newPass.equals("")){
            user.setPassword(newPass);
            if(!cm.updateCustomer(user)){
                JOptionPane.showMessageDialog(adminView,"Error while updating. Wrong data format");
            }
        }
        else{
            JOptionPane.showMessageDialog(adminView,"Nothing was introduced");
        }
    }
    private void updateProductName(){
        String id = adminView.getIdProduct().getText();
        Products product = pm.findProductById(id);
        String newName = adminView.getUpdateProductName().getText();
        if(!newName.equals("")){
            product.setProductName(newName);
            if(!pm.updateProduct(product)){
                JOptionPane.showMessageDialog(adminView,"Error while updating. Wrong data format");
            }
        }
        else{
            JOptionPane.showMessageDialog(adminView,"Nothing was introduced");
        }
    }
    private void updateProductQuantity(){
        String id = adminView.getIdProduct().getText();
        Products product = pm.findProductById(id);
        String newQuantity = adminView.getUpdateQuantity().getText();
        if(!newQuantity.equals("")){
            product.setQuantity(Integer.valueOf(newQuantity));
            if(!pm.updateProduct(product)){
                JOptionPane.showMessageDialog(adminView,"Error while updating. Wrong data format");
            }
        }
        else{
            JOptionPane.showMessageDialog(adminView,"Nothing was introduced");
        }
    }
    private void updateProductPrice(){
        String id = adminView.getIdProduct().getText();
        Products product = pm.findProductById(id);
        String newPrice = adminView.getUpdatePrice().getText();
        if(!newPrice.equals("")){
            product.setPrice(Float.valueOf(newPrice));
            if(!pm.updateProduct(product)){
                JOptionPane.showMessageDialog(adminView,"Error while updating. Wrong data format");
            }
        }
        else{
            JOptionPane.showMessageDialog(adminView,"Nothing was introduced");
        }
    }
    private void updateProductDescription(){
        String id = adminView.getIdProduct().getText();
        Products product = pm.findProductById(id);
        String newDesc = adminView.getUpdateDescription().getText();
        if(!newDesc.equals("")){
            product.setDescription(newDesc);
            if(!pm.updateProduct(product)){
                JOptionPane.showMessageDialog(adminView,"Error while updating. Wrong data format");
            }
        }
        else{
            System.out.println("Nothing was introduced");
        }
    }

    public void makeDiscount(){
        String id = adminView.getIdUser().getText();
        Customers customer = cm.findCustomerById(id);
        customer.setLoyal(true);
        cm.updateCustomer(customer);
    }
    private void insertProduct(){
        String productName = adminView.getUpdateProductName().getText();
        String quantity = adminView.getUpdateQuantity().getText();
        String price = adminView.getUpdatePrice().getText();
        String description = adminView.getUpdateDescription().getText();
        if(pm.insertProduct(productName,price,quantity,description)==null){
            JOptionPane.showMessageDialog(adminView,"Error while creating product. Wrong data format");
        }
    }
    private void insertCustomer(){
        String name = adminView.getUpdateName().getText();
        String address = adminView.getUpdateAddress().getText();
        String pnc = adminView.getUpdatePNC().getText();
        String username = adminView.getUpdateUser().getText();
        String pass = adminView.getUpdatePass().getText();
        if(cm.insertCustomer(name,address,pnc,username,pass)==null){
            JOptionPane.showMessageDialog(adminView,"Error while creating customer. Wrong data format");
        }
    }
    private void deleteProduct(){
        String productId = adminView.getIdProduct().getText();
        if(!pm.deleteProduct(productId)){
            JOptionPane.showMessageDialog(adminView,"Error while deleting product.");
        }
    }
    private void deleteCustomer(){
        String customerId = adminView.getIdUser().getText();
        if(!cm.deleteCustomer(customerId)){
            JOptionPane.showMessageDialog(adminView,"Error while deleting customer.");
        }
    }
    private float computeTotalSumForOrder(Integer idOrder){
        List<ShoppingCarts> allOrders = scm.findAllShopCarts();
        float sum = allOrders.stream()
                             .filter(entry->entry.getIdOrder()==idOrder)
                             .map(entry->pm.findProductById(entry.getIdProduct().toString()).getPrice()*entry.getQuantity())
                             .reduce(0f, Float::sum);
        return sum;
    }
    private JTable computeSumForCustomerOrder(List<Orders> customerOrders,boolean loyal){
        String[] col= {"ID","DATE","ID User","Sum"};
        String [][] el= new String[customerOrders.size()+1][4];
        String[] row1= {"ID","DATE","ID User","Sum"};
        el[0]=row1;
        int i=1;
        for(Orders order : customerOrders){
            float totalSum=computeTotalSumForOrder(order.getID());
            if(loyal){
                totalSum-=0.05f*totalSum;
            }
            String[] row={order.getID().toString(),order.getDate().toString(), order.getIdCustomer().toString(), String.valueOf(totalSum)};
            el[i++]=row;
        }
        JTable tabel=new JTable(el,col) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        TableColumnModel columnModel = tabel.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(70);
       // adminView.addReportTable(tabel);
        return tabel;
    }
    private List<Orders> generateOrderFromTo(String from, String to, Integer idCustomer){
        List<Orders> orders = om.findAllWithCustomerId(idCustomer).stream()
                                .filter(entry->(entry.getDate().toString().compareTo(to)==-1 && entry.getDate().toString().compareTo(from)==1))
                                .collect(Collectors.toList());
        return orders;
    }

    private void generateReport(){
        String idCustomer = adminView.getIdUser().getText();
        Customers customer = cm.findCustomerById(idCustomer);
        if(customer!=null){
            String fromDate = adminView.getDateFrom().getText();
            String toDate = adminView.getDateTo().getText();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setLenient(false);
            try {
                sdf.parse(toDate);
                sdf.parse(fromDate);
                List<Orders>orders=generateOrderFromTo(fromDate,toDate,Integer.valueOf(idCustomer));
                adminView.addReportTable(computeSumForCustomerOrder(orders,customer.getLoyal()));
            } catch (ParseException e) {
                System.out.println("Wrong date format!");
            }
        }
       else{
            System.out.println("Customer couldnt be found by the provided ID");
        }
    }
    private void generateHistory(){
        List<Orders> orders = om.findAllWithCustomerId(presentCustomer.getID());
        userView.addHistoryTable(computeSumForCustomerOrder(orders,presentCustomer.getLoyal()));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source =e.getSource();

        //Login View
        if(source==loginView.getLoginUser()){
            loginUser();
        }
        if(source==loginView.getLoginAdmin()){
            loginAdmin();
        }
        if(source==loginView.getSignUp()){
            signUpView();
        }

        //Sign Up
        if(source==signUpView.getSignUp()){
            validateSignUp();
        }

        //User View
        if(source==userView.getPersonalInfoButton()){
            showPersonalInfo();
        }
        if(source==userView.getCheckoutButton()){
            goToShop();
        }
        if(source==userView.getUpdateNameButton()){
            updateName();
        }
        if(source==userView.getUpdateAddressButton()){
            updateAddress();
        }
        if(source==userView.getUpdatePNCButton()){
            updatePNC();
        }
        if(source==userView.getUpdateUserButton()){
            updateUsername();
        }
        if(source==userView.getUpdatePassButton()){
            updatePass();
        }
        if(source==userView.getHistoryButton()){
            generateHistory();
        }
        //Shopping Cart
        if(source==shopCartView.getViewProductsButton()){
            showProducts();
        }
        if(source==shopCartView.getViewCartButton()){
            showShopCart();
        }
        if(source==shopCartView.getInsertCartButton()){
            insertIntoCart();
        }
        if(source==shopCartView.getUpdateCartButton()){
            updateCart();
        }
        if(source==shopCartView.getDeleteFromCartButton()){
            deleteFromCart();
        }
        if(source==shopCartView.getCheckoutButton()){
            goToCheckOut();
        }

        //Check out
        if(source==checkOutView.getCardButton()){
            payCard();
        }
        if(source==checkOutView.getCashButton()){
            finishPayment();
        }
        if(source==checkOutView.getFinishButton()){
            finishPayment();
        }

        //Admin
        if(source==adminView.getViewUsersButton()){
            showCustomers();
        }
        if(source==adminView.getViewProductsButton()){
            showProductsAdmin();
        }
        if(source==adminView.getInsertCustomerButton()){
            insertCustomer();
        }
        if(source==adminView.getInsertProductButton()){
            insertProduct();
        }
        if(source==adminView.getDeleteCustomerButton()){
            deleteCustomer();
        }
        if(source==adminView.getDeleteProductButton()){
            deleteProduct();
        }
        if(source==adminView.getUpdateNameButton()){
            updateNameAdmin();
        }
        if(source==adminView.getUpdateAddressButton()){
            updateAddressAdmin();
        }
        if(source==adminView.getUpdatePNCButton()){
            updatePNCAdmin();
        }
        if(source==adminView.getUpdateUerButton()){
            updateUsernameAdmin();
        }
        if(source==adminView.getUpdatePassButton()){
            updatePassAdmin();
        }
        if(source==adminView.getUpdateProductNameButton()){
            updateProductName();
        }
        if(source==adminView.getUpdatePriceButton()){
            updateProductPrice();
        }
        if(source==adminView.getUpdateQuantityButton()){
            updateProductQuantity();
        }
        if(source==adminView.getUpdateDescriptionButton()){
            updateProductDescription();
        }
        if(source==adminView.getGenerateReportButton()){
            generateReport();
        }
        if(source==adminView.getMakeDiscoutButton()){
            makeDiscount();
        }
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public SignUpView getSignUpView() {
        return signUpView;
    }

    public UserView getUserView() {
        return userView;
    }
}
