package com.ivanmz.androidlwatchface;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WatchFaceActivity  extends Activity {

    private final static IntentFilter intentFilter;
    ImageView background;
    TextView mDate;

    static {
        intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_face);

        background = (ImageView) findViewById(R.id.background);
        mDate = (TextView) findViewById(R.id.date);

        registerReceiver(mDateInfoReceiver, intentFilter);
    }

    private BroadcastReceiver mDateInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context arg0, Intent intent) {
            SimpleDateFormat dayformat = new SimpleDateFormat("EEEE");
            String day = dayformat.format(new Date());

            SimpleDateFormat numberformat = new SimpleDateFormat("d");
            String number = numberformat.format(new Date());

            SimpleDateFormat monthformat = new SimpleDateFormat("MMMM");
            String month = monthformat.format(new Date());

            mDate.setText(number + " - " + month);
        }
    };

    @Override
    protected void onPause() {super.onPause();
        background.setImageResource(R.drawable.background_off);
        mDate.setTextColor(Color.BLACK);
    }

    @Override
    protected void onResume() { super.onResume();
        background.setImageResource(R.drawable.example_watch_background);
        mDate.setTextColor(Color.WHITE);
    }

    @Override
    protected void onDestroy(){ super.onDestroy();
        unregisterReceiver(mDateInfoReceiver);
    }


}