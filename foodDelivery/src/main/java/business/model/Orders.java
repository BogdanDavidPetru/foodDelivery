package business.model;

import java.util.Date;

public class Orders {
    private Integer ID;

    private Date date;

    private Integer idCustomer;

    public Orders(Integer ID, Date date, Integer idCustomer) {
        this.ID = ID;
        this.date = date;
        this.idCustomer = idCustomer;
    }

    public Orders() {
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }
}
