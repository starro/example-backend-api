package com.starro.bodoc.common.maker;

import com.google.common.collect.ImmutableList;
import com.starro.bodoc.common.engine.annotation.entity.Description;
import com.starro.bodoc.common.engine.constant.StringConstant;
import com.starro.bodoc.common.engine.exception.ColumnDefinitionNotFoundException;
import com.starro.bodoc.common.engine.exception.CommentNotFoundException;
import com.starro.bodoc.entity.Comment;
import com.starro.bodoc.sample.api.entity.Sample;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import org.springframework.util.ObjectUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
public class CoffeeMakerTest {

//	@Test
	@SneakyThrows
	public void t01_make() {
		for (Target target : ImmutableList.of(Target.builder().clazz(Comment.class).author("lucas")
				.packages("example.comment").api("/comments").build())) {
			generateSource(target);
		}
	}

	@SneakyThrows
	private void generateSource(Target target) {
		Class clazz = target.getClazz();
		String packages = target.getPackages();
		String mapper = replaceToTarget(INPUT_MAPPER, target);
		log.info(mapper);
		String service = replaceToTarget(INPUT_SERVICE, target);
		log.info(service);
		String repository = replaceToTarget(INPUT_REPOSITORY, target);
		log.info(repository);
		String predicate = getPredicate(target);
		log.info(predicate);
		String form = getForm(target);
		log.info(form);
		String test = replaceToTest(INPUT_TEST, target);
		log.info(test);
		String testHelper = replaceToTest(INPUT_TEST_HELPER, target);
		log.info(testHelper);
		String controller = replateToController(INPUT_CONTROLLER, target);
		log.info(controller);

		FileUtils.writeStringToFile(Paths.get(String.format(OUTPUT_FORM,
				StringUtils.replace(packages, StringConstant.DOT, File.separator), clazz.getSimpleName())).toFile(),
				form, Charsets.UTF_8);
		FileUtils.writeStringToFile(Paths.get(String.format(OUTPUT_MAPPER,
				StringUtils.replace(packages, StringConstant.DOT, File.separator), clazz.getSimpleName())).toFile(),
				mapper, Charsets.UTF_8);
		FileUtils.writeStringToFile(Paths.get(String.format(OUTPUT_PREDICATE,
				StringUtils.replace(packages, StringConstant.DOT, File.separator), clazz.getSimpleName())).toFile(),
				predicate, Charsets.UTF_8);
		FileUtils.writeStringToFile(Paths.get(String.format(OUTPUT_CONTROLLER,
				StringUtils.replace(packages, StringConstant.DOT, File.separator), clazz.getSimpleName())).toFile(),
				controller, Charsets.UTF_8);
		FileUtils.writeStringToFile(Paths.get(String.format(OUTPUT_SERVICE,
				StringUtils.replace(packages, StringConstant.DOT, File.separator), clazz.getSimpleName())).toFile(),
				service, Charsets.UTF_8);
		FileUtils.writeStringToFile(Paths.get(String.format(OUTPUT_REPOSITORY,
				StringUtils.replace(packages, StringConstant.DOT, File.separator), clazz.getSimpleName())).toFile(),
				repository, Charsets.UTF_8);
		FileUtils.writeStringToFile(Paths.get(String.format(OUTPUT_TEST,
				StringUtils.replace(packages, StringConstant.DOT, File.separator), clazz.getSimpleName())).toFile(),
				test, Charsets.UTF_8);
		FileUtils.writeStringToFile(Paths
				.get(String.format(OUTPUT_TEST_HELPER,
						StringUtils.replace(packages, StringConstant.DOT, File.separator), clazz.getSimpleName()))
				.toFile(), testHelper, Charsets.UTF_8);
	}

