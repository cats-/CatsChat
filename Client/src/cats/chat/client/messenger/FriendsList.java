package cats.chat.client.messenger;

import cats.chat.client.Client;
import cats.chat.client.messenger.comp.ProfilePanel;
import cats.chat.core.connection.data.type.impl.AddUserData;
import cats.chat.core.connection.data.type.impl.RemoveUserData;
import cats.chat.core.profile.SafeProfile;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

/**
 * CatsChat
 * Josh
 * 25/07/13
 * 11:54 PM
 */
public class FriendsList extends JPanel{

    private final JPanel container;
    private final List<ProfilePanel> panels;

    public FriendsList(){
        super(new BorderLayout());

        panels = new LinkedList<>();

        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        final JButton addButton = new JButton("Add");
        addButton.addActionListener(
                e -> {
                    final String user = JOptionPane.showInputDialog(null, "Enter user name");
                    if(user == null || user.isEmpty())
                        return;
                    Client.send(new AddUserData(Client.user(), user));
                }
        );

        add(addButton, BorderLayout.NORTH);
        add(new JScrollPane(container), BorderLayout.CENTER);
    }

    public void add(final SafeProfile profile){
        final ProfilePanel panel = new ProfilePanel(profile);
        final JPopupMenu popup = new JPopupMenu();
        final JMenuItem removeItem = new JMenuItem("Remove");
        removeItem.addActionListener(e -> Client.send(new RemoveUserData(Client.user(), profile.user().get())));
        popup.add(removeItem);
        panel.addMouseListener(
                new MouseAdapter() {
                    public void mousePressed(final MouseEvent e) {
                        switch (e.getButton()) {
                            case MouseEvent.BUTTON1:
                                select(profile.user().get());
                                break;
                            case MouseEvent.BUTTON3:
                                popup.show(e.getComponent(), e.getX(), e.getY());
                                break;
                        }
                    }
                }
        );
        panels.add(panel);
        container.add(panel);
        container.revalidate();
        container.repaint();
    }

    public void remove(final SafeProfile profile){
        final ProfilePanel panel = panels.stream().filter(p -> p.profile().equals(profile)).findFirst().get();
        panel.setSelected(false);
        panels.remove(panel);
        container.remove(panel);
        container.revalidate();
        container.repaint();
    }

    public SafeProfile getSelected(){
        final Optional<ProfilePanel> p = panels.stream().filter(ProfilePanel::isSelected).findFirst();
        return p.isPresent() ? p.get().profile() : null;
    }

    public void select(final String user){
        if(user == null)
            Client.messenger().convoArea().setSelected(null);
        panels.stream().forEach(p -> p.setSelected(user == null ? false : p.profile().equals(user)));
    }
}
