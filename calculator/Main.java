package calculator;

import java.util.Scanner;

public class Main {
    static final String ARABIAN_CALC = "it's arabian calculator";
    static final String ROMAN_CALC = "it's roman calculator";
    static final String[] ROMAN_ARRAY = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    static final String[] ARABIAN_ARRAY = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    static String operationType;

    public static void main(String[] args) {
        System.out.println("Enter a mathematical expression that matches the conditions: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().replaceAll("\\s", "");
        String output = calc(input);
        System.out.printf("The result of calculation is %s", output);
    }

    public static String calc(String input) {
        String output;

        if (input.contains("+")) {
            operationType = "+";
        } else if (input.contains("-")) {
            operationType = "-";
        } else if (input.contains("/")) {
            operationType = "/";
        } else if (input.contains("*")) {
            operationType = "*";
        } else {
            throw new RuntimeException("It isn't mathematical operation");
        }

        String checkingString = input + "1"; /* данная стринга необходима. Так как при сплите, например, такого
        выражения  "1+2+", мы бы получили массив длиной 2.  И запустилось бы дальнейшее вычисление выражения,
        оутпут был бы "3". А после прибавления к  1+2+" + "1" мы получим массив длиной 3.*/

        String[] checkingArray = checkingString.split("[-/+*]");
        if (checkingArray.length != 2) {
            throw new RuntimeException("Incorrect input");
        }

        String[] inputArray = input.split("[-/+*]");

        String calculator = kindOfCalc(inputArray);

        String romNum1 = inputArray[0];
        String romNum2 = inputArray[1];

        if (calculator.equals(ROMAN_CALC)) {
            output = romanCalculating(romNum1, romNum2, operationType);
        } else {
            int arabNum1 = Integer.parseInt(inputArray[0]);
            int arabNum2 = Integer.parseInt(inputArray[1]);
            output = String.valueOf(calculating(arabNum1, arabNum2, operationType));
        }
        return output;
    }


    private static String kindOfCalc(String[] array) {
        boolean b1Arab = false;
        boolean b2Arab = false;
        boolean b1Rom = false;
        boolean b2Rom = false;
        for (String s : ARABIAN_ARRAY) {
            if (s.equals(array[0])) {
                b1Arab = true;
            }
            if (s.equals(array[1])) {
                b2Arab = true;
            }
        }
        for (String s : ROMAN_ARRAY) {
            if (s.equals(array[0])) {
                b1Rom = true;
            }
            if (s.equals(array[1])) {
                b2Rom = true;
            }
        }

        if (b1Arab && b2Arab) {
            return ARABIAN_CALC;
        } else if (b1Rom && b2Rom) {
            return ROMAN_CALC;
        } else {
            throw new RuntimeException("Incorrect input");
        }
    }

    private static String romanCalculating(String romNum1, String romNum2, String operation) {
        int num1 = convertRomanToArabian(romNum1);
        int num2 = convertRomanToArabian(romNum2);
        int result = 0;
        if (operation.equals("-")) {
            if (num1 <= num2) {
                throw new RuntimeException("Incorrect output. There are no negative numbers in the Roman system");
            } else {
                result = calculating(num1, num2, operation);
            }
        } else if (operation.equals("/")) {
            if (num1 < num2) {
                throw new RuntimeException("Incorrect output. There are no negative numbers in the Roman system");
            } else {
                result = calculating(num1, num2, operation);
            }
        } else {
            result = calculating(num1, num2, operation);
        }

        return convertArabianToRoman(result);
    }

    private static int convertRomanToArabian(String romNum) {
        int arabNum = 0;
        for (int i = 0; i < ROMAN_ARRAY.length; i++) {
            if (romNum.equals(ROMAN_ARRAY[i])) {
                arabNum = Integer.parseInt(ARABIAN_ARRAY[i]);
            }
        }
        return arabNum;
    }

    private static String convertArabianToRoman(int arabNum) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV",
                "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII",
                "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII",
                "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI",
                "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV",
                "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII",
                "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII",
                "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX",
                "C"};
        final String result = roman[arabNum];
        return result;
    }

    private static int calculating(int num1, int num2, String operation) {
        int result;
        switch (operation) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Incorrect operation type");  // Данное исключение никогда не будет
                // выброшено, так как выше делалась проверка на корректность знака операции.
        }
        return result;
    }

}

