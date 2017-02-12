package edu.cpp.cs580.controller;

import edu.cpp.cs580.manager.UsersManager;
import edu.cpp.cs580.service.EmailService;
import edu.cpp.cs580.util.Users;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;

import java.net.SocketPermission;
import java.util.ArrayList;

@RestController
public class WebController {

    @Autowired
    UsersManager usersManager;

    @Autowired
    EmailService service;

    private static final Logger Logger = LoggerFactory.getLogger(WebController.class);

    @RequestMapping(value = "/databasetesting", method = RequestMethod.GET)
    ArrayList<Users> databaseTesting() {
        //ArrayList<Users> userss1 = (ArrayList<Users>) usersManager.findByName("Hardeep Singh");
        ArrayList<Users> userss = (ArrayList<Users>) usersManager.findAll();
        return userss;
    }


    @RequestMapping(value = "/valid/{uName}", method = RequestMethod.GET)
    String validateInput(@PathVariable("uName") String uName) {
        if (uName.contains("@")) {
            return "Valid";
        } else {
            return "Invalid";
        }
    }

    @RequestMapping(value = "/encrypt/{password}", method = RequestMethod.GET)
    String encrypt(@PathVariable("password") String password) {
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String encryptedPassword = passwordEncryptor.encryptPassword(password);
        return encryptedPassword;
    }

    @RequestMapping(value = "/sendsms")
    ModelAndView loadSendEmail() {
        ModelAndView modelAndView = new ModelAndView("sms");
        return modelAndView;
    }

    @RequestMapping(value = "/processSMS/{number}", method = RequestMethod.GET)
    String sendEmail(@PathVariable("number") String number,
                     @RequestParam("provider") String provider,
                     @RequestParam("subject") String subject,
                     @RequestParam("message") String message) {

        //Use service class to send email
        try {
            service.sendSMS(number, provider, subject, message);
        } catch (Exception e) {
            return "error";
        }
        return "success";
    }

    //Registration
    @RequestMapping(value = "/registration")
    ModelAndView loadRegistration() {
        ModelAndView modelAndView = new ModelAndView("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/processRegistration/{rName}", method = RequestMethod.GET)
    String register(@PathVariable("rName") String rName,
                    @RequestParam("rEmail") String rEmail,
                    @RequestParam("rPassword") String rPassword,
                    @RequestParam("rProvider") String rProvider,
                    @RequestParam("rNumber") String rNumber) {

        String code;
        Users users;
        try {
            code = service.registerUser(rName, rEmail, rProvider, rNumber);

            //save to the database make a new entry
            users = new Users(rName, rEmail, rPassword, rProvider, rNumber, code, false);
            usersManager.save(users);
        } catch (Exception e) {
            return "error";
        }
        return "success";
    }

    //load validation
    @RequestMapping(value = "/verificationCode")
    ModelAndView verificationCode() {
        ModelAndView modelAndView = new ModelAndView("verificationCode");
        return modelAndView;
    }

    //Verify Code
    @RequestMapping(value = "/validateCode/{vCode}", method = RequestMethod.GET)
    String validateCode(@PathVariable("vCode") String vCode) {
        ArrayList<Users> usersList = (ArrayList<Users>) usersManager.findByVcode(vCode);
        if(!usersList.isEmpty()) {
            Users users = usersList.get(0);
            users.setVerified(true);
            usersManager.save(users);
            return users.getName() + " (" + users.getEmail() + ")";
        }
        return "invalid";
    }
    
    @RequestMapping(value = "/log/{logString}", method = RequestMethod.GET)
    String logger(@PathVariable("logString") String logString) {
        Logger.debug(logString);
        return "Successfully Logged " + logString;
    }

    @RequestMapping(value = "/login")
    ModelAndView logIn() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @RequestMapping(value = "/success")
    ModelAndView success() {
        ModelAndView modelAndView = new ModelAndView("success");
        return modelAndView;
    }


    @RequestMapping(value = "/logOutSuccess")
    ModelAndView logOutSuccessMV() {
        ModelAndView modelAndView = new ModelAndView("logOutSuccess");
        return modelAndView;
    }

    @RequestMapping(value = "/forgotPassword")
    ModelAndView forgotPasswordMV() {
        ModelAndView modelAndView = new ModelAndView("forgotPassword");
        return modelAndView;
    }
}

	 
	
