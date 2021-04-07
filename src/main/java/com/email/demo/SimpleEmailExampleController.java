package com.email.demo;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

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
        String mailTo = "phongtv@ttc-solutions.com.vn";
        MimeMessage message = mailSender.createMimeMessage();

        // use the true flag to indicate you need a multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(mailTo);

        helper.setSubject("Email with Inline images Example");
        helper.setText(
                "<html>"
                        + "<body>"
                        + "<div>Dear student,"
                        + "<div><strong>Add the image to the right:</strong></div>"
                        + "<div>"
                        + "<img src='cid:rightSideImage' style='float:right;width:50px;height:50px;'/>"
                        + "<div>Adding a inline resource/image on to the right of the paragraph.</div>"
                        + "<div>Adding a inline resource/image on to the right of the paragraph.</div>"
                        + "<div>Adding a inline resource/image on to the right of the paragraph.</div>"
                        + "<div>Adding a inline resource/image on to the right of the paragraph.</div>"
                        + "</div>"
                        + "<div><strong>Add the image to the left :</strong></div>"  + "<div>"
                        + "<img src='cid:leftSideImage' style='float:left;width:50px;height:50px;'/>"
                        + "<div>Adding a inline resource/image on to the left of the paragraph.</div>"
                        + "<div>Adding a inline resource/image on to the left of the paragraph.</div>"
                        + "<div>Adding a inline resource/image on to the left of the paragraph.</div>"
                        + "<div>Adding a inline resource/image on to the left of the paragraph.</div>"
                        + "</div>"
                        + "<div>Thanks,</div>"
                        + "kalliphant"
                        + "</div></body>"
                        + "</html>", true);
        helper.addInline("rightSideImage",
                new File("C:/Users/phongtv/Desktop/Talens Genera (1).png"));

        mailSender.send(message);
        return "thanh cong";
    }
}
