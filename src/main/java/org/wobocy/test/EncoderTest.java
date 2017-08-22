package org.wobocy.test;

import static org.junit.gen5.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import org.wobocy.util.encode.Base64Decoder;
import org.wobocy.util.encode.Base64Encoder;

import static java.lang.System.*;


public class EncoderTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testEncodeByteArray() {
		String resultStr = Base64Encoder.encode("");
		out.println(resultStr);
		assertNull(resultStr);
	}

	@Test
	public void testEncodeString() {
		String resultStr = Base64Decoder.decodeToStr("SSBMb3ZlIFUu");
		out.println(resultStr);
		assertEquals("I Love U.", resultStr);
	}

}
