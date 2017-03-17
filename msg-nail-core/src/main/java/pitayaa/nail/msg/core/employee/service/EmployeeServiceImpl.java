package pitayaa.nail.msg.core.employee.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pitayaa.nail.domain.employee.Employee;
import pitayaa.nail.msg.core.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	EmployeeViewService viewService;

	@Override
	public List<Employee> findAllEmployee(String salonId) {
		return employeeRepo.findAllEmployee(salonId);
	}

	@Override
	public Optional<Employee> findOne(UUID id) {
		return Optional.ofNullable(employeeRepo.findOne(id));
	}

	@Override
	public Employee save(Employee employeeBody) throws Exception {

		employeeBody = employeeRepo.save(employeeBody);
		
		if(employeeBody.getView().getImgData() != null 
				&& !"".equalsIgnoreCase(employeeBody.getView().getImgData().toString())){
			viewService.buildView(employeeBody);
		}

		return employeeBody;
	}

	@Override
	public Optional<Employee> findEmployee(String salonId, String pin) {
		// TODO Auto-generated method stub
		return employeeRepo.findAllEmployee(salonId, pin);
	}
}
