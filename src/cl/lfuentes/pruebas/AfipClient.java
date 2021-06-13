package cl.lfuentes.pruebas;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AfipClient {

    public static String createRequestMessage(){
        String LoginTicketRequest_xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:a5=\"http://a5.soap.ws.server.puc.sr/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <a5:getPersona>"
                +"<token>PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/"
                +"Pgo8c3NvIHZlcnNpb249IjIuMCI+CiAgICA8aWQgc3JjPSJDTj13c2FhaG9tbywgTz1BRklQLCBDPU"
                +"FSLCBTRVJJQUxOVU1CRVI9Q1VJVCAzMzY5MzQ1MDIzOSIgdW5pcXVlX2lkPSI1Mzk3NDczMjQiIGdl"
                +"bl90aW1lPSIxNjIzMzQxMTE4IiBleHBfdGltZT0iMTYyMzM4NDM3OCIvPgogICAgPG9wZXJhdGlvbi"
                +"B0eXBlPSJsb2dpbiIgdmFsdWU9ImdyYW50ZWQiPgogICAgICAgIDxsb2dpbiBlbnRpdHk9IjMzNjkz"
                +"NDUwMjM5IiBzZXJ2aWNlPSJ3c19zcl9jb25zdGFuY2lhX2luc2NyaXBjaW9uIiB1aWQ9IlNFUklBTE"
                +"5VTUJFUj1DVUlUIDIwNDE0NjI0NzE2LCBDTj1wdWViYTEiIGF1dGhtZXRob2Q9ImNtcyIgcmVnbWV0"
                +"aG9kPSIyMiI+CiAgICAgICAgICAgIDxyZWxhdGlvbnM+CiAgICAgICAgICAgICAgICA8cmVsYXRpb2"
                +"4ga2V5PSIyMDQxNDYyNDcxNiIgcmVsdHlwZT0iNCIvPgogICAgICAgICAgICA8L3JlbGF0aW9ucz4K"
                +"ICAgICAgICA8L2xvZ2luPgogICAgPC9vcGVyYXRpb24+Cjwvc3NvPgo=</token>"
                +"<sign>UVeoWo+DowIW3w4hjPS26jDTYSozjTwK+JcAdThEr6Obm3OkOYpKe9xqdCqXnyX4OAzWaMYO"
                +"Db85uW6qW+k6D99lQi+Zg3BtVAT8snHVtfQjoNb3v3oks34CKw4YWA9pkuQpypjLprvvnZEUqVput3"
                +"Gply59Kt7c5U372b67ihY=</sign>"
                +"<cuitRepresentada>20414624716</cuitRepresentada>"
                +"<idPersona>20002307554</idPersona>"
                +"</a5:getPersona>"
                +"</soapenv:Body>"
                +"</soapenv:Envelope>";
        return LoginTicketRequest_xml;
    }

    public static String invoke() throws IOException, MalformedURLException {

//Code to make a webservice HTTP request
        String responseString = "";
        String outputString = "";
        String wsURL = "https://awshomo.afip.gov.ar/sr-padron/webservices/personaServiceA5";
        URL url = new URL(wsURL);
        URLConnection connection = url.openConnection();
        HttpURLConnection httpConn = (HttpURLConnection)connection;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        String xmlInput = createRequestMessage();

        byte[] buffer = new byte[xmlInput.length()];
        buffer = xmlInput.getBytes();
        bout.write(buffer);
        byte[] b = bout.toByteArray();
        String SOAPAction = "getPersona";
// Set the appropriate HTTP parameters.
        httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
        httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        httpConn.setRequestProperty("SOAPAction", SOAPAction);
        httpConn.setRequestMethod("POST");
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);
        OutputStream out = httpConn.getOutputStream();
//Write the content of the request to the outputstream of the HTTP Connection.
        out.write(b);
        out.close();
//Ready with sending the request.

//Read the response.
        InputStreamReader isr = null;
        if (httpConn.getResponseCode() == 200) {
            isr = new InputStreamReader(httpConn.getInputStream());
        } else {
            isr = new InputStreamReader(httpConn.getErrorStream());
        }

        BufferedReader in = new BufferedReader(isr);

//Write the SOAP message response to a String.
        while ((responseString = in.readLine()) != null) {
            outputString = outputString + responseString;
        }

        System.out.println(outputString);
//Parse the String output to a org.w3c.dom.Document and be able to reach every node with the org.w3c.dom API.
       /* Document document = parseXmlFile(outputString); // Write a separate method to parse the xml input.
        NodeList nodeLst = document.getElementsByTagName("<TagName of the element to be retrieved>");
        String elementValue = nodeLst.item(0).getTextContent();
        System.out.println(elementValue);

//Write the SOAP message formatted to the console.
        String formattedSOAPResponse = formatXML(outputString); // Write a separate method to format the XML input.
        System.out.println(formattedSOAPResponse);*/
        return "0";
    }

    private String formatXML(String outputString) {
        return null;
    }

    private Document parseXmlFile(String outputString) {
        return null;
    }

}
