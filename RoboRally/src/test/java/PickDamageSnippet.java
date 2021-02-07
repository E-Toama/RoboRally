import utilities.MessageHandler;
import utilities.messages.MapSelected;
import utilities.messages.Message;
import utilities.messages.PickDamage;
import utilities.messages.SelectDamage;
import utilities.messages.SelectMap;

import java.util.Random;

public class PickDamageSnippet {

    private void handlePickDamage(Message incomingMessage) {

        if (incomingMessage.getMessageBody() instanceof PickDamage) {

            PickDamage receivedMessage = (PickDamage) incomingMessage.getMessageBody();
            int count = receivedMessage.getCount();

            String[] damageCardsToChooseFrom = {"Virus", "Worm", "Trojan"};
            String[] chosenDamageCards = new String[count];
            Random random = new Random();

            for (int i = 0; i < count; i++) {
                chosenDamageCards[i] = damageCardsToChooseFrom[random.nextInt(3)];
            }

            //Todo: Uncomment the next two lines for Transmission
            /*String chosenCardsMessage = messageHandler.buildMessage("SelectDamage", new SelectDamage(chosenDamageCards));
            sendJson(chosenCardsMessage);*/

        }

    }

}
