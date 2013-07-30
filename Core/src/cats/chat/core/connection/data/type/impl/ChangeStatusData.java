package cats.chat.core.connection.data.type.impl;

import cats.chat.core.connection.data.type.ChangeData;
import java.io.Serializable;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 7:25 PM
 */
public class ChangeStatusData extends ChangeData<Byte> implements Serializable {

    public ChangeStatusData(final String user, final byte status){
        super(CHANGE_STATUS, user, status);
    }

    public Byte status(){
        return value();
    }
}
