package com.reservas.dao;

public interface MailSenderService {

	public static final String SMTP_HOST_NAME = "SMTP_HOST_NAME";
	public static final String SMTP_HOST_PORT = "SMTP_HOST_PORT";
	public static final String SMTP_AUTH_USER = "SMTP_AUTH_USER";
	public static final String SMTP_AUTH_PASS = "SMTP_AUTH_PASS";
	public static final String SMTP_FROM_NAME = "SMTP_FROM_NAME";
	public static final String SMTP_FROM_MAIL = "SMTP_FROM_MAIL";
	public static final String MAIL_OLVIDO_SUBJECT = "MAIL_OLVIDO_SUBJECT";
	public static final String MAIL_OLVIDO_BODY = "MAIL_OLVIDO_BODY";
	public static final String MAIL_OLVIDO_TXT = "MAIL_OLVIDO_TXT";
	public static final String MAIL_BLOQUEO_SUBJECT = "MAIL_BLOQUEO_SUBJECT";
	public static final String MAIL_BLOQUEO_BODY = "MAIL_BLOQUEO_BODY";
	public static final String MAIL_BLOQUEO_TXT = "MAIL_BLOQUEO_TXT";
	public static final String MAIL_BIENVENIDA_SUBJECT = "MAIL_BIENVENIDA_SUBJECT";;
	public static final String MAIL_BIENVENIDA_BODY = "MAIL_BIENVENIDA_BODY";;
	public static final String MAIL_BIENVENIDA_TXT = "MAIL_BIENVENIDA_TXT";
	public static final String MAIL_COBERTURAS_PRENDARIOS_BODY = "MAIL_COBERTURAS_PRENDARIOS_BODY";
	public static final String MAIL_COBERTURAS_DIRECTO_BODY = "MAIL_COBERTURAS_DIRECTO_BODY";
	public static final String MAIL_COBERTURAS_POSTPLAN_BODY = "MAIL_COBERTURAS_POSTPLAN_BODY";

	public void sendMail(String to, String subject, String text) throws Exception;

}