	@SneakyThrows
	private String getPredicate(Target target) {
		String predicate = replaceToTarget(INPUT_PREDICATE, target);
		String conditionString = replaceToTarget(INPUT_PREDICATE_STRING, target);
		String conditionLong = replaceToTarget(INPUT_PREDICATE_LOGN, target);
		String conditionInteger = replaceToTarget(INPUT_PREDICATE_INTEGER, target);
		String conditionBoolean = replaceToTarget(INPUT_PREDICATE_BOOLEAN, target);
		String conditionLocalDate = replaceToTarget(INPUT_PREDICATE_LOCALDATE, target);

		StringBuffer searchFields = new StringBuffer();
		StringBuffer conditionFields = new StringBuffer();
		for (Field field : Arrays.asList(target.getClazz().getDeclaredFields())) {
			if (ObjectUtils.isEmpty(field.getAnnotation(Id.class))) {
				if (BooleanUtils.isFalse(getColumn(field).nullable())) {
					String addField = StringUtils.uncapitalize(
							StringUtils.join(target.getClazz().getSimpleName(), StringConstant.DOT, field.getName()));
					searchFields.append(StringUtils.isEmpty(searchFields.toString()) ? addField
							: StringUtils.join(StringConstant.COMMA, StringConstant.BLANK, addField));
				}
			}
		}
		for (Field field : Arrays.asList(target.getClazz().getDeclaredFields())) {
			if (ObjectUtils.isEmpty(field.getAnnotation(Id.class))) {
				if (BooleanUtils.isFalse(getColumn(field).nullable())) {
					if (String.class == field.getType()) {
						conditionFields.append(String.format(conditionString, StringUtils.capitalize(field.getName()),
								StringUtils.uncapitalize(target.getClazz().getSimpleName()), field.getName()));
					} else if (Long.class == field.getType()) {
						conditionFields.append(String.format(conditionLong, StringUtils.capitalize(field.getName()),
								StringUtils.uncapitalize(target.getClazz().getSimpleName()), field.getName()));
					} else if (Integer.class == field.getType()) {
						conditionFields.append(String.format(conditionInteger, StringUtils.capitalize(field.getName()),
								StringUtils.uncapitalize(target.getClazz().getSimpleName()), field.getName()));
					} else if (Boolean.class == field.getType()) {
						conditionFields.append(String.format(conditionBoolean, StringUtils.capitalize(field.getName()),
								StringUtils.uncapitalize(target.getClazz().getSimpleName()), field.getName()));
					} else if (LocalTime.class == field.getType()) {
						conditionFields
								.append(String.format(conditionLocalDate, StringUtils.capitalize(field.getName()),
										StringUtils.uncapitalize(target.getClazz().getSimpleName()), field.getName()));
					} else if (LocalDate.class == field.getType()) {
						conditionFields
								.append(String.format(conditionLocalDate, StringUtils.capitalize(field.getName()),
										StringUtils.uncapitalize(target.getClazz().getSimpleName()), field.getName()));
					} else if (LocalDateTime.class == field.getType()) {
						conditionFields
								.append(String.format(conditionLocalDate, StringUtils.capitalize(field.getName()),
										StringUtils.uncapitalize(target.getClazz().getSimpleName()), field.getName()));
					}
				}
			}
		}

		return StringUtils.replace(predicate, REPLACE_PREDICATE, conditionFields.toString());
	}

	@SneakyThrows
	private String getForm(Target target) {
		String form = replaceToTarget(INPUT_FORM, target);
		String requestFind = replaceToTarget(INPUT_FORM_REQUEST_FIND, target);
		String requestAdd = replaceToTarget(INPUT_FORM_REQUEST_ADD, target);
		String requestModify = replaceToTarget(INPUT_FORM_REQUEST_MODIFY, target);
		String responseFindOne = replaceToTarget(INPUT_FORM_RESPONSE_FINDONE, target);
		String responseFindAll = replaceToTarget(INPUT_FORM_RESPONSE_FINDALL, target);

		StringBuffer requestFindFields = new StringBuffer();
		StringBuffer requestAddFields = new StringBuffer();
		StringBuffer requestModifyFields = new StringBuffer();
		StringBuffer responseFindOneFields = new StringBuffer();
		StringBuffer responseFindAllFields = new StringBuffer();
		for (Field field : Arrays.asList(target.getClazz().getDeclaredFields())) {
			if (ObjectUtils.isEmpty(field.getAnnotation(Id.class))) {
				String requestFindField = String.format(requestFind, field.getType().getSimpleName(), field.getName());
				requestFindField = StringUtils.replace(requestFindField, REPLACE_FORM_VALIDATION, StringUtils.EMPTY);
				requestFindField = StringUtils.replace(requestFindField, REPLACE_FORM_REQUIRED, StringUtils.EMPTY);
				requestFindField = getDescriptionByField(field, requestFindField);
				String requestAddField = getValidationByField(field,
						String.format(requestAdd, field.getType().getSimpleName(), field.getName()));
				requestAddField = getRequiredByField(field, requestAddField);
				requestAddField = getDescriptionByField(field, requestAddField);
				String requestModifyField = getValidationByField(field,
						String.format(requestModify, field.getType().getSimpleName(), field.getName()));
				requestModifyField = getRequiredByField(field, requestModifyField);
				requestModifyField = getDescriptionByField(field, requestModifyField);

				requestFindFields.append(requestFindField);
				requestAddFields.append(requestAddField);
				requestModifyFields.append(requestModifyField);
			}
		}
		for (Field field : Arrays.asList(target.getClazz().getDeclaredFields())) {
			String responseFindOneField = String.format(responseFindOne, field.getType().getSimpleName(),
					field.getName());
			responseFindOneField = getDescriptionByField(field, responseFindOneField);
			String responseFindAllField = String.format(responseFindAll, field.getType().getSimpleName(),
					field.getName());
			responseFindAllField = getDescriptionByField(field, responseFindAllField);

			responseFindOneFields.append(responseFindOneField);
			responseFindAllFields.append(responseFindAllField);
		}

		form = StringUtils.replace(form, REPLACE_FORM_REQUEST_FIND, requestFindFields.toString());
		form = StringUtils.replace(form, REPLACE_FORM_REQUEST_ADD, requestAddFields.toString());
		form = StringUtils.replace(form, REPLACE_FORM_REQUEST_MODIFY, requestModifyFields.toString());
		form = StringUtils.replace(form, REPLACE_FORM_RESPONSE_FINDONE, responseFindOneFields.toString());
		form = StringUtils.replace(form, REPLACE_FORM_RESPONSE_FINDALL, responseFindAllFields.toString());

		return form;
	}

