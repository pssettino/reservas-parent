package com.reservas.dao.impl;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.mail.internet.InternetAddress;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservas.dao.MailSenderService;

@Service
@Transactional
public class MailSenderServiceImpl implements MailSenderService {

	private static Logger log = LoggerFactory.getLogger(MailSenderServiceImpl.class);

	private String hostName;
	private int hostPort;
	private String authUser;
	private String authPassword;
	private String fromName;
	private String fromEmail;

	public MailSenderServiceImpl() {
		// SystemService service = new SystemServiceImpl(); // TODO via spring?
		hostName = "";// service.findConfigurationDataByFieldName(SMTP_HOST_NAME).getFieldValue();
		hostPort = 0;// Integer.valueOf(service.findConfigurationDataByFieldName(SMTP_HOST_PORT).getFieldValue()).intValue();
		authUser = "";// service.findConfigurationDataByFieldName(SMTP_AUTH_USER).getFieldValue();
		authPassword = "";// EncryptionUtils.decrypt(service.findConfigurationDataByFieldName(SMTP_AUTH_PWD).getFieldValue());
	}

	/*
	 * public static synchronized MailSenderService getService() { return new
	 * MailSenderServiceImpl(); }
	 */

	public void sendMail(String subject, String HTML, String text, String to, String cc) throws Exception {
		sendMail(subject, HTML, text, to, cc, 0);
	}

	public void sendMail(String subject, String HTML, String text, String[] to, String[] cc) throws Exception {
		HtmlEmail email = setearEmail(subject, HTML, text, to, cc, 0, 1000);
		// send the email
		email.send();
	}

	public void sendMail(String subject, String HTML, String text, String to, String cc, int retries) throws Exception {
		sendMail(subject, HTML, text, to, cc, 0, 1000);
	}

	public void sendMail(String subject, String HTML, String text, String to, String cc, int retries, int waitInterval)
			throws Exception {
		HtmlEmail email = setearEmail(subject, HTML, text, to, cc, retries, waitInterval);
		// send the email
		email.send();
	}

	public void sendMail(String subject, String HTML, String text, String to, String cc, int retries, int waitInterval,
			ByteArrayOutputStream outputStream, String filename) throws Exception {
		HtmlEmail email = setearEmail(subject, HTML, text, to, cc, retries, waitInterval);
		// OutputStream os = null;
		// ByteArrayOutputStream baos = new ByteArrayOutputStream();
		log.debug("Preparando el attachment " + subject);

		byte[] array = outputStream.toByteArray();

		email.attach(new ByteArrayDataSource(array, "application/pdf"), filename, subject, EmailAttachment.ATTACHMENT);
		email.send();

		// DataOutputStream dos = new DataOutputStream(outputStream);

		// baos.writeTo(outputStream);
		// baos = outputStream;

		// ByteArrayOutputStream baos = (ByteArrayOutputStream)outputStream;
		// DataSource dataSource = new ByteArrayDataSource(array,
		// "application/octet-stream");
		// MimeBodyPart pdfAttachmentPart = new MimeBodyPart();
		// pdfAttachmentPart.setDataHandler(new DataHandler(dataSource));
		// MimeMultipart mimeMultipart = new MimeMultipart();
		// mimeMultipart.addBodyPart(pdfAttachmentPart);
		// log.debug("Agregamos la parte");
		// email.addPart(mimeMultipart);
		// // send the email
		// log.debug("Enviamos el mail");
		// email.send();
	}

	public void sendMail(String subject, String HTML, String text, String to, String cc, int retries, int waitInterval,
			Map<String, ByteArrayOutputStream> files) throws Exception {
		HtmlEmail email = setearEmail(subject, HTML, text, to, cc, retries, waitInterval);
		log.debug("Preparando el attachment " + subject);

		for (Entry<String, ByteArrayOutputStream> entry : files.entrySet()) {
			if (entry.getValue().toByteArray().length > 0) {
				byte[] array = entry.getValue().toByteArray();
				email.attach(new ByteArrayDataSource(array, "application/pdf"), entry.getKey(), subject,
						EmailAttachment.ATTACHMENT);
			}
		}
		email.send();

	}

