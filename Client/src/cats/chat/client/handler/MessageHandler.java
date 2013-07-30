package cats.chat.client.handler;

import cats.chat.client.Client;
import cats.chat.core.connection.data.type.impl.Message;
import cats.chat.core.connection.event.DataEvent;

/**
 * CatsChat
 * Josh
 * 26/07/13
 * 10:42 AM
 */
public class MessageHandler extends AbstractHandler<Message>{

    public void handle(final DataEvent e, final Message message){
        final String other = message.other(Client.user());
        Client.profile().convoMap().add(other, message);
        if(Client.messenger().friendsList().getSelected() != null)
            if(Client.messenger().friendsList().getSelected().equals(other))
                Client.messenger().convoArea().add(message);
        if(Client.profile().friends().contains(other))
            Client.messenger().friendsList().select(other);
    }
}
