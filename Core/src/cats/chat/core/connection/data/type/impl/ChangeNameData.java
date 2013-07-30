package cats.chat.core.connection.data.type.impl;

import cats.chat.core.connection.data.type.ChangeData;
import java.io.Serializable;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 5:21 PM
 */
public class ChangeNameData extends ChangeData<String> implements Serializable {

    public ChangeNameData(final String user, final String name){
        super(CHANGE_NAME, user, name);
    }

    public String name(){
        return value();
    }
}
