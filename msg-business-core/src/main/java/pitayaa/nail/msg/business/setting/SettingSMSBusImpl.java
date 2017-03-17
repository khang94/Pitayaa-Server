package pitayaa.nail.msg.business.setting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pitayaa.nail.domain.setting.SettingSms;

@Service
public class SettingSMSBusImpl implements SettingSMSBus {

	@Override
	public List<SettingSms> getListSettingSMSDefaul(String salonId) {
		// TODO Auto-generated method stub
		List<SettingSms> lstSetting=new ArrayList<>();
				
		SettingSms smsAllCustomer=new SettingSms();
		smsAllCustomer.setAutoSend(false);
		smsAllCustomer.setSalonId(salonId);
		smsAllCustomer.setType("CUSTOMER");
		smsAllCustomer.setKey("PROMOTION");
		smsAllCustomer.setContent("");
		smsAllCustomer.setTimesRepeat(5);
		lstSetting.add(smsAllCustomer);
		//---------------
		
		SettingSms smsNewCustomer=new SettingSms();
		smsNewCustomer.setAutoSend(false);
		smsNewCustomer.setSalonId(salonId);
		smsNewCustomer.setType("CUSTOMER");
		smsNewCustomer.setKey("NEW");
		smsNewCustomer.setTimesRepeat(5);
		smsNewCustomer.setContent("");

		lstSetting.add(smsNewCustomer);
		//---------------
		
		SettingSms smsReferralCustomer=new SettingSms();
		smsReferralCustomer.setAutoSend(false);
		smsReferralCustomer.setSalonId(salonId);
		smsReferralCustomer.setType("CUSTOMER");
		smsReferralCustomer.setKey("REFERRAL");
		smsReferralCustomer.setTimesRepeat(5);
		smsReferralCustomer.setContent("");

		lstSetting.add(smsReferralCustomer);
		//---------------
		
		SettingSms smsReturnCustomer=new SettingSms();
		smsReturnCustomer.setAutoSend(false);
		smsReturnCustomer.setSalonId(salonId);
		smsReturnCustomer.setType("CUSTOMER");
		smsReturnCustomer.setKey("RETURN");
		smsReturnCustomer.setTimesRepeat(5);
		smsReturnCustomer.setContent("");

		lstSetting.add(smsReturnCustomer);
		//---------------
		
		
		SettingSms smsAppointmentCustomer=new SettingSms();
		smsAppointmentCustomer.setAutoSend(false);
		smsAppointmentCustomer.setSalonId(salonId);
		smsAppointmentCustomer.setType("CUSTOMER");
		smsAppointmentCustomer.setKey("APPOINTMENT_REMIND");
		smsAppointmentCustomer.setTimesRepeat(5);
		smsAppointmentCustomer.setContent("");

		lstSetting.add(smsAppointmentCustomer);
		//---------------
		
		SettingSms smsAppointmentCustomerCancel=new SettingSms();
		smsAppointmentCustomerCancel.setAutoSend(false);
		smsAppointmentCustomerCancel.setSalonId(salonId);
		smsAppointmentCustomerCancel.setType("CUSTOMER");
		smsAppointmentCustomerCancel.setKey("APPOINTMENT_CANCEL");
		smsAppointmentCustomerCancel.setTimesRepeat(5);
		smsAppointmentCustomerCancel.setContent("");

		lstSetting.add(smsAppointmentCustomerCancel);
		//---------------
		
		return lstSetting;
	}

}
