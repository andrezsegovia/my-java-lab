package com.ansecru.ws;

import org.springframework.data.repository.CrudRepository;
import com.ansecru.ws.Users;

public interface UserRepository extends CrudRepository<Users, Long> {

}
