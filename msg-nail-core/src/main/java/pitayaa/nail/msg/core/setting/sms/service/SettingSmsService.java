package pitayaa.nail.msg.core.setting.sms.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import pitayaa.nail.domain.setting.SettingSms;

public interface SettingSmsService {

	Optional<SettingSms> findOne(UUID id);
	List<SettingSms> getListSettingSMS(String salonId);
	SettingSms save(SettingSms salon);

}
