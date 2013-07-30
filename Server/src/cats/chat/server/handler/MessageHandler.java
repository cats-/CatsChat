package cats.chat.server.handler;

import cats.chat.core.connection.data.type.impl.Message;
import cats.chat.core.connection.data.type.impl.PopupMessage;
import cats.chat.core.connection.event.DataEvent;
import cats.chat.core.profile.Profile;
import cats.chat.server.Server;
import cats.chat.server.io.ServerIO;

/**
 * CatsChat
 * Josh
 * 23/07/13
 * 9:32 AM
 */
public class MessageHandler extends AbstractHandler<Message>{

    public void handle(final DataEvent e, final Message message){
        final Profile from = Server.instance().profiles().profile(message.from());
        if(from.friends().profile(message.to()) == null){
            e.reply(new PopupMessage("This person is not on your friends list"));
            return;
        }
        e.reply(message);
        from.convoMap().add(message.to(), message);
        final Profile to = Server.instance().profiles().profile(message.to());
        to.convoMap().add(message.from(), message);
        if(to.isOnline())
            to.connection.send(message);
        else
            to.queue().add(message);
        ServerIO.save();
    }
}
