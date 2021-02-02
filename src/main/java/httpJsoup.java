import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class httpJsoup {

    public static void main(String[] args) {
        String url = "https://t.bilibili.com/486879511316314358";
        String title = null;

        try {
            Document doc = Jsoup.connect("https://t.bilibili.com/486879511316314358").get();
            title = doc.title();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(title);
    }
}
