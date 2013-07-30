package cats.chat.core.connection.data.type;

import java.io.Serializable;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 2:15 AM
 */
public class ChangeData<T> extends SingleValueData<T> implements Serializable{

    private final String user;

    public ChangeData(final byte opcode, final String user, final T value){
        super(opcode, value);
        this.user = user;
    }

    public String user(){
        return user;
    }
}
