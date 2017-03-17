package pitayaa.nail.msg.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pitayaa.nail.domain.category.Category;
import pitayaa.nail.domain.license.elements.Price;
import pitayaa.nail.domain.service.ServiceModel;

@Service
public class ServiceBusImpl implements ServiceBus {

	@Override
	public List<ServiceModel> getListServiceDefault(String salonId) {
		// TODO Auto-generated method stub
		List<ServiceModel> lst = new ArrayList<>();
		ServiceModel model = new ServiceModel();
		model.setDuration(0);
		Price price1 = new Price();
		price1.setPrice(20.0);
		model.setPrice1(price1);;
		//
		Price price2 = new Price();
		price2.setPrice(20.0);
		model.setPrice2(price2);;
		model.setServiceName("Manicure");

		model.setSalonId(salonId);
		lst.add(model);

		// service 2
		model = new ServiceModel();
		model.setDuration(0);

		price1 = new Price();
		price1.setPrice(40.0);
		model.setPrice1(price1);;
		//
		price2 = new Price();
		price2.setPrice(20.0);
		model.setPrice2(price2);;
		model.setPrice2(price2);;
		model.setServiceName("Jelly pedicure");

		model.setSalonId(salonId);

		lst.add(model);

		// service 3
		model = new ServiceModel();
		model.setDuration(0);

		price1 = new Price();
		price1.setPrice(40.0);
		model.setPrice1(price1);;
		//
		price2 = new Price();
		price2.setPrice(60.0);
		model.setPrice2(price2);;
		model.setServiceName("Body massage");

		model.setSalonId(salonId);
		lst.add(model);
		return lst;
	}

}
