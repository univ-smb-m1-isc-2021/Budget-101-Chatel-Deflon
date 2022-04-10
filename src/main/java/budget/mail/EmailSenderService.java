package budget.mail;

import budget.persistence.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;


    public void sendRecap(User user, String recap) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setFrom("gunter101bipbip@gmail.com");
        message.setSubject("Monthly recap");
        String tmp = "Voici un récapitulatif de votre budget :\n\n" + recap;
        message.setText(tmp);
        mailSender.send(message);
    }
}
