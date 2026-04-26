public class Main {
    public static void main(String[] args) {
        BankAccount yulhan = new BankAccount (1000000, "8790094922","Yulhan Wahyudin");
        yulhan.deposit(5000000);
        yulhan.withdraw(500000);
        System.out.println(yulhan);
    }
}
