package com.ztom.springcloud;

import java.time.ZonedDateTime;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	public static void main(String[] args) {
		ZonedDateTime zbj = ZonedDateTime.now();
		System.out.println(zbj);
	}
}
