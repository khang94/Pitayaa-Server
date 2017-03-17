package pitayaa.nail.msg.core.customer.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pitayaa.nail.domain.customer.Customer;
import pitayaa.nail.domain.hibernate.transaction.QueryCriteria;
import pitayaa.nail.msg.core.customer.repository.CustomerRepository;
import pitayaa.nail.msg.core.hibernate.CriteriaRepository;
import pitayaa.nail.msg.core.hibernate.SearchCriteria;



@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	CriteriaRepository criteriaRepo;
	
	@Autowired
	CustomerViewService viewService;

	@Override
	public List<Customer> findAllCustomer(String salonId) {
		return customerRepo.findAllCustomer(salonId);
	}

	@Override
	public Customer save(Customer customerBody) throws Exception {
		
		byte[] binaryImg = null;
		
		// Get stream image
		if (customerBody.getView() != null){
			binaryImg = customerBody.getView().getImgData();
			customerBody.getView().setImgData(null);	
		}

		customerBody = customerRepo.save(customerBody);
		
		if(customerBody.getSalonId() != null || !"".equalsIgnoreCase(customerBody.getSalonId())){
			viewService.buildViewByDate(customerBody, binaryImg);
		}
		
		return customerBody;
	}
	
	@Override
	public Optional<Customer> findOne(UUID id) {
		return Optional.ofNullable(customerRepo.findOne(id));
	}

	@Override
	public void delete(Customer customer) {
		customerRepo.delete(customer);
	}
	
	@Override
	public List<?> findAllByQuery(QueryCriteria query) throws ClassNotFoundException {
		SearchCriteria sc = criteriaRepo.extractQuery(query.getQuery());
		sc.setEntity(query.getObject());
		return criteriaRepo.searchCriteria(sc);
	}
}
