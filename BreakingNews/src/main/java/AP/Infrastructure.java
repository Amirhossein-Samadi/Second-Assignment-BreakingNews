package AP;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.time.LocalDate;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Scanner;



public class Infrastructure {

    private final String URL;
    private final String APIKEY;
    private final String JSONRESULT;
    private ArrayList<News> newsList; // TODO: Create the News class

    public class News{

        private String title;
        private String description;
        private String sourceName;
        private String author;
        private String url;
        private String publishedAt;

        public News(String title, String description, String sourceName, String author, String url, String publishedAt) {

            this.title = title;
            this.description = description;
            this.sourceName = sourceName;
            this.author = author;
            this.url = url;
            this.publishedAt = publishedAt;
        }

        public void displayNews() {
            System.out.println("Title: " + title);
            System.out.println("Description: " + description);
            System.out.println("Source: " + sourceName);
            System.out.println("Author: " + author);
            System.out.println("URL: " + url);
            System.out.println("Published At: " + publishedAt);
        }

        public String getTitle() {
            return title;
        }
    }


    public Infrastructure(String APIKEY) {
        this.APIKEY = APIKEY;
        this.URL = "https://newsapi.org/v2/everything?q=tesla&from=" + LocalDate.now().minusDays(1) + "&sortBy=publishedAt&apiKey=";
        this.JSONRESULT = getInformation();
    }

    public ArrayList<News> getNewsList() {
        return newsList;
    }

    private String getInformation() {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL + APIKEY))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                throw new IOException("HTTP error code: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("!!Exception : " + e.getMessage());
        }
        return null;
    }

    private void parseInformation() {
        // TODO: Get the first 20 news from the articles array of the json result
        //  and parse the information of each on of them to be mapped to News class
        //  finally add them to newsList in this class to display them in the output

        try {
            newsList = new ArrayList<>();
            JsonObject jsonObject = JsonParser.parseString(JSONRESULT).getAsJsonObject();
            JsonArray articles = jsonObject.getAsJsonArray("articles");

            for (int i = 0; i < articles.size() && i < 20; i++) {
                JsonObject article = articles.get(i).getAsJsonObject();
                News news = new News(
                        article.get("title").getAsString(),
                        article.get("description").getAsString(),
                        article.get("source").getAsJsonObject().get("name").getAsString(),
                        article.has("author") ? article.get("author").getAsString() : "Unknown",
                        article.get("url").getAsString(),
                        article.get("publishedAt").getAsString()
                );
                newsList.add(news);
            }
        } catch (Exception e) {
            System.out.println("Exception while parsing: " + e.getMessage());
        }
    }

    public void displayNewsList() {
        // TODO: Display titles of the news you got from api
        //  and print them in a way that user can choose one
        //  to see the full information of the news

        while (true)
        {
            System.out.println("News Headlines:");
            for (int i = 0; i < newsList.size(); i++) {
                System.out.println((i + 1) + ". " + newsList.get(i).getTitle());
            }

            System.out.print("Enter the number of the article you want to read more about, or 0 to exit: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (choice == 0) {
                break;
            } else if (choice > 0 && choice <= newsList.size()) {
                newsList.get(choice - 1).displayNews();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}
