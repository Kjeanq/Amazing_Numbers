package numbers;

import java.math.BigInteger;

public class Calculations {
    public boolean buzz(String input) {
        BigInteger number = new BigInteger(input);
        return number.remainder(new BigInteger("10")).equals(new BigInteger("7")) ||
                number.remainder(new BigInteger("7")).equals(BigInteger.ZERO);
    }
    public boolean duck(String input){
        for (int i = input.length() - 1; i > 0; i--) {
            if (input.charAt(i) == '0') {
                return true;
            }
        }
        return false;
    }
    public boolean palindromic(String input){
        StringBuilder stringBuilder = new StringBuilder(input);
        String test = String.valueOf(stringBuilder.reverse());
        return test.equals(input);
    }
    public boolean gapful(String input) {
        if (input.length() < 3) {
            return false;
        }
        BigInteger number = new BigInteger(input);
        String modulo = input.charAt(0) + input.substring(input.length() - 1);
        return number.remainder(new BigInteger(modulo)).equals(BigInteger.ZERO);
    }
    public boolean spy(String input) {
        char[] numbers = input.toCharArray();
        int sum = 0;
        int product = 1;
        for (char number : numbers) {
            sum = sum + Character.getNumericValue(number);
            product = product * Character.getNumericValue(number);
        }
        return sum == product;
    }
    public boolean square(String input) {
        BigInteger number = new BigInteger(input);
        BigInteger[] square = number.sqrtAndRemainder();
        return square[1].equals(BigInteger.ZERO);
    }
    public boolean sunny(String input) {
        BigInteger number = new BigInteger(input);
        BigInteger[] square = number.add(BigInteger.ONE).sqrtAndRemainder();
        return square[1].equals(BigInteger.ZERO);
    }
    public boolean jumping(String input) {
        char[] array = input.toCharArray();
        if (array.length == 1) {
            return true;
        }
        int prev = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] + 1 != prev && array[i] - 1 != prev) {
                return false;
            }
            prev = array[i];
        }
        return true;
    }
    public boolean happy (String input) {
        BigInteger number = new BigInteger(input);
        BigInteger sum = new BigInteger("0");
        do {
            while (!number.equals(BigInteger.ZERO)) {
                sum = sum.add(number.remainder(BigInteger.TEN).pow(2));
                number = number.divide(BigInteger.TEN);
            }
            if (sum.equals(BigInteger.ONE)) {
                return true;
            }
            if (sum.equals(new BigInteger("4"))) {
                return false;
            }
            String temp = String.valueOf(sum);
            sum = BigInteger.ZERO;
            number = new BigInteger(temp);
        } while (!number.equals(new BigInteger(input)));
        return false;
    }
}
