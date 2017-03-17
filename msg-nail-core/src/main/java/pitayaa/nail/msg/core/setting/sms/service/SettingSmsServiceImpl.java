package pitayaa.nail.msg.core.setting.sms.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pitayaa.nail.domain.setting.SettingSms;
import pitayaa.nail.msg.core.setting.sms.repository.SettingSmsRepository;

@Service
public class SettingSmsServiceImpl implements SettingSmsService {

	@Autowired
	SettingSmsRepository settingSmsRepo;

	@Override
	public Optional<SettingSms> findOne(UUID id) {
		return Optional.ofNullable(settingSmsRepo.findOne(id));
	}

	@Override
	public SettingSms save(SettingSms salon) {
		return settingSmsRepo.save(salon);
	}

	@Override
	public List<SettingSms> getListSettingSMS(String salonId) {
		// TODO Auto-generated method stub
		return settingSmsRepo.getListSetting(salonId);
	}
}
