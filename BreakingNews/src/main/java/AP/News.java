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
