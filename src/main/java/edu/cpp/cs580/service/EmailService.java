package edu.cpp.cs580.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by hardeepsingh on 1/24/17.
 */
@Service
public class EmailService {
    private JavaMailSender javaMailSender;
    private String appEmail = "reminderalertservice@gmail.com";

    @Autowired
    public EmailService(JavaMailSender javaMailSender) throws Exception {
        this.javaMailSender = javaMailSender;
    }

    public void sendSMS(String number, String provider, String subject, String message) {
        String dAddresss = getSMSAddress(provider, number);

        //send email via SMS
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(dAddresss);
        mailMessage.setFrom(appEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        javaMailSender.send(mailMessage);
    }

    public String registerUser(String name, String email, String provider, String number) {
        String dAddresss = getSMSAddress(provider, number);

        Random r = new Random();
        int low = 5000;
        int high = 10000;
        int result = r.nextInt(high - low) + low;

        //send email via SMS
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(dAddresss);
        mailMessage.setFrom(appEmail);
        mailMessage.setSubject("Verification Code: " + result);
        mailMessage.setText(name + ", Thank you for registering with Reminder");

        javaMailSender.send(mailMessage);

        return result + "";
    }

    public String getSMSAddress(String provider, String number) {
        String sendEmail;
        switch (provider) {
            case "ATT":
                sendEmail = number + "@txt.att.net";
                break;
            case "Boost Mobile":
                sendEmail = number + "@myboostmobile.com";
                break;
            case "Sprint":
                sendEmail = number + "@messaging.sprintpcs.com";
                break;
            case "TMobile":
                sendEmail = number + "@tmomail.net";
                break;
            case "Verizon":
                sendEmail = number + "@vtext.com";
                break;
            case "Virgin Mobile":
                sendEmail = number + "@vmobl.com";
                break;
            default:
                //Verizon as default
                sendEmail = number + "@vtext.com";
        }
        return sendEmail;
    }


}
