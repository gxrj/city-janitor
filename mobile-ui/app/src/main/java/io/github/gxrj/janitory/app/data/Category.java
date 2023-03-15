package io.github.gxrj.janitory.app.data;

import java.io.Serializable;

public class Category implements Serializable {
    public Long id;
    public String description;

    Category( Long id, String description ) {
        this.id = id;
        this.description = description;
    }
}
