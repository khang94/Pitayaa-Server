package pitayaa.nail.notification.common;

public class NotificationConstant {

	/* Type of Request */
	public static final String POST_REQUEST = "POST";
	public static final String GET_REQUEST = "GET";
	public static final String DELETE_REQUEST = "DELETE";
	public static final String PATCH_REQUEST = "PATCH";
	
	/* Firebase Config */
	public static final String SET = "set";
	//public static final String SERVICE_ACCOUNT_PATH = "config/pitayaa_account2.json";
	//public static final String URL_DATABASE = "https://pitayaa-3fd3a.firebaseio.com/";
	
	/* Rest template */
	public static final String ACCEPT_HEADER = "Accept";
	
	/* Slash */
	public static final String SLASH = "/";
	
	/* Config Appointment */
	public static final String PATH_APPOINTMENT = "properties/api_config.properties";
	
	/* Appointment */
	public static final String LOAD_LIST_APPOINTMENT = "appointment.loadlist";
	public static final String UPDATE_APPOINTMENT = "appointment.update";
	
	// Salon 
	public static final String SALON_URI_ID = "salon.getById";
	
	/* Business Status Appointment */
	public static final String BUSINESS_STATUS_READY = "READY_TO_NOTIFY";
	public static final String BUSINESS_STATUS_PENDING = "PENDING_BY_CUSTOMER";
	public static final String BUSINESS_STATUS_CONFIRM = "CONFIRMED";
	public static final String BUSINESS_STATUS_SIGNAL_CUSTOMER = "SendToCustomer";
	public static final String BUSINESS_STATUS_SIGNAL_EMPLOYEE = "SendToEmployee";
	
	public static final String STATUS_STRING = "status";
	public static final String ID_STRING = "Id";
	
	/* Template Type */
	public static final String TEMPLATE_SMS_APPOINTMENT = "SMS_APPOINTMENT";
}
