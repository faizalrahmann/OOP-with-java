package TokoKami;

public class Food extends Product {
    String expire;

    public Food(String name, double price, String expire) {
        super(name, price);
        this.expire = expire;
    }
    
    public String getExpired(){
        return expire;
    }

    public void setExpired(String expired){
        this.expire = expired;
    }

    public void infoProduct(){
        super.infoProduct();
        System.out.println("Expired: " + getExpired());
    }
}
