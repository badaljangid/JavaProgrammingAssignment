import java.util.Scanner;
import mypackage.MyString;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();
        new MyString(input); // menu appears automatically
    }
}
