package com.alse.paideia;

import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;
        import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
        import org.w3c.dom.*;
        import javax.xml.parsers.DocumentBuilderFactory;
        import javax.xml.parsers.DocumentBuilder;
        import org.xml.sax.SAXException;
        import org.xml.sax.SAXParseException;


public class XMLparser{
    public static HashMap<String , String> WolframGet(String query){
        HashMap<String, String> results = new HashMap<>();
        try {
            String appid = "AV8XGU-PTR2Q4AYTQ";
            URL url = new URL("http://api.wolframalpha.com/v2/query?input="+query+"&appid="+appid);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(conn.getInputStream());


            NodeList listOfPods = doc.getElementsByTagName("pod");
            NodeList listOfPlaintext = doc.getElementsByTagName("plaintext");

            int totalPods = listOfPods.getLength();
            Log.e("reader", Integer.toString(totalPods));
            if (totalPods == 0){
                System.out.println("Oops I didn't get that!");
            } else {
                for (int i = 0; i < totalPods; ++i) {
                    Node podNode = listOfPods.item(i);
                    NamedNodeMap attributes = podNode.getAttributes();
                    results.put(attributes.getNamedItem("title").getNodeValue(), listOfPlaintext.item(i).getTextContent().replace("\t", "").replace("  ", " "));
                }
            }

        }catch (SAXParseException err) {
//            System.out.println ("** Parsing error" + ", line " + err.getLineNumber () + ", uri " + err.getSystemId ());
//            System.out.println(" " + err.getMessage ());

        }catch (SAXException e) {
//            Exception x = e.getException ();
//            ((x == null) ? e : x).printStackTrace ();
        }catch (Throwable t) {
//            t.printStackTrace ();
        }
        return results;
    }
}
