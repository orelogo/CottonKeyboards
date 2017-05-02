package com.orelogo.cottonkeyboards;

import android.app.Activity;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by patso on 5/1/2017.
 */

class HttpController {

    private static final String DOMAIN = "https://shopicruit.myshopify.com";
    private static final String ACCESS_TOKEN = "c32313df0d0ef512ca64d5b336a0d7c6";

    private static final OkHttpClient client = new OkHttpClient();

    static void getOrders(final SummaryActivity activity) {
        Request request = new Request.Builder()
                .url(DOMAIN + "/admin/orders.json?page=1&access_token=" + ACCESS_TOKEN)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                loadError(activity);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) loadError(activity);

                loadSummary(activity, response.body().string());
            }
        });
    }

    private static void loadSummary(final SummaryActivity activity, final String body) {

        try {
            final Result result = Parser.parseOrders(body);

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    activity.displaySummary(result);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void loadError(final SummaryActivity activity) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.displayError();
            }
        });
    }


}
