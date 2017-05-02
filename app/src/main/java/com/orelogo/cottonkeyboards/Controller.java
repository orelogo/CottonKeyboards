package com.orelogo.cottonkeyboards;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.view.View.GONE;

/**
 * Created by patso on 5/1/2017.
 */

public class Controller {

    private static final String DOMAIN = "https://shopicruit.myshopify.com";
    private static final String ACCESS_TOKEN = "c32313df0d0ef512ca64d5b336a0d7c6";

    private static final OkHttpClient client = new OkHttpClient();

    static void getOrders(final Activity activity, int pageNumber) {
        Request request = new Request.Builder()
                .url(DOMAIN + "/admin/orders.json?page=" + pageNumber + "&access_token=" + ACCESS_TOKEN)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                displayError(activity);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) displayError(activity);

                displayResult(activity, response.body().string());
            }
        });
    }

    private static void displayResult(final Activity activity, final String body) {

        try {
            final Result result = Parser.parseOrders(body);

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView statusView = (TextView) activity.findViewById(R.id.status);
                    statusView.setVisibility(View.INVISIBLE);

                    TextView revenueView = (TextView) activity.findViewById(R.id.totalRevenue);
                    String totalRevenue = activity.getString(R.string.total_revenue, result.getTotalRevenue());
                    revenueView.setText(totalRevenue);

                    TextView keyboardsView = (TextView) activity.findViewById(R.id.aeroCottonkeyboardCount);
                    String acKeyboardsSold = activity.getString(R.string.ac_keyboards_sold, result.getACKeyboardCount());
                    keyboardsView.setText(acKeyboardsSold);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void displayError(final Activity activity) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView statusView = (TextView) activity.findViewById(R.id.status);
                statusView.setText(R.string.unable);
            }
        });
    }


}
