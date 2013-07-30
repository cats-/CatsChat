package cats.chat.client.handler;

import cats.chat.client.Client;
import cats.chat.core.connection.data.type.impl.ChangePicData;
import cats.chat.core.connection.event.DataEvent;

/**
 * CatsChat
 * Josh
 * 25/07/13
 * 11:22 PM
 */
public class ChangePicHandler extends AbstractHandler<ChangePicData>{

    public void handle(final DataEvent e, final ChangePicData data){
        if(Client.profile().equals(data.user()))
            Client.profile().pic().set(data.pic());
        else
            Client.profile().friends().profile(data.user()).pic().set(data.pic());
    }
}
