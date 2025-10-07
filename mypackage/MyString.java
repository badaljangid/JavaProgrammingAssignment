package mypackage;
import java.util.Scanner;

public class MyString {
    private String str;

    public MyString(String input) {
        this.str = input;
        runMenu();
    }
    //Menu For the All the Methods
    private void runMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nCurrent String: " + str);
            System.out.println("Choose an operation:");
            System.out.println("1. Append");
            System.out.println("2. Count Words");
            System.out.println("3. Replace");
            System.out.println("4. Check Palindrome");
            System.out.println("5. Splice");
            System.out.println("6. Split");
            System.out.println("7. Max Repeating Character");
            System.out.println("8. Sort");
            System.out.println("9. Shift");
            System.out.println("10. Reverse");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter string to append: ");
                    append(sc.nextLine());
                    break;
                case 2:
                    System.out.println("Word Count: " + countWords());
                    break;
                case 3:
                    System.out.print("Enter substring to replace: ");
                    String oldStr = sc.nextLine();
                    System.out.print("Enter replacement: ");
                    String newStr = sc.nextLine();
                    replace(oldStr, newStr);
                    break;
                case 4:
                    System.out.println("Is Palindrome? " + isPalindrome());
                    break;
                case 5:
                    System.out.print("Enter start index: ");
                    int start = sc.nextInt();
                    System.out.print("Enter length: ");
                    int len = sc.nextInt();
                    splice(start, len);
                    break;
                case 6:
                    System.out.print("Enter split pattern (single char): ");
                    String pattern = sc.nextLine();
                    String[] parts = split(pattern);
                    System.out.println("Split parts:");
                    for (String p : parts) System.out.println(p);
                    break;
                case 7:
                    System.out.println("Max Repeating Character: " + maxRepeat());
                    break;
                case 8:
                    sort();
                    break;
                case 9:
                    System.out.print("Enter n: ");
                    int n = sc.nextInt();
                    shift(n);
                    break;
                case 10:
                    reverse();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

         // Append Method
        public void append(String newString) {
        char[] oldArr = str.toCharArray();
        char[] newArr = newString.toCharArray();
        char[] combined = new char[oldArr.length + newArr.length];
        for (int i = 0; i < oldArr.length; i++) combined[i] = oldArr[i];
        for (int i = 0; i < newArr.length; i++) combined[oldArr.length + i] = newArr[i];
        str = new String(combined);
    }
    //CountWords
    public int countWords() {
        int count = 0;
        boolean inWord = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ' && c != '\t' && c != '\n') {
                if (!inWord) {
                    count++;
                    inWord = true;
                }
            } else inWord = false;
        }
        return count;
    }
     //Replace
    public void replace(String a, String b) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            boolean match = true;
            if (i + a.length() <= str.length()) {
                for (int j = 0; j < a.length(); j++) {
                    if (str.charAt(i + j) != a.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    result += b;
                    i += a.length() - 1;
                    continue;
                }
            }
            result += str.charAt(i);
        }
        str = result;
    }
     //Palindrome
    public boolean isPalindrome() {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            while (left < right && str.charAt(left) == ' ') left++;
            while (left < right && str.charAt(right) == ' ') right--;
            if (Character.toLowerCase(str.charAt(left)) != Character.toLowerCase(str.charAt(right)))
                return false;
            left++;
            right--;
        }
        return true;
    }
     
    //Splice Method
    public void splice(int start, int length) {
        if (start < 0 || start >= str.length()) return;
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            if (i < start || i >= start + length) result += str.charAt(i);
        }
        str = result;
    }
     
    // SPlit Method
    public String[] split(String pattern) {
        char sep = pattern.charAt(0);
        int count = 1;
        for (int i = 0; i < str.length(); i++) if (str.charAt(i) == sep) count++;
        String[] parts = new String[count];
        int idx = 0;
        String temp = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == sep) {
                parts[idx++] = temp;
                temp = "";
            } else temp += str.charAt(i);
        }
        parts[idx] = temp;
        return parts;
    }
     
    // MaxRepeat
    public char maxRepeat() {
        int[] freq = new int[256];
        for (int i = 0; i < str.length(); i++) freq[str.charAt(i)]++;
        int maxCount = 0;
        char maxChar = '\0';
        for (int i = 0; i < 256; i++) {
            if (freq[i] > maxCount) {
                maxCount = freq[i];
                maxChar = (char) i;
            }
        }
        return maxChar;
    }
    
    // Sort (using MergeSort)
   public void sort() {
    char[] arr = str.toCharArray();
    mergeSort(arr, 0, arr.length - 1);
    str = new String(arr);
}

// Recursive merge sort
private void mergeSort(char[] arr, int left, int right) {
    if (left < right) {
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
}


private void merge(char[] arr, int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    char[] L = new char[n1];
    char[] R = new char[n2];

    // Copy data into temporary arrays
    for (int i = 0; i < n1; i++) L[i] = arr[left + i];
    for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

    // Merge back into arr[]
    int i = 0, j = 0, k = left;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k++] = L[i++];
        } else {
            arr[k++] = R[j++];
        }
    }

    // Copy any remaining elements
    while (i < n1) arr[k++] = L[i++];
    while (j < n2) arr[k++] = R[j++];
}

    //Shift Method
    public void shift(int n) {
        if (n <= 0 || n >= str.length()) return;
        char[] arr = str.toCharArray();
        char[] shifted = new char[arr.length];
        int index = 0;
        for (int i = n; i < arr.length; i++) shifted[index++] = arr[i];
        for (int i = 0; i < n; i++) shifted[index++] = arr[i];
        str = new String(shifted);
    }
   
     //Reverse
    public void reverse() {
        char[] arr = str.toCharArray();
        int left = 0, right = arr.length - 1;
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
        str = new String(arr);
    }
}
