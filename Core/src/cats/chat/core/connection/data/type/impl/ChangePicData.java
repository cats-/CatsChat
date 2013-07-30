package cats.chat.core.connection.data.type.impl;

import cats.chat.core.connection.data.type.ChangeData;
import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 5:23 PM
 */
public class ChangePicData extends ChangeData<ImageIcon> implements Serializable{

    public ChangePicData(final String user, final ImageIcon pic){
        super(CHANGE_PIC, user, pic);
    }

    public ImageIcon pic(){
        return value();
    }
}
