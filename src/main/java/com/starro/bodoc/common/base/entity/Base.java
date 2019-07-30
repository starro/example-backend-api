package com.starro.bodoc.common.base.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

/**   
 * @since       2018.10.03
 * @author      starro
 * @description base
 **********************************************************************************************************************/
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Base {

	@CreatedDate
	@Column(updatable=false)
	private LocalDateTime createdAt;
	
	@LastModifiedDate
	@Column(insertable=false)
	private LocalDateTime updatedAt;	
}
