package it.unibs.core.businessLogic.Users;

import it.unibs.core.businessLogic.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Manager extends User {


    public Manager(String username, String password) {
        super(username, password);
    }
}