package cats.chat.client.handler;

import cats.chat.client.Client;
import cats.chat.core.connection.data.type.impl.ChangeStatusData;
import cats.chat.core.connection.event.DataEvent;

/**
 * CatsChat
 * Josh
 * 25/07/13
 * 11:18 PM
 */
public class ChangeStatusHandler extends AbstractHandler<ChangeStatusData>{

    public void handle(final DataEvent e, final ChangeStatusData data){
        if(Client.profile().equals(data.user()))
            Client.profile().status().set(data.status());
        else
            Client.profile().friends().profile(data.user()).status().set(data.status());
    }
}
