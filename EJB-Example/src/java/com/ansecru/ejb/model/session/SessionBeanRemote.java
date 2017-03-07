/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ansecru.ejb.model.session;

import com.ansecru.ejb.model.entity.Users;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author ansecru
 */
@Remote
public interface SessionBeanRemote {

    public List<Users> getUsers();

    public void addUser(Users user);
    
    public String getName();
    
}
