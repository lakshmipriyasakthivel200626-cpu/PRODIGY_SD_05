import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;

public class WebScraper {
    public static void main(String[] args) {
        try {
            String url = "https://books.toscrape.com/";

            Document doc = Jsoup.connect(url).get();

            Elements books = doc.select("article.product_pod");

            FileWriter writer = new FileWriter("products.csv");
            writer.write("Name,Price,Rating\n");

            for (Element book : books) {
                String name = book.select("h3 a").attr("title");
                String price = book.select(".price_color").text();
                String rating = book.select("p.star-rating").attr("class").replace("star-rating ", "");

                writer.write(name + "," + price + "," + rating + "\n");
            }

            writer.close();
            System.out.println("Data saved to products.csv");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}