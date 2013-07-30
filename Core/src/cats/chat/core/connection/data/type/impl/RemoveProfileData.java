package cats.chat.core.connection.data.type.impl;

import cats.chat.core.connection.data.type.SingleValueData;
import cats.chat.core.profile.SafeProfile;
import java.io.Serializable;

/**
 * CatsChat
 * Josh
 * 23/07/13
 * 2:49 PM
 */
public class RemoveProfileData extends SingleValueData<SafeProfile> implements Serializable{

    public RemoveProfileData(final SafeProfile profile){
        super(REMOVE_PROFILE, profile);
    }

    public SafeProfile profile(){
        return value();
    }
}
