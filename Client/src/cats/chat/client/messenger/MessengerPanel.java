package cats.chat.client.messenger;

import cats.chat.client.Client;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 * CatsChat
 * Josh
 * 25/07/13
 * 9:52 PM
 */
public class MessengerPanel extends JPanel{

    private final SelfPanel selfPanel;
    private final FriendsList friendsList;
    private final ConversationArea convoArea;

    public MessengerPanel(){
        super(new BorderLayout());

        selfPanel = new SelfPanel();

        friendsList = new FriendsList();
        friendsList.setPreferredSize(new Dimension(200, Client.instance().getPreferredSize().height));

        convoArea = new ConversationArea();

        final JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, friendsList, convoArea);

        add(selfPanel, BorderLayout.NORTH);
        add(split, BorderLayout.CENTER);
    }

    public SelfPanel selfPanel(){
        return selfPanel;
    }

    public FriendsList friendsList(){
        return friendsList;
    }

    public ConversationArea convoArea(){
        return convoArea;
    }
}
