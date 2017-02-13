package edu.cpp.cs580;

import edu.cpp.cs580.manager.UsersManager;
import edu.cpp.cs580.service.EmailService;
import edu.cpp.cs580.util.Users;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
    
    
    @Autowired
    EmailService service;   
    
    @Test
    public void testgetSMSAddress(){
    	 assertTrue(service.getSMSAddress("ATT","6262418445").equals("6262418445@txt.att.net"));
    	 assertTrue(service.getSMSAddress("Boost Mobile","6262418445").equals("6262418445@myboostmobile.com"));
    	 assertTrue(service.getSMSAddress("Sprint","6262418445").equals("6262418445@messaging.sprintpcs.com"));
    	 assertTrue(service.getSMSAddress("TMobile","6262418445").equals("6262418445@tmomail.net"));
    	 assertTrue(service.getSMSAddress("Verizon","6262418445").equals("6262418445@vtext.com"));
    	 assertTrue(service.getSMSAddress("","6262418445").equals("6262418445@vtext.com"));
    }
    
    
    
}
