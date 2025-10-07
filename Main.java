import mypackage.MyString;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();
        MyString myStr = new MyString(input);

        System.out.print("Enter text to append: ");
        String toAppend = sc.nextLine();
        myStr.append(toAppend);

        System.out.println("Result: " + myStr.getString());
        sc.close();
    }
}
