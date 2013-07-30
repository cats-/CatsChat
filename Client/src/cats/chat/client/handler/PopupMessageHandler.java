package cats.chat.client.handler;

import cats.chat.core.connection.data.type.impl.PopupMessage;
import cats.chat.core.connection.event.DataEvent;
import javax.swing.JOptionPane;

/**
 * CatsChat
 * Josh
 * 25/07/13
 * 9:08 PM
 */
public class PopupMessageHandler extends AbstractHandler<PopupMessage>{

    public void handle(final DataEvent e, final PopupMessage message){
        JOptionPane.showMessageDialog(null, message.message());
    }
}
