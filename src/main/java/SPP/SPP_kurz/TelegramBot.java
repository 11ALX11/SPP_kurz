package SPP.SPP_kurz;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot
{
    private final BotConfig botConfig;
    private final static String welcomeString =
            "Привет, %1\n" +
            "Я помогу извлечь звуковую дорожку " +
            "с любого видео на ютубе. Вставь ссылку:";
    private final static String fileString =
            "Вот твоя музыка:";

    @Override
    public String getBotUsername()
    {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken()
    {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        if (update.hasMessage() && update.getMessage().hasText())
        {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText)
            {
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                default:
                    // ToDo check link? in messageText
                    // ToDo download or cash
                    // ToDo progressbar
                    // ToDo put file
                    sendFileAndMessage(chatId, fileString, "");
            }
        }

    }

    private void startCommandReceived(Long chatId, String name)
    {
        String answer = welcomeString.replace("%1", name);
        sendMessage(chatId, answer);
    }

    private void sendMessage(Long chatId, String textToSend)
    {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try
        {
            execute(sendMessage);
        } catch (TelegramApiException e)
        {
            // ToDo Logging
        }
    }

    private void sendFileAndMessage(Long chatId, String textToSend, String file)
    {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);

        // ToDo put file

        try
        {
            execute(sendMessage);
        } catch (TelegramApiException e)
        {
            // ToDo Logging
        }
    }
}
