package io.github.gxrj.janitory.utils;

public class PlainJson {
 
    private String plainJson;

    private PlainJson() {
        plainJson = "{ ";
    }

    public static PlainJson builder() {
        return new PlainJson();
    }

    public PlainJson append( String key, String value ) {
        plainJson += plainJson.equals( "{ " ) ? "" : ", " ;
        plainJson += "\"" + key + "\" : \"" + value + "\"" ;
        return this;
    }

    public String build() {
        return plainJson + " }"; 
    }

}
