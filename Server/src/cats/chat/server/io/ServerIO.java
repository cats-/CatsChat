package cats.chat.server.io;

import cats.chat.core.Constants;
import cats.chat.core.profile.Profile;
import cats.chat.server.Server;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 9:04 PM
 */
public final class ServerIO implements Constants{

    private static final String EXTENSION = ".cchat";

    private static final File DIR = new File(System.getProperty("user.home"), "." + TITLE);
    private static final File FILE = new File(DIR, "profiles" + EXTENSION);

    static{
        if(!DIR.exists())
            DIR.mkdir();
    }

    private ServerIO(){}

    public static void load(){
        if(!FILE.exists())
            return;
        ObjectInputStream input = null;
        try{
            input = new ObjectInputStream(new FileInputStream(FILE));
            while(true){
                try{
                    final Profile profile = (Profile)input.readObject();
                    if(profile != null)
                        Server.instance().profiles().add(profile);
                }catch(Exception ex){
                    ex.printStackTrace();
                    break;
                }
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }finally{
            try{
                if(input != null)
                    input.close();
            }catch(IOException ex){}
        }
    }

    public static void save(){
        ObjectOutputStream output = null;
        try{
            output = new ObjectOutputStream(new FileOutputStream(FILE, false));
            for(final Profile profile : Server.instance().profiles()){
                output.writeObject(profile);
                output.flush();
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }finally{
            try{
                if(output != null){
                    output.flush();
                    output.close();
                }
            }catch(IOException ex){}
        }
    }
}
