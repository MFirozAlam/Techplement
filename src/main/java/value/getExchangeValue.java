package value;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class getExchangeValue {
    private static final String API_KEY = "znSzjUGRNjmTgL45XelpethTyk86foHP";
    private static final String API_URL = "https://api.apilayer.com/exchangerates_data/latest";

    private static Map<String, BigDecimal> favoriteCurrencies = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Options:");
            System.out.println("1. Add Favorite Currency");
            System.out.println("2. View Favorite Currencies");
            System.out.println("3. Convert Currency");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addFavoriteCurrency(sc);
                    break;
                case 2:
                    viewFavoriteCurrencies();
                    break;
                case 3:
                    convertCurrency(sc);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    private static void addFavoriteCurrency(Scanner sc) {
        System.out.print("Enter the currency code to add to favorites: ");
        String currencyCode = sc.nextLine().toUpperCase();
        favoriteCurrencies.put(currencyCode, BigDecimal.ZERO); // Default value, actual rates will be fetched when converting
        System.out.println(currencyCode + " added to favorites.");
    }

    private static void viewFavoriteCurrencies() {
        System.out.println("Favorite Currencies:");
        for (String currency : favoriteCurrencies.keySet()) {
            System.out.println(currency);
        }
    }

    private static void convertCurrency(Scanner sc) {
        System.out.print("Enter currency to convert from: ");
        String convertFrom = sc.nextLine().toUpperCase();
        System.out.print("Enter currency to convert to: ");
        String convertTo = sc.nextLine().toUpperCase();
        System.out.print("Enter quantity to convert: ");
        BigDecimal quantity = sc.nextBigDecimal();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL + "?symbols=" + convertTo.toUpperCase() + "&base=" + convertFrom.toUpperCase())
                .addHeader("apikey", API_KEY)
                .method("GET", null)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String stringResponse = response.body().string();
            System.out.println("Response: " + stringResponse); // Print the JSON response for debugging

            // Parsing the JSON response and performing currency conversion...
            // (You can use the same logic as in the previous example)
            // Remember to handle exceptions appropriately.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
