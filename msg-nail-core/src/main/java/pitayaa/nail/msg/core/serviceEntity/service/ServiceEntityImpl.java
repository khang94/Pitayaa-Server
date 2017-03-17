package pitayaa.nail.msg.core.serviceEntity.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pitayaa.nail.domain.packages.PackageModel;
import pitayaa.nail.domain.service.ServiceModel;
import pitayaa.nail.msg.core.serviceEntity.repository.ServiceRepository;

@Service
public class ServiceEntityImpl implements ServiceEntityInterface {
	
	@Autowired
	private ServiceRepository serviceRepo;
	
	@Autowired
	private ServiceEntityViewService viewService;
	
	@Override
	public List<ServiceModel> getServicesBySalonId(String salonId){
		return serviceRepo.getServicesBySalonId(salonId);
	}
	
	@Override
	public Optional<ServiceModel> findOne(UUID id){
		return Optional.ofNullable(serviceRepo.findOne(id));
	}
	
	@Override
	public ServiceModel save(ServiceModel serviceBody) throws Exception {

		serviceBody = serviceRepo.save(serviceBody);
		
		if(serviceBody.getView().getImgData() != null 
				&& !"".equalsIgnoreCase(serviceBody.getView().getImgData().toString())){
			viewService.buildView(serviceBody);
		}

		return serviceBody;
	}
}
