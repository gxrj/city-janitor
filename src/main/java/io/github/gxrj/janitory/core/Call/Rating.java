package io.github.gxrj.janitory.core.Call;

import jakarta.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Rating {
    
    private String ratingDescription;
}
