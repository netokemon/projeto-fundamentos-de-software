package com.usforus.transcare.notification;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class EmailService {

    @Value("${spring.sendgrid.api-key}")
    private String apiKey;

    @Value("${transcare.mail.from}")
    private String remetente;

    // AQUI ESTÁ O NOME CORRETO: enviarAvisoConsulta
    public void enviarAvisoConsulta(String destinatarioEmail, String nomePaciente, String nomeMedico, String dataHora) {
        Email from = new Email(remetente);
        Email to = new Email(destinatarioEmail);

        String subject = "Lembrete: Sua consulta no Transcare é amanhã!";

        String texto = "Olá " + nomePaciente + ",\n\n" +
                "Lembrete de que você tem uma consulta agendada com " + nomeMedico +
                " para amanhã, dia " + dataHora + ".\n\n" +
                "Não se esqueça!";

        Content content = new Content("text/plain", texto);

        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            if (response.getStatusCode() >= 400) {
                System.out.println("Erro SendGrid: " + response.getBody());
            } else {
                System.out.println("Email enviado para: " + destinatarioEmail);
            }

        } catch (IOException ex) {
            System.out.println("Erro de conexão: " + ex.getMessage());
        }
    }
}