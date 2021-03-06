package pitayaa.nail.msg.core.employee.service;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pitayaa.nail.domain.employee.Employee;
import pitayaa.nail.domain.salon.Salon;
import pitayaa.nail.domain.view.View;
import pitayaa.nail.msg.core.common.CoreConstant;
import pitayaa.nail.msg.core.common.CoreHelper;
import pitayaa.nail.msg.core.employee.repository.EmployeeRepository;
import pitayaa.nail.msg.core.salon.service.SalonService;
import pitayaa.nail.msg.core.setting.service.SettingService;

@Service
public class EmployeeViewService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(EmployeeViewService.class);

	@Autowired
	EmployeeRepository employeeRepo;

	@Autowired
	CoreHelper coreHelper;

	@Autowired
	SettingService settingService;

	@Autowired
	SalonService salonService;

	public Employee buildView(Employee employee) throws Exception {
		if (employee.getView() == null) {
			LOGGER.info("View in this employee = null");
			View viewBody = (View) coreHelper.createModelStructure(new View());
			employee.setView(viewBody);
		}

		// Get path
		String path = this.buildPathFile(employee);

		// Extract Image to Folder
		String pathImage = coreHelper.buildFileNameFromPath(path,
				employee.getView());
		employee.getView().setPathImage(pathImage);

		// Extract Image to Folder
		if (pathImage != null) {
			coreHelper.writeBytesToFileNio(employee.getView().getImgData(),
					pathImage);
		}

		// Update employee
		employee.getView().setImgData(null);
		employeeRepo.save(employee);

		return employee;
	}

	private String buildEmployeeFileName(String uid, Date date) {
		String formatTime = coreHelper.getTimeFolder(date);
		return CoreConstant.VIEW_EMPLOYEE + CoreConstant.UNDERLINE + uid
				+ CoreConstant.UNDERLINE + formatTime;
	}

	private String getPathBySalonId(String salonId) {
		Salon salonSaved = salonService.findOne(UUID.fromString(salonId)).get();
		return salonSaved.getView().getPathImage();
	}

	private String buildPathFile(Employee employee) {

		// Get path
		String path = this.getPathBySalonId(employee.getSalonId())
				+ CoreConstant.SLASH + CoreConstant.VIEW_EMPLOYEE;

		// Build file Name
		String fileName = this.buildEmployeeFileName(employee.getUuid()
				.toString(), employee.getCreatedDate());
		path = path + CoreConstant.SLASH + fileName;
		LOGGER.info("Folder name [" + fileName + "] in path [" + path + "]");

		// Return path
		return path;
	}

}
