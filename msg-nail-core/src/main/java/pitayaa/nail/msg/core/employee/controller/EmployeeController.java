package pitayaa.nail.msg.core.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pitayaa.nail.domain.employee.Employee;
import pitayaa.nail.json.http.JsonHttp;
import pitayaa.nail.msg.core.common.CoreHelper;
import pitayaa.nail.msg.core.employee.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private CoreHelper coreHelper;
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = "employees/model", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> initAccountModel() throws Exception {

		Employee account = (Employee) coreHelper
				.createModelStructure(new Employee());

		return ResponseEntity.ok(account);
	}
	
	@RequestMapping(value = "employees", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> saveEmployee(@RequestBody Employee employeeBody) throws Exception {

		employeeBody = employeeService.save(employeeBody);

		return ResponseEntity.ok(employeeBody);
	}
	
	@RequestMapping(value = "employees/bySalon", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> findAllCustomerBySalonId(
			@RequestParam("salonId") String salonId) throws Exception {

		List<Employee> lstEmployee = employeeService.findAllEmployee(salonId);
		JsonHttp jsonHttp = new JsonHttp();
		if (lstEmployee != null && lstEmployee.size() > 0) {
			jsonHttp.setCode(200);
			jsonHttp.setObject(lstEmployee);
			jsonHttp.setStatus("success");
			jsonHttp.setResponseMessage("get list success");
		}

		else {
			jsonHttp.setCode(404);
			jsonHttp.setStatus("error");
			jsonHttp.setResponseMessage("get list failed");
		}

		return new ResponseEntity<>(jsonHttp, HttpStatus.OK);
	}
	
	@RequestMapping(value = "employee/login", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> checkPinEmployee(@RequestBody HashMap<String,Object> data) throws Exception {
		String salonId=(String) data.get("salonId");

		String password=(String) data.get("password");

		Optional <Employee> employee =employeeService.findEmployee(salonId, password);
		
		return ResponseEntity.ok(employee.get());
	}
}
