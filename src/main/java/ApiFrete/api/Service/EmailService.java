package ApiFrete.api.Service;



import ApiFrete.api.domain.Shipments.ShipmentStatus;
import ApiFrete.api.utils.TemplateEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(String client_email, Long id, ShipmentStatus status) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("ernandesventura@gmail.com");
            message.setTo(client_email);
            message.setSubject("Atualização do Seu pedido de id:" + id);
            switch (status) {
                case SENT -> message.setText(TemplateEmail.EmailSent);
                case IN_TRANSIT -> message.setText(TemplateEmail.EmailInTransit);
                case DELIVERED -> message.setText(TemplateEmail.EmailDelivered);
            }
            emailSender.send(message);
        } catch (MailException e) {
        }
    }
}
