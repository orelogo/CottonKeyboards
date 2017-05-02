package com.orelogo.cottonkeyboards;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by patso on 5/1/2017.
 */

public class Controller {

    private static final String DOMAIN = "https://shopicruit.myshopify.com";
    private static final String ACCESS_TOKEN = "c32313df0d0ef512ca64d5b336a0d7c6";

    private static final OkHttpClient client = new OkHttpClient();

    static void getOrders(int pageNumber) {
        Request request = new Request.Builder()
                .url(DOMAIN + "/admin/orders.json?page=" + pageNumber + "&access_token=" + ACCESS_TOKEN)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

//                Headers responseHeaders = response.headers();
//                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
//                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//                }

                System.out.println(response.body().string());
            }
        });
    }
}
