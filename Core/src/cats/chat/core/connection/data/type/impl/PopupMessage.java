package cats.chat.core.connection.data.type.impl;

import cats.chat.core.connection.data.type.SingleValueData;
import java.io.Serializable;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 6:23 PM
 */
public class PopupMessage extends SingleValueData<String> implements Serializable{

    public PopupMessage(final String message){
        super(POPUP_MESSAGE, message);
    }

    public String message(){
        return value();
    }
}