	public HtmlEmail setearEmail(String subject, String HTML, String text, String to, String ccDestination, int retries,
			int waitInterval) throws Exception {
		boolean succeed = false;
		int attemps = 0;
		// Create the email message
		HtmlEmail email = new HtmlEmail();
		log.debug("Preparando el mail para " + to);

		hostName = "64.233.165.108";
		log.debug("leyendo hostname=" + hostName);
		hostPort = 587;
		log.debug("leyendo hostPort=" + hostPort);
		authUser = "pablo.settino@gmail.com";
		log.debug("leyendo authUser=" + authUser);
		authPassword = "Pablo265";
		log.debug("leyendo authPassword=" + authPassword);
		fromName = "Pablo Settino";
		log.debug("leyendo fromName=" + fromName);
		fromEmail = "pablo.settino@gmail.com";
		log.debug("leyendo fromEmail=" + fromEmail);
		while (!succeed && attemps <= retries) {
			try {
				log.debug("Antes de enviar el mail - " + new Date());

				email.addTo(to);
				// email.setDebug(true);
				email.setHostName(hostName);
				email.setSmtpPort(hostPort);
				email.setAuthenticator(new DefaultAuthenticator(authUser, authPassword));
				// email.setSSLOnConnect(true);
				email.setStartTLSEnabled(true);
				email.getMailSession().getProperties().put("mail.smtps.auth", "true");
				email.getMailSession().getProperties().put("mail.debug", "false");
				email.getMailSession().getProperties().put("mail.smtps.port", hostPort);
				email.getMailSession().getProperties().put("mail.smtps.socketFactory.port", hostPort);
				email.getMailSession().getProperties().put("mail.smtps.socketFactory.class",
						"javax.net.ssl.SSLSocketFactory");
				email.getMailSession().getProperties().put("mail.smtps.socketFactory.fallback", "false");
				email.getMailSession().getProperties().put("mail.smtp.starttls.enable", "true");
				email.setFrom(fromEmail, fromName);

				List<InternetAddress> ccDest = new ArrayList<InternetAddress>();
				if (StringUtils.isNotEmpty(ccDestination)) {
					InternetAddress copiaCorreo = new InternetAddress(ccDestination);
					ccDest.add(copiaCorreo);
					email.setCc(ccDest);
				}
				email.setSubject(subject);

				// embed the image and get the content id
				// URL url = new
				// URL("http://www.renault.com.ar/_images/drivem.gif");
				// String cid = email.embed(url, "Renault logo");

				email.setHtmlMsg(HTML);
				email.setTextMsg(text);

				log.debug("Despues de enviar el mail - " + new Date());

				succeed = true;
			} catch (Exception e) {
				log.warn("Error al enviar el mail, intento " + attemps, e);
				attemps++;
				try {
					Thread.sleep(waitInterval);
				} catch (Exception te) {
					// TODO: ver que hacer
					log.debug("No puede enviar el mail");
				}
			}
		}

		if (!succeed) {
			log.warn("Error al enviar el mail. ");
			throw new Exception();
		}
		return email;
	}

	public HtmlEmail setearEmail(String subject, String HTML, String text, String[] to, String[] ccDestination,
			int retries, int waitInterval) throws Exception {
		boolean succeed = false;
		int attemps = 0;
		// Create the email message
		HtmlEmail email = new HtmlEmail();
		log.debug("Preparando el mail para " + to);

		hostName = "64.233.165.108";
		log.debug("leyendo hostname=" + hostName);
		hostPort = 587;
		log.debug("leyendo hostPort=" + hostPort);
		authUser = "pablo.settino@gmail.com";
		log.debug("leyendo authUser=" + authUser);
		authPassword = "Pablo265";
		log.debug("leyendo authPassword=" + authPassword);
		fromName = "Pablo Settino";
		log.debug("leyendo fromName=" + fromName);
		fromEmail = "pablo.settino@gmail.com";
		log.debug("leyendo fromEmail=" + fromEmail);
		while (!succeed && attemps <= retries) {
			try {
				log.debug("Antes de enviar el mail - " + new Date());

				email.addTo(to);
				// email.setDebug(true);
				email.setHostName(hostName);
				email.setSmtpPort(hostPort);
				email.setAuthenticator(new DefaultAuthenticator(authUser, authPassword));
				// email.setSSLOnConnect(true);
				email.setStartTLSEnabled(true);
				email.getMailSession().getProperties().put("mail.smtps.auth", "true");
				email.getMailSession().getProperties().put("mail.debug", "false");
				email.getMailSession().getProperties().put("mail.smtps.port", hostPort);
				email.getMailSession().getProperties().put("mail.smtps.socketFactory.port", hostPort);
				email.getMailSession().getProperties().put("mail.smtps.socketFactory.class",
						"javax.net.ssl.SSLSocketFactory");
				email.getMailSession().getProperties().put("mail.smtps.socketFactory.fallback", "false");
				email.getMailSession().getProperties().put("mail.smtp.starttls.enable", "true");
				email.setFrom(fromEmail, fromName);

				List<InternetAddress> ccDest = new ArrayList<InternetAddress>();
				if (ccDestination.length > 0) {
					for (int i = 0; i < ccDestination.length; i++) {
						InternetAddress copiaCorreo = new InternetAddress(ccDestination[i]);
						ccDest.add(copiaCorreo);
					}

					email.setCc(ccDest);
				}
				email.setSubject(subject);

				// embed the image and get the content id
				// URL url = new
				// URL("http://www.renault.com.ar/_images/drivem.gif");
				// String cid = email.embed(url, "Renault logo");

				email.setHtmlMsg(HTML);
				email.setTextMsg(text);

				log.debug("Despues de enviar el mail - " + new Date());

				succeed = true;
			} catch (Exception e) {
				log.warn("Error al enviar el mail, intento " + attemps, e);
				attemps++;
				try {
					Thread.sleep(waitInterval);
				} catch (Exception te) {
					// TODO: ver que hacer
					log.debug("No puede enviar el mail");
				}
			}
		}

		if (!succeed) {
			log.warn("Error al enviar el mail. ");
			throw new Exception();
		}
		return email;
	}

}