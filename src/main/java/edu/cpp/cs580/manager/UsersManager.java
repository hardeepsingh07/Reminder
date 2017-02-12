package edu.cpp.cs580.manager;

import edu.cpp.cs580.util.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hardeepsingh on 2/8/17.
 */
public interface UsersManager extends CrudRepository<Users, Integer> {

    List<Users> findById(String id);
    List<Users> findByName(String name);
    List<Users> findByEmail(String email);
    List<Users> findByNumber(String number);
    List<Users> findByVcode(String vcode);

}
