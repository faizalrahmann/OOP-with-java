public class Aritmetic1 {
    
    public static void main(String[] args) {
        
        int hasil = 10 /0;

        System.out.println(hasil);

    } catch (ArithmeticException e) {
        System.out.println("tidak bisa dibagi nol");
    }
    
}
