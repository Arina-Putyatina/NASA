
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.FileOutputStream;

public class Main {

    public static void main(String[] args) throws Exception {

        final String url = "https://api.nasa.gov/planetary/apod?api_key=tKvvGvK0efh5i1KFgz65FI9s4nIQ7tpO3uLlhQcK";

        ObjectMapper mapper = new ObjectMapper();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        AstronomyPicture astronomyPictures;
        try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
            astronomyPictures = mapper.readValue(httpResponse.getEntity().getContent(), new TypeReference<>() {
            });
        }

        String pictureUrl = astronomyPictures.getUrl();
        String fileName = getFileName(pictureUrl);

        httpGet = new HttpGet(pictureUrl);
        try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
                    entity.writeTo(outputStream);
                }
            }
        }
    }

    public static String getFileName(String pictureUrl) {

        String[] parts = pictureUrl.split("/");
        return parts[parts.length - 1];

    }
}
