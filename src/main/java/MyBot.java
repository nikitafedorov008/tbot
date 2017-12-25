import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;


import javax.xml.bind.annotation.XmlElementDecl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyBot extends TelegramLongPollingBot {

    ArrayList<String> users = new ArrayList<>();
    HashMap<String, String> answer = new HashMap<>();
    boolean AskMode = false;

    HashMap<String, String> match = new HashMap<>();
    boolean GameMode = false;

    HashMap<String, String> addComent = new HashMap<>();
    boolean addMode = false;

    int dollars = 0;

    public MyBot() {
        answer.put("1", "Привет.");
        answer.put("2", "Как твои дела?.");
        answer.put("3", "Что делаешь?");
        answer.put("4", "Как ты относишься к коммунизму?");
        answer.put("5", "А при отражении в жеркале свет теряет свои энергитические свойства?");
        answer.put("6", "Любите просматривать свеже испечёные мемчики?");
        answer.put("7", "Если в кофе коффеин, то в какао ... ?");
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            long chatId = update.getMessage().getChatId();
            String message = update.getMessage().getText();
            int messageId = update.getMessage().getMessageId();



            if (AskMode) {
                String question = answer.get(message);
                sendMessage("" + question, chatId);
                AskMode = false;
            } else {
                switch (message) {
                    case "/getQuestion":
                        getQuestion(chatId);
                        sendMessage("Что тебя интересует?", chatId);
                        AskMode = true;
                        break;
                    case "/addQuestion":
                        addAsk(message, chatId);
                        sendMessage("Скажи, пожалуйста, номер, а затем сам вопрос", chatId);
                        AskMode = true;
                        break;
                    default:
                        sendMessage(message, chatId, messageId);
                }



            }

            if (GameMode) {
                String matchGame = match.get(message);
                sendMessage("" + matchGame, chatId);
                GameMode = false;
            } else {switch (message) {
                case "/getGame":
                    getQuestion(chatId);
                    sendMessage("Что тебя интересует?", chatId);
                    GameMode = true;
                    break;
                case "/игра":
                    addAsk(message, chatId);
                    sendMessage("Хочешь сыграть, ну ладно. Приступим", chatId);
                    GameMode = true;
                    break;
                case "":
                    
                default:
                    sendMessage(message, chatId, messageId);

            }

            }

        }
    }



    @Override
    public String getBotUsername() {
        return "StringSadPepeFrogBot";
    }

    @Override
    public String getBotToken() {
        return "455170288:AAFjQsm1SQJXA9aZA663ckeQ1J524M1xguU";
    }


    private void addAsk(String text, long charId) {
        String[] кусочки = text.split(" ");
        answer.put(кусочки[0], кусочки[1]);
        sendMessage("Вопрос добавлен, Спасибо.)", charId);
    }

    private void getQuestion(long charId) {
//
    }



    //===============================================================================================================================

    private void sendMessage(String text, long chatId) {
        SendMessage sendMessage = new SendMessage()
                .setText(text)
                .setChatId(chatId);


        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    private void sendMessage(String text, long chatId, int messageId) {
        SendMessage sendMessage = new SendMessage()
                .setText(text)
                .setChatId(chatId)
                .setReplyToMessageId(messageId);


        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendPhoto(long chatId, String photo) {
        // создаём запрос
        SendPhoto request = new SendPhoto();
        // устанавливаем идентификатор чата, в который нужно отправить фоточку
        request.setChatId(chatId);
        // устанавливаем фотографию
        request.setPhoto(photo);

        // отправляем запрос
        try { // ALT + ENTER !!!!!!!!!!!!!!!!!
            sendPhoto(request);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
