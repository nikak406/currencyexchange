package ce.controller;

import ce.model.Order;
import ce.model.Transaction;
import ce.model.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {

    public static final String FROM = "currencyexchange@mail.com";
    public static final String SUBJECT = "New transaction on Currency Exchange";
    public static final String BODY = "Hi %s,\n" +
            "New transaction has been performed on your order %s.\n" +
            "User %s made a transaction on %s.\n" +
            "Email: %s, Tel: %s\n" +
            "Currency Exchange";

    private Transaction transaction;

    public MailSender(Transaction transaction) {
        this.transaction = transaction;
    }

    static void sendMail(Transaction transaction){
        Order order = transaction.getOrder();
        User dealer = order.getDealer();
        if (dealer.getNotifyViaMail()){
            new MailSender(transaction).sendMail();
        }

    }

    private void sendMail() {
        final String username = "currencyexchange@mail.com";
        final String password = "password";

        String emailTo = transaction.getOrder().getDealer().getEmail();
        String orderInfo = transaction.getOrder().toString();
        String customerName = transaction.getCustomer().getName();
        String customerEmail = transaction.getCustomer().getName();
        String customerPhone = transaction.getCustomer().getName();
        int transactionAmount = transaction.getAmount();
        String currency = transaction.getOrder().getCurrency();
        String dealerName = transaction.getOrder().getDealer().getName();

        String body = String.format(BODY, dealerName, orderInfo, customerName,
                "" + transactionAmount + ' ' + currency, customerEmail, customerPhone);

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.mail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });


        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
            message.setSubject(SUBJECT);
            message.setText(body);
            Transport.send(message);
        } catch (MessagingException ignored) {
        }
    }
}
