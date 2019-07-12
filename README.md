# IMAExoPlayer

IMA-Exo player integration for dynamic ad inseration (pre-roll/mid-roll/post-roll) over video playback.

Just Include view in xml and pass 'adURL' and 'contentURL' to play ads over the video on Exo player
# 1. Adding Gradle dependency 
   project/build.gradle > `maven { url 'https://jitpack.io' }`
   
   app/build.gradle > `implementation 'com.github.SubhPa19:IMAExoPlayer:0.1.1'`
   
   
# 2. Add view to 
    <com.example.imaexoplayer.IMAExoPlayer
        android:id="@+id/IMAExoPlayer"
        android:layout_width="match_parent"
        android:layout_height="230dp"
        android:background="@color/colorPrimary"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">
    </com.example.imaexoplayer.IMAExoPlayer>

# 3. Set AdUrl and contentURL

        IMAExoPlayer imaExoPlayer  = findViewById(R.id.IMAExoPlayer);
        imaExoPlayer.initPlayer(getApplicationContext(), contentURL2, adURL);
