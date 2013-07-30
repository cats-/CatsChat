package cats.chat.server.handler;

import cats.chat.core.connection.data.type.impl.ChangeStatusData;
import cats.chat.core.connection.event.DataEvent;
import cats.chat.core.profile.Profile;
import cats.chat.server.Server;

/**
 * CatsChat
 * Josh
 * 23/07/13
 * 2:53 PM
 */
public class ChangeStatusHandler extends AbstractHandler<ChangeStatusData>{

    public void handle(final DataEvent e, final ChangeStatusData data){
        final Profile profile = Server.instance().profiles().profile(data.user());
        profile.status().set(data.status());
    }
}
