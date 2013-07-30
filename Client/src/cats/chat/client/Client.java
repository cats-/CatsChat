package cats.chat.client;

import cats.chat.client.auth.AuthPanel;
import cats.chat.client.handler.AbstractHandler;
import cats.chat.client.handler.AddProfileHandler;
import cats.chat.client.handler.AssignProfileHandler;
import cats.chat.client.handler.ChangeMoodHandler;
import cats.chat.client.handler.ChangeNameHandler;
import cats.chat.client.handler.ChangePicHandler;
import cats.chat.client.handler.ChangeStatusHandler;
import cats.chat.client.handler.MessageHandler;
import cats.chat.client.handler.PopupMessageHandler;
import cats.chat.client.handler.RemoveProfileHandler;
import cats.chat.client.messenger.MessengerPanel;
import cats.chat.core.Constants;
import cats.chat.core.connection.Connection;
import cats.chat.core.connection.data.Data;
import cats.chat.core.connection.data.Opcodes;
import cats.chat.core.connection.data.type.impl.ChangeStatusData;
import cats.chat.core.connection.event.ConnectionListener;
import cats.chat.core.connection.event.DataListener;
import cats.chat.core.profile.Profile;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * CatsChat
 * Josh
 * 25/07/13
 * 8:09 PM
 */
public class Client extends JFrame implements Constants, Opcodes{

    private static Profile profile;
    private static Connection connection;

    private static Client instance;

    public static boolean loggedIn = false;

    private static JPanel container;
    private static MessengerPanel messenger;

    private static final Map<Byte, AbstractHandler> HANDLERS = new HashMap<>();

    static{
        HANDLERS.put(ADD_PROFILE, new AddProfileHandler());
        HANDLERS.put(ASSIGN_PROFILE, new AssignProfileHandler());
        HANDLERS.put(CHANGE_STATUS, new ChangeStatusHandler());
        HANDLERS.put(CHANGE_MOOD, new ChangeMoodHandler());
        HANDLERS.put(CHANGE_PIC, new ChangePicHandler());
        HANDLERS.put(CHANGE_NAME, new ChangeNameHandler());
        HANDLERS.put(MESSAGE, new MessageHandler());
        HANDLERS.put(POPUP_MESSAGE, new PopupMessageHandler());
        HANDLERS.put(REMOVE_PROFILE, new RemoveProfileHandler());
    }

    public Client(){
        super(TITLE);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(final WindowEvent e) {
                        if (loggedIn)
                            send(new ChangeStatusData(user(), STATUS_OFFLINE));
                        connection.close();
                        System.exit(0);
                    }
                }
        );

        container = new JPanel(new BorderLayout());
        container.setBorder(new EmptyBorder(5, 5, 5, 5));
        container.add(new AuthPanel(), BorderLayout.CENTER);

        add(container, BorderLayout.CENTER);
    }

    public static MessengerPanel messenger(){
        return messenger;
    }

    public static Profile profile(){
        return profile;
    }

    public static String user(){
        return profile.user().get();
    }

    public static Client instance(){
        return instance;
    }

    public static boolean send(final Data data){
        return connection.send(data);
    }

    public static void startMessenger(){
        container.removeAll();
        container.revalidate();
        container.repaint();
        messenger = new MessengerPanel();
        container.add(messenger, BorderLayout.CENTER);
        container.revalidate();
        container.repaint();
        instance.revalidate();
        instance.repaint();
        instance.setSize(700, 500);
    }

    public static void main(String args[]) throws IOException {
        profile = new Profile();
        connection = new Connection(new Socket(HOST, PORT));
        connection.addListener(
                (DataListener) e -> {
                    final Data d = e.data();
                    if(HANDLERS.containsKey(d.opcode()))
                        HANDLERS.get(d.opcode()).handle(e, d);
                }
        );
        connection.addListener(
                (ConnectionListener) e -> {
                    System.out.println("CONNECTION HAS CLOSED!");
                    loggedIn = false;
                }
        );
        instance = new Client();
        instance.setSize(300, 300);
        instance.setVisible(true);
        instance.setLocationRelativeTo(null);
    }
}
