package cats.chat.server.handler;

import cats.chat.core.connection.data.type.impl.ChangeMoodData;
import cats.chat.core.connection.event.DataEvent;
import cats.chat.core.profile.Profile;
import cats.chat.server.Server;

/**
 * CatsChat
 * Josh
 * 23/07/13
 * 2:54 PM
 */
public class ChangeMoodHandler extends AbstractHandler<ChangeMoodData>{

    public void handle(final DataEvent e, final ChangeMoodData data){
        final Profile profile = Server.instance().profiles().profile(data.user());
        profile.mood().set(data.mood());
    }
}
