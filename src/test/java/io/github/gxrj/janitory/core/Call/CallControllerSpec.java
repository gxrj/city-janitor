package io.github.gxrj.janitory.core.Call;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import org.junit.jupiter.api.Test;

public class CallControllerSpec {

    @Test
    void createCallAndExpectOkMsg() throws IOException, InterruptedException {

        var body = """
                {
                    \"description\" : \"teste\",
                    \"author_email\" : \"user@gmail.com\",
                    \"address\" : {
                        \"zip_code\": \"27900000\",
                        \"district\": { \"name\": \"centro\" },
                        \"pub_place\": \"rua a\",
                        \"reference\": ""
                    },
                    \"dept\" : {
                        \"name\" : \"Secretaria Adjunta de Saneamento\"
                    },
                    \"duty\" : {
                        \"name\" : \"Bueiro sem tampa\"
                    }
                }
                """;

        var request = HttpRequest.newBuilder( URI.create( "http://localhost:8080/anonymous/call/new" ) )
                                 .POST( BodyPublishers.ofString( body ) )
                                 .header( "content-type", "application/json" )
                                 .build();

        HttpClient.newHttpClient().send( request, BodyHandlers.ofString() );
    }
}