	@SneakyThrows
	private String getValidationByField(Field field, String target) {
		Column column = getColumn(field);
		StringBuffer annotations = new StringBuffer();

		if (String.class == field.getType()) {
			annotations.append(String.format(getSource(INPUT_FORM_VALIDATION_LENGTH), column.length()));
		} else if (Long.class == field.getType()) {
			annotations.append(String.format(getSource(INPUT_FORM_VALIDATION_RANGE), column.precision()));
		} else if (Integer.class == field.getType()) {
			annotations.append(String.format(getSource(INPUT_FORM_VALIDATION_RANGE), column.precision()));
		} else if (BigDecimal.class == field.getType()) {
			annotations
					.append(String.format(getSource(INPUT_FORM_VALIDATION_DIGITS), column.precision(), column.scale()));
		} else if (LocalTime.class == field.getType()) {
			annotations.append(getSource(INPUT_FORM_VALIDATION_LOCALTIME));
		} else if (LocalDate.class == field.getType()) {
			annotations.append(getSource(INPUT_FORM_VALIDATION_LOCALDATE));
		} else if (LocalDateTime.class == field.getType()) {
			annotations.append(getSource(INPUT_FORM_VALIDATION_LOCALDATETIME));
		}

		if (BooleanUtils.isFalse(column.nullable())) {
			if (String.class == field.getType()) {
				annotations.append(getSource(INPUT_FORM_VALIDATION_NOTBLANK));
			}
			if (Long.class == field.getType()) {
				annotations.append(getSource(INPUT_FORM_VALIDATION_NOTNULL));
			}
			if (Integer.class == field.getType()) {
				annotations.append(getSource(INPUT_FORM_VALIDATION_NOTNULL));
			}
			if (BigDecimal.class == field.getType()) {
				annotations.append(getSource(INPUT_FORM_VALIDATION_NOTNULL));
			}
			if (Boolean.class == field.getType()) {
				annotations.append(getSource(INPUT_FORM_VALIDATION_NOTNULL));
			}
			if (LocalTime.class == field.getType()) {
				annotations.append(getSource(INPUT_FORM_VALIDATION_NOTNULL));
			}
			if (LocalDate.class == field.getType()) {
				annotations.append(getSource(INPUT_FORM_VALIDATION_NOTNULL));
			}
			if (LocalDateTime.class == field.getType()) {
				annotations.append(getSource(INPUT_FORM_VALIDATION_NOTNULL));
			}
		}

		return target.replaceAll(REPLACE_FORM_VALIDATION, annotations.toString());
	}

	@SneakyThrows
	private String getDescriptionByClass(Class<?> target) {
		return Optional.ofNullable(target.getAnnotation(Description.class)).orElseThrow(CommentNotFoundException::new)
				.value();
	}

	@SneakyThrows
	private String getDescriptionByField(Field field, String target) {
		return StringUtils.replace(target, REPLACE_FORM_DESCRIPTION, getDescriptoin(field).value());
	}

	@SneakyThrows
	private String getRequiredByField(Field field, String target) {
		return StringUtils.replace(target, REPLACE_FORM_REQUIRED,
				getColumn(field).nullable() ? StringUtils.EMPTY : getSource(INPUT_FORM_SWAGGER_REQUIRED));
	}

