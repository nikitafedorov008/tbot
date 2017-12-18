import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.util.Date;

public class Main {

   // public void printI() {
     //   Date currentTime = new Date();
       // int hours = currentTime.getHours();
        //int minutes = currentTime.getMinutes();
        //int seconds = currentTime.getSeconds();
        //if (minutes < 16)
          //  System.out.println("На момент запуска программы, вечер: " + hours + " часов " + minutes + " минут " + seconds + " секунд " + "\nЕсли хотите узнать время на данный момент, перезапустите программу");
        //else if (minutes > 16)
          //  System.out.println("На момент запуска программы, день: " + hours + " часов " + minutes + " минут " + seconds + " секунд " + "\nЕсли хотите узнать время на данный момент, перезапустите программу");
    //};

public static void main (String[] args) {

    ApiContextInitializer.init();

    TelegramBotsApi api = new TelegramBotsApi();

    MyBot bot = new MyBot();

    try {
        api.registerBot(bot);
    } catch (TelegramApiRequestException e) {
        e.printStackTrace();
    }
}

}
