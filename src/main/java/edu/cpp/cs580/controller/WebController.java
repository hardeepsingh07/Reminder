package edu.cpp.cs580.controller;

import edu.cpp.cs580.manager.BillManager;
import edu.cpp.cs580.manager.UsersManager;
import edu.cpp.cs580.security.CustomUser;
import edu.cpp.cs580.security.CustomUserService;
import edu.cpp.cs580.service.EmailService;
import edu.cpp.cs580.util.Bill;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
public class WebController {


    @Autowired
    UsersManager usersManager;

    @Autowired
    BillManager billManager;

    @Autowired
    EmailService service;

    @Autowired
    CustomUserService customUserService;

    //Process registration
    @RequestMapping(value = "/processRegistration/{rName}", method = RequestMethod.GET)
    String register(@PathVariable("rName") String rName,
                    @RequestParam("rEmail") String rEmail,
                    @RequestParam("rPassword") String rPassword,
                    @RequestParam("rProvider") String rProvider,
                    @RequestParam("rNumber") String rNumber) {

        try {
            String code = service.registerUser(rName, rEmail, rProvider, rNumber);

            //save to the database make a new entry
            Users users = new Users(rName, rEmail, rPassword, rProvider, rNumber, code, false, "");
            usersManager.save(users);
        } catch (Exception e) {
            System.out.println(e.toString());
            return "error";
        }
        return "success";
    }

    //Verify Code
    @RequestMapping(value = "/validateCode/{vCode}", method = RequestMethod.GET)
    String validateCode(@PathVariable("vCode") String vCode) {
        ArrayList<Users> usersList = (ArrayList<Users>) usersManager.findByVcode(vCode);
        if (!usersList.isEmpty()) {
            Users users = usersList.get(0);
            //Reset Verification Code once verified to --> 000000
            users.setVcode("000000");
            users.setVerified(true);
            usersManager.save(users);
            return users.getName() + " (" + users.getEmail() + ")";
        }
        return "invalid";
    }

    //Add Bill Info
    @RequestMapping(value = "/bill/{name}", method = RequestMethod.GET)
    String saveBill(@PathVariable("name") String name,
                    @RequestParam("amount") String amount,
                    @RequestParam("duedate") String duedate) {

        try {
            Date initDate = new SimpleDateFormat("MM/dd/yyyy").parse(duedate);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = formatter.parse(formatter.format(initDate));

            Bill bill = new Bill(name, amount, parsedDate, false);
            billManager.save(bill);

            //connect the bill to user
            Users users = customUserService.getCurrentuser();
            users.setBills(users.getBills() + bill.getId() + ":");
            usersManager.save(users);
        } catch (Exception e) {
            System.out.println(e.toString());
            return "error";
        }
        return "success";
    }

    //Change Paid status of bill
    @RequestMapping(value = "/bill/{id}", method = RequestMethod.POST)
    String updateBill(@PathVariable("id") String id) {
        ArrayList<Bill> bills = (ArrayList<Bill>) billManager.findById(Integer.parseInt(id));
        if(!bills.isEmpty()) {
            Bill bill = bills.get(0);
            bill.setStatus(true);
            billManager.save(bill);
            return "success";
        }
        return "error";
    }

    //Update User Profile
    @RequestMapping(value="/userupdate/{name}", method = RequestMethod.POST)
    String updateUserProfile(@PathVariable("name") String name,
                             @RequestParam("email") String email,
                             @RequestParam("password") String password,
                             @RequestParam("number") String number,
                             @RequestParam("serviceprovider") String serviceprovider) {
        Users users = customUserService.getCurrentuser();
        if(!name.equals(users.getName())) { users.setName(name); }
        if(!email.equals(users.getEmail())) { users.setEmail(email); }
        if(!password.equals("")) { users.setPassword(password); }
        if(!number.equals(users.getNumber())) { users.setEmail(number); }
        if(!serviceprovider.equals(users.getServiceProvider())) { users.setServiceProvider(serviceprovider); }

        try {
            usersManager.save(users);
        } catch (Exception e) {
            return "error";
        }
        return "success";
    }

    //Delete all bills
    @RequestMapping(value = "/clearbills", method = RequestMethod.GET)
    String deleteAllBills() {
        try {
           Users users = customUserService.getCurrentuser();
           users.setBills("");
           usersManager.save(users);
        } catch (Exception e) {
            return "error";
        }
        return "success";
    }

    //Delete single bill
    @RequestMapping(value = "/bill/{id}", method = RequestMethod.DELETE)
    String deleteBill(@PathVariable("id") String id) {
        try {
            Users users = customUserService.getCurrentuser();
            String bills = users.getBills();
            bills = bills.replace(id + ":", "");
            users.setBills(bills);
            usersManager.save(users);

//            billManager.delete(Integer.parseInt(id));
        } catch (Exception e) {
            return "error";
        }
        return "success";
    }



    //--------------------------------------Load Page View------------------------------------
    //Home
    @RequestMapping(value = "/home")
    ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }

    //Login
    @RequestMapping(value = "/login")
    ModelAndView logIn() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    //Registration
    @RequestMapping(value = "/registration")
    ModelAndView loadRegistration() {
        ModelAndView modelAndView = new ModelAndView("registration");
        return modelAndView;
    }

    //Verification
    @RequestMapping(value = "/verification")
    ModelAndView verification() {
        ModelAndView modelAndView = new ModelAndView("verification");
        return modelAndView;
    }

    //Load Main Dashboard
    @RequestMapping(value = "/dashboard")
    ModelAndView update() {
        Users users = customUserService.getCurrentuser();
        ArrayList<Bill> userBiills = new ArrayList<>();
        String billString = users.getBills();
        if(!billString.equals("")) {
            String[] token = users.getBills().split(":");
            for (int i = 0; i < token.length; i++) {
                userBiills.add(billManager.findById(Integer.parseInt(token[i])).get(0));
            }
        }
        ModelAndView modelAndView = new ModelAndView("dashboard");
        modelAndView.addObject("bills", userBiills);
        modelAndView.addObject("currentuser", customUserService.getCurrentuser());
        return modelAndView;
    }
}