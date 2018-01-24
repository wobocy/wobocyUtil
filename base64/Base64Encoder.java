package org.wobocy.util.encode;

/**
 * The method contains two static mehtods to encode string or bytes based on base64 rules.
 * @author wobocy
 * @since 2017.08.24
 */
public class Base64Encoder {
	//dictionary
	private static final String[] REF_STRING = 
		                 {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P",
			              "Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f",
			              "g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v",
			              "w","x","y","z","0","1","2","3","4","5","6","7","8","9","+","/","="};
	
	/**
	 * Encode method with byte[] input
	 * @param inputByte
	 * @return String
	 */
	public static String encode (byte[] inputByte) {
		
		// input data check 
		if( inputByte.length < 1) {
			return null;
		}
		
		// convert input data to binary string
		String inputBinaryStr = "";
		for (int i = 0; i < inputByte.length; i++){
			inputBinaryStr += String.format("%08d", Integer.parseInt(Integer.toBinaryString(inputByte[i])));
		}
		
		// add zeros if iputBinaryStr can not divided by six
		int partAddNum = 0;// number of zeros need to add
		int strLen = inputBinaryStr.length();
		if ( (strLen % 6) != 0 ) {
			partAddNum = (int) Math.ceil( strLen / 6.0 ) * 6 - strLen;
			for (int i = 0; i < partAddNum; i++) {
				inputBinaryStr += "0";
			}
			strLen += partAddNum;
		}
		
		// convert based on base64
		String finalConvStr = "";
		for ( int i = 0; i < (strLen / 6); i++ ) {
			String subString = inputBinaryStr.substring( 6*i, 6 * (i + 1) );
			String eleAfterConv = REF_STRING[ Integer.valueOf(subString,2).intValue() ];
			finalConvStr += eleAfterConv;
		}
		
		// the "=" string need to add to the end when the inputBinaryStr can not divided by eight
		String addStr = "";
		int strLenMid = strLen;
		while (( strLenMid % 8) != 0) {
			strLenMid += 6; addStr += "=";
		}
		finalConvStr += addStr; // add "=" string
		return finalConvStr;
	}
	
	/**
	 * Encode method with string input
	 * @param inputStr
	 * @return String
	 */
	public static String encode (String inputStr) {
		
		// input data check
		if( inputStr == null ) {
			return inputStr;
		}
		
		byte[] inputByte = inputStr.getBytes();
		return encode(inputByte);
	}

}
