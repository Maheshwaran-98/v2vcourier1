package com.v2v.logging;

package com.virtusa.ttt.Logging_Example;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class v2vlogging 
{
    public static void main( String[] args )
    {  PropertyConfigurator.configure("C:\\Users\\eclipse-workspace\\Logging_Example\\src\\log4j.properties");
    	Logger log =   Logger.getLogger(App.class.getName());
    	log.info("welcome ");
      //  System.out.println( "Heoooollo World!" );
    }
}
