package pitayaa.nail.notification.scheduler;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pitayaa.nail.domain.appointment.Appointment;
import pitayaa.nail.domain.notification.common.KeyValueModel;
import pitayaa.nail.domain.notification.sms.SmsModel;
import pitayaa.nail.domain.salon.Salon;
import pitayaa.nail.notification.common.NotificationConstant;

@Service
public class JobHelper {

	public static final Logger LOGGER = LoggerFactory.getLogger(JobHelper.class);

	// @Autowired
	// RestTemplateHelper restTemplateHelper;

	private String getAppointmentProperties(String propertiesName) {

		Properties prop = new Properties();
		InputStream input = null;
		String propertiesValue = null;
		try {
			input = AppointmentJob.class.getClassLoader().getResourceAsStream(NotificationConstant.PATH_APPOINTMENT);
			prop.load(input);
			propertiesValue = prop.getProperty(propertiesName);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return propertiesValue;
	}

	/**
	 * Load list appointment
	 * 
	 * @return
	 */
	public List<Appointment> loadAppointments() {

		LOGGER.info("Call rest template .....Load list appointment .........");

		Map<String, String> headersMap = new HashMap<String, String>();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.setContentType(MediaType.APPLICATION_JSON);
		for (String header : headers.keySet()) {
			headersMap.put(header, headers.getFirst(header));
		}

		// urlParameters
		Map<String, String> urlParameters = new HashMap<String, String>();
		urlParameters.put(NotificationConstant.STATUS_STRING, NotificationConstant.BUSINESS_STATUS_READY);

		// String url = this.urlLoadListAppm();
		String url = this.getAppointmentProperties(NotificationConstant.LOAD_LIST_APPOINTMENT);
		RestTemplateHelper restTemplateHelper = new RestTemplateHelper();
		url = restTemplateHelper.buildUrlRequestParam(urlParameters, url);
		LOGGER.info("Get URL Load List Appointment : [" + url + "] to send request !");

		// Execute Request By Rest Template
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Appointment>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Appointment>>() {
				});
		if (response.getStatusCode().is2xxSuccessful()) {
			LOGGER.info("Get response successully from URL [" + url + "]");
		}

		return response.getBody();
	}

	/**
	 * Update appointment
	 * 
	 * @return
	 */
	public ResponseEntity<Appointment> updateAppointment(String appointmentId, Appointment appointmentUpdate) {

		LOGGER.info("Update appointment ID [" + appointmentId + "]");

		Map<String, String> headersMap = new HashMap<String, String>();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		for (String header : headers.keySet()) {
			headersMap.put(header, headers.getFirst(header));
		}

		// urlParameters
		Map<String, String> urlParameters = new HashMap<String, String>();

		// Path variables
		Map<String, String> pathVars = new HashMap<String, String>();
		pathVars.put(NotificationConstant.ID_STRING, appointmentId);

		HttpEntity<Appointment> request = new HttpEntity<>(appointmentUpdate, headers);

		// Get URL
		String url = this.getAppointmentProperties(NotificationConstant.UPDATE_APPOINTMENT);
		RestTemplateHelper restTemplateHelper = new RestTemplateHelper();
		url = restTemplateHelper.buildUrlPathVariable(pathVars, url);
		LOGGER.info("Get URL Update Appointment : [" + url + "] to send request !");

		// Execute Request By Rest Template
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Appointment> response = restTemplate.exchange(url, HttpMethod.PUT, request, Appointment.class,
				new ParameterizedTypeReference<Appointment>() {
				});
		if (response.getStatusCode().is2xxSuccessful()) {
			LOGGER.info("Get response successully from URL [" + url + "]");
		}
		LOGGER.info("Update appointment success !");

		return response;
	}

	/**
	 * Load list appointment
	 * 
	 * @return
	 */
	public ResponseEntity<Salon> getSalonById(String Id) {

		LOGGER.info("Get Salon Information by Id [" + Id + "]");

		Map<String, String> headersMap = new HashMap<String, String>();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.setContentType(MediaType.APPLICATION_JSON);
		for (String header : headers.keySet()) {
			headersMap.put(header, headers.getFirst(header));
		}

		// urlParameters
		Map<String, String> urlParameters = new HashMap<String, String>();

		// Path variables
		Map<String, String> pathVars = new HashMap<String, String>();
		pathVars.put(NotificationConstant.ID_STRING, Id);

		// HttpEntity<String> request = new HttpEntity<>(input, createHeader());
		String url = this.getAppointmentProperties(NotificationConstant.SALON_URI_ID);
		RestTemplateHelper restTemplateHelper = new RestTemplateHelper();
		url = restTemplateHelper.buildUrlPathVariable(pathVars, url);
		LOGGER.info("Get Salon by URL : [" + url + "] to send request !");

		// Execute Request By Rest Template
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Salon> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<Salon>() {
				});
		if (response.getStatusCode().is2xxSuccessful()) {
			LOGGER.info("Get response successully from URL [" + url + "]");
		}

		return response;
	}

	public SmsModel bindDataSms(SmsModel smsModel, Salon salon, Appointment appointment) {
		LOGGER.info("Binding data for sms body .......");
		int count = 0;
		for (KeyValueModel keyValue : smsModel.getWordBinding()) {
			try {
				if (keyValue.getKey().equalsIgnoreCase("#CustomerName")) {
					keyValue.setValue(appointment.getCustomer().getCustomerDetail().getFirstName());
				}
			} catch (Exception ex) {
				LOGGER.info("[ERROR]  Customer name = null !");
			}
			try {
				if (keyValue.getKey().equalsIgnoreCase("#SalonName")) {
					keyValue.setValue(salon.getSalonDetail().getBusinessName());
				}
			} catch (Exception ex) {
				LOGGER.info("[ERROR] Salon name = null !");
			}
			try {
				if (keyValue.getKey().equalsIgnoreCase("#SalonPhone")) {
					keyValue.setValue(salon.getContact().getHomePhone());
				}
			} catch (Exception ex) {
				LOGGER.info("[ERROR] Salon Phone = null !");
			}
			try {
				if (keyValue.getKey().equalsIgnoreCase("#Date")) {
					keyValue.setValue(appointment.getStartTime().toString());
				}
			} catch (Exception ex) {
				LOGGER.info("[ERROR] Date = null !");
			}
			try {
				if (keyValue.getKey().equalsIgnoreCase("#Time")) {
					keyValue.setValue(appointment.getStartTime().toString());
				}
			} catch (Exception ex) {
				LOGGER.info("[ERROR] Time = null !");
			}
			smsModel.getWordBinding().set(count, keyValue);
			count++;
		}
		LOGGER.info("Finish binding data for SMS .............");
		return smsModel;

	}

}
