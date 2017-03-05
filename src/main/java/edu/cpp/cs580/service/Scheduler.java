package edu.cpp.cs580.service;

import edu.cpp.cs580.manager.BillManager;
import edu.cpp.cs580.manager.UsersManager;
import edu.cpp.cs580.util.Bill;
import edu.cpp.cs580.util.Users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hardeepsingh on 3/4/17.
 */
@Component
public class Scheduler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UsersManager usersManager;

    @Autowired
    BillManager billManager;

    @Autowired
    EmailService emailService;

    String billSubject = "Bill Reminder: ";

    @Scheduled(fixedRate = 86400000)
    public void lessThan7() throws Exception {
        //Get all Bills
        List<Bill> bills = (ArrayList<Bill>) billManager.findAll();

        //Set Date format and get today's date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayDate = LocalDate.now().toString();

        //Parse through the bills to get check number of days till due date
        for (Bill b : bills) {
            LocalDate startDate = LocalDate.parse(todayDate, formatter);
            LocalDate dueDate = LocalDate.parse(b.getFormattedDate(), formatter);
            long numberOfDaysLeft = ChronoUnit.DAYS.between(startDate, dueDate);

            //If 7 days left, remind once
            if (numberOfDaysLeft <= 7) {
                Users user = usersManager.findOne(b.getUserid());
                emailService.sendSMS(user.getNumber(), user.getServiceProvider(),
                        billSubject,
                        "Your bill for " + b.getName() + " of amount $" + b.getAmount() + " is due within " + numberOfDaysLeft + " days. \n Bill Due Date: "
                                + b.getTextFormattedDate());
                logger.info("BILL - {} notified to USER - {} ::: DUE DATE - {}", b.getName(), user.getId(), b.getTextFormattedDate());
            }
        }
    }

    @Scheduled(initialDelay = 43200000, fixedRate = 86400000)
    public  void lessThan5() {
        //Get all Bills
        List<Bill> bills = (ArrayList<Bill>) billManager.findAll();

        //Set Date format and get today's date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayDate = LocalDate.now().toString();

        //Parse through the bills to get check number of days till due date
        for (Bill b : bills) {
            LocalDate startDate = LocalDate.parse(todayDate, formatter);
            LocalDate dueDate = LocalDate.parse(b.getFormattedDate(), formatter);
            long numberOfDaysLeft = ChronoUnit.DAYS.between(startDate, dueDate);

            //If 7 days left, remind once
            if (numberOfDaysLeft <= 5) {
                Users user = usersManager.findOne(b.getUserid());
                emailService.sendSMS(user.getNumber(), user.getServiceProvider(),
                        billSubject,
                        "Your bill for " + b.getName() + " of amount $" + b.getAmount() + " is due within " + numberOfDaysLeft + " days. \n Bill Due Date: "
                                + b.getTextFormattedDate());
                logger.info("BILL - {} notified to USER - {} ::: DUE DATE - {}", b.getName(), user.getId(), b.getTextFormattedDate());
            }
        }
    }

    @Scheduled(initialDelay = 21600000, fixedRate = 86400000)
    public  void lessThan3() {
        //Get all Bills
        List<Bill> bills = (ArrayList<Bill>) billManager.findAll();

        //Set Date format and get today's date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayDate = LocalDate.now().toString();

        //Parse through the bills to get check number of days till due date
        for (Bill b : bills) {
            LocalDate startDate = LocalDate.parse(todayDate, formatter);
            LocalDate dueDate = LocalDate.parse(b.getFormattedDate(), formatter);
            long numberOfDaysLeft = ChronoUnit.DAYS.between(startDate, dueDate);

            //If 7 days left, remind once
            if (numberOfDaysLeft <= 3) {
                Users user = usersManager.findOne(b.getUserid());
                emailService.sendSMS(user.getNumber(), user.getServiceProvider(),
                        billSubject,
                        "Your bill for " + b.getName() + " of amount $" + b.getAmount() + " is due within " + numberOfDaysLeft + " days. \n Bill Due Date: "
                                + b.getTextFormattedDate());
                logger.info("BILL - {} notified to USER - {} ::: DUE DATE - {}", b.getName(), user.getId(), b.getTextFormattedDate());
            }
        }
    }
}
