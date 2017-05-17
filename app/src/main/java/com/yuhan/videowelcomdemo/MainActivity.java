package com.yuhan.videowelcomdemo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private WelcomVideoView welcomVideoView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        button = (Button) findViewById(R.id.button);
        welcomVideoView = (WelcomVideoView) findViewById(R.id.backgroundVideo);
        initData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(welcomVideoView.isPlaying()){
                    welcomVideoView.stopPlayback();
                    welcomVideoView=null;
                }
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
                finish();
            }

        });
    }

    private void initData() {
        welcomVideoView.setVideoURI(Uri.parse("android.resource://"+this.getPackageName()+"/"+R.raw.kr36));
        welcomVideoView.start();
        welcomVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                welcomVideoView.start();
            }
        });


    }


}
