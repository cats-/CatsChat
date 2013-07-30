package cats.chat.client.messenger.comp;

import cats.chat.client.Client;
import cats.chat.client.util.Constants;
import cats.chat.client.util.Status;
import cats.chat.client.util.Utils;
import cats.chat.core.profile.SafeProfile;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
 * CatsChat
 * Josh
 * 25/07/13
 * 10:38 PM
 */
public class ProfilePanel extends JPanel implements Constants{

    private static final Color BACKGROUND = UIManager.getColor("Label.background");
    private static final Color AREA_BACKGROUND = UIManager.getColor("TextArea.background");

    private static final Color SELECTED = Color.BLUE;

    private final SafeProfile profile;

    protected final JLabel nameLabel;
    protected final JTextArea moodArea;
    protected final JLabel picLabel;

    public ProfilePanel(final SafeProfile prof){
        super(new BorderLayout());
        profile = prof;

        final Status status = Status.getByOpcode(profile.status().get());

        nameLabel = new JLabel(profile.name().get());
        nameLabel.setOpaque(true);
        nameLabel.setIcon(status.icon());
        nameLabel.setForeground(status.color());
        nameLabel.setToolTipText(String.format("User: %s", profile.user().get()));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);

        moodArea = new JTextArea(profile.mood().get());
        moodArea.setEditable(false);
        moodArea.setBackground(BACKGROUND);
        moodArea.setToolTipText(profile.mood().get());
        moodArea.setLineWrap(true);
        moodArea.setWrapStyleWord(true);

        picLabel = new JLabel(profile.pic().get() != null ? Utils.resize(profile.pic().get(), PIC_SIZE) : Utils.createIcon(status.color(), PIC_SIZE));
        picLabel.setToolTipText(String.format("User: %s", profile.user().get()));

        final JPanel center = new JPanel(new BorderLayout());
        center.add(nameLabel, BorderLayout.NORTH);
        center.add(new JScrollPane(moodArea), BorderLayout.CENTER);

        add(picLabel, BorderLayout.WEST);
        add(center, BorderLayout.CENTER);

        setMaximumSize(new Dimension(Integer.MAX_VALUE, getPreferredSize().height));

        profile.name().addListener(
                (o, n) -> {
                    nameLabel.setText(n);
                    nameLabel.repaint();
                }
        );
        profile.mood().addListener(
                (o, n) -> {
                    moodArea.setText(n);
                    moodArea.setToolTipText(n);
                    moodArea.repaint();
                }
        );
        profile.status().addListener(
                (o, n) -> {
                    final Status s = Status.getByOpcode(n);
                    nameLabel.setForeground(s.color());
                    nameLabel.setIcon(s.icon());
                    nameLabel.repaint();
                    if(profile.pic().get() == null){
                        picLabel.setIcon(Utils.createIcon(s.color(), PIC_SIZE));
                        picLabel.repaint();
                    }
                }
        );
        profile.pic().addListener(
                (o, n) -> {
                    picLabel.setIcon(Utils.resize(n, PIC_SIZE));
                    picLabel.repaint();
                }
        );
        profile.user().addListener(
                (o, n) -> {
                    nameLabel.setToolTipText(n);
                    nameLabel.repaint();
                }
        );
    }

    public void addMouseListener(final MouseListener l){
        nameLabel.addMouseListener(l);
        moodArea.addMouseListener(l);
        picLabel.addMouseListener(l);
    }

    public SafeProfile profile(){
        return profile;
    }

    public void setSelected(final boolean selected){
        final Color b = selected ? SELECTED : BACKGROUND;
        moodArea.setBackground(b);
        moodArea.setForeground(selected ? Color.WHITE : Color.BLACK);
        nameLabel.setBackground(b);
        moodArea.repaint();
        nameLabel.repaint();
        if(selected)
            Client.messenger().convoArea().setSelected(profile);
        else{
            final SafeProfile select = Client.messenger().convoArea().getSelected();
            if(select != null && select.equals(profile))
                Client.messenger().convoArea().setSelected(null);
        }
    }

    public boolean isSelected(){
        return moodArea.getBackground().equals(SELECTED);
    }
}
