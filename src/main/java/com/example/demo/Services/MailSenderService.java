package com.example.demo.Services;

import com.example.demo.entities.Application;
import com.example.demo.entities.userEntities.Entrant;
import com.example.demo.entities.userEntities.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private EntrantService entrantService;

    @Value("${appBaseDomain}")
    private String appBaseDomain;
    @Value("${verifyLink}")
    private String verifyLink;

    public void sendVerificationEmail(String userEmail, String hash) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(userEmail);
        simpleMailMessage.setSubject("Підтвердіть свій акаунт");

        String text = "Перейдіть за посиланням знизу, щоб підтвердити свій акаунт:\n";
        String link = appBaseDomain + verifyLink + hash;

        simpleMailMessage.setText(text + link);

        try {
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void message(String userEmail, String subject, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(userEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);

        try {
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendPassMassage(List<Application> pastedApplications) {
        List<Entrant> allEntrants = entrantService.findAll();
        List<Entrant> passedEntrants = new ArrayList<>();

        for (Application application: pastedApplications) {
            passedEntrants.add(application.getEntrant());
        }

        for (Entrant e: allEntrants) {
            if(passedEntrants.contains(e)){
                entrantService.changeRole(e.getId(), Roles.PAST.name());
                message(e.getEmail(), "Закінчення реєстрації", "Васи за численно на факультет: '" +
                        "'!");
            }else {
                entrantService.changeRole(e.getId(), Roles.NOT_PAST.name());
                message(e.getEmail(), "Закінчення реєстрації", "Нажаль ви не зачислені ні на один факультет");
            }
        }
    }
}
