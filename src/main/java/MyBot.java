import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class MyBot extends TelegramLongPollingBot {

    //ArrayList<String> users = new ArrayList<>();
    boolean codeMode = false;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage().hasText()){
            String text = update.getMessage().getText();
            long charId = update.getMessage().getChatId();
            sendText(text, charId);
            if (text.equals("/newGame")) {
                newGame();
                user.bitcoins +=text.length();
                nextDay(charId);
                codeMode = false;
            }else if (text.equals("/code")) {
                codeMode = true;
                sendText("Ваш код на сегодня", charId);
            }
        }
    }//else if (upadte.getMessege().hasPhoto()){
    // String photo = update.getMessege().getPhoto().get(0).FileId();
    //    long charId = update.getMessege().getCharId();
    //    sendPhoto("http://static4.businessinsider.com/image/55ba87b8dd0895c81c8b4581-480/pepe-the-frog.png", charId);
    //}

    @Override
    public String getBotUsername() {
        return "StringSadPepeFrogBot";
    }



    @Override
    public String getBotToken() {
        return "455170288:AAFjQsm1SQJXA9aZA663ckeQ1J524M1xguU" ;
    }

    private void sendText(String text, long charId) {
        SendMessage resquest = new SendMessage();
        resquest.setText(text);
        resquest.setChatId(charId);
    }

   User user;
    int day;

    private void newGame() {
        day = 0;
        user = new User();
    }

    private void nextDay(long charId) {
        day++;
        sendText("День" + day, charId);
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


      //  try {
        //    execute(resquest);
        //} catch (TelegramApiException e) {
          //  e.printStackTrace();
        //}


    }

}
