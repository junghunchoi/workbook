package junghun.workbook.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;

import net.minidev.json.parser.ParseException;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class apiText {

    //https://api.odcloud.kr/api/RltmArpltnInforInqireSvrc/v1/getMsrstnAcctoRltmMesureDnsty?numOfRows=1&stationName=%EA%B0%95%EB%82%A8%EA%B5%AC&dataTerm=DAILY&ver=1.3&serviceKey=B2f0CZtRmiIZZ14OGurfGv7dbUnvm3V0mmwCrmHxS%2BXRN3zHO1bNH6Zq6IA7rqY11fXOIHniNKqXiVy%2B9J61sw%3D%3D&returnType=json
    public static void main(String[] args)
        throws IOException, JSONException, ParseException, org.json.simple.parser.ParseException {

        StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/RltmArpltnInforInqireSvrc/v1/getMsrstnAcctoRltmMesureDnsty?numOfRows=100&stationName=%EA%B0%95%EB%82%A8%EA%B5%AC&dataTerm=3MONTH&ver=1.3&serviceKey=B2f0CZtRmiIZZ14OGurfGv7dbUnvm3V0mmwCrmHxS%2BXRN3zHO1bNH6Zq6IA7rqY11fXOIHniNKqXiVy%2B9J61sw%3D%3D&returnType=json"); /*URL*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        Map<String, Object> map = new HashMap<String, Object>();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        BufferedReader rd;

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        String result;

        result = bf.readLine();

        System.out.println("result" + result);
        // parser 시작
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject =  (JSONObject) jsonParser.parse(result);
        JSONObject body = (JSONObject) jsonObject.get("response");
        JSONObject innerbody = (JSONObject) body.get("body");
        System.out.println("innerbody" + innerbody.get("items"));

        ArrayList<JSONObject> jsonObjectArrayList = new ArrayList<>();

        JSONArray jsonArray = (JSONArray) innerbody.get("items");


        //parser 종료

        rd.close();
        conn.disconnect();

//        ObjectMapper objectMapper = new ObjectMapper();
//        TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>() {};
//
//        map = new ObjectMapper().readValue(responseJson.toString(), Map.class);
//
//
//        for (String key: map.keySet()){
//            System.out.println(key+ " = " + map.get(key));
//            if (key.equals("items")) {
//                System.out.println(key+ " = " + map.get(key));
//            }
//
//        }


    }

}
