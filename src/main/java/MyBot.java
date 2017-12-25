import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyBot extends TelegramLongPollingBot {

    ArrayList<String> users = new ArrayList<>();
    HashMap<String, String> answer = new HashMap<>();
    boolean AskMode = false;

    public MyBot() {
        answer.put("1", "Привет.");
        answer.put("2", "Как твои дела?.");
        answer.put("3", "Что делаешь?");
        answer.put("4", "Как ты относишься к коммунизму?");
        answer.put("5", "А при отражении в жеркале свет теряет свои энергитические свойства?");
        answer.put("6", "Любите просматривать свежже испечёные мемчики?");
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
        }
    }



    @Override
    public String getBotUsername() {
        return "StringSadPepeFrogBot";
    }

    @Override
    public String getBotToken() {
        return "455170288:AAFjQsm1SQjXA9Aza663ckeQ1J524M1xguU";
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
}
