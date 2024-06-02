public class Drug {
     int drugId;
     String name;
     String type;
     java.sql.Date manufactureDate;
     java.sql.Date expiryDate;
     double price;
     int quantity;

    public Drug(int drugId, String name, String type, java.sql.Date manufactureDate, java.sql.Date expiryDate, double price, int quantity) {
        this.drugId = drugId;
        this.name = name;
        this.type = type;
        this.manufactureDate = manufactureDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.quantity = quantity;
    }

    public Drug(int drugId, String name, String type, double price, int quantity) {
        this.drugId = drugId;
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }


    public int getDrugId() {
        return drugId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public java.sql.Date getManufactureDate() {
        return manufactureDate;
    }

    public java.sql.Date getExpiryDate() {
        return expiryDate;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
