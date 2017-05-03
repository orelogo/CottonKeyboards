package com.orelogo.cottonkeyboards;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Parser for parsing JSON orders data.
 */
class Parser {

    // JSON property names
    private static final String ORDERS = "orders";
    private static final String TOTAL_PRICE = "total_price";
    private static final String LINE_ITEMS = "line_items";
    private static final String TITLE = "title";
    private static final String QUANTITY = "quantity";

    private static final String AERO_COTTON_KEYBOARD = "Aerodynamic Cotton Keyboard";

    /**
     * Parses json formatted orders and returns total revenue and amount of aerodynamic cotton
     * keyboards sold.
     *
     * @param body json formatted string of orders
     * @return total revenue and number of keyboards sold
     * @throws JSONException if the json in not formatted as expected
     */
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

    /**
     * Parses a single order, returning the total revenue for the order and the number of
     * aerodynamic cotton keyboards sold.
     *
     * @param order a single order
     * @return total revenue and number of keyboards sold
     * @throws JSONException if the json in not formatted as expected
     */
    private static Result parseOrder(JSONObject order) throws JSONException {
        double revenue = order.getDouble(TOTAL_PRICE);
        int keyboardCount = 0;
        JSONArray items = order.getJSONArray(LINE_ITEMS);

        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            if (item.getString(TITLE).equals(AERO_COTTON_KEYBOARD))
                keyboardCount += item.getInt(QUANTITY);
        }

        return new Result(revenue, keyboardCount);
    }
}
