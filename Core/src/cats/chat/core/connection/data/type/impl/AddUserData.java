package cats.chat.core.connection.data.type.impl;

import cats.chat.core.connection.data.type.ChangeData;
import java.io.Serializable;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 5:54 PM
 */
public class AddUserData extends ChangeData<String> implements Serializable {

    public AddUserData(final String user, final String add){
        super(ADD_USER, user, add);
    }

    public String add(){
        return value();
    }
}
