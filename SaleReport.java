public class SaleReport {
    private String drugName;
    private int quantitySold;
    private double totalPrice;
    private java.sql.Date saleDate;

    public SaleReport(String drugName, int quantitySold, double totalPrice, java.sql.Date saleDate) {
        this.drugName = drugName;
        this.quantitySold = quantitySold;
        this.totalPrice = totalPrice;
        this.saleDate = saleDate;
    }

    public String getDrugName() {
        return drugName;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public java.sql.Date getSaleDate() {
        return saleDate;
    }
}
