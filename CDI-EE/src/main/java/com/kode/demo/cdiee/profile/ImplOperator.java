package com.kode.demo.cdiee.profile;

import javax.enterprise.inject.Default;

@Profile(ProfileType.OPERATOR)
@Default
public class ImplOperator implements UserProfile {

    @Override
    public ProfileType type() {
        System.out.println("User is operator");
        return ProfileType.OPERATOR;
    }
}
