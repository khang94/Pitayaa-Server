package pitayaa.nail.msg.core.setting.sms.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pitayaa.nail.domain.setting.SettingSms;
import pitayaa.nail.msg.core.common.CoreHelper;
import pitayaa.nail.msg.core.setting.sms.service.SettingSmsService;

@Controller
public class SettingSmsController {

	@Autowired
	private CoreHelper coreHelper;

	@Autowired
	private SettingSmsService settingSmsService;

	@RequestMapping(value = "settingSms/model", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> initSettingSms() throws Exception {

		SettingSms settingSms = (SettingSms) coreHelper.createModelStructure(new SettingSms());

		return ResponseEntity.ok(settingSms);
	}
	
	@RequestMapping(value = "settingSms", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> createSetting(@RequestBody SettingSms settingSms) throws Exception {

		settingSms = settingSmsService.save(settingSms);

		return ResponseEntity.ok(settingSms);
	}
	
	@RequestMapping(value = "settingSms/{Id}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<?> updateSetting(@PathVariable("Id") UUID uid,
			@RequestBody SettingSms settingSms) throws Exception {

		Optional<SettingSms> savedSettingSms = settingSmsService.findOne(uid);
		
		if(!savedSettingSms.isPresent()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		settingSms = settingSmsService.save(settingSms);

		return ResponseEntity.ok(settingSms);
	}
	
	@RequestMapping(value = "settingSms/{Id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> createSetting(@PathVariable("Id") UUID uid,
			@RequestBody SettingSms settingSms) throws Exception {

		Optional<SettingSms> savedSettingSms = settingSmsService.findOne(uid);
		
		if(!savedSettingSms.isPresent()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(settingSms);
	}
	
	@RequestMapping(value = "settingSms/getListSetting/{salonId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getListSetting(@PathVariable("salonId") String salonId) throws Exception {
		
		List<SettingSms>lst=settingSmsService.getListSettingSMS(salonId);
		
		return ResponseEntity.ok(lst);
	}
	
	@RequestMapping(value = "settingSms/updateListSetting", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<?> updateListSetting(
			@RequestBody List<SettingSms> lstSettingSms) throws Exception {
		try{
			for(SettingSms settingSms:lstSettingSms){
				settingSmsService.save(settingSms);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	


		return ResponseEntity.ok("ok");
	}
}
