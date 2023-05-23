import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

class URLReader {
    public String readURLContent(String url) throws Exception {
        Connection conn = Jsoup.connect(url);
        Document doc = conn.get();
        return doc.body().text();
    }
}
