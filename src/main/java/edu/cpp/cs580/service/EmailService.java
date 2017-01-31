package edu.cpp.cs580.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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

        //send email via SMS
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(sendEmail);
        mailMessage.setFrom(appEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        javaMailSender.send(mailMessage);
    }
}
