package io.github.gxrj.janitory.core.Action;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Rating implements Serializable {
    
    private String ratingDescription;
}
