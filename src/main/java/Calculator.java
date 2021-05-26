import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.System.*;

/**
 * Calculator class. Prints messages, gets user inputs and prints results.
 * @author Dawid Zaorski
 */
class Calculator {
    static final String PATH = "src/main/resources/eurofxref-daily.xml";
    static HashMap<String, Double> currencyRates;
    static double amount;
    static String currency;
    static Scanner scanner = new Scanner(in).useLocale(Locale.GERMANY);

    static {
        try {
            currencyRates = XMLParser.getCurrencyRates(PATH);
        } catch (IOException ioe) {
            err.println(ioe.getMessage());
        }
    }

    public static void main(String[] args) {
        out.println("CURRENCY CALCULATOR");

        do {
            out.print("Enter amount in EUR: ");

            while(!scanner.hasNextDouble()) {
                scanner.next();
                out.println("Enter a valid number! Use decimal comma.");
            }
            amount = scanner.nextDouble();
            out.print("Enter currency: ");
            currency = scanner.next().trim().toUpperCase();

            while (!isCurrencyPresent(currency)) {
                out.println("Currency not present");
                currency = scanner.nextLine().trim().toUpperCase();
            }
            out.println("Amount in " + currency + ": " + calculate(currency, amount));
            out.print("Enter 'X' to exit: ");

        } while (!scanner.next().equalsIgnoreCase("X"));
    }

    private static double calculate(String currency, double amount) {
        return currencyRates.get(currency) * amount;
    }

    private static boolean isCurrencyPresent(String input) {
        return currencyRates.containsKey(input.toUpperCase().trim());
    }
}
