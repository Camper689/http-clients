package com.cmiyc;

import com.cmiyc.client.RestClient;
import com.cmiyc.client.SoapClient;
import com.cmiyc.client.WebsocketClient;

public class HttpClientDemo {

    public static void main(String[] args) {
        try {
            RestClient restClient = new RestClient("http://localhost:8080");
            System.out.println("RestClient GET: " + restClient.getRequest("/rest?firstName=Peter&lastName=Peterson"));
            System.out.println("RestClient POST: " + restClient.postRequest("/rest", "{\"firstName\":\"Peter\",\"lastName\":\"Peterson\"}"));
        } catch (Exception e) {
            System.out.println("RestClient exception: " + e.getMessage());
        }
        System.out.println();

        try {
            SoapClient soapClient = new SoapClient("http://localhost:9003");
            System.out.println("SoapClient: " + soapClient.postRequest("/soap", soapRequestBody));
        } catch (Exception e) {
            System.out.println("SoapClient exception: " + e.getMessage());
        }
        System.out.println();

        try {
            WebsocketClient websocketClient = new WebsocketClient("ws://localhost:9005/ws");
            System.out.println("WebsocketClient: " + websocketClient.sendAndReceive("{\"firstName\":\"Amanda\",\"lastName\":\"Armstrong\",\"birthDayDate\":\"04.04.1984\",\"gender\":\"FEMALE\"}"));
        } catch (Exception e) {
            System.out.println("WebsocketClient exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static final String soapRequestBody =
            "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:gs=\"http://soap.api/xsd\">\n" +
            "                <soapenv:Header/>\n" +
            "                <soapenv:Body>\n" +
            "                    <gs:getPersonRequest>\n" +
            "                        <gs:firstName>Steven</gs:firstName>\n" +
            "                        <gs:lastName>Stevenson</gs:lastName>\n" +
            "                        <gs:birthDayDate>03.03.1983</gs:birthDayDate>\n" +
            "                    </gs:getPersonRequest>\n" +
            "                </soapenv:Body>\n" +
            "            </soapenv:Envelope>";
}
