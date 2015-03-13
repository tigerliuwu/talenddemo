package org.wliu.marketo.soap;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.namespace.QName;

import org.apache.commons.codec.binary.Hex;

import com.marketo.mktows.ArrayOfLeadRecord;
import com.marketo.mktows.Attribute;
import com.marketo.mktows.AuthenticationHeader;
import com.marketo.mktows.LeadKey;
import com.marketo.mktows.LeadKeyRef;
import com.marketo.mktows.LeadRecord;
import com.marketo.mktows.MktMktowsApiService;
import com.marketo.mktows.MktowsPort;
import com.marketo.mktows.ParamsGetLead;
import com.marketo.mktows.ResultGetLead;
import com.marketo.mktows.SuccessGetLead;

public class GetLead {

    public static void main(String[] args) {
        System.out.println("Executing Get Lead");
        try {
            URL marketoSoapEndPoint = new URL("https://347-IAT-677.mktoapi.com/soap/mktows/2_7" + "?WSDL");
            String marketoUserId = "talend1_377614264D2D9106D24C48";
            String marketoSecretKey = "?????";

            QName serviceName = new QName("http://www.marketo.com/mktows/", "MktMktowsApiService");
            MktMktowsApiService service = new MktMktowsApiService(marketoSoapEndPoint, serviceName);
            MktowsPort port = service.getMktowsApiSoapPort();

            // Create Signature
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            String text = df.format(new Date());
            String requestTimestamp = text.substring(0, 22) + ":" + text.substring(22);         
            String encryptString = requestTimestamp + marketoUserId ;

            SecretKeySpec secretKey = new SecretKeySpec(marketoSecretKey.getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(secretKey);
            byte[] rawHmac = mac.doFinal(encryptString.getBytes());
            char[] hexChars = Hex.encodeHex(rawHmac);
            String signature = new String(hexChars); 

            // Set Authentication Header
            AuthenticationHeader header = new AuthenticationHeader();
            header.setMktowsUserId(marketoUserId);
            header.setRequestTimestamp(requestTimestamp);
            header.setRequestSignature(signature);

            // Create Request
            ParamsGetLead request = new ParamsGetLead();
            LeadKey key = new LeadKey();
            key.setKeyType(LeadKeyRef.EMAIL);
            key.setKeyValue("test2015021202@qq.com");
            request.setLeadKey(key);

            SuccessGetLead result = port.getLead(request, header);
            ResultGetLead resultLeads = result.getResult();
            ArrayOfLeadRecord r =  resultLeads.getLeadRecordList().getValue();
            List<LeadRecord> list= r.getLeadRecords();
            
            for (LeadRecord record: list) {
            	System.out.println("Id:"+record.getId() + "\r" + "mail:"+record.getEmail()+"\tForeignSysPersonId:"+record.getForeignSysPersonId() + "\r" + "ForeignSysType:"+record.getForeignSysType());
            	List<Attribute> attributes = record.getLeadAttributeList().getValue().getAttributes();
            	System.out.println("attributes:");
            	for (Attribute attribute: attributes) {
            		System.out.print("AttrName:"+attribute.getAttrName() + "\r" + "AttrValue:"+attribute.getAttrValue());

            	}
            	System.out.println();
            }
            System.out.println(list.size());
            

//            JAXBContext context = JAXBContext.newInstance(SuccessGetLead.class);
//            Marshaller m = context.createMarshaller();
//            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            m.marshal(result, System.out);

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}