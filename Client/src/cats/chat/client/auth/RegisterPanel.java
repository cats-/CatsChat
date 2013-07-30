package cats.chat.client.auth;

import cats.chat.client.Client;
import cats.chat.client.comp.LabelBox;
import cats.chat.client.util.Constants;
import cats.chat.core.connection.data.type.impl.RegisterData;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import static cats.chat.client.util.Utils.space;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * CatsChat
 * Josh
 * 25/07/13
 * 8:30 PM
 */
public class RegisterPanel extends JPanel implements Constants, ActionListener{

    private final LabelBox userBox;
    private final LabelBox passBox;
    private final LabelBox pass2Box;
    private final LabelBox nameBox;

    private final JButton registerButton;

    public RegisterPanel(){
        super(new BorderLayout());

        userBox = new LabelBox("Username", "This is also your login ID");
        passBox = new LabelBox("Password", "Your desired password", true);
        pass2Box = new LabelBox("Re-enter Password", "Enter in your password again for confirmation", true);
        nameBox = new LabelBox("Display Name", "Your name which other users will see you by (can be changed)");

        final JPanel boxContainer = new JPanel();
        boxContainer.setLayout(new BoxLayout(boxContainer, BoxLayout.Y_AXIS));
        boxContainer.add(space(V_SPACING));
        boxContainer.add(userBox);
        boxContainer.add(space(V_SPACING));
        boxContainer.add(passBox);
        boxContainer.add(space(V_SPACING));
        boxContainer.add(pass2Box);
        boxContainer.add(space(V_SPACING));
        boxContainer.add(nameBox);

        registerButton = new JButton("Register");
        registerButton.addActionListener(this);

        add(new JScrollPane(boxContainer), BorderLayout.CENTER);
        add(registerButton, BorderLayout.SOUTH);
    }

    public void actionPerformed(final ActionEvent e){
        final Object source = e.getSource();
        if(source.equals(registerButton)){
            final String user = userBox.value();
            final String pass = passBox.value();
            final String pass2 = pass2Box.value();
            final String name = nameBox.value();
            if(user.isEmpty() || pass.isEmpty() || pass2.isEmpty() || name.isEmpty()){
                showMessageDialog(null, "Fill in all required fields");
                return;
            }
            if(!pass.equals(pass2)){
                showMessageDialog(null, "Passwords don't match");
                return;
            }
            Client.send(new RegisterData(user, pass, name));
            userBox.clear();
            passBox.clear();
            pass2Box.clear();
            nameBox.clear();
        }
    }
}
