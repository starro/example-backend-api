package com.starro.bodoc.common.engine.test;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

/**   
 * @since       2018.10.03
 * @author      starro
 * @description super test
 **********************************************************************************************************************/
@SpringBootTest
@RunWith(SpringRunner.class)
@Ignore
public class SuperTest {

	           public MockMvc 						 mock;
	           public RestDocumentationResultHandler handler;
	@Autowired public WebApplicationContext          context;
	@Rule      public JUnitRestDocumentation         document = new JUnitRestDocumentation();

	@Before
	public void before() {
		handler = MockMvcRestDocumentation.document(
				"{class-name}/{method-name}"
				, preprocessRequest(prettyPrint())
				, preprocessResponse(prettyPrint()));

		mock = MockMvcBuilders.webAppContextSetup(context)
				.apply(documentationConfiguration(document))
				.alwaysDo(handler)
				.build();

		TestHelper.initialize(mock, handler);
	}
}