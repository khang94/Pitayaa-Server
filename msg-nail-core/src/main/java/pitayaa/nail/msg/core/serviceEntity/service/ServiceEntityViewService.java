package pitayaa.nail.msg.core.serviceEntity.service;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pitayaa.nail.domain.salon.Salon;
import pitayaa.nail.domain.service.ServiceModel;
import pitayaa.nail.domain.view.View;
import pitayaa.nail.msg.core.common.CoreConstant;
import pitayaa.nail.msg.core.common.CoreHelper;
import pitayaa.nail.msg.core.salon.service.SalonService;
import pitayaa.nail.msg.core.serviceEntity.repository.ServiceRepository;
import pitayaa.nail.msg.core.setting.service.SettingService;

@Service
public class ServiceEntityViewService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServiceEntityViewService.class);

	@Autowired
	ServiceRepository serviceModelRepo;

	@Autowired
	CoreHelper coreHelper;

	@Autowired
	SettingService settingService;

	@Autowired
	SalonService salonService;

	public ServiceModel buildView(ServiceModel serviceModel) throws Exception {
		if (serviceModel.getView() == null) {
			LOGGER.info("View in this service = null");
			View viewBody = (View) coreHelper.createModelStructure(new View());
			serviceModel.setView(viewBody);
		}

		// Get path
		String path = this.buildPathFile(serviceModel);

		// Extract Image to Folder
		String pathImage = coreHelper.buildFileNameFromPath(path,
				serviceModel.getView());
		serviceModel.getView().setPathImage(pathImage);

		// Extract Image to Folder
		if (pathImage != null) {
			coreHelper.writeBytesToFileNio(serviceModel.getView().getImgData(),
					pathImage);
		}

		// Update serviceModel
		serviceModel.getView().setImgData(null);
		serviceModelRepo.save(serviceModel);

		return serviceModel;
	}

	private String buildServiceModelFileName(String uid, Date date) {
		String formatTime = coreHelper.getTimeFolder(date);
		return CoreConstant.VIEW_SERVICE + CoreConstant.UNDERLINE + uid
				+ CoreConstant.UNDERLINE + formatTime;
	}

	private String getPathBySalonId(String salonId) {
		Salon salonSaved = salonService.findOne(UUID.fromString(salonId)).get();
		return salonSaved.getView().getPathImage();
	}

	private String buildPathFile(ServiceModel serviceModel) {

		// Get path
		String path = this.getPathBySalonId(serviceModel.getSalonId())
				+ CoreConstant.SLASH + CoreConstant.VIEW_SERVICE;

		// Build file Name
		String fileName = this.buildServiceModelFileName(serviceModel.getUuid()
				.toString(), serviceModel.getCreatedDate());
		path = path + CoreConstant.SLASH + fileName;
		LOGGER.info("Folder name [" + fileName + "] in path [" + path + "]");

		// Return path
		return path;
	}

}
