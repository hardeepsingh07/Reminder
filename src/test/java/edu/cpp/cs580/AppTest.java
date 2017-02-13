package edu.cpp.cs580;

import edu.cpp.cs580.manager.UsersManager;
import edu.cpp.cs580.util.Users;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class AppTest  {

    @Autowired
    UsersManager usersManager;

    @Before
    public void setUp() throws Exception {
        Users users = new Users();
        users.setName("DbTestUser");
        users.setEmail("DbTestUser@yahoo.com");
        users.setPassword("dbtest");
        users.setServiceProvider("Verizon");
        users.setNumber("909");
        users.setVcode("3456");
        users.setVerified(false);

        usersManager.save(users);
    }

    @Test
    public void checkSave() throws Exception{
        Users retrieved = (usersManager.findByVcode("3456")).get(0);
        assertEquals("DbTestUser@yahoo.com", retrieved.getEmail());
        assertEquals("DbTestUser", retrieved.getName());
        assertEquals(false, retrieved.isVerified());
    }

    @After
    public void deleteUser() throws Exception {
        Users retrieved = (usersManager.findByVcode("3456")).get(0);
        usersManager.delete(retrieved.getId());
    }
}
