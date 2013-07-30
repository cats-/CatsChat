package cats.chat.client.handler;

import cats.chat.client.Client;
import cats.chat.core.connection.data.type.impl.ChangeMoodData;
import cats.chat.core.connection.event.DataEvent;

/**
 * CatsChat
 * Josh
 * 25/07/13
 * 11:22 PM
 */
public class ChangeMoodHandler extends AbstractHandler<ChangeMoodData>{

    public void handle(final DataEvent e, final ChangeMoodData data){
        if(Client.profile().equals(data.user()))
            Client.profile().mood().set(data.mood());
        else
            Client.profile().friends().profile(data.user()).mood().set(data.mood());
    }
}
