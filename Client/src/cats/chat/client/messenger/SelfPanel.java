package cats.chat.client.messenger;

import cats.chat.client.Client;
import cats.chat.client.messenger.comp.ProfilePanel;
import cats.chat.client.util.Status;
import cats.chat.client.util.Utils;
import cats.chat.core.connection.data.type.impl.ChangeMoodData;
import cats.chat.core.connection.data.type.impl.ChangeNameData;
import cats.chat.core.connection.data.type.impl.ChangePicData;
import cats.chat.core.connection.data.type.impl.ChangeStatusData;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * CatsChat
 * Josh
 * 25/07/13
 * 10:37 PM
 */
public class SelfPanel extends ProfilePanel {

    public SelfPanel(){
        super(Client.profile());

        final JPopupMenu popup = new JPopupMenu();
        final JMenuItem changeNameItem = new JMenuItem("Change Name");
        changeNameItem.addActionListener(
                e -> {
                    final String name = JOptionPane.showInputDialog(null, "Enter new display name");
                    if(name == null || name.isEmpty())
                        return;
                    Client.send(new ChangeNameData(Client.user(), name));
                }
        );
        popup.add(changeNameItem);
        popup.addSeparator();
        for(final Status s : Status.values()){
            final JMenuItem item = new JMenuItem(s.title());
            item.setForeground(s.color());
            item.setIcon(s.icon());
            item.addActionListener(e -> Client.send(new ChangeStatusData(Client.user(), s.opcode())));
            popup.add(item);
        }

        nameLabel.addMouseListener(
                new MouseAdapter(){
                    public void mousePressed(final MouseEvent e){
                        popup.show(nameLabel, e.getX(), e.getY());
                    }
                }
        );

        moodArea.setEditable(true);
        moodArea.addKeyListener(
                new KeyAdapter(){
                    public void keyPressed(final KeyEvent e){
                        if(e.getKeyCode() != KeyEvent.VK_ENTER)
                            return;
                        final String mood = moodArea.getText().trim();
                        Client.send(new ChangeMoodData(Client.user(), mood));
                    }
                }
        );

        final JFileChooser picChooser = new JFileChooser();
        picChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png"));
        picChooser.setDialogTitle("Select new profile pic");
        picChooser.setMultiSelectionEnabled(false);
        picChooser.setApproveButtonText("Set as profile pic");
        picChooser.setCurrentDirectory(new File(System.getProperty("user.home"), "Pictures"));

        picLabel.addMouseListener(
                new MouseAdapter(){
                    public void mousePressed(final MouseEvent e){
                        final int value = picChooser.showOpenDialog(null);
                        if(value != JFileChooser.APPROVE_OPTION)
                            return;
                        final File file = picChooser.getSelectedFile();
                        final ImageIcon icon = Utils.resize(new ImageIcon(file.getPath()), PIC_SIZE);
                        Client.send(new ChangePicData(Client.user(), icon));
                    }
                }
        );
    }
}
