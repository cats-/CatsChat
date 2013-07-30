package cats.chat.server.handler;

import cats.chat.core.connection.data.type.impl.AddProfileData;
import cats.chat.core.connection.data.type.impl.AddUserData;
import cats.chat.core.connection.data.type.impl.PopupMessage;
import cats.chat.core.connection.event.DataEvent;
import cats.chat.core.profile.Profile;
import cats.chat.server.Server;
import cats.chat.server.io.ServerIO;

/**
 * CatsChat
 * Josh
 * 23/07/13
 * 2:16 PM
 */
public class AddUserHandler extends AbstractHandler<AddUserData>{

    public void handle(final DataEvent e, final AddUserData data){
        final Profile target = Server.instance().profiles().profile(data.add());
        if(target == null){
            e.reply(new PopupMessage("That user does not exist"));
            return;
        }
        final Profile from = Server.instance().profiles().profile(data.user());
        if(from.friends().profile(target.user().get()) != null){
            e.reply(new PopupMessage("That user is already on your friends list"));
            return;
        }
        if(from.equals(target)){
            e.reply(new PopupMessage("You are not allowed to add yourself"));
            return;
        }
        from.friends().add(target.createSafe());
        target.friends().add(from.createSafe());
        e.reply(new AddProfileData(target.createSafe()));
        if(target.isOnline())
            target.connection.send(new AddProfileData(from.createSafe()));
        ServerIO.save();
    }
}
