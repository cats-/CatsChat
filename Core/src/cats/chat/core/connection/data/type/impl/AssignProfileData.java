package cats.chat.core.connection.data.type.impl;

import cats.chat.core.connection.data.type.SingleValueData;
import cats.chat.core.profile.SafeProfile;
import java.io.Serializable;

/**
 * CatsChat
 * Josh
 * 23/07/13
 * 9:36 AM
 */
public class AssignProfileData extends SingleValueData<SafeProfile> implements Serializable{

    public AssignProfileData(final SafeProfile profile){
        super(ASSIGN_PROFILE, profile);
    }

    public SafeProfile profile(){
        return value();
    }
}
