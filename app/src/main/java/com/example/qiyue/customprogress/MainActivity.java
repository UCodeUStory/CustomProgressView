package com.example.qiyue.customprogress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //final ProgressWheel progressWheel = (ProgressWheel) findViewById(R.id.progress_wheel);
        View view = LayoutInflater.from(this).inflate(R.layout.content_layout,null);
        ProgressWheel progressWheel = (ProgressWheel) view.findViewById(R.id.progress_wheel);
        progressWheel.setBarWidth(15);
        final CustomDailog customDailog = new CustomDailog(this,view);
        findViewById(R.id.btn_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDailog.show();
            }
        });
        //progressWheel.setBarColor(defaultBarColor);
       // progressWheel.setRimColor(R.color.);
      //  progressWheel.setProgress(0.0f);
       /* progressWheel.setCallback(new ProgressWheel.ProgressCallback() {
            @Override
            public void onProgressUpdate(float progress) {
                if(progress == 0.0f) {
                    Log.i("qiyue","progress1111="+progress);
                    progressWheel.setProgress(1.0f);
                } else if(progress == 1.0f) {
                    Log.i("qiyue","progress222="+progress);
                    progressWheel.setProgress(0.0f);
                }


            }
        });*/

    }
}
