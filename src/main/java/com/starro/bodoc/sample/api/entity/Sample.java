package com.starro.bodoc.sample.api.entity;

import com.starro.bodoc.common.base.entity.Base;
import com.starro.bodoc.common.engine.annotation.entity.Description;
import lombok.*;

import javax.persistence.*;

/**
 * @since       2018.10.02
 * @author      starro
 * @description sample
 **********************************************************************************************************************/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder=true)
@Entity(name="sample")
@Description("샘플")
public class Sample extends Base {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Description("샘플일련번호")
	@Column(nullable=false, precision=20)
	private Long id;

	@Description("사용자아이디")
	@Column(nullable=false, length=50)
	private String userId;

	@Description("제목")
	@Column(length=100)
	private String title;

	@Description("내용")
	@Column(length=500)
	private String content;
}