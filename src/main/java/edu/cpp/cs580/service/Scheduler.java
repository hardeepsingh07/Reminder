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

        //Parse through the bills to get check number of days till due date
        for (Bill b : bills) {
            //If 7 days left, remind once, if status is not paid (false)
            long numberOfDaysLeft = b.getNumberOfDays();
            boolean isPaid = b.isStatus();
            logger.info("Status: {} and Days: {}", isPaid, numberOfDaysLeft);
            if (!isPaid && numberOfDaysLeft <= 7 && numberOfDaysLeft >= 0) {
                reminder(b, numberOfDaysLeft);
            }

            //Reminder User last time for their overdue bill
            finalReminder(b, numberOfDaysLeft);
        }
    }

    @Scheduled(initialDelay = 43200000, fixedRate = 86400000)
    public void lessThan5() {
        //Get all Bills
        List<Bill> bills = (ArrayList<Bill>) billManager.findAll();

        //Parse through the bills to get check number of days till due date
        for (Bill b : bills) {
            //If 5 days left, remind twice, if status is not paid (false)
            long numberOfDaysLeft = b.getNumberOfDays();
            boolean isPaid = b.isStatus();
            if (!isPaid && numberOfDaysLeft <= 5 && numberOfDaysLeft >= 0) {
                reminder(b, numberOfDaysLeft);
            }

            //Reminder User last time for their overdue bill
            finalReminder(b, numberOfDaysLeft);
        }
    }

    @Scheduled(initialDelay = 21600000, fixedRate = 86400000)
    public void lessThan3() {
        //Get all Bills
        List<Bill> bills = (ArrayList<Bill>) billManager.findAll();

        //Parse through the bills to get check number of days till due date
        for (Bill b : bills) {
            //If 2 days left, remind four times, if status is not paid (false)
            long numberOfDaysLeft = b.getNumberOfDays();
            boolean isPaid = b.isStatus();
            if (!isPaid && numberOfDaysLeft <= 3 && numberOfDaysLeft >= 0) {
                reminder(b, numberOfDaysLeft);
            }

            //Reminder User last time for their overdue bill
            finalReminder(b, numberOfDaysLeft);
        }
    }

    public void reminder(Bill b, long numberOfDaysLeft) {
        Users user = usersManager.findOne(b.getUserid());
        emailService.sendSMS(user.getNumber(), user.getServiceProvider(),
                billSubject,
                "Your bill for " + b.getName() + " of amount $" + b.getAmount() + " is due within " + numberOfDaysLeft + " days. \n Bill Due Date: "
                        + b.getTextFormattedDate());
        logger.info("BILL - {} notified to USER - {} ::: DUE DATE - {}", b.getName(), user.getId(), b.getTextFormattedDate());
    }

    public void finalReminder(Bill b, long numberOfDaysLeft) {
        if (numberOfDaysLeft == -1) {
            Users user = usersManager.findOne(b.getUserid());
            emailService.sendSMS(user.getNumber(), user.getServiceProvider(),
                    billSubject,
                    "LAST REMINDER: Your bill for " + b.getName() + " of amount $" + b.getAmount() + " has crossed the deadline yesterday. \n Bill Due Date: "
                            + b.getTextFormattedDate());
            logger.info("OVER DUE BILL REMINDER - {} notified to USER - {} ::: DUE DATE - {}", b.getName(), user.getId(), b.getTextFormattedDate());
        }
    }
}
