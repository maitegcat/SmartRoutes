package urjc.com.wayfindingapp;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                // .baseUrl("http://10.0.2.2:8080")//emulador
                //   .baseUrl("http://192.168.1.128:8080")//emulador
                // .baseUrl("http://192.168.1.134:8080")//Wifi
                // .baseUrl("http://192.168.1.132:8080")//phone
                // .baseUrl("http://10.0.2.2:8080")//phone
                 .baseUrl("http://193.147.52.3:8082")//phone
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }
}

   /* private static Retrofit API_SERVICE;

    public static Retrofit getClient() {

        //interceptor y le indicamos el log level a usar
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Asociamos el interceptor a las peticiones
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()

                .baseUrl("http://192.168.1.128:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build()) // <-- usamos el log level
                .build();

        API_SERVICE = retrofit.create(Retrofit.class);
    }

        return API_SERVICE;
   }
}*/
