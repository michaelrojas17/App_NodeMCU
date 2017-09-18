package michaelrojas.app_nodemcu;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Mike on 15/09/2017.
 */

public class Conexion {

    public static String getData(String urlUsuario){

        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url(urlUsuario)
                .build();


        try{
            Response response = client.newCall(request).execute();
            return response.body().string();

        } catch (IOException error) {
            return null;
        }

    }
}
