package AP;

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
        System.out.println("Title: " + this.title);
        System.out.println("Description: " + this.description);
        System.out.println("Source: " + this.sourceName);
        System.out.println("Author: " + this.author);
        System.out.println("URL: " + this.url);
        System.out.println("Published At: " + this.publishedAt);
    }

    public String getTitle() {
        return title;
    }
}
