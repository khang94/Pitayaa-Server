package pitayaa.nail.notification.sms.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.UUID;

import pitayaa.nail.domain.notification.sms.SmsModel;
import pitayaa.nail.domain.notification.sms.SmsReceive;

public interface ISmsService {

	String readFile(String path, Charset encoding) throws IOException;

	String getValueProperties(String propertiesName);

	Properties getPropertiesSmsConfig();

	SmsModel findOne(UUID uid) throws Exception;

	SmsModel initModelSms() throws Exception;

	SmsModel createSms(SmsModel smsModel) throws Exception;

	SmsModel sendSms(SmsModel smsModel) throws IOException ;

	SmsReceive saveSmsReceive(SmsReceive smsReceive);

	String getSmsTemplateConfig(String propertiesName);

	SmsModel initAppointmentSms(SmsModel smsBody);

	String findTemplateInClasspath(String templateId) throws IOException;

}
