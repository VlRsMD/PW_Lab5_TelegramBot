import java.util.ArrayList;
import java.util.List;

public class NewsCollection {
    public List<String> getScienceNewsLinks() {
        List<String> scienceNewsLinks = new ArrayList<>();
        scienceNewsLinks.add("https://www.sciencenews.org");
        scienceNewsLinks.add("https://www.sciencedaily.com/news/top/science/");
        scienceNewsLinks.add("https://www.sciencedaily.com/breaking/");
        scienceNewsLinks.add("https://www.discovery.com/science/all-science-articles");
        scienceNewsLinks.add("https://science.nasa.gov/science-news/");
        return scienceNewsLinks;
    }
    public List<String> getTechnologyNewsLinks() {
        List<String> scienceNewsLinks = new ArrayList<>();
        scienceNewsLinks.add("https://www.reuters.com/technology/");
        scienceNewsLinks.add("https://www.theverge.com/tech");
        scienceNewsLinks.add("https://www.nytimes.com/section/technology");
        scienceNewsLinks.add("https://scitechdaily.com/news/technology/");
        scienceNewsLinks.add("https://www.cnbc.com/technology/");
        return scienceNewsLinks;
    }
    public List<String> getPoliticsNewsLinks() {
        List<String> scienceNewsLinks = new ArrayList<>();
        scienceNewsLinks.add("https://edition.cnn.com/politics");
        scienceNewsLinks.add("https://www.politico.com/politics");
        scienceNewsLinks.add("https://www.theguardian.com/politics");
        scienceNewsLinks.add("https://www.bloomberg.com/politics");
        scienceNewsLinks.add("https://abcnews.go.com/politics");
        return scienceNewsLinks;
    }
    public List<String> getArtNewsLinks() {
        List<String> scienceNewsLinks = new ArrayList<>();
        scienceNewsLinks.add("https://www.theartnewspaper.com/");
        scienceNewsLinks.add("https://edition.cnn.com/style/arts");
        scienceNewsLinks.add("https://www.nytimes.com/topic/subject/art");
        scienceNewsLinks.add("https://www.theguardian.com/artanddesign/art");
        scienceNewsLinks.add("https://www.independent.co.uk/topic/art");
        return scienceNewsLinks;
    }
    public List<String> getSportNewsLinks() {
        List<String> scienceNewsLinks = new ArrayList<>();
        scienceNewsLinks.add("https://www.bbc.co.uk/sport");
        scienceNewsLinks.add("https://www.skysports.com/");
        scienceNewsLinks.add("https://edition.cnn.com/sport");
        scienceNewsLinks.add("https://sports.ndtv.com/");
        scienceNewsLinks.add("https://www.euronews.com/news/sport");
        return scienceNewsLinks;
    }
}
