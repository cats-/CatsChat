package cats.chat.server.handler;

import cats.chat.core.connection.data.type.impl.PopupMessage;
import cats.chat.core.connection.data.type.impl.RemoveProfileData;
import cats.chat.core.connection.data.type.impl.RemoveUserData;
import cats.chat.core.connection.event.DataEvent;
import cats.chat.core.profile.Profile;
import cats.chat.server.Server;
import cats.chat.server.io.ServerIO;

/**
 * CatsChat
 * Josh
 * 23/07/13
 * 2:38 PM
 */
public class RemoveUserHandler extends AbstractHandler<RemoveUserData>{

    public void handle(final DataEvent e, final RemoveUserData data){
        final Profile target = Server.instance().profiles().profile(data.remove());
        if(target == null){
            e.reply(new PopupMessage("That user does not exist"));
            return;
        }
        final Profile from = Server.instance().profiles().profile(data.user());
        from.friends().remove(target);
        target.friends().remove(from);
        e.reply(new RemoveProfileData(target.createSafe()));
        if(target.isOnline())
            target.connection.send(new RemoveProfileData(from.createSafe()));
        ServerIO.save();
    }
}
