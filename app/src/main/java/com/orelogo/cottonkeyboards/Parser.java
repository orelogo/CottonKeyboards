package com.orelogo.cottonkeyboards;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by patso on 5/2/2017.
 */

class Parser {

    // JSON property names
    private static final String ORDERS = "orders";
    private static final String TOTAL_PRICE = "total_price";
    private static final String LINE_ITEMS = "line_items";
    private static final String TITLE = "title";

    private static final String AERO_COTTON_KEYBOARD = "Aerodynamic Cotton Keyboard";

    static Result parseOrders(String body) throws JSONException {
        double revenue = 0;
        int keyboardCount = 0;

        JSONArray orders = new JSONObject(body).getJSONArray(ORDERS);
        for (int i = 0; i < orders.length(); i++) {
            Result result = parseOrder(orders.getJSONObject(i));
            revenue += result.getTotalRevenue();
            keyboardCount += result.getACKeyboardCount();
        }

        return new Result(revenue, keyboardCount);
    }

    private static Result parseOrder(JSONObject order) throws JSONException {
        double revenue = order.getDouble(TOTAL_PRICE);
        int keyboardCount = 0;
        JSONArray items = order.getJSONArray(LINE_ITEMS);

        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            if (item.getString(TITLE).equals(AERO_COTTON_KEYBOARD))
                keyboardCount++;
        }

        return new Result(revenue, keyboardCount);
    }
}
