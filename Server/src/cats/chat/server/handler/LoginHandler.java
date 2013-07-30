package cats.chat.server.handler;

import cats.chat.core.connection.data.type.impl.AddProfileData;
import cats.chat.core.connection.data.type.impl.AssignProfileData;
import cats.chat.core.connection.data.type.impl.LoginData;
import cats.chat.core.connection.data.type.impl.PopupMessage;
import cats.chat.core.connection.event.DataEvent;
import cats.chat.core.profile.Profile;
import cats.chat.core.profile.SafeProfile;
import cats.chat.server.Server;
import cats.chat.server.io.ServerIO;

/**
 * CatsChat
 * Josh
 * 23/07/13
 * 10:08 AM
 */
public class LoginHandler extends AbstractHandler<LoginData>{

    public void handle(final DataEvent e, final LoginData data){
        final Profile profile = Server.instance().profiles().profile(data.user());
        if(profile == null){
            e.reply(new PopupMessage("That user does not exist"));
            return;
        }
        if(!profile.pass().get().equals(data.pass())){
            e.reply(new PopupMessage("Passwords do not match"));
            return;
        }
        profile.connection = e.connection();
        final SafeProfile safe = profile.createSafe();
        safe.status().privateSet(STATUS_ONLINE);
        profile.convoMap().copyTo(safe.convoMap());
        e.reply(new AssignProfileData(safe));
        profile.queue().forEach(e::reply);
        profile.queue().clear();
        profile.friends().forEach(f -> f.privateUpdate(Server.instance().profiles().profile(f.user().get())));
        profile.friends().forEach(f -> e.reply(new AddProfileData(f)));
        profile.status().set(STATUS_ONLINE);
        ServerIO.save();
    }
}
