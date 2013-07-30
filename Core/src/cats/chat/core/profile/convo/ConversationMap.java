package cats.chat.core.profile.convo;

import cats.chat.core.connection.data.type.impl.Message;
import cats.chat.core.profile.SafeProfile;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * CatsChat
 * Josh
 * 25/07/13
 * 8:50 AM
 */
public class ConversationMap extends HashMap<String, LinkedList<Message>> implements Serializable{

    public LinkedList<Message> get(final SafeProfile profile){
        return get(profile.user().get());
    }

    public void add(final String user, final Message message){
        if(!containsKey(user))
            put(user, new LinkedList<>());
        get(user).add(message);
    }

    public void copyTo(final ConversationMap map){
        map.clear();
        putAll(map);
    }
}
