public class Exception2 {
    // codingan pertama:

    // public static void main(String[] args) {
    //     int i=0;
    //     String greeting[]={
    //         "Hello World!",
    //         "No, I mean it!",
    //         "Hello World"
    //     };
    //     while(i<4)
    //     {   
    //         System.out.println(greeting[i]);
    //         i++;
    //     }
    // }

    //codingan perbaikan:

    public static void main(String[] args) {
        int i=0;
        String greetings[]={
            "Hello World!",
            "No,I mean it!",
            "HELLO WORLD!"
        };
        while(i<4)
        {
            try
            {
            System.out.println(greetings[i]);
            i++;
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
            System.out.println("Resetting index value");
            i=0;
            }
        }
    }
}