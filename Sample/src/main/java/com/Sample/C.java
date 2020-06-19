package com.Sample;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Sample.CompanyName.ProjectName.testbase.TestBase;

public class C extends TestBase{

	int i=1;
	@Test
	public void testLoginC() {
		if (i==3) {
			Assert.assertTrue(true);
		}
		else {
		System.out.println(i);
		i++;
		Assert.assertTrue(false);
	}
/*}
	@Test
	public void testLoginC1() {
		System.out.println(i);
		i++;
		Assert.assertTrue(false);
	}*/
}
}
