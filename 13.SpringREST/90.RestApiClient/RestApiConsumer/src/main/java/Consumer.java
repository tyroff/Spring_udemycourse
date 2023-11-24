import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Consumer {
    public static void main(String[] args) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://reqres.in/api/users/2";
        String response = restTemplate.getForObject(url, String.class);

        System.out.println(response);
//--------------------------------------------------------------
        Map<String, String> jsonTeoSend = new HashMap<>();
        jsonTeoSend.put("name", "Test name");
        jsonTeoSend.put("job", "Test job");

        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonTeoSend);

        String urlPost = "https://reqres.in/api/users";
        String responsePost = restTemplate.postForObject(urlPost, request, String.class);

        //Парсит полученный JSON с помощью Jackson
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node =  mapper.readTree(responsePost);

        System.out.println(responsePost);
        // из JSONа получаем значение ключа "name" и "job"
        System.out.println(node.get("name"));
        System.out.println(node.get("job"));
//--------------------------------------------------------------
        // JACKSON распарсил JSON в созданный объект RegresInResponse
        RegresInResponse responseRegresIn = restTemplate.postForObject(urlPost, request, RegresInResponse.class);

        System.out.println(responseRegresIn.getId() + responseRegresIn.getName() + responseRegresIn.getJob() + responseRegresIn.getCreatedAt());
    }
}
