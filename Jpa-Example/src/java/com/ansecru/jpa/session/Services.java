/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ansecru.jpa.session;

import com.ansecru.jpa.entity.Users;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author ansecru
 */
public class Services {
    
    public static void main(String[] args){
        
        Services services = new Services();
    
        EntityManagerFactory emfactory = 
                Persistence.createEntityManagerFactory("database-unit");
        
        EntityManager entityManager = emfactory.createEntityManager();
        
        
        Users user = new Users();
        user.setUsername("ansecru");
        user.setFirstName("Andrez");
        user.setLastName("Segovia");
        user.setPassword("12345");
        user.setPhoneNumber("12345679-5");
        user.setEmail("andres@users.com");
                
        if(services.createUser(user, entityManager)){
           System.out.println("User creation successful");
        }else{
           System.out.println("User creation failure");
        }
        
        services.readUsers(entityManager);
        
        entityManager.close();
        emfactory.close();
        
    }
    
    @SuppressWarnings("FinallyDiscardsException")
    public boolean createUser(Users user, EntityManager entityManager){
        
                boolean state = false;
                
                try{
                    
                    entityManager.getTransaction().begin();
                    entityManager.persist(user);
                    entityManager.getTransaction().commit();
                    
                    state=true;
                    
                }catch(IllegalStateException | PersistenceException e){
                    state=false;
                    e.printStackTrace();
                }finally{
                    return state;
                }
            
    }
    
    
    public boolean readUsers(EntityManager entityManager){
        
        // Query usando JPQL
        //Query query = entityManager.createQueryn("Select u from Users u");
        
        //Query usando SQL
        Query query = entityManager.createNativeQuery("select * from users", Users.class);
        
        List<Users> userList = query.getResultList();
        
        for(Users user : userList){
            System.out.println(user);
        }
        
        return true;
        
    }
}
