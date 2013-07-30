package cats.chat.core.connection.event;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 4:24 PM
 */
public interface ConnectionListener extends Listener{

    public void onConnectionClosed(final ConnectionEvent e);
}
