/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.util;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author lucia
 */
public class Email {
    
    private static Properties props = new Properties();
    
    public static Properties getProps(){
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port","465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketfactory");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.port","465");
        return props;
    }
    public static void enviarEmail(String titulo, String corpo, String destinatario) throws javax.mail.MessagingException {
        Session session; 
        session = Session.getDefaultInstance(getProps(), 
                new javax.mail.Authenticator(){
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication("luhelenapereira@gmail.com", "XXXXXXXX");
                    }
                });
        session.setDebug(true);
        try{
        Message message =  new MimeMessage (session);
        message.setFrom(new InternetAddress("luhelenapereira@gmail.com"));
        InternetAddress[] toUser = InternetAddress.parse(destinatario);
        message.setRecipients(Message.RecipientType.TO, toUser);
        message.setSubject(titulo);
        message.setText(corpo);
        Transport.send(message);
        }catch(MessagingException e){
            throw new RuntimeException (e);
        }
    }

    
    
}
