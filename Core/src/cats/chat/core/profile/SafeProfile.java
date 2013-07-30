package cats.chat.core.profile;

import cats.chat.core.connection.data.Opcodes;
import cats.chat.core.profile.convo.ConversationMap;
import cats.chat.core.property.Property;
import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 * CatsChat
 * Josh
 * 23/07/13
 * 9:15 AM
 */
public class SafeProfile implements Serializable, Opcodes{

    protected final Property<String> name;
    protected final Property<String> user;
    protected final Property<ImageIcon> pic;
    protected final Property<String> mood;
    protected final Property<Byte> status;

    protected final ConversationMap convoMap;

    public SafeProfile(){
        name = new Property<>();
        user = new Property<>();
        pic = new Property<>();
        mood = new Property<>();
        status = new Property<>();
        status.privateSet(STATUS_OFFLINE);

        convoMap = new ConversationMap();
    }

    public void update(final SafeProfile profile){
        user.set(profile.user.get());
        name.set(profile.name.get());
        pic.set(profile.pic.get());
        mood.set(profile.mood.get());
        status.set(profile.status.get());
        profile.convoMap.copyTo(convoMap);
    }
    
    public void privateUpdate(final SafeProfile profile){
        user.privateSet(profile.user.get());
        name.privateSet(profile.name.get());
        pic.privateSet(profile.pic.get());
        mood.privateSet(profile.mood.get());
        status.privateSet(profile.status.get());
        profile.convoMap.copyTo(convoMap);
    }

    public ConversationMap convoMap(){
        return convoMap;
    }

    public boolean isStatus(final byte status){
        return this.status.get() == status;
    }

    public boolean isOnline(){
        return isStatus(STATUS_ONLINE) || isStatus(STATUS_AWAY) || isStatus(STATUS_BUSY);
    }

    public Property<String> name(){
        return name;
    }

    public Property<String> user(){
        return user;
    }

    public Property<ImageIcon> pic(){
        return pic;
    }

    public Property<String> mood(){
        return mood;
    }

    public Property<Byte> status(){
        return status;
    }

    public boolean equals(final Object object){
        if(object == null)
            return false;
        if(object instanceof String)
            return user.get().equalsIgnoreCase((String)object);
        else if(object instanceof SafeProfile)
            return equals(((SafeProfile)object).user.get());
        else
            return false;
    }
}
