package AP;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String apiKey = "YOUR_API_KEY_HERE";
        Infrastructure infrastructure = new Infrastructure(apiKey);

        infrastructure.displayNewsList();
    }
}