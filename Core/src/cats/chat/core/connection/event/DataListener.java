package cats.chat.core.connection.event;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 4:26 PM
 */
public interface DataListener extends Listener{

    public void onDataReceived(final DataEvent e);
}
