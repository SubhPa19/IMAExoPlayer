//package com.example.exoplayerdemo;
//
//import android.content.Context;
//import android.net.Uri;
//import android.text.Html;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.widget.FrameLayout;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
//import com.google.ads.interactivemedia.v3.api.AdEvent;
//import com.google.ads.interactivemedia.v3.api.AdsLoader;
//import com.google.ads.interactivemedia.v3.api.AdsManagerLoadedEvent;
//import com.google.android.exoplayer2.C;
//import com.google.android.exoplayer2.ExoPlayerFactory;
//import com.google.android.exoplayer2.SimpleExoPlayer;
//import com.google.android.exoplayer2.ext.ima.ImaAdsLoader;
//import com.google.android.exoplayer2.source.MediaSource;
//import com.google.android.exoplayer2.source.ProgressiveMediaSource;
//import com.google.android.exoplayer2.source.ads.AdsMediaSource;
//import com.google.android.exoplayer2.source.dash.DashMediaSource;
//import com.google.android.exoplayer2.source.hls.HlsMediaSource;
//import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
//import com.google.android.exoplayer2.text.Cue;
//import com.google.android.exoplayer2.text.TextOutput;
//import com.google.android.exoplayer2.ui.PlayerControlView;
//import com.google.android.exoplayer2.ui.PlayerView;
//import com.google.android.exoplayer2.ui.SubtitleView;
//import com.google.android.exoplayer2.upstream.DataSource;
//import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
//import com.google.android.exoplayer2.util.Util;
//
//import java.util.List;
//
//import androidx.annotation.Nullable;
//import androidx.constraintlayout.widget.ConstraintLayout;
//
//public class IMAExoPlayer extends LinearLayout implements AdsMediaSource.MediaSourceFactory,  AdEvent.AdEventListener, AdErrorEvent.AdErrorListener,
//        AdsLoader.AdsLoadedListener{
//
//    private PlayerView playerView;
//    private PlayerControlView playerControlView;
//    private DataSource.Factory dataSourceFactory;
//    private long contentPosition;
//    private TextView textViewCC;
//    private boolean isCCEnabled;
//    private SubtitleView subtitleView;
//    private SimpleExoPlayer player;
//    private ImaAdsLoader adsLoader;
//
//    public IMAExoPlayer(Context context) {
//        super(context);
//    }
//
//    public IMAExoPlayer(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//
//        LayoutInflater inflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        inflater.inflate(R.layout.layout_ima_exo, this, true);
//
//        ConstraintLayout constraintLayout = (ConstraintLayout) getChildAt(0);
//        playerView = (PlayerView) constraintLayout.getChildAt(0);
//        playerControlView = (PlayerControlView) constraintLayout.getChildAt(1);
//
//        FrameLayout frameLayout = (FrameLayout) playerControlView.getChildAt(0);
//
//        LinearLayout linearLayout = (LinearLayout) frameLayout.getChildAt(2);
////        textViewCC = (TextView) linearLayout.getChildAt(3);
////        textViewCC.setOnClickListener(new OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                toggleCC();
////            }
////        });
//
//        dataSourceFactory = new DefaultDataSourceFactory(context, context.getString(R.string.app_name));
//    }
//
//    public IMAExoPlayer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    public IMAExoPlayer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
//
//
//    @Override
//    public void onAdError(AdErrorEvent adErrorEvent) {
//
//    }
//
//    @Override
//    public void onAdEvent(AdEvent adEvent) {
//
//    }
//
//    @Override
//    public void onAdsManagerLoaded(AdsManagerLoadedEvent adsManagerLoadedEvent) {
//
//    }
//
//    @Override
//    public MediaSource createMediaSource(Uri uri) {
//        return buildMediaSource(uri);
//    }
//
//    @Override
//    public int[] getSupportedTypes() {
//        return new int[]{C.TYPE_DASH, C.TYPE_HLS, C.TYPE_OTHER};
//    }
//
//    private MediaSource buildMediaSource(Uri uri) {
//        @C.ContentType int type = Util.inferContentType(uri);
//        switch (type) {
//            case C.TYPE_DASH:
//                return new DashMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
//            case C.TYPE_SS:
//                return new SsMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
//            case C.TYPE_HLS:
//                return new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
//            case C.TYPE_OTHER:
//                return new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
//            default:
//                throw new IllegalStateException("Unsupported type: " + type);
//        }
//    }
//
//
//    public void initPlayer(Context context, String contentURL, String adURL) {
//        adsLoader = new ImaAdsLoader(context, Uri.parse(adURL));
//        subtitleView = findViewById(com.google.android.exoplayer2.R.id.exo_subtitles);
//        subtitleView.setVisibility(GONE);
//        player = ExoPlayerFactory.newSimpleInstance(context);
//        playerView.setPlayer(player);
//        player.addTextOutput(new TextOutput() {
//            @Override
//            public void onCues(List<Cue> cues) {
//                if (subtitleView != null) {
//                    subtitleView.onCues(cues);
//                }
//            }
//        });
//
//        playerControlView.setPlayer(player);
//        adsLoader.setPlayer(player);
//        MediaSource mediaSourceContent = buildMediaSource(Uri.parse(contentURL));
//        MediaSource mediaSourceWithAds =
//                new AdsMediaSource(
//                        mediaSourceContent, this, adsLoader, playerView);
//
//        player.seekTo(contentPosition);
//        player.prepare(mediaSourceWithAds);
//        player.setPlayWhenReady(true);
//    }
//
//    public void toggleCC(TextView textViewCC){
//        isCCEnabled = !isCCEnabled;
//        if(isCCEnabled){
//            subtitleView.setVisibility(VISIBLE);
//            textViewCC.setText(Html.fromHtml("<u>CC</u>"));
//        }else{
//            subtitleView.setVisibility(GONE);
//            textViewCC.setText(Html.fromHtml("CC"));
//        }
//    }
//
//    public void onPause(){
//        if (player != null) {
//            contentPosition = player.getContentPosition();
//            player.release();
//            player = null;
//            adsLoader.setPlayer(null);
//        }
//    }
//
//    public void onResume(){
//        if(player != null){
//            player.seekTo(contentPosition);
//        }
//    }
//
//    public void onDestroy(){
//        if (player != null) {
//            contentPosition = player.getContentPosition();
//            player.release();
//            player = null;
//            adsLoader.setPlayer(null);
//        }
//    }
//}
