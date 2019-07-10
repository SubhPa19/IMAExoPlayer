//package com.example.exoplayerdemo;
//
//import android.net.Uri;
//import android.os.Bundle;
//import android.text.Html;
//import android.view.View;
//import android.widget.TextView;
//
//import java.util.List;
//
//import javax.sql.DataSource;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import static android.view.View.GONE;
//
////import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
////import com.google.ads.interactivemedia.v3.api.AdEvent;
////import com.google.ads.interactivemedia.v3.api.AdsLoader;
////import com.google.ads.interactivemedia.v3.api.AdsManagerLoadedEvent;
////import com.google.android.exoplayer2.C;
////import com.google.android.exoplayer2.ExoPlaybackException;
////import com.google.android.exoplayer2.ExoPlayer;
////import com.google.android.exoplayer2.ExoPlayerFactory;
////import com.google.android.exoplayer2.PlaybackParameters;
////import com.google.android.exoplayer2.Player;
////import com.google.android.exoplayer2.SimpleExoPlayer;
////import com.google.android.exoplayer2.Timeline;
////import com.google.android.exoplayer2.ext.ima.ImaAdsLoader;
////import com.google.android.exoplayer2.source.MediaSource;
////import com.google.android.exoplayer2.source.ProgressiveMediaSource;
////import com.google.android.exoplayer2.source.TrackGroupArray;
////import com.google.android.exoplayer2.source.ads.AdsMediaSource;
////import com.google.android.exoplayer2.source.dash.DashMediaSource;
////import com.google.android.exoplayer2.source.hls.HlsMediaSource;
////import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
////import com.google.android.exoplayer2.text.Cue;
////import com.google.android.exoplayer2.text.TextOutput;
////import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
////import com.google.android.exoplayer2.ui.PlayerControlView;
////import com.google.android.exoplayer2.ui.PlayerView;
////import com.google.android.exoplayer2.ui.SubtitleView;
////import com.google.android.exoplayer2.upstream.DataSource;
////import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
////import com.google.android.exoplayer2.util.Util;
//
//public class MainActivity extends AppCompatActivity implements AdsMediaSource.MediaSourceFactory,  AdEvent.AdEventListener, AdErrorEvent.AdErrorListener,
//        AdsLoader.AdsLoadedListener {
//    private SimpleExoPlayer player;
//    private PlayerView playerView;
//    private PlayerControlView playerControlView;
//    private ImaAdsLoader adsLoader;
//    private String TAG = "MainActivity";
//    private DataSource.Factory dataSourceFactory;
//    private String adURL = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dlinear&correlator=]";
//    private String contentURLWithCC = "https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8";
//    private String contentURL2 = "https://d1akq03u1jevln.cloudfront.net/wp-gmg/2019/07/05/5d1ec2efcff47e0009420af0/t_9a7a4b13461648a28658e68158d62b3e_name_video/hlsv4_master.m3u8";
//    private long contentPosition;
//    private TextView textViewCC;
//    private boolean isCCEnabled;
//    private SubtitleView subtitleView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        playerView = findViewById(R.id.playerView);
//        playerControlView = findViewById(R.id.playerControlView);
//        textViewCC = findViewById(R.id.exo_cc);
//        textViewCC.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toggleCC();
//            }
//        });
//        subtitleView = findViewById(com.google.android.exoplayer2.R.id.exo_subtitles);
//        subtitleView.setVisibility(GONE);
//
//        adsLoader = new ImaAdsLoader(this, Uri.parse(adURL));
//        dataSourceFactory = new DefaultDataSourceFactory(this, getString(R.string.app_name));
//
//    }
//
//    private void toggleCC() {
//        isCCEnabled = !isCCEnabled;
//        if (isCCEnabled) {
//            subtitleView.setVisibility(View.VISIBLE);
//            textViewCC.setText(Html.fromHtml("<u>CC</u>"));
//        }else{
//            subtitleView.setVisibility(GONE);
//            textViewCC.setText(Html.fromHtml("CC"));
//        }
//
//    }
//
//    private void initPlayer() {
//        player = ExoPlayerFactory.newSimpleInstance(this);
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
//        MediaSource mediaSourceContent = buildMediaSource(Uri.parse(contentURL2));
//        MediaSource mediaSourceWithAds =
//                new AdsMediaSource(
//                        mediaSourceContent, this, adsLoader, playerView);
//
//        player.seekTo(contentPosition);
//        player.prepare(mediaSourceWithAds);
//        player.setPlayWhenReady(true);
//        player.addListener(new Player.EventListener() {
//            @Override
//            public void onTimelineChanged(Timeline timeline, @Nullable Object manifest, int reason) {
//            }
//
//            @Override
//            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
//
//            }
//
//            @Override
//            public void onLoadingChanged(boolean isLoading) {
//
//            }
//
//            @Override
//            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
//                if (playbackState == ExoPlayer.STATE_READY) {
//                    long duration = player.getDuration();
//                }
//
//            }
//
//            @Override
//            public void onRepeatModeChanged(int repeatMode) {
//
//            }
//
//            @Override
//            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {
//
//            }
//
//            @Override
//            public void onPlayerError(ExoPlaybackException error) {
//
//            }
//
//            @Override
//            public void onPositionDiscontinuity(int reason) {
//
//            }
//
//            @Override
//            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
//
//            }
//
//            @Override
//            public void onSeekProcessed() {
//
//            }
//        });
//
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
//        // IMA does not support Smooth Streaming ads.
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
//    @Override
//    public void onResume() {
//        super.onResume();
//        initPlayer();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        reset();
//    }
//
//    @Override
//    public void onDestroy() {
//        player.release();
//        super.onDestroy();
//    }
//
//    public void reset() {
//        if (player != null) {
//            contentPosition = player.getContentPosition();
//            player.release();
//            player = null;
//            adsLoader.setPlayer(null);
//        }
//    }
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
//}
