package kg.alatoo.libraryapp.services;

public interface EmailSender {
    void sendEmail(String toEmail, String subject, String body);
}
