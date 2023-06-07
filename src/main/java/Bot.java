import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import spark.Spark;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramWebhookBot {

    public List<String> savedNews = new ArrayList<>();
    final String BOT_USERNAME = "faf201_vladimir_russu_bot";
    final String BOT_TOKEN = "6271790313:AAHASsLucN27bJkS63upyNkow9azm5isrYY";

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }


    private void start(Long id) {
        String greetingMessage = "Hello! I am a Telegram news bot. Proceed with the following commands: " + "\n" +
                "/start - to start the bot" + "\n" +
                "/latest_news_<topic> - to get links to the latest news on a specific topic" + "\n" +
                "/save_news_<topic> - to save a link to a specific news" + "\n" +
                "/saved_news - to get links of the saved news" + "\n" +
                "/navigate_<link> - to get partial content of a page and the link to that page";
        sendText(id, greetingMessage);
    }

    private void showLatestNews(Long id, Message msg) throws Exception {
        String command = msg.getText();
        String[] splitCommand = command.split("latest_news_");
        String topic = splitCommand[1];
        NewsCollection newsCollection = new NewsCollection();
        if(topic.equals("science")) {
            StringBuilder scienceNewsLinksCollected = new StringBuilder();
            for (int i=0; i<newsCollection.getScienceNewsLinks().size(); i++) {
                scienceNewsLinksCollected.append(newsCollection.getScienceNewsLinks().get(i)).append("\n");
            }
            String scienceNewsLinks = scienceNewsLinksCollected.toString();
            sendText(id, scienceNewsLinks);
        }
        if(topic.equals("technology")) {
            StringBuilder technologyNewsLinksCollected = new StringBuilder();
            for (int i=0; i<newsCollection.getTechnologyNewsLinks().size(); i++) {
                technologyNewsLinksCollected.append(newsCollection.getTechnologyNewsLinks().get(i)).append("\n");
            }
            String technologyNewsLinks = technologyNewsLinksCollected.toString();
            sendText(id, technologyNewsLinks);
        }
        if(topic.equals("politics")) {
            StringBuilder politicsNewsLinksCollected = new StringBuilder();
            for (int i=0; i<newsCollection.getPoliticsNewsLinks().size(); i++) {
                politicsNewsLinksCollected.append(newsCollection.getPoliticsNewsLinks().get(i)).append("\n");
            }
            String politicsNewsLinks = politicsNewsLinksCollected.toString();
            sendText(id, politicsNewsLinks);
        }
        if(topic.equals("art")) {
            StringBuilder artNewsLinksCollected = new StringBuilder();
            for (int i=0; i<newsCollection.getArtNewsLinks().size(); i++) {
                artNewsLinksCollected.append(newsCollection.getArtNewsLinks().get(i)).append("\n");
            }
            String artNewsLinks = artNewsLinksCollected.toString();
            sendText(id, artNewsLinks);
        }
        if(topic.equals("sport")) {
            StringBuilder sportNewsLinksCollected = new StringBuilder();
            for (int i=0; i<newsCollection.getSportNewsLinks().size(); i++) {
                sportNewsLinksCollected.append(newsCollection.getSportNewsLinks().get(i)).append("\n");
            }
            String sportNewsLinks = sportNewsLinksCollected.toString();
            sendText(id, sportNewsLinks);
        }
    }

    public void saveNews(Long id, Message msg) {
        String command = msg.getText();
        String[] splitCommand = command.split("save_news_");
        String link = splitCommand[1];
        savedNews.add(link);
        String acknowledgement = "News successfully saved!";
        sendText(id, acknowledgement);
    }

    public void showSavedNews(Long id) {
        StringBuilder savedNewsLinksCollected = new StringBuilder();
        for (int i=0; i<savedNews.size(); i++) {
            savedNewsLinksCollected.append(savedNews.get(i)).append("\n");
        }
        String savedNewsLinks = savedNewsLinksCollected.toString();
        sendText(id, savedNewsLinks);
    }

    public void navigateToLink(Long id, Message msg) throws Exception {
        String command = msg.getText();
        String[] splitCommand = command.split("navigate_");
        String link = splitCommand[1];
        URLReader urlReader = new URLReader();
        String content = urlReader.readURLContent(link);
        String partialContent = content.substring(0, 700);
        String messageContent = partialContent + "..." + "\n" + link;
        sendText(id, messageContent);
    }

    @Override
    public String getBotPath() {
        return "https://127.0.0.1:8080";
    }


    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(what).build();
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        var msg = update.getMessage();
        var user = msg.getFrom();
        var id = user.getId();
        var txt = msg.getText();
        if(msg.isCommand()) {
            if(txt.equals("/start")) {
                start(id);
            }
            if(txt.contains("/latest_news_")) {
                try {
                    showLatestNews(id, msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (txt.contains("/save_news_")) {
                saveNews(id, msg);
            }
            if(txt.equals("/saved_news")) {
                showSavedNews(id);
            }
            if(txt.contains("/navigate_")) {
                try {
                    navigateToLink(id, msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void main(String[] args) throws TelegramApiException {
        Bot bot = new Bot();
        SetWebhook request = new SetWebhook();
        String webhookUrl = "https://127.0.0.1:8080";
        request.setUrl(webhookUrl);
        try {
            bot.execute(request);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}