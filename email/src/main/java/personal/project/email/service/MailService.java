package personal.project.email.service;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import personal.project.email.model.ApprovalRequest;

@Slf4j
@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${fromEmail}")
    private String fromEmail; //Fetched the value from properties to not having to write every time
    //Even if the mailId gets changed, we can change the value in the property alone and the rest eill be taken care of

    public void sendApprovalMail(ApprovalRequest approvalRequest) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        mimeMessageHelper.setTo(approvalRequest.getMailId());
        mimeMessageHelper.setSubject("APPROVAL MAIL");
        mimeMessageHelper.setFrom(fromEmail);

        Context context = new Context();
        Map<String, Object> properties = new HashMap<>();
        properties.put("fullName", approvalRequest.getFullName());
        properties.put("firstName", approvalRequest.getFirstName());
        context.setVariables(properties);

        String htmlContent = templateEngine.process("approval-mail.html", context);
        mimeMessageHelper.setText(htmlContent, true);
        log.info("Sending Approval Mail to: [{}]", approvalRequest.getMailId());
        mailSender.send(mimeMessage);
    }

    public void sendBasicEmail(String toEmail, String subject, String bodyText) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        mimeMessageHelper.setFrom(fromEmail);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(bodyText);//Taking the message body from the User
        
        log.info("Sending Approval Mail from: [{}]", fromEmail); //added Logs
        log.info("Sending Approval Mail to: [{}]", approvalRequest.getMailId());
        
        mailSender.send(mimeMessage);
    }
}
