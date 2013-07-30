package cats.chat.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 1:07 AM
 */
public interface Constants {

    DateFormat TIME_FORMAT = new SimpleDateFormat("hh:mm:ss");

    String TITLE = "CatsChat";

    int PORT = 1274;
    String HOST = "localhost";
}
