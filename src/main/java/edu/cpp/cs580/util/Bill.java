package edu.cpp.cs580.util;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Created by hardeepsingh on 2/15/17.
 */
@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String amount;
    private Date duedate;
    private boolean status;
    private int userid;

    public Bill() {}

    public Bill(String name, String amount, Date duedate, boolean status, int userid) {
        this.name = name;
        this.amount = amount;
        this.duedate = duedate;
        this.status = status;
        this.userid = userid;
    }

    public String getFormattedDate() {
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
        return output.format(this.getDuedate());
    }

    public String getTextFormattedDate() {
        SimpleDateFormat output = new SimpleDateFormat("MMM dd, yyyy");
        return output.format(this.getDuedate());
    }

    public long getNumberOfDays() {
        //Set Date format and get today's date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = LocalDate.now().toString();
        String billDate =  output.format(this.getDuedate());

        LocalDate startDate = LocalDate.parse(todayDate, formatter);
        LocalDate dueDate = LocalDate.parse(billDate, formatter);
        long numberOfDaysLeft = ChronoUnit.DAYS.between(startDate, dueDate);

        return numberOfDaysLeft;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
