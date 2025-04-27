//package com.techhub.demo.Service;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//import org.springframework.mail.MailException;
//
//@Service("otp")
//public class EmailSenderService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Value("${spring.mail.username}")
//    private String fromEmail;
//
//    public void sendEmail(String email, String subject, String body) {
//        try {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setFrom(fromEmail);
//            message.setTo(email);
//            message.setSubject(subject);
//            message.setText(body);
//            mailSender.send(message);
//            System.out.println("Mail Sent Successfully...");
//        } catch (MailException e) {
//            System.err.println("Error sending email: " + e.getMessage());
//        }
//    }
//}
