package business.model;

public class Customers {
    private Integer ID;

    private String address;

    private String name;

    private String pnc;

   // private Boolean loyal;
    private Boolean loyal;
    private String username;

    private String password;

    public Customers(Integer id, String address, String name, String pnc, Boolean loyal, String username, String password){
        this.ID=id;
        this.address=address;
        this.name=name;
        this.pnc=pnc;
        this.loyal=loyal;
        this.username=username;
        this.password=password;
    }
    public Customers(){

    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPnc() {
        return pnc;
    }

    public void setPnc(String pnc) {
        this.pnc = pnc;
    }

    public Boolean getLoyal() {
        return loyal;
    }

    public void setLoyal(Boolean loyal) {
        this.loyal = loyal;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
