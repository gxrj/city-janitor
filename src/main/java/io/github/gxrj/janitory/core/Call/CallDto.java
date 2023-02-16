package io.github.gxrj.janitory.core.Call;

import io.github.gxrj.janitory.core.Address.Address;
import io.github.gxrj.janitory.core.Dept.Dept;
import io.github.gxrj.janitory.core.Duty.Duty;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties( value = "createdAt", allowGetters = true )

public class CallDto implements Serializable {

    String status;
    String protocol;
    String description;
    String authorEmail;
    String createdAt;
    Address address;
    Dept destination;
    Duty duty;
}