	@SneakyThrows
	private String replaceToTest(String sourcePath, Target target) {
		String source = FileUtils.readFileToString(new File(sourcePath), Charsets.UTF_8);
		source = StringUtils.replace(source, SOURCE_API, target.getApi());
		source = replaceToEntity(source, target);
		source = replaceToPackage(source, target, StringConstant.DOT);
		source = replaceToPackage(source, target, StringConstant.SEMICOLON);
		source = uncapitalizeToEntity(source, target);
		source = replaceToEntityName(source, target);
		source = replaceToAuthorOfDescription(source, target);
		source = replaceToSinceOfDescription(source, target);
		return source;
	}

	@SneakyThrows
	private String replateToController(String sourcePath, Target target) {
		return replaceToTest(sourcePath, target);
	}

	@SneakyThrows
	private String replaceToTarget(String sourcePath, Target target) {
//		String source = FileUtils.readFileToString  (Paths.get(sourcePath).toFile(), Charsets.UTF_8);
		File f = new File(sourcePath);
		String source = FileUtils.readFileToString(f, Charsets.UTF_8);
		source = replaceToEntity(source, target);
		source = replaceToPackage(source, target, StringConstant.DOT);
		source = uncapitalizeToEntity(source, target);
		source = replaceToEntityName(source, target);
		source = replaceToAuthorOfDescription(source, target);
		source = replaceToSinceOfDescription(source, target);
		return source;
	}

	@SneakyThrows
	private String replaceToEntity(String source, Target target) {
		return StringUtils.replace(source, Sample.class.getSimpleName(), target.getClazz().getSimpleName());
	}

	@SneakyThrows
	private String replaceToEntityName(String source, Target target) {
		return StringUtils.replace(source, REPLACE_ENTITY_NAME, getDescriptionByClass(target.getClazz()));
	}

	@SneakyThrows
	private String uncapitalizeToEntity(String source, Target target) {
		return StringUtils.replace(source, Sample.class.getSimpleName().toLowerCase(),
				StringUtils.uncapitalize(target.getClazz().getSimpleName()));
	}

	@SneakyThrows
	private String replaceToPackage(String source, Target target, String endsWith) {
		return StringUtils.replace(source, StringUtils.join(Sample.class.getSimpleName().toLowerCase(), endsWith),
				StringUtils.join(target.packages, endsWith));
	}

	@SneakyThrows
	private String replaceToAuthorOfDescription(String source, Target target) {
		return StringUtils.replace(source, REPLACE_DESCRIPTION_AUTHOR, target.getAuthor());
	}

