package it.unibs.core.entity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class User {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
