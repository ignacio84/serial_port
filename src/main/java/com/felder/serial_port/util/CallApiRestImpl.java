package com.felder.serial_port.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felder.serial_port.model.pojo.PesoBascula;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallApiRestImpl implements ICallApiRest {

    private static String END_POINT = "http://192.168.1.15:8080/weight/putWeight";
    private PesoBascula pesoBascula;

    @Override
    public void setObject(Object obj) {
        this.pesoBascula = (PesoBascula) obj;
    }

    @Override
    public void call() throws Exception {
        URL url = new URL(END_POINT);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        //ENVIA PARAMETROS AL BACKEND
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(new ObjectMapper().writeValueAsString(this.pesoBascula).getBytes());
        os.flush();
        os.close();

        int responseCode = con.getResponseCode();
        System.out.println("response code :" + responseCode);
//        System.out.println("Mensaje :" + con.getResponseMessage());
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
        }
    }
}
