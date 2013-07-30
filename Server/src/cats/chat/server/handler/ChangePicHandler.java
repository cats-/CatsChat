package cats.chat.server.handler;

import cats.chat.core.connection.data.type.impl.ChangePicData;
import cats.chat.core.connection.event.DataEvent;
import cats.chat.core.profile.Profile;
import cats.chat.server.Server;

/**
 * CatsChat
 * Josh
 * 23/07/13
 * 2:51 PM
 */
public class ChangePicHandler extends AbstractHandler<ChangePicData>{

    public void handle(final DataEvent e, final ChangePicData data){
        final Profile profile = Server.instance().profiles().profile(data.user());
        profile.pic().set(data.pic());
    }
}
