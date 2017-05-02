package com.orelogo.cottonkeyboards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    private static final String TAG = "SummaryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button refresh = (Button) findViewById(R.id.refersh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
        HttpController.getOrders(this);
    }

    void displaySummary(Result result) {
        TextView statusView = (TextView) findViewById(R.id.status);
        statusView.setVisibility(View.INVISIBLE);

        TextView revenueView = (TextView) findViewById(R.id.totalRevenue);
        String totalRevenue = getString(R.string.total_revenue, result.getTotalRevenue());
        revenueView.setText(totalRevenue);

        TextView keyboardsView = (TextView) findViewById(R.id.aeroCottonkeyboardCount);
        String acKeyboardsSold = getString(R.string.ac_keyboards_sold, result.getACKeyboardCount());
        keyboardsView.setText(acKeyboardsSold);
    }

    void displayError() {
        TextView statusView = (TextView) findViewById(R.id.status);
        statusView.setText(R.string.unable);
    }

    void refresh() {
        TextView statusView = (TextView) findViewById(R.id.status);
        statusView.setText(getString(R.string.loading_summary));
        statusView.setVisibility(View.VISIBLE);

        TextView revenueView = (TextView) findViewById(R.id.totalRevenue);
        revenueView.setText(null);

        TextView keyboardsView = (TextView) findViewById(R.id.aeroCottonkeyboardCount);
        keyboardsView.setText(null);

        HttpController.getOrders(this);
    }

}
