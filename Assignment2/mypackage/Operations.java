package mypackage;

public class Operations {

    // 1. Count unique palindromic substrings in a string
    public int countUniquePalindromes(String str) {
        String[] uniquePalindromes = new String[str.length() * str.length()];
        int uniqueCount = 0;

        for (int center = 0; center < str.length(); center++) {
            // Odd-length palindromes
            uniqueCount = expandAndStore(str, center, center, uniquePalindromes, uniqueCount);
            // Even-length palindromes
            uniqueCount = expandAndStore(str, center, center + 1, uniquePalindromes, uniqueCount);
        }

        return uniqueCount;
    }

    // Expand around center and store unique palindromes
    private int expandAndStore(String str, int left, int right, String[] uniqueList, int count) {
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {

            String current = "";
            for (int i = left; i <= right; i++) {
                current += str.charAt(i);
            }

            boolean alreadyExists = false;
            for (int i = 0; i < count; i++) {
                if (isSame(uniqueList[i], current)) {
                    alreadyExists = true;
                    break;
                }
            }

            if (!alreadyExists) {
                uniqueList[count] = current;
                count++;
            }

            left--;
            right++;
        }
        return count;
    }

    // Compare two strings manually
    private boolean isSame(String a, String b) {
        if (a.length() != b.length()) return false;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) return false;
        }
        return true;
    }

    // 2. Find the Nth number in the Fibonacci sequence
    public int getNthFibonacci(int n) {
        if (n == 1) return 0;
        if (n == 2) return 1;

        int first = 0, second = 1, next = 0;

        for (int i = 3; i <= n; i++) {
            next = first + second;
            first = second;
            second = next;
        }

        return next;
    }

    // 3. Convert snake_case to camelCase
    public static String snakeToCamel(String snakeCaseString) {
        String result = "";
        boolean toUpper = false;

        for (int i = 0; i < snakeCaseString.length(); i++) {
            char ch = snakeCaseString.charAt(i);

            if (ch == '_') {
                toUpper = true;
            } else {
                if (toUpper && ch >= 'a' && ch <= 'z') {
                    ch = (char) (ch - 32); // convert to uppercase
                }
                result += ch;
                toUpper = false;
            }
        }

        return result;
    }

    // 4. Count consonants in a string
    public static int countConsonants(String str) {
        int consonantCount = 0;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch >= 'A' && ch <= 'Z') {
                ch = (char) (ch - 'A' + 'a'); // convert to lowercase
            }

            if (ch >= 'a' && ch <= 'z' &&
                ch != 'a' && ch != 'e' && ch != 'i' && ch != 'o' && ch != 'u') {
                consonantCount++;
            }
        }

        return consonantCount;
    }

    // 5. Convert binary string to decimal (manual calculation)
    public static int binaryToDecimal(String binaryString) {
        int decimalValue = 0;
        int base = 1;

        for (int i = binaryString.length() - 1; i >= 0; i--) {
            if (binaryString.charAt(i) == '1') {
                decimalValue += base;
            }
            base *= 2;
        }

        return decimalValue;
    }

    // 6. Expand characters based on the digit following them
    public static String expandCharacters(String str) {
        String result = "";
        int i = 0;

        while (i < str.length()) {
            char ch = str.charAt(i);

            if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
                i++;
                int count = 0;

                while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                    count = count * 10 + (str.charAt(i) - '0');
                    i++;
                }

                for (int j = 0; j < count; j++) {
                    result += ch;
                }
            } else {
                i++;
            }
        }

        return result;
    }

    // 7. Character frequency in a string (compressed form)
    public static String charFrequency(String str) {
        String result = "";
        int n = str.length();

        for (int i = 0; i < n; i++) {
            char ch = str.charAt(i);
            int count = 1;

            // Skip already counted characters
            boolean alreadyCounted = false;
            for (int k = 0; k < i; k++) {
                if (str.charAt(k) == ch) {
                    alreadyCounted = true;
                    break;
                }
            }
            if (alreadyCounted) continue;

            // Count occurrences
            for (int j = i + 1; j < n; j++) {
                if (str.charAt(j) == ch) count++;
            }

            result += ch + Integer.toString(count);
        }

        return result;
    }

    // 8. Prime number checker
    public static boolean isPrime(int num) {
        if (num <= 1) return false;

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }

        return true;
    }

    // Arrays for number-to-words conversion
    private static final String[] units = {
        "zero", "one", "two", "three", "four",
        "five", "six", "seven", "eight", "nine"
    };

    private static final String[] teens = {
        "ten", "eleven", "twelve", "thirteen", "fourteen",
        "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    private static final String[] tens = {
        "", "", "twenty", "thirty", "forty",
        "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    // 9. Convert number to words
    public static String numberToWords(int num) {
        if (num < 0) return "minus " + numberToWords(-num);
        if (num < 10) return units[num];
        if (num < 20) return teens[num - 10];

        if (num < 100) {
            int tenPart = num / 10;
            int unitPart = num % 10;
            return (unitPart == 0)
                    ? tens[tenPart]
                    : tens[tenPart] + " " + units[unitPart];
        }

        if (num < 1000) {
            int hundredPart = num / 100;
            int remainder = num % 100;
            return (remainder == 0)
                    ? units[hundredPart] + " hundred"
                    : units[hundredPart] + " hundred " + numberToWords(remainder);
        }

        if (num < 10000) {
            int thousandPart = num / 1000;
            int remainder = num % 1000;
            return (remainder == 0)
                    ? units[thousandPart] + " thousand"
                    : units[thousandPart] + " thousand " + numberToWords(remainder);
        }

        return "Number too large";
    }

    // 10. Length of the longest substring without repeating characters
    public static int longestUniqueSubstring(String s) {
        int n = s.length();
        int maxLength = 0;

        int[] lastIndex = new int[256];
        for (int i = 0; i < 256; i++) {
            lastIndex[i] = -1;
        }

        int start = 0;

        for (int end = 0; end < n; end++) {
            char current = s.charAt(end);

            if (lastIndex[current] >= start) {
                start = lastIndex[current] + 1;
            }

            lastIndex[current] = end;
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }
}
