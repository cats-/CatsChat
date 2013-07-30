package cats.chat.client.auth;

import cats.chat.client.Client;
import cats.chat.client.comp.LabelBox;
import cats.chat.client.util.Constants;
import cats.chat.core.connection.data.type.impl.LoginData;
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
 * 8:44 PM
 */
public class LoginPanel extends JPanel implements Constants, ActionListener{

    private final LabelBox userBox;
    private final LabelBox passBox;

    private final JButton loginButton;

    public LoginPanel(){
        super(new BorderLayout());

        userBox = new LabelBox("Username", "Your login ID");
        passBox = new LabelBox("Password", "Your password", true);

        final JPanel boxContainer = new JPanel();
        boxContainer.setLayout(new BoxLayout(boxContainer, BoxLayout.Y_AXIS));
        boxContainer.add(space(V_SPACING));
        boxContainer.add(userBox);
        boxContainer.add(space(V_SPACING));
        boxContainer.add(passBox);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        add(new JScrollPane(boxContainer), BorderLayout.CENTER);
        add(loginButton, BorderLayout.SOUTH);
    }

    public void actionPerformed(final ActionEvent e){
        final Object source = e.getSource();
        if(source.equals(loginButton)){
            final String user = userBox.value();
            final String pass = passBox.value();
            if(user.isEmpty() || pass.isEmpty()){
                showMessageDialog(null, "Fill in all required fields first");
                return;
            }
            Client.send(new LoginData(user, pass));
            userBox.clear();
            passBox.clear();
        }
    }
}
