package junghun.api.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class DustServiceImpl implements DustService{

    private final ModelMapper modelMapper;
    private final DustServiceImpl dustRepository;


    @Override
    public JSONArray returnList(int num) throws IOException,  org.json.simple.parser.ParseException {

        StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/RltmArpltnInforInqireSvrc/v1/getMsrstnAcctoRltmMesureDnsty?numOfRows=100&stationName=%EA%B0%95%EB%82%A8%EA%B5%AC&dataTerm=3MONTH&ver=1.3&serviceKey=B2f0CZtRmiIZZ14OGurfGv7dbUnvm3V0mmwCrmHxS%2BXRN3zHO1bNH6Zq6IA7rqY11fXOIHniNKqXiVy%2B9J61sw%3D%3D&returnType=json"); /*URL*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

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


        // parser 시작
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject =  (JSONObject) jsonParser.parse(result);
        JSONObject body = (JSONObject) jsonObject.get("response");
        JSONObject innerbody = (JSONObject) body.get("body");


        Iterator iter = innerbody.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) iter.next();
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }

        ArrayList<JSONObject> jsonObjectArrayList = new ArrayList<>();

        JSONArray jsonArray = (JSONArray) innerbody.get("items");

        Object[] list = jsonArray.stream().toArray(Object[]::new);

        //
        //parser 종료

        rd.close();
        conn.disconnect();

        return jsonArray;
    }
}
