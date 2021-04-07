package com.email.demo;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

@Controller
public class SimpleEmailExampleController {
    @Autowired
    public JavaMailSender mailSender;

    @ResponseBody
    @RequestMapping("/sendSimpleEmail")
    public String sendSimpleEmail() {

        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("phongtv@ttc-solutions.com.vn");
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email");

        // Send Message!
        this.mailSender.send(message);

        return "Email Sent!";
    }
    @ResponseBody
    @RequestMapping("/sendSimpleEmail-with-attract")
    public String sendMailWithAttachment(String fileToAttach) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        String mailTo = "phongtv@ttc-solutions.com.vn";

        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(mailTo);
            helper.setSubject("sdas");
            helper.setText("hahsasd");
            FileSystemResource file = new FileSystemResource("C:\\Users\\phongtv\\Desktop\\Talens Genera (1).png");
            helper.addAttachment(Objects.requireNonNull(file.getFilename()), file);

        }catch (MessagingException e) {
            throw new MailParseException(e);
        }
        mailSender.send(message);
        return "thanh cong";
    }
}
