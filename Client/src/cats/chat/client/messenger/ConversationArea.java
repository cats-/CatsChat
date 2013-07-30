package cats.chat.client.messenger;

import cats.chat.client.Client;
import cats.chat.client.messenger.comp.ProfilePanel;
import cats.chat.client.util.Constants;
import cats.chat.client.util.Utils;
import cats.chat.core.connection.data.type.impl.Message;
import cats.chat.core.profile.SafeProfile;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * CatsChat
 * Josh
 * 26/07/13
 * 9:02 AM
 */
public class ConversationArea extends JPanel implements Constants{

    private class MessagePanel extends JPanel{

        private MessagePanel(final Message message){
            super(new BorderLayout());

            final JLabel headerLabel = new JLabel(String.format("%s %s", message.timestamp(), message.from()));
            headerLabel.setHorizontalAlignment(JLabel.CENTER);

            final SafeProfile profile = Client.profile().equals(message.from()) ? Client.profile() : Client.profile().friends().profile(message.from());

            final JLabel picLabel = new JLabel(profile.pic().get() == null ? Utils.createIcon(Color.GRAY, MESSAGE_SIZE) : Utils.resize(profile.pic().get(), MESSAGE_SIZE));

            final JTextArea messageArea = new JTextArea(message.message());
            messageArea.setLineWrap(true);
            messageArea.setWrapStyleWord(true);
            messageArea.setEditable(false);

            final JPanel center = new JPanel(new BorderLayout());
            center.add(headerLabel, BorderLayout.NORTH);
            center.add(messageArea, BorderLayout.CENTER);

            add(picLabel, BorderLayout.WEST);
            add(center, BorderLayout.CENTER);

            setMaximumSize(new Dimension(Integer.MAX_VALUE, getPreferredSize().height));
        }
    }

    private final JPanel container;

    private final JTextArea inputArea;
    private ProfilePanel selected;

    public ConversationArea(){
        super(new BorderLayout());

        selected = null;

        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        inputArea = new JTextArea();
        inputArea.setLineWrap(true);
        inputArea.setRows(1);
        inputArea.setWrapStyleWord(true);
        inputArea.addKeyListener(
                new KeyAdapter(){
                    public void keyPressed(final KeyEvent e){
                        if(e.getKeyCode() != KeyEvent.VK_ENTER)
                            return;
                        if(selected == null){
                            showMessageDialog(null, "Select a friend to talk to first");
                            return;
                        }
                        final String text = inputArea.getText().trim();
                        if(text.isEmpty())
                            return;
                        Client.send(new Message(Client.user(), selected.profile().user().get(), text));
                        inputArea.setText("");
                        inputArea.repaint();
                    }
                }
        );

        final JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(container), new JScrollPane(inputArea));

        add(split, BorderLayout.CENTER);
    }

    public void add(final Message message){
        final MessagePanel panel = new MessagePanel(message);
        panel.setAutoscrolls(true);
        container.add(new MessagePanel(message));
        container.revalidate();
        container.repaint();
    }

    public SafeProfile getSelected(){
        return selected == null ? null : selected.profile();
    }

    public void setSelected(final SafeProfile profile){
        if(profile == null){
            remove(selected);
            revalidate();
            repaint();
            return;
        }
        selected = new ProfilePanel(profile);
        add(selected, BorderLayout.NORTH);
        revalidate();
        repaint();
        container.removeAll();
        container.revalidate();
        final List<Message> messages = Client.profile().convoMap().get(profile);
        if(messages != null)
            messages.forEach(this::add);
    }
}
