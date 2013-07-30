package cats.chat.client.handler;

import cats.chat.client.Client;
import cats.chat.core.connection.data.type.impl.AddProfileData;
import cats.chat.core.connection.event.DataEvent;

/**
 * CatsChat
 * Josh
 * 25/07/13
 * 11:44 PM
 */
public class AddProfileHandler extends AbstractHandler<AddProfileData>{

    public void handle(final DataEvent e, final AddProfileData data){
        Client.profile().friends().add(data.profile());
        Client.messenger().friendsList().add(data.profile());
    }
}
