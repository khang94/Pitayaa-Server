package pitayaa.nail.domain.setting;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import pitayaa.nail.domain.common.Discount;
import pitayaa.nail.domain.hibernate.transaction.ObjectHibernateListener;
import pitayaa.nail.domain.license.elements.Price;
import pitayaa.nail.domain.view.View;

@Data
@Entity
@EntityListeners(ObjectHibernateListener.class)
public class SettingSms {
	
	@Id
	@GenericGenerator(name = "uuid-gen", strategy = "uuid2")
	@GeneratedValue(generator = "uuid-gen")
	@Type(type = "pg-uuid")
	private UUID uuid;
	
	@Version 
	Long version;
	
	private String type;
	private String key;
	private String repeatType;
	private Integer timesRepeat;
	private String content;
	private boolean autoSend;
	private String sendSmsOn;
	
	private String sendSmsTo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	private String updatedBy;
	private String salonId;
}
