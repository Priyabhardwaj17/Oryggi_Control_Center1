package com.oryggi.utils;

import java.util.Properties;

import jakarta.mail.Flags;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.search.FlagTerm;

public class EmailUtils {
	private static final String HOST = "imap.gmail.com";
	private static final String USERNAME = "oryggiserver@gmail.com";
	private static final String PASSWORD = "qmoqtdboeqtjugrf";
	
	public static String getLatestOtp()  {
		try {
		Properties properties = new Properties();
		properties.put("mail.store.protocol", "imaps");
		
		Session session =  Session.getInstance(properties);
		Store store = session.getStore("imaps");
		store.connect(HOST, USERNAME, PASSWORD);
		
		Folder inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_WRITE);
		
		//fetch ubread mails
		Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN),false));
		
		
		for(Message message : messages) {
			String subject = message.getSubject();
			if(subject.contains("OTP to reset Password")) {
				String content = message.getContent().toString();
				String otp = content.replaceAll("[^0-9]", "");
				  System.out.println("Extracted OTP: " + otp);
				message.setFlag(Flags.Flag.SEEN, true);
				return otp;
			}
			
		}
		inbox.close(false);
		store.close();
	}catch (Exception e) {
		e.printStackTrace();
	}
		return null;
  }
}
