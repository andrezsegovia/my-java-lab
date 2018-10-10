package com.kode.demo.cdiee;

import javax.enterprise.inject.Produces;
import java.io.Serializable;

public class UserFactory implements Serializable {

    @Produces
    public User getUser() {
        return new User("Andrez", "andrez@email.com");
    }
}
