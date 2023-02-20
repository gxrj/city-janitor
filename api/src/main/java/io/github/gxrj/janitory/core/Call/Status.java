package io.github.gxrj.janitory.core.Call;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Getter;

@Getter

@JsonRootName( "status" )
public enum Status {

    // Auto generated when the call is created
    PROCESSING( "Em andamento" ),
    // Set by the public agent
    FORWARDED( "Encaminhada" ),
    ANSWERED( "Respondida" ),
    REJECTED( "Indeferida" ),
    // Set by the citizen
    NOT_SOLVED( "Não resolvida" ),
    FINISHED( "Finalizada" );

    private final String value;

    Status( String value ) {
        this.value = value;
    }

    @Override
    public String toString() { return value; }

    public static Status fromString( String status ) {
        return switch( status ) {
            case "Encaminhada" -> Status.FORWARDED;
            case "Respondida" -> Status.ANSWERED;
            case "Indeferida" -> Status.REJECTED;
            case "Não resolvida" -> Status.NOT_SOLVED;
            case "Finalizada" -> Status.FINISHED;
            default -> Status.PROCESSING;
       };
    }
}
