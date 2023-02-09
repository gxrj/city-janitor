package io.github.gxrj.janitory.core.Dept;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotEmpty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonNaming( PropertyNamingStrategies.SnakeCaseStrategy.class )
@JsonIgnoreProperties( value = "newName", allowSetters = true, ignoreUnknown = true )
public class DeptDto implements Serializable {

    @NotEmpty
    public String name;
    public String newName;

    @JsonCreator
    public DeptDto( 
        @JsonProperty( "name" ) String name, 
        @JsonProperty( "new_name" ) String newName ) {
        this.name = name;
        this.newName = newName;
    }
}
