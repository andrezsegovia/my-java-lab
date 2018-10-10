package com.kode.demo.cdiee.profile;

@Profile(ProfileType.ADMIN)
public class ImplAdmin implements UserProfile {

    @Override
    public ProfileType type() {
        System.out.println("User is admin");
        return ProfileType.ADMIN;
    }
}
