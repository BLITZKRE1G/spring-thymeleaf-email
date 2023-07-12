package personal.project.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import personal.project.email.model.ApprovalRequest;
import personal.project.email.service.MailService;

@Slf4j
@RestController
@RequestMapping(value = "/mail-service")
public class MailController {

    @Autowired
    MailService service;

    @ResponseBody
    @PostMapping(value = "/approval-mail")
    public String sendApprovalMail(@RequestBody ApprovalRequest approvalRequest) {
        try {
            log.info("Sending Approval Mail to: {}", approvalRequest.getMailId());
            service.sendApprovalMail(approvalRequest);
        } catch (Exception exception) {
            log.info("Exception Encountered!");
            return "ABORTED";
        }
        return "Mail Sent!\nPort Terminated for further use";
    }

    @ResponseBody
    @PostMapping(value = "/basic-mail")
    public String sendBasicMail(@RequestParam String toEmail, @RequestParam String subject, @RequestParam String emailText) {
        try {
            log.info("Sending Mail without Templates");
            service.sendBasicEmail(toEmail, subject, emailText);
        } catch (Exception exception) {
            log.info("Exception Encountered while sending");
            return "Exception Encountered while sending\nABORTED";
        }
        return "Mail Sent!\nPort Terminated for Further use!";
    }
}
