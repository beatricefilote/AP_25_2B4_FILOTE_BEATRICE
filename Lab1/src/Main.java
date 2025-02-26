//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello World!");
        String[] c ={"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        System.out.println("nr random (n): " + n);
        int rezultat;
        rezultat = n * 3;
        rezultat = rezultat + 0b10101;
        rezultat = rezultat + 0xFF;
        rezultat = rezultat * 6;
        System.out.println("rezultat: " + rezultat);

         while (rezultat >=10) {

             int suma = 0;
             int temp = rezultat;
             while (temp > 0) {
                 suma = suma + temp % 10;
                 temp = temp / 10;
             }
             rezultat = suma;
         }
         System.out.println("rezultat: " + rezultat);
         System.out.println("Willy-nilly, this semester I will learn " + c[rezultat]);


    }
}