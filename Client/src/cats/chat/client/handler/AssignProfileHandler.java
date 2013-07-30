package cats.chat.client.handler;

import cats.chat.client.Client;
import cats.chat.core.connection.data.type.impl.AssignProfileData;
import cats.chat.core.connection.event.DataEvent;

/**
 * CatsChat
 * Josh
 * 25/07/13
 * 9:08 PM
 */
public class AssignProfileHandler extends AbstractHandler<AssignProfileData>{

    public void handle(final DataEvent e, final AssignProfileData data){
        Client.profile().update(data.profile());
        Client.loggedIn = true;
        Client.startMessenger();
    }
}
