package cats.chat.client.handler;

import cats.chat.client.Client;
import cats.chat.core.connection.data.type.impl.RemoveProfileData;
import cats.chat.core.connection.event.DataEvent;

/**
 * CatsChat
 * Josh
 * 25/07/13
 * 11:46 PM
 */
public class RemoveProfileHandler extends AbstractHandler<RemoveProfileData>{

    public void handle(final DataEvent e, final RemoveProfileData data){
        Client.profile().friends().remove(data.profile());
        Client.messenger().friendsList().remove(data.profile());
    }
}
