
public class Electronic extends Product{
    int warranty;

    public Electronic (String name, double price, int warranty) {
        super(name, price);
        this.warranty = warranty;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    //Overriding
    public void infoProduct(){
        super.infoProduct();
    }
}
