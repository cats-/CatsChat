package cats.chat.server.handler;

import cats.chat.core.connection.data.type.impl.ChangeNameData;
import cats.chat.core.connection.data.type.impl.PopupMessage;
import cats.chat.core.connection.event.DataEvent;
import cats.chat.core.profile.Profile;
import cats.chat.server.Server;
import cats.chat.server.util.CredentialsValidator;

/**
 * CatsChat
 * Josh
 * 23/07/13
 * 2:54 PM
 */
public class ChangeNameHandler extends AbstractHandler<ChangeNameData>{

    public void handle(final DataEvent e, final ChangeNameData data){
        final String msg = CredentialsValidator.validate(CredentialsValidator.NAME, data.name());
        if(msg != null){
            e.reply(new PopupMessage(msg));
            return;
        }
        final Profile profile = Server.instance().profiles().profile(data.user());
        profile.name().set(data.name());
    }
}
