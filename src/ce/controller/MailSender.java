package ce.controller;

import ce.model.Transaction;
import ce.model.User;

import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Stateless
public class MailSender {

    public static final String FROM = "currencyexchange@mail.com";
    public static final String SUBJECT = "New transaction on Currency Exchange";
    public static final String BODY = "Hi %s,\n" +
            "New transaction has been performed on your order %s.\n" +
            "User %s made a transaction on %s.\n" +
            "Email: %s, Tel: %s\n" +
            "Currency Exchange";

    public static final String USERNAME = "currencyexchangeteam@yahoo.com";
    public static final String PASSWORD = "cUr5encYE%change";
    public static final String AUTH = "true";
    public static final String STARTTLS = "true";
    public static final String HOST = "smtp.mail.yahoo.com";
    public static final String PORT = "587";


    private Transaction transaction;

    public void sendMail(Transaction transaction){
        User dealer = transaction.getOrder().getDealer();
        if (dealer.getNotifyViaMail()){
            this.transaction = transaction;
            sendMail();
        }
    }

    private void sendMail() {
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
        props.put("mail.smtp.auth", AUTH);
        props.put("mail.smtp.starttls.enable", STARTTLS);
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
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
            ignored.printStackTrace();
        }
    }
}
