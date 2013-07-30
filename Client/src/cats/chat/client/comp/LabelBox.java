package cats.chat.client.comp;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * CatsChat
 * Josh
 * 25/07/13
 * 8:30 PM
 */
public class LabelBox extends JPanel{

    private final JLabel label;
    private final JTextField box;

    public LabelBox(final String text, final String tooltip, final boolean pass){
        super(new GridLayout(1, 2));
        setToolTipText(tooltip);

        label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.CENTER);

        box = pass ? new JPasswordField() : new JTextField();
        box.setHorizontalAlignment(JLabel.CENTER);

        add(label);
        add(box);

        setMaximumSize(new Dimension(Integer.MAX_VALUE, getPreferredSize().height));
    }

    public LabelBox(final String text, final String tooltip){
        this(text, tooltip, false);
    }

    public void clear(){
        box.setText("");
        box.repaint();
    }

    public String value(){
        return box.getText().trim();
    }

}
