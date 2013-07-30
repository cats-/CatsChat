package cats.chat.core.connection.data.type.impl;

import cats.chat.core.connection.data.type.ChangeData;
import java.io.Serializable;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 5:57 PM
 */
public class RemoveUserData extends ChangeData<String> implements Serializable{

    public RemoveUserData(final String name, final String remove){
        super(REMOVE_USER, name, remove);
    }

    public String remove(){
        return value();
    }
}
