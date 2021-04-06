package business.model;

public class ShoppingCarts{

    private Integer ID;

    private Integer idProduct;

    private Integer quantity;

    private Integer idOrder;

    public ShoppingCarts(Integer ID, Integer idProduct, Integer quantity, Integer idOrder) {
        this.ID = ID;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.idOrder = idOrder;
    }

    public ShoppingCarts() {
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }
}
