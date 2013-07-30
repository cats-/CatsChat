package cats.chat.server.handler;

import cats.chat.core.connection.data.Data;
import cats.chat.core.connection.data.type.impl.ChangeMoodData;
import cats.chat.core.connection.data.type.impl.ChangeNameData;
import cats.chat.core.connection.data.type.impl.ChangePicData;
import cats.chat.core.connection.data.type.impl.ChangeStatusData;
import cats.chat.core.connection.data.type.impl.PopupMessage;
import cats.chat.core.connection.data.type.impl.RegisterData;
import cats.chat.core.connection.event.DataEvent;
import cats.chat.core.profile.Profile;
import cats.chat.server.Server;
import cats.chat.server.io.ServerIO;
import cats.chat.server.util.CredentialsValidator;

/**
 * CatsChat
 * Josh
 * 23/07/13
 * 9:35 AM
 */
public class RegisterHandler extends AbstractHandler<RegisterData>{

    public void handle(final DataEvent e, final RegisterData data){
        String message = CredentialsValidator.validate(CredentialsValidator.USER, data.user());
        if(message != null){
            e.reply(new PopupMessage(message));
            return;
        }
        message = CredentialsValidator.validate(CredentialsValidator.PASS, data.pass());
        if(message != null){
            e.reply(new PopupMessage(message));
            return;
        }
        message = CredentialsValidator.validate(CredentialsValidator.NAME, data.name());
        if(message != null){
            e.reply(new PopupMessage(message));
            return;
        }
        final Profile profile = new Profile();
        profile.user().set(data.user());
        profile.pass().set(data.pass());
        profile.name().set(data.name());
        profile.name().addListener(
                (o, n) -> {
                    final Data d = new ChangeNameData(profile.user().get(), n);
                    if(profile.connection != null)
                        profile.connection.send(d);
                    profile.friends().stream().filter(f -> f.isOnline() && Server.instance().profiles().profile(f.user().get()).connection != null).forEach(f -> Server.instance().profiles().profile(f.user().get()).connection.send(d));
                    ServerIO.save();
                }
        );
        profile.pic().addListener(
                (o, n) -> {
                    final Data d = new ChangePicData(profile.user().get(), n);
                    if(profile.connection != null)
                        profile.connection.send(d);
                    profile.friends().stream().filter(f -> f.isOnline() && Server.instance().profiles().profile(f.user().get()).connection != null).forEach(f -> Server.instance().profiles().profile(f.user().get()).connection.send(d));
                    ServerIO.save();
                }
        );
        profile.status().addListener(
                (o, n) -> {
                    final Data d = new ChangeStatusData(profile.user().get(), n);
                    if(profile.connection != null)
                        profile.connection.send(d);
                    profile.friends().stream().filter(f -> f.isOnline() && Server.instance().profiles().profile(f.user().get()).connection != null).forEach(f -> Server.instance().profiles().profile(f.user().get()).connection.send(d));
                    ServerIO.save();
                }
        );
        profile.mood().addListener(
                (o, n) -> {
                    final Data d = new ChangeMoodData(profile.user().get(), n);
                    if(profile.connection != null)
                        profile.connection.send(d);
                    profile.friends().stream().filter(f -> f.isOnline() && Server.instance().profiles().profile(f.user().get()).connection != null).forEach(f -> Server.instance().profiles().profile(f.user().get()).connection.send(d));
                    ServerIO.save();
                }
        );
        Server.instance().profiles().add(profile);
        ServerIO.save();
        e.reply(new PopupMessage(String.format("%s successfully registered", profile.user().get())));
    }
}
