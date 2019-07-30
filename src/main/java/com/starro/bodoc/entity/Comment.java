package com.starro.bodoc.entity;

import com.starro.bodoc.common.base.entity.Base;
import com.starro.bodoc.common.engine.annotation.entity.Description;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @since       2018.10.02
 * @author      starro
 * @description comment
 **********************************************************************************************************************/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder=true)
@Entity(name="comment")
@Description("커맨트")
public class Comment extends Base {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Description("커맨트일련번호")
	@Column(nullable=false, precision=20)
	private Long id;

	@Description("내용")
	@Column(length=500)
	private String content;

	@Description("나이")
	@Column(nullable=false, precision=3)
	private Integer age;

	@Description("가격")
	@Column(nullable=false, precision=3, scale=2)
	private BigDecimal amount;

	@Description("사용여부")
	@Column(nullable=false)
	private Boolean useYn;
}