package mypackage;

/**
 * @filename - Operation.java
 * @Descripiton - This will perform most of the string operation : (e.g-
 *              uniquePalindromesCounter, getnthFibonacci,
 *              convertSnakeToCamel, countConsonants, binaryToDecimal,
 *              expandCharacter, getCharFrequency, isPrime, numberToWords,
 *              lengthOfLongestSubstring)
 * @author - Badal Jangid
 */
public class Operations {

    // 1. Count & Print Unique Palindromic Substrings (Raw Logic)
    public int countUniquePalindromes(String input) {
        if (input == null || input.length() == 0) {
            System.out.println("No input string provided!");
            return 0;
        }

        int len = input.length();
        String[] storedPalindromes = new String[len * len]; // store unique substrings
        int uniqueCount = 0;

        // expand around every center
        for (int center = 0; center < len; center++) {
            uniqueCount = expandAndStore(input, center, center, storedPalindromes, uniqueCount); // odd length
            uniqueCount = expandAndStore(input, center, center + 1, storedPalindromes, uniqueCount); // even length
        }

        System.out.println("\n--- Unique Palindromic Substrings ---");
        for (int i = 0; i < uniqueCount; i++) {
            System.out.println("→ " + storedPalindromes[i] + " (length: " + storedPalindromes[i].length() + ")");
        }

        System.out.println("--------------------------------------");
        System.out.println("Total Unique Palindromic Substrings: " + uniqueCount);

        return uniqueCount;
    }

