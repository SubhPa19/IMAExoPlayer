package com.example.exoplayerdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.imaexoplayer.IMAExoPlayer;

import androidx.appcompat.app.AppCompatActivity;

public class CustomIMAPlayerActivity extends AppCompatActivity {


    private String adURL = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dlinear&correlator=]";
    private String contentURLWithCC = "https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8";
    private String contentURL2 = "http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8";
    private IMAExoPlayer imaExoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_imaplayer);

        imaExoPlayer  = findViewById(R.id.IMAExoPlayer);
        imaExoPlayer.initPlayer(getApplicationContext(), contentURL2, adURL);

        final TextView textViewCC = findViewById(R.id.exo_cc);
        textViewCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imaExoPlayer.toggleCC(textViewCC);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        imaExoPlayer.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        imaExoPlayer.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imaExoPlayer.onDestroy();
    }
}