	@SneakyThrows
	private String replaceToSinceOfDescription(String source, Target target) {
		return StringUtils.replace(source, REPLACE_DESCRIPTION_SINCE,
				LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
	}

	@SneakyThrows
	private Description getDescriptoin(Field field) {
		return Optional.ofNullable(field.getAnnotation(Description.class)).orElseThrow(CommentNotFoundException::new);
	}

	@SneakyThrows
	private Column getColumn(Field field) {
		return Optional.ofNullable(field.getAnnotation(Column.class))
				.orElseThrow(ColumnDefinitionNotFoundException::new);
	}

	@SneakyThrows
	private static String getSource(String sourcePath) {
		return FileUtils.readFileToString(new File(sourcePath));
	}

	@SneakyThrows
	private static File getFile(String sourcePath) {
		return new File(sourcePath); 
	}

	private static String getProjectPath() {
		String projectPath = Sample.class.getResource(File.separator).getPath().toString();
		return projectPath.substring(NumberUtils.INTEGER_ZERO, projectPath.indexOf(INPUT_DIR));
	}

	@Getter
	@Setter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	static class Target {

		private Class<?> clazz;
		private String packages;
		private String api;
		private String author;
	}

	private final static String SOURCE_API = "/samples";
	private final static String INPUT_DIR = "example-backend-api";

	private final static String OUTPUT_FORM = StringUtils.join(getProjectPath(), "output-", INPUT_DIR,
			"/src/%s/form/%sForm.java");
	private final static String OUTPUT_MAPPER = StringUtils.join(getProjectPath(), "output-", INPUT_DIR,
			"/src/%s/mapper/%sMapper.java");
	private final static String OUTPUT_PREDICATE = StringUtils.join(getProjectPath(), "output-", INPUT_DIR,
			"/src/%s/predicate/%sPredicate.java");
	private final static String OUTPUT_CONTROLLER = StringUtils.join(getProjectPath(), "output-", INPUT_DIR,
			"/src/%s/controller/%sController.java");
	private final static String OUTPUT_SERVICE = StringUtils.join(getProjectPath(), "output-", INPUT_DIR,
			"/src/%s/service/%sService.java");
	private final static String OUTPUT_REPOSITORY = StringUtils.join(getProjectPath(), "output-", INPUT_DIR,
			"/src/%s/repository/%sRepository.java");
	private final static String OUTPUT_TEST = StringUtils.join(getProjectPath(), "output-", INPUT_DIR,
			"/test//%s/%sTest.java");
	private final static String OUTPUT_TEST_HELPER = StringUtils.join(getProjectPath(), "output-", INPUT_DIR,
			"/test/%s/%sHelper.java");

	private final static String INPUT_TEST = StringUtils.join(getProjectPath(), INPUT_DIR, "/src/main/resources/sample",
			"/test/test.template");
	private final static String INPUT_TEST_HELPER = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/test/test.helper.template");
	private final static String INPUT_MAPPER = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/mapper/mapper.template");
	private final static String INPUT_CONTROLLER = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/controller/controller.template");
	private final static String INPUT_SERVICE = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/service/service.template");
	private final static String INPUT_REPOSITORY = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/repository/repository.template");
	private final static String INPUT_PREDICATE = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/predicate/predicate.template");
	private final static String INPUT_PREDICATE_STRING = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/predicate/predicate.string.template");
	private final static String INPUT_PREDICATE_LOGN = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/predicate/predicate.long.template");
	private final static String INPUT_PREDICATE_INTEGER = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/predicate/predicate.integer.template");
	private final static String INPUT_PREDICATE_BOOLEAN = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/predicate/predicate.boolean.template");
	private final static String INPUT_PREDICATE_LOCALDATE = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/predicate/predicate.localdate.template");
	private final static String INPUT_FORM = StringUtils.join(getProjectPath(), INPUT_DIR, "/src/main/resources/sample",
			"/form/form.template");
	private final static String INPUT_FORM_REQUEST_FIND = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/form/form.request.find.template");
	private final static String INPUT_FORM_REQUEST_ADD = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/form/form.request.add.template");
	private final static String INPUT_FORM_REQUEST_MODIFY = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/form/form.request.modify.template");
	private final static String INPUT_FORM_RESPONSE_FINDONE = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/form/form.response.findone.template");
	private final static String INPUT_FORM_RESPONSE_FINDALL = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/form/form.response.findall.template");
	private final static String INPUT_FORM_VALIDATION_NOTBLANK = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/form/form.validation.notblank.template");
	private final static String INPUT_FORM_VALIDATION_NOTNULL = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/form/form.validation.notnull.template");
	private final static String INPUT_FORM_VALIDATION_RANGE = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/form/form.validation.range.template");
	private final static String INPUT_FORM_VALIDATION_LENGTH = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/form/form.validation.length.template");
	private final static String INPUT_FORM_VALIDATION_DIGITS = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/form/form.validation.digits.template");
	private final static String INPUT_FORM_VALIDATION_LOCALTIME = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/form/form.validation.localtime.template");
	private final static String INPUT_FORM_VALIDATION_LOCALDATE = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/form/form.validation.localdate.template");
	private final static String INPUT_FORM_VALIDATION_LOCALDATETIME = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/form/form.validation.localdatetime.template");
	private final static String INPUT_FORM_SWAGGER_REQUIRED = StringUtils.join(getProjectPath(), INPUT_DIR,
			"/src/main/resources/sample", "/form/form.swagger.property.required.template");

	private final static String REPLACE_DESCRIPTION_SINCE = "#description.since#";
	private final static String REPLACE_DESCRIPTION_AUTHOR = "#description.author#";
	private final static String REPLACE_ENTITY_NAME = "#entity.name#";
	private final static String REPLACE_PREDICATE = "#predicate#";
	private final static String REPLACE_FORM_REQUEST_FIND = "#request.find#";
	private final static String REPLACE_FORM_REQUEST_ADD = "#request.add#";
	private final static String REPLACE_FORM_REQUEST_MODIFY = "#request.modify#";
	private final static String REPLACE_FORM_RESPONSE_FINDONE = "#response.findone#";
	private final static String REPLACE_FORM_RESPONSE_FINDALL = "#response.findall#";
	private final static String REPLACE_FORM_VALIDATION = "#form.validation#";
	private final static String REPLACE_FORM_DESCRIPTION = "#form.description#";
	private final static String REPLACE_FORM_REQUIRED = "#form.required#";
}
