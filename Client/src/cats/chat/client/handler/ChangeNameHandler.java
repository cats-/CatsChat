package cats.chat.client.handler;

import cats.chat.client.Client;
import cats.chat.core.connection.data.type.impl.ChangeNameData;
import cats.chat.core.connection.event.DataEvent;

/**
 * CatsChat
 * Josh
 * 25/07/13
 * 11:32 PM
 */
public class ChangeNameHandler extends AbstractHandler<ChangeNameData>{

    public void handle(final DataEvent e, final ChangeNameData data){
        if(Client.profile().equals(data.user()))
            Client.profile().name().set(data.name());
        else
            Client.profile().friends().profile(data.user()).name().set(data.name());
    }
}
