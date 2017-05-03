package com.orelogo.cottonkeyboards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Activity for displaying Shopify merchant summary with total revenue and number of aerodynamic
 * cotton keyboards sold for all orders.
 */
public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Button refresh = (Button) findViewById(R.id.refersh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
        HttpController.getOrders(this);
    }

    /**
     * Display total revenue and number of aerodynamic cotton keyboards sold.
     * @param result total revenue and number of keyboards sold for all orders
     */
    void displaySummary(Result result) {
        TextView statusView = (TextView) findViewById(R.id.status);
        statusView.setVisibility(View.INVISIBLE);

        TextView revenueView = (TextView) findViewById(R.id.totalRevenue);
        String totalRevenue = getString(R.string.money, result.getTotalRevenue());
        revenueView.setText(totalRevenue);

        TextView keyboardsView = (TextView) findViewById(R.id.keyboardsSold);
        String acKeyboardsSold = Integer.toString(result.getACKeyboardCount());
        keyboardsView.setText(acKeyboardsSold);

        View results = findViewById(R.id.resultsLayout);
        results.setVisibility(View.VISIBLE);
    }

    /**
     * Display error message that there was an issue connecting to the server.
     */
    void displayError() {
        TextView statusView = (TextView) findViewById(R.id.status);
        statusView.setText(R.string.unable);
    }

    /**
     * Attempt to connect to server and refresh summary information.
     */
    void refresh() {
        TextView statusView = (TextView) findViewById(R.id.status);
        statusView.setText(getString(R.string.loading_summary));
        statusView.setVisibility(View.VISIBLE);

        View results = findViewById(R.id.resultsLayout);
        results.setVisibility(View.INVISIBLE);

        HttpController.getOrders(this);
    }

}
