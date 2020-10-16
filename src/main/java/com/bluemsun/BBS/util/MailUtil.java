package com.bluemsun.BBS.util;

import com.bluemsun.BBS.entity.User;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class MailUtil {

    public static void SendTextMial(User user,String verificationCode) throws GeneralSecurityException, IOException, MessagingException {
        InputStream in = MailUtil.class.getResourceAsStream("/mail.properties");
        Properties properties = new Properties();
        properties.load(in);
        //关于QQ邮箱，还要设置SSL加密
        MailSSLSocketFactory mailSSLSocketFactory = new MailSSLSocketFactory();
        mailSSLSocketFactory.setTrustAllHosts(true);
        properties.put("mail.stmp.ssl.socketFactory", mailSSLSocketFactory);
        properties.put("mail.smtp.ssl.enable", "true");
        final String username = properties.getProperty("QQmail.username");
        final String password = properties.getProperty("QQmail.password");
        String host = properties.getProperty("QQmail.host");
        //使用JavaMail发送邮件的5个步骤
        //创建定义整个应用程序所需要的环境信息的 Session 对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //发件人邮箱用户名、授权码
                return new PasswordAuthentication(username, password);
            }
        });
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2.通过session得到transport对象
        Transport transport = session.getTransport();
        //3.使用邮箱的用户名和授权码连上邮件服务器
        transport.connect(host, username, password);
        //4.创建邮件
        //创建邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);
        //指明邮件的发件人
        mimeMessage.setFrom(new InternetAddress(username));
        //指明邮件的收件人
        mimeMessage.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(user.getEmail())});
        //邮件的标题
        mimeMessage.setSubject("尊敬的用户您好！欢迎加入蓝旭BBS");
        //邮件的内容
        mimeMessage.setContent("您的验证码为:"+verificationCode+"<h1>有效期为10分钟，请尽快完成邮箱绑定<h1/>"+"<h1>如有任何问题，请联系客服：18037029697<h1/>", "text/html;charset=UTF-8");
        //5.发送邮件
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
    }
}