    // helper: expand from center and store unique palindromes manually
    private int expandAndStore(String str, int left, int right, String[] stored, int count) {
        int len = str.length();

        while (left >= 0 && right < len && str.charAt(left) == str.charAt(right)) {
            int subLen = right - left + 1;

            // manually extract substring
            char[] temp = new char[subLen];
            for (int i = 0; i < subLen; i++) {
                temp[i] = str.charAt(left + i);
            }
            String current = new String(temp);

            boolean exists = false;
            for (int i = 0; i < count; i++) {
                if (isSame(stored[i], current)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                stored[count] = current;
                count++;
            }

            left--;
            right++;
        }

        return count;
    }

    // helper: manual string comparison
    private boolean isSame(String a, String b) {
        if (a == null || b == null)
            return false;
        if (a.length() != b.length())
            return false;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i))
                return false;
        }
        return true;
    }

    // 2. Nth Fibonacci Number (1-based)

    public int getNthFibonacci(int n) {
        if (n <= 0 || n > 46)
            return Integer.MIN_VALUE;
        if (n == 1)
            return 0;
        if (n == 2)
            return 1;

        int prev = 0, curr = 1, next = 0;
        for (int i = 3; i <= n; i++) {
            next = prev + curr;
            prev = curr;
            curr = next;
        }
        return next;
    }

    // 3. Snake Case → Camel Case

    public static String snakeToCamel(String input) {
        if (input == null)
            return "";

        String result = "";
        boolean capitalizeNext = false;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (ch == '_') {
                capitalizeNext = true;
            } else {
                if (capitalizeNext && ch >= 'a' && ch <= 'z') {
                    ch = (char) (ch - 32); // to uppercase
                }
                result += ch;
                capitalizeNext = false;
            }
        }
        return result;
    }

    // 4. Count Consonants

    public static int countConsonants(String input) {
        if (input == null)
            return 0;

        int consonantCount = 0;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            // Convert uppercase to lowercase manually
            if (ch >= 'A' && ch <= 'Z') {
                ch = (char) (ch - 'A' + 'a');
            }

            if (ch >= 'a' && ch <= 'z' &&
                    ch != 'a' && ch != 'e' && ch != 'i' && ch != 'o' && ch != 'u') {
                consonantCount++;
            }
        }
        return consonantCount;
    }

    // 5. Binary to Decimal

    public static int binaryToDecimal(String binary) {
        if (binary == null || binary.length() == 0)
            return Integer.MIN_VALUE;

        int decimalValue = 0;
        int power = 1;

        for (int i = binary.length() - 1; i >= 0; i--) {
            char bit = binary.charAt(i);

            if (bit == '1') {
                decimalValue += power;
            } else if (bit != '0') {
                return Integer.MIN_VALUE; // Invalid character
            }

            power *= 2;
        }
        return decimalValue;
    }

    // 6. Expand Characters Based on Digits (a2b3 → aabbb)

    public static String expandCharacters(String input) {
        if (input == null)
            return "";

        String result = "";
        int i = 0;

        while (i < input.length()) {
            char current = input.charAt(i);

            if ((current >= 'a' && current <= 'z') || (current >= 'A' && current <= 'Z')) {
                i++;
                int repeatCount = 0;
                boolean hasDigit = false;

                while (i < input.length() && input.charAt(i) >= '0' && input.charAt(i) <= '9') {
                    hasDigit = true;
                    repeatCount = repeatCount * 10 + (input.charAt(i) - '0');
                    i++;
                }

                if (!hasDigit)
                    repeatCount = 1;

                for (int j = 0; j < repeatCount; j++) {
                    result += current;
                }
            } else {
                i++;
            }
        }
        return result;
    }

    // 7. Character Frequency (e.g. "aab" → "a2b1")
    public static String charFrequency(String input) {
        if (input == null)
            return "";

        String result = "";
        int length = input.length();

        for (int i = 0; i < length; i++) {
            char current = input.charAt(i);
            int count = 1;

            boolean alreadyProcessed = false;
            for (int k = 0; k < i; k++) {
                if (input.charAt(k) == current) {
                    alreadyProcessed = true;
                    break;
                }
            }
            if (alreadyProcessed)
                continue;

            for (int j = i + 1; j < length; j++) {
                if (input.charAt(j) == current)
                    count++;
            }

            result += current + Integer.toString(count);
        }
        return result;
    }

    // 8. Prime Number Checker
    public static boolean isPrime(int num) {
        if (num <= 1)
            return false;
        if (num == 2)
            return true;
        if (num % 2 == 0)
            return false;

        for (int i = 3; (long) i * i <= num; i += 2) {
            if (num % i == 0)
                return false;
        }
        return true;
    }

    // 9. Number to Words (supports -9999 to 9999)

    private static final String[] UNITS = {
            "zero", "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine"
    };

    private static final String[] TEENS = {
            "ten", "eleven", "twelve", "thirteen", "fourteen",
            "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    private static final String[] TENS = {
            "", "", "twenty", "thirty", "forty",
            "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    public static String numberToWords(int num) {
        if (num < 0) {
            if (num == Integer.MIN_VALUE)
                return "Number too large";
            return "minus " + numberToWords(-num);
        }

        if (num < 10)
            return UNITS[num];
        if (num < 20)
            return TEENS[num - 10];
        if (num < 100) {
            int tensPart = num / 10;
            int unitsPart = num % 10;
            return (unitsPart == 0) ? TENS[tensPart] : TENS[tensPart] + " " + UNITS[unitsPart];
        }
        if (num < 1000) {
            int hundreds = num / 100;
            int remainder = num % 100;
            return (remainder == 0)
                    ? UNITS[hundreds] + " hundred"
                    : UNITS[hundreds] + " hundred " + numberToWords(remainder);
        }
        if (num < 10000) {
            int thousands = num / 1000;
            int remainder = num % 1000;
            return (remainder == 0)
                    ? UNITS[thousands] + " thousand"
                    : UNITS[thousands] + " thousand " + numberToWords(remainder);
        }
        return "Number too large";
    }

    // 11. Longest Unique Substring with Output and Length
public static int longestUniqueSubstringWithOutput(String input) {
    if (input == null || input.length() == 0) {
        System.out.println("No input string provided!");
        return 0;
    }

    int len = input.length();
    int[] lastIndex = new int[256];   // stores last seen index of characters
    for (int i = 0; i < 256; i++) {
        lastIndex[i] = -1;
    }

    int start = 0;
    int maxLen = 0;
    int maxStart = 0;

    // Sliding window logic without built-ins
    for (int end = 0; end < len; end++) {
        char current = input.charAt(end);

        if (lastIndex[current] >= start) {
            start = lastIndex[current] + 1;
        }

        lastIndex[current] = end;
        int currentLength = end - start + 1;

        if (currentLength > maxLen) {
            maxLen = currentLength;
            maxStart = start;
        }
    }

    // Build the longest substring manually
    char[] longestChars = new char[maxLen];
    for (int i = 0; i < maxLen; i++) {
        longestChars[i] = input.charAt(maxStart + i);
    }
    String longestSub = new String(longestChars);

    // Print results
    System.out.println("\n--- Longest Unique Substring ---");
    System.out.println("Substring: " + longestSub);
    System.out.println("Length: " + maxLen);
    System.out.println("--------------------------------");

    return maxLen;
}


}
