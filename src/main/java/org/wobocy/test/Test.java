package org.wobocy.test;

import java.util.logging.Logger;

import org.wobocy.util.encode.Base64Encoder;

public class Test {
	public static void main( String[] args ) {
		String str = Base64Encoder.encode("abc");
		if( str == null ) {
			throw new NullPointerException();
		}
		Logger logger = Logger.getLogger("testLogger");
		logger.info(str);
	}
}
