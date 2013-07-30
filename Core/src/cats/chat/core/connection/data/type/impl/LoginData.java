package cats.chat.core.connection.data.type.impl;

import cats.chat.core.connection.data.type.DoubleValueData;
import java.io.Serializable;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 5:21 PM
 */
public class LoginData extends DoubleValueData<String, String> implements Serializable {

    public LoginData(final String user, final String pass){
        super(LOGIN, user, pass);
    }

    public String user(){
        return first();
    }

    public String pass(){
        return second();
    }
}
