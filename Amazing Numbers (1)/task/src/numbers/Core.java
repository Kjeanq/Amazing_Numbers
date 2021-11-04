package numbers;

import java.math.BigInteger;
import java.util.*;

public class Core {
    private final Calculations calculations = new Calculations();
    private List<String> validProperty = new LinkedList<>(Arrays.asList("BUZZ", "DUCK", "PALINDROMIC",
            "GAPFUL", "SPY", "EVEN", "ODD", "SUNNY", "SQUARE", "JUMPING", "HAPPY", "SAD"));
    public void printing() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a request: ");
        String input = scanner.nextLine();
        if (input.equals("0")) {
            System.exit(0);
        }
        testing(input);
    }

    private void testing(String input){
        String[] inputArray = input.split(" ");
        if (inputArray.length == 1) {
            firstArg(inputArray[0]);
        } else if (inputArray.length == 2) {
            secondArg(inputArray[0], inputArray[1]);
            printing();
        } else if (inputArray.length >= 3) {
            MultiArg(inputArray);
        }
    }
    private void firstArg (String num){
        try {
            BigInteger isPositive = new BigInteger(num);
            if ((isPositive.compareTo(BigInteger.ZERO)) < 0) {
                System.out.println("The first parameter should be a natural number or zero.");
                System.out.println();
                printing();
            } else {
                result(num);
            }
        } catch (NumberFormatException e) {
            System.out.println("The first parameter should be a natural number or zero.");
            printing();
        }
    }
    private void secondArg (String num, String seriesCount) {
        try {
            int seriesCheck = Integer.parseInt(seriesCount);
            if (seriesCheck < 0) {
                System.out.println("The second parameter should be a natural number.");
                System.out.println();
            } else {
                series(num, seriesCheck);
            }
            printing();
        } catch (NumberFormatException e) {
            System.out.println("The second parameter should be a natural number.");
            System.out.println();
            printing();
        }
    }
    private void MultiArg (String[] inputArray) {
        int count = 0;
        ArrayList<String> invalid = new ArrayList<>();
        for (int i = 2; i < inputArray.length; i++) {
            if (!validProperty.contains(inputArray[i].toUpperCase()) && !validProperty.contains(inputArray[i].toUpperCase().substring(1))) {
                count += 1;
                invalid.add("[" + inputArray[i] + "]");
            }
        }
        if (count == 1) {
            System.out.println("The property " + invalid.get(0) + " is wrong.");
            System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]\n");
            System.out.println();
            printing();
        }  else if (count > 1) {
            System.out.print("The properties ");
            for (String s : invalid) {
                System.out.print("[" + s + "] ");
            }
            System.out.println("are wrong.");
            System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]\n");
            printing();
        }

        if (inputArray.length == 3) {
            numberSearch(inputArray);
        } else {
            exclusive(inputArray);
        }

    }
    private  void result(String input) {
        boolean even = new BigInteger(input).remainder(BigInteger.TWO).equals(BigInteger.ZERO);
        System.out.println("Properties of " + input);
        System.out.println("\t \t even: " + even);
        System.out.println("\t \t odd: " + !even);
        System.out.println("\t \t buzz: " + calculations.buzz(input));
        System.out.println("\t \t duck: " + calculations.duck(input));
        System.out.println("\t \t palindromic: " + calculations.palindromic(input));
        System.out.println("\t \t gapful: " + calculations.gapful(input));
        System.out.println("\t \t spy: " + calculations.spy(input));
        System.out.println("\t \t square: " + calculations.square(input));
        System.out.println("\t \t sunny: " + calculations.sunny(input));
        System.out.println("\t \t jumping: " + calculations.jumping(input));
        System.out.println("\t \t happy: " + calculations.happy(input));
        System.out.println("\t \t sad: " + !calculations.happy(input));
        System.out.println();
        printing();
    }

    private  void series(String num, int seriesLenght){
        BigInteger numbers = new BigInteger(num);
        for (int i = 0; i < seriesLenght; i++) {
            System.out.print("\t \t" + numbers + " is ");
            if (numbers.remainder(BigInteger.TWO).equals(BigInteger.ZERO)) {
                System.out.print("even, ");
            } else {
                System.out.print("odd, ");
            }
            if (calculations.buzz(String.valueOf(numbers))) {
                System.out.print("buzz, ");
            }
            if (calculations.duck(String.valueOf(numbers))) {
                System.out.print("duck, ");
            }
            if (calculations.palindromic(String.valueOf(numbers))) {
                System.out.print("palindromic, ");
            }
            if (calculations.gapful(String.valueOf(numbers))) {
                System.out.print("gapful, ");
            }
            if (calculations.spy(String.valueOf(numbers))) {
                System.out.print("spy, ");
            }
            if (calculations.square(String.valueOf(numbers))) {
                System.out.print("square, ");
            }
            if (calculations.sunny(String.valueOf(numbers))) {
                System.out.print("sunny, ");
            }
            if (calculations.jumping(String.valueOf(numbers))) {
                System.out.print("jumping, ");
            }
            if (calculations.happy(String.valueOf(numbers))) {
                System.out.println("happy");
            } else if (!calculations.happy(String.valueOf(numbers))) {
                System.out.println("sad");
            }

            numbers = numbers.add(BigInteger.ONE);
        }
    }

    private  void numberSearch (String[] input) {
        BigInteger number = new BigInteger(input[0]);
        int count = Integer.parseInt(input[1]);
        String property = input[2].toLowerCase();
        int i = 0;
        while (i < count) {
            if (property.substring(0, 1).equals("-")) {
                if (!checking(property.substring(1), number)) {
                    series(String.valueOf(number), 1);
                    i++;
                }
            } else if (checking(property, number)) {
                series(String.valueOf(number), 1);
                i++;
            }
            number = number.add(BigInteger.ONE);
        }
        System.out.println();
        printing();
    }
    private  boolean checking (String property, BigInteger number) {
        switch (property) {
            case "buzz":
                return calculations.buzz(String.valueOf(number));
            case "duck":
                return calculations.duck(String.valueOf(number));
            case "palindromic":
                return calculations.palindromic(String.valueOf(number));
            case "gapful":
                return calculations.gapful(String.valueOf(number));
            case "spy":
                return calculations.spy(String.valueOf(number));
            case "square":
                return calculations.square(String.valueOf(number));
            case "sunny":
                return calculations.sunny(String.valueOf(number));
            case "even":
                return number.remainder(BigInteger.TWO).equals(BigInteger.ZERO);
            case "odd":
                return !(number.remainder(BigInteger.TWO).equals(BigInteger.ZERO));
            case "jumping":
                return calculations.jumping(String.valueOf(number));
            case "sad":
                if (!calculations.happy(String.valueOf(number))) return true;
                else return false;
            case "happy":
                return calculations.happy(String.valueOf(number));
            default:
                return false;
        }

    }
    private  void exclusive (String[] inputArray) {
        ArrayList<String> prop = new ArrayList<>(Arrays.asList(inputArray).subList(2, inputArray.length));
        for (String property : validProperty) {
            if (prop.contains(property.toLowerCase(Locale.ROOT)) && prop.contains("-" + property.toLowerCase(Locale.ROOT))) {
                System.out.println("The request contains mutually exclusive properties: [" + property + ", -" + property +
                "] There are no numbers with these properties.");
                System.out.println();
                printing();
            }
        }
        if (prop.contains("square") && prop.contains("sunny")) {
            System.out.println("The request contains mutually exclusive properties: [SQUARE, SUNNY] " +
                    "There are no numbers with these properties.");
        } else if (prop.contains("odd") && prop.contains("even")) {
            System.out.println("The request contains mutually exclusive properties: [EVEN, ODD] " +
                    "There are no numbers with these properties.");
        } else if (prop.contains("duck") && prop.contains("spy")) {
            System.out.println("The request contains mutually exclusive properties: [DUCK, SPY] " +
                    "There are no numbers with these properties.");
        } else if (prop.contains("happy") && prop.contains("sad")){
            System.out.println("The request contains mutually exclusive properties: [HAPPY, SAD] " +
                    "There are no numbers with these properties.");
        }else if (prop.contains("-square") && prop.contains("-sunny")) {
            System.out.println("The request contains mutually exclusive properties: [-SQUARE, -SUNNY] " +
                    "There are no numbers with these properties.");
        } else if (prop.contains("-odd") && prop.contains("-even")) {
            System.out.println("The request contains mutually exclusive properties: [-EVEN, -ODD] " +
                    "There are no numbers with these properties.");
        } else if (prop.contains("-duck") && prop.contains("-spy")) {
            System.out.println("The request contains mutually exclusive properties: [-DUCK, -SPY] " +
                    "There are no numbers with these properties.");
        } else if (prop.contains("-happy") && prop.contains("-sad")){
            System.out.println("The request contains mutually exclusive properties: [-HAPPY, -SAD] " +
                    "There are no numbers with these properties.");
        } else {
            numberSearchSecProp(inputArray);
        }
        System.out.println();
        printing();
    }
    private  void numberSearchSecProp (String[] input) {
        BigInteger number = new BigInteger(input[0]);
        int count = Integer.parseInt(input[1]);
        int i = 0;
        while (i < count) {
            for (int j = 2; j < input.length; j++) {
                if (input[j].substring(0, 1).contains("-")) {
                    if (checking(input[j].substring(1), number)) {
                        break;
                    }
                } else {
                    if (!checking(input[j], number)) {
                        break;
                    }
                }

                if (j == input.length - 1) {
                    series(String.valueOf(number), 1);
                    i++;
                }
            }
            number = number.add(BigInteger.ONE);
        }
        printing();
    }
}
