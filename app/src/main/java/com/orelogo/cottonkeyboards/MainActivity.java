package com.orelogo.cottonkeyboards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

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
        Controller.getOrders(this, 1);
    }

    public void refresh() {
        TextView statusView = (TextView) findViewById(R.id.status);
        statusView.setText(getString(R.string.loading_summary));
        statusView.setVisibility(View.VISIBLE);

        TextView revenueView = (TextView) findViewById(R.id.totalRevenue);
        revenueView.setText(null);

        TextView keyboardsView = (TextView) findViewById(R.id.aeroCottonkeyboardCount);
        keyboardsView.setText(null);

        Controller.getOrders(this, 1);
    }

}
