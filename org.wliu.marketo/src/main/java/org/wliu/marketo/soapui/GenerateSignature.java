package org.wliu.marketo.soapui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class GenerateSignature {

	public static void main(String[] args) {

		try {
			//URL marketoSoapEndPoint = new URL("CHANGE ME" + "?WSDL");
			String marketoUserId = "talend1_377614264D2D9106D24C48";
			String marketoSecretKey = "?";

//			QName serviceName = new QName("http://www.marketo.com/mktows/",
//					"MktMktowsApiService");
//			MktMktowsApiService service = new MktMktowsApiService(
//					marketoSoapEndPoint, serviceName);
//			MktowsPort port = service.getMktowsApiSoapPort();
//
//			// Create Signature
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
			String text = df.format(new Date());
			String requestTimestamp = text.substring(0, 22) + ":"
					+ text.substring(22);
			requestTimestamp = "2015-03-14T14:15:06-07:00"; // change to a bigger than today Date
			String encryptString = requestTimestamp + marketoUserId;

			SecretKeySpec secretKey = new SecretKeySpec(
					marketoSecretKey.getBytes(), "HmacSHA1");
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(secretKey);
			byte[] rawHmac = mac.doFinal(encryptString.getBytes());
			char[] hexChars = Hex.encodeHex(rawHmac);
			String signature = new String(hexChars);
			System.out.println(signature);
			/*
			 * // Set Authentication Header AuthenticationHeader header = new
			 * AuthenticationHeader(); header.setMktowsUserId(marketoUserId);
			 * header.setRequestTimestamp(requestTimestamp);
			 * header.setRequestSignature(signature);
			 * 
			 * JAXBContext context =
			 * JAXBContext.newInstance(AuthenticationHeader.class); Marshaller m
			 * = context.createMarshaller();
			 * m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			 * m.marshal(header, System.out);
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	String signature;
	String requestTimestamp;
	String marketoUserId;

}
