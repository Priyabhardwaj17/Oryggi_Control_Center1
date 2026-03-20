package com.oryggi.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.oryggi.base.BaseTest;

public class ScreenshotTest extends BaseTest {

	@Test
	public void testFailureScreenshot() {
	    Assert.fail("Intentional Failure to Test Screenshot");
	}

}
