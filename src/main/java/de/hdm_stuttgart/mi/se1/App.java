package de.hdm_stuttgart.mi.se1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * A simple http://logging.apache.org/log4j/2.x demo,
 * see file resources/log4j2.xml for configuration options
 * and A1.log containing debugging output.
 */

public class App {
    private static Logger log = LogManager.getLogger(App.class);

    /**
     * Your application's main entry point.
     *
     * @param args Yet unused
     */
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        
        log.debug("With respect to logging you may want to configure file ");
        log.debug("'src/main/resources/log4j2.xml' to suit your needs.");
        log.debug("This config file 'log4j2.xml' will result in 'A1.log'");
        log.debug("file containing logging output as well.");
        System.out.println("Noch mehr Hello Class!!!");
        System.out.println("Es funktioniiieeert");
    }
}
