package cats.chat.server;

import cats.chat.core.Constants;
import cats.chat.core.connection.Connection;
import cats.chat.core.connection.data.Data;
import cats.chat.core.connection.data.Opcodes;
import cats.chat.core.connection.event.ConnectionListener;
import cats.chat.core.connection.event.DataListener;
import cats.chat.core.profile.Profile;
import cats.chat.core.profile.Profiles;
import cats.chat.server.handler.AbstractHandler;
import cats.chat.server.handler.AddUserHandler;
import cats.chat.server.handler.ChangeMoodHandler;
import cats.chat.server.handler.ChangeNameHandler;
import cats.chat.server.handler.ChangePicHandler;
import cats.chat.server.handler.ChangeStatusHandler;
import cats.chat.server.handler.LoginHandler;
import cats.chat.server.handler.MessageHandler;
import cats.chat.server.handler.RegisterHandler;
import cats.chat.server.handler.RemoveUserHandler;
import cats.chat.server.io.ServerIO;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 4:32 PM
 */
public class Server extends Thread implements Serializable, Constants, Opcodes{

    private static Server instance = null;

    private ServerSocket server;

    private final Profiles<Profile> profiles;
    private final Map<Byte, AbstractHandler> handlers;

    public Server(){
        setPriority(MAX_PRIORITY);

        handlers = new HashMap<>();
        handlers.put(MESSAGE, new MessageHandler());
        handlers.put(ADD_USER, new AddUserHandler());
        handlers.put(CHANGE_MOOD, new ChangeMoodHandler());
        handlers.put(CHANGE_NAME, new ChangeNameHandler());
        handlers.put(CHANGE_PIC, new ChangePicHandler());
        handlers.put(CHANGE_STATUS, new ChangeStatusHandler());
        handlers.put(LOGIN, new LoginHandler());
        handlers.put(REGISTER, new RegisterHandler());
        handlers.put(REMOVE_USER, new RemoveUserHandler());

        profiles = new Profiles<>();
    }

    public Profiles<Profile> profiles(){
        return profiles;
    }

    public static Server instance(){
        return instance;
    }

    public void run(){
        try{
            server = new ServerSocket(PORT);
            while(true){
                final Socket socket = server.accept();
                final Connection connection = new Connection(socket);
                connection.addListener(
                        (ConnectionListener) e -> {
                            final Profile profile = profiles.profile(e.connection());
                            if(profile == null)
                                return;
                            profile.connection = null;
                            profile.status().set(STATUS_OFFLINE);
                            ServerIO.save();
                        }
                );
                connection.addListener(
                        (DataListener) e -> {
                            final Data data = e.data();
                            handlers.get(data.opcode()).handle(e, data);
                        }
                );
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static void main(String args[]){
        instance = new Server();
        ServerIO.load();
        instance.start();
    }
}
