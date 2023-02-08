package io.github.gxrj.janitory.core.Call;

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
}
