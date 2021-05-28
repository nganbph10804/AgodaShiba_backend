package com.vn.jav.henllo.Service;


import com.vn.jav.henllo.Model.Users;
import com.vn.jav.henllo.Repository.UserRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;

@Service
public class EmailService {
    private final UserRepository userRes;
    @Autowired
    public EmailService(UserRepository userRes){
        this.userRes =userRes;
    }
    @Autowired
   private JavaMailSender mailSender;

    public void sendMail  (@PathVariable String to,@PathVariable String pass) throws MessagingException {
        String from = "nganbph10804@fpt.edu.vn";


        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,false);

        helper.setSubject("Recovery your password account from AGODA SHIBA!");
        helper.setFrom(from);
        helper.setTo(to);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here is Your Password: ").append("<b>").append(pass).append("</b>")
                .append("<br/>").append("Thank for use our Service!");

        helper.setText(stringBuilder.toString(),true);

//        helper.setText("<b>Dear friend</b>,<br><i>Please find the book attached.</i>", true);
//
//        FileSystemResource file = new FileSystemResource(new File("Book.pdf"));
//        helper.addAttachment("FreelanceSuccess.pdf", file);

        mailSender.send(message);
    }

}
