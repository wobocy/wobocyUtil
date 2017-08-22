package org.wobocy.util.encode;

/**
 * The Class contains two static methods which is used to decode base64 strings.
 * @author wobocy
 * @since 2017.08.21
 */
public class Base64Decoder {
	
	//dictionary
	private static final String refString ="ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			                              + "abcdefghijklmnopqrstuvwxyz"
			                              + "0123456789+"+"/"+"="; 
	
	/**
	 * The method is used to decode the string encoded by base64 rule
	 * @param inputStr
	 * @return String
	 */
	public static String decodeToStr ( String inputStr ){
		
		// data format check
		if( inputStr == null ){
			return null;
		}
		
		// delete the "=" string
		while ( inputStr.contains( "=" ) ) {
			inputStr = inputStr.replace("=", "");
		}
		
		// decoded input data to original binary data
		String decodeBinStr = "";
		for ( int i = 0; i < inputStr.length(); i++) {
			int index = refString.indexOf( inputStr.substring( i, i + 1) );
			// check if the string contains illegal chars
			if ( index != -1 ){
				decodeBinStr += String.format( "%06d", Integer.valueOf( Integer.toBinaryString( index ) ) );
			}else{
				System.err.println(" >>Error: Input values contain exception characters. ");
				return null;
			}
		}
		
		// convert the decoded binary data to string data
		String finalDecodeStr = "";
		for ( int i = 0; i < ( decodeBinStr.length() / 8 ); i++ ) {
			String subString = decodeBinStr.substring( 8*i, 8 * (i + 1) );
			char subConvertedChar =(char) Integer.valueOf( subString, 2 ).intValue();
			finalDecodeStr += String.valueOf(subConvertedChar);
		}
		return finalDecodeStr;
	}
	
	
	/**
	 * The method takes a string and decoded it to bytes based on base64 rules
	 * @param inputStr
	 * @return byte[]
	 */
	public static byte[] decodeToBytes(String inputStr ) {
		
		// input data check 
		if( inputStr == null ) {
			return null;
		}
		return decodeToStr(inputStr).getBytes();
	}
}
