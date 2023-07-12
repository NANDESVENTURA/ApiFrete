package ApiFrete.api.Service;

import ApiFrete.api.domain.Shipments.DataRegisterShipment;
import okhttp3.*;
import org.json.*;
import org.springframework.stereotype.Service;
import java.io.IOException;


@Service
public class GoogleService {
    public Integer getDistance(DataRegisterShipment dataShipment) throws IOException {


        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        HttpUrl httpUrl = HttpUrl.parse("https://maps.googleapis.com/maps/api/distancematrix/json")
                .newBuilder()
                .addQueryParameter("origins", dataShipment.zip_code_origin().toString())
                .addQueryParameter("destinations", dataShipment.zip_code_destination().toString())
                .addQueryParameter("units", "metric")
                .addQueryParameter("key", "AIzaSyCeIkeY1uV0zEXsr_3Lwl_2RKJIcy8YiTM")
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .build();
        Response response = client.newCall(request).execute();


        JSONObject jsonObject = new JSONObject(response.body().string());
        JSONArray arrayRows = jsonObject.getJSONArray("rows");
        JSONArray arrayElements = arrayRows.getJSONObject(0).getJSONArray("elements");
        JSONObject arrayDistance = arrayElements.getJSONObject(0).getJSONObject("distance");
        Integer value = arrayDistance.getInt("value");
        System.out.println(arrayRows);
        System.out.println(arrayElements);
        System.out.println(arrayDistance);
        System.out.println(value);
        return value;
    }
}

