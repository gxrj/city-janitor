package io.github.gxrj.janitory.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProtocolEncoder {
    
    public static String encode( LocalDateTime creationDate, String username ) {
        var characters = username.toCharArray();
        var output = creationDate.format( DateTimeFormatter.ofPattern( "yMdHms" ) );

        for( int i = 0; i < characters.length; i++ ) 
            output += Integer.toHexString( ( int ) characters[ i ] );

        return output;
    }
}
