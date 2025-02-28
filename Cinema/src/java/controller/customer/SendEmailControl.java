/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.customer;

import entity.User;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import controller.guest.SignupControl;

/**
 *
 * @author HP
 */
public class SendEmailControl {
    
    public String getRandom(){
        Random rnd= new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }
    
    
    public boolean sendEmail(User user, String code1){
        
        boolean test=false;
        
        String toEmail = user.getEmail();
        String fromEmail = "hoangkhoinguyenpro@gmail.com";
        String password = "gjje zadw mlos hpxy";
        
        try{
                
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.connectiontimeout", "150000");  // Timeout 150s
            props.setProperty("mail.smtp.timeout", "150000");

            //props.put("mail.smtp.socketFactory.port", "587");
            //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            
            Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
                }
            };
        
            Session session = Session.getInstance(props, auth);
            
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            
            msg.setSubject("User Email Verification");
            
            msg.setText("Registed successfully. Please verify your account using this code: "+code1);
            
            Transport.send(msg);
            test=true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return test;
 
    }
}
