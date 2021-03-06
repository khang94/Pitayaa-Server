package pitayaa.nail.msg.core.salon.service;

import java.util.Optional;
import java.util.UUID;

import pitayaa.nail.domain.salon.Salon;

public interface SalonService {

	Optional<Salon> findOne(UUID id);

	Salon save(Salon salon) throws Exception;

	Salon initModel() throws Exception;

	Salon update(Salon salonUpdate, Salon salonOld) throws Exception;

}
