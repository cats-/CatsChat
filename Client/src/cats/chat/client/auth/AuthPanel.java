package cats.chat.client.auth;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * CatsChat
 * Josh
 * 25/07/13
 * 8:18 PM
 */
public class AuthPanel extends JPanel {

    public AuthPanel(){
        super(new BorderLayout());

        final JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Login", new LoginPanel());
        tabbedPane.addTab("Register", new RegisterPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }
}
