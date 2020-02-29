package com.vincent.coretest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleTest {
	protected final Logger logger = LoggerFactory.getLogger(SimpleTest.class);

	@Test
	public void test() {
		logger.info("test");
	}
}
