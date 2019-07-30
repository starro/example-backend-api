package com.starro.bodoc.sample.api.form;

import com.starro.bodoc.common.engine.validator.form.date.DateValidator;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

/**   
 * @since       2018.10.02
 * @author      starro
 * @description sample form
 **********************************************************************************************************************/
public class SampleForm {

	public static class Request {

		@Getter
		@Setter
		@Builder
		@ToString
		@NoArgsConstructor
		@AllArgsConstructor
		public static class Find {

			@ApiModelProperty(value="사용자아이디", required=true)
			private String userId;

			@ApiModelProperty(value="제목", required=true)
			private String title;

			@ApiModelProperty(value="시작일시(형식:yyyy-MM-dd)")
			@PastOrPresent
			@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
			private LocalDate startCreatedAt;

			@ApiModelProperty(value="종료일시(형식:yyyy-MM-dd)")
			@PastOrPresent
			@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
			private LocalDate endCreatedAt;

			@AssertTrue
			public boolean isValidDateRange() {
				return DateValidator.isValidDateRange(startCreatedAt, endCreatedAt);
			}
		}

		@Getter
		@Setter
		@Builder
		@ToString
		@NoArgsConstructor
		@AllArgsConstructor
		public static class Add {

			@ApiModelProperty(value="사용자아이디", required=true)
			@NotBlank
			@Length(max=50)
			private String userId;

			@ApiModelProperty(value="제목", required=true)
			@NotBlank
			@Length(max=100)
			private String title;

			@ApiModelProperty(value="내용", required=true)
			@NotBlank
			@Length(max=200)
			private String content;
		}

		@Getter
		@Setter
		@Builder
		@ToString
		@NoArgsConstructor
		@AllArgsConstructor
		public static class Modify {

			@ApiModelProperty(value="사용자아이디", required=true)
			@NotBlank
			@Length(max=50)
			private String userId;

			@ApiModelProperty(value="제목", required=true)
			@NotBlank
			@Length(max=100)
			private String title;

			@ApiModelProperty(value="내용", required=true)
			@NotBlank
			@Length(max=200)
			private String content;
		}
	}

	public static class Response {

		@Data
		public static class FindAll {

			@ApiModelProperty(value="샘플일련번호")
			private Long id;

			@ApiModelProperty(value="사용자아이디")
			private String userId;

			@ApiModelProperty(value="제목")
			private String title;

			@ApiModelProperty(value="등록일시")
			private String createdAt;
		}

		@Data
		public static class FindOne {

			@ApiModelProperty(value="샘플일련번호")
			private Long id;

			@ApiModelProperty(value="사용자아이디")
			private String userId;

			@ApiModelProperty(value="제목")
			private String title;

			@ApiModelProperty(value="등록일시")
			private String createdAt;
		}
	}
}
