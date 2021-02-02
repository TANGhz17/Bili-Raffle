import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class httpJsoup {

    public static void main(String[] args) {
        String url = "https://t.bilibili.com/486879511316314358";
        String title = null;
        int strings_length = 0;
        try {
            Document doc = Jsoup.connect("https://t.bilibili.com/486879511316314358")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.96 Safari/537.36 Edg/88.0.705.56")
                    .timeout(3000)
                    .get();
            String element=doc.select("a.name ").text();
            String[] strings=element.split(" ");
            title = String.valueOf(doc);
            strings_length = strings.length;
            // title = doc.title();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(title);
    }
}
