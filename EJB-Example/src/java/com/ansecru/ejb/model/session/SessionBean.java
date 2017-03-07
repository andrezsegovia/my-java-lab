/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ansecru.ejb.model.session;

import com.ansecru.ejb.model.entity.Users;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ansecru
 */
@Stateless
public class SessionBean implements SessionBeanRemote {

    @PersistenceContext(unitName="EJB-ExamplePU")
    private EntityManager entityManager;
        
    @Override
    public void addUser(Users user) {
        entityManager.persist(user);
    }

    @Override
    public List<Users> getUsers() {
        return entityManager.createQuery("Select u From Users u").getResultList();
    }

    @Override
    public String getName() {
        return "Hello World fonr a session bean";
    }
}
