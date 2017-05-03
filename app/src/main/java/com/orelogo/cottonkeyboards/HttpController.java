package com.orelogo.cottonkeyboards;

import android.util.Log;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Wrapper for OkHttp used to connect to server and get order information.
 */
class HttpController {
    private static final String TAG = "HttpController";

    private static final String DOMAIN = "https://shopicruit.myshopify.com";
    private static final String ACCESS_TOKEN = "c32313df0d0ef512ca64d5b336a0d7c6";

    private static final OkHttpClient client = new OkHttpClient();

    /**
     * Async get request for orders. If successful, will update the given activity with summary
     * information about the orders. If an error occurs, a message will be displayed notifying
     * the user.
     *
     * @param activity summary activity that will display the information
     */
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
                else loadSummary(activity, response.body().string());
            }
        });
    }

    /**
     * Attempt to parse orders and load the summary information into the given activity.
     *
     * @param activity summary activity for displaying information
     * @param body json of orders
     */
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
            Log.e(TAG, "Parsing error", e);
        }
    }

    /**
     * Load error message.
     *
     * @param activity summary activity for displaying message
     */
    private static void loadError(final SummaryActivity activity) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.displayError();
            }
        });
    }


}
