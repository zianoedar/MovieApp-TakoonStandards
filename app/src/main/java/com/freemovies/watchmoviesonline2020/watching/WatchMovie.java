package com.freemovies.watchmoviesonline2020.watching;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.freemovies.watchmoviesonline2020.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class WatchMovie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_watch_movie2);
        } catch (Exception e) {
            Toast.makeText(this, "Device Error!", Toast.LENGTH_SHORT).show();        }
            startActivity(new Intent(this, SelectionActivity.class));

        try {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }catch (Exception e){}

        AdView banner1 = findViewById(R.id.watchbanner);
        banner1.getLayoutParams().height = AdSize.MEDIUM_RECTANGLE.getHeightInPixels(this);
        banner1.loadAd(new AdRequest.Builder().build());

        Intent intent = getIntent();
        final String VideoID = intent.getStringExtra("id");
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
//        youTubePlayerView.enterFullScreen();

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = VideoID;
                try {
                    youTubePlayer.loadVideo(videoId, 0);
                }catch (Exception e){
                    Toast.makeText(WatchMovie.this, "Video loading Error!", Toast.LENGTH_SHORT).show();
                    /*Button watchOnYoutube=findViewById(R.id.watch_on_youtube_button);
                    watchOnYoutube.setVisibility(View.VISIBLE);
                    watchOnYoutube.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent playVideoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(VideoID));
                            startActivity(playVideoIntent);
                        }
                    });*/
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit")
                .setMessage("Are you sure you want to close this Movie?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}