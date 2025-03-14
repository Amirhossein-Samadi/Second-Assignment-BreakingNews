package AP;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String apiKey = "cc18cca7296e4f89b76a9c968909d7e2";
        Infrastructure infrastructure = new Infrastructure(apiKey);

        infrastructure.displayNewsList();
    }
}