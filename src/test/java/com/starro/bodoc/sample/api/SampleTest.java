package com.starro.bodoc.sample.api;

import com.starro.bodoc.common.engine.test.SuperTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.transaction.annotation.Transactional;

import static com.starro.bodoc.sample.api.SampleHelper.*;

/**
 * @since       2018.10.03
 * @author      starro
 * @description sample test
 **********************************************************************************************************************/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class SampleTest extends SuperTest {

	@Test
	public void t01_getAll() {
		add   (addSample());
		add   (addSample());
		getAll(findSample());
	}

	@Test
	public void t02_get() {
		get(add(addSample()).getId());
	}

	@Test
	public void t03_add() {
		add(addSample());
	}

	@Test
	public void t04_modify() {
		modify(add(addSample()).getId(), modifySample());
	}

	@Test
	public void t05_remove() {
		remove(add(addSample()).getId());
	}

	@Test
	public void t06_get_notFound() {
		get_notFound(Long.MIN_VALUE);
	}

	@Test
	public void t07_modify_notFound() {
		modify_notFound(Long.MIN_VALUE, modifySample());
	}

	@Test
	public void t08_remove_notFound() {
		remove_notFound(Long.MIN_VALUE);
	}
}