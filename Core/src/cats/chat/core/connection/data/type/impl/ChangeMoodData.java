package cats.chat.core.connection.data.type.impl;

import cats.chat.core.connection.data.type.ChangeData;
import java.io.Serializable;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 5:23 PM
 */
public class ChangeMoodData extends ChangeData<String> implements Serializable{

    public ChangeMoodData(final String user, final String mood){
        super(CHANGE_MOOD, user, mood);
    }

    public String mood(){
        return value();
    }
}
