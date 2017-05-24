package com.rahulsabnis.soundasleep;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.spotify.sdk.android.player.Error;
import com.spotify.sdk.android.player.Metadata;
import com.spotify.sdk.android.player.Player;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by rahulsabnis on 5/24/17.
 */

public class SongActivity extends AppCompatActivity {

    private Player mPlayer;

    private AppCompatImageView imageView;
    private FancyButton prev;
    private FancyButton playPause;
    private FancyButton next;

    private AppCompatTextView timeLeft;
    private AppCompatTextView artistName;
    private AppCompatTextView songName;

    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playing_song);

        Bundle extras = getIntent().getExtras();
        final int time = extras.getInt("time");

        mPlayer = MainActivity.getPlayer();

        imageView = (AppCompatImageView) findViewById(R.id.image);

        prev = (FancyButton) findViewById(R.id.previous);
        playPause = (FancyButton) findViewById(R.id.playPause);
        next = (FancyButton) findViewById(R.id.next);

        timeLeft = (AppCompatTextView) findViewById(R.id.timeLeft);
        songName = (AppCompatTextView) findViewById(R.id.songName);
        artistName = (AppCompatTextView) findViewById(R.id.artistName);

        mPlayer.playUri(new Player.OperationCallback() {
            @Override
            public void onSuccess() {

                timer = new CountDownTimer(time * 1000 * 60, 1000) {
                    @Override
                    public void onTick(long l) {
                        timeLeft.setText(String.format("%d min, %d sec",
                                TimeUnit.MILLISECONDS.toMinutes(l),
                                TimeUnit.MILLISECONDS.toSeconds(l) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l))));
                    }

                    @Override
                    public void onFinish() {
                        mPlayer.pause(null);
                        finish();
                    }
                }.start();
                mPlayer.setShuffle(null, true);

                Metadata.Track current = mPlayer.getMetadata().currentTrack;

                Picasso.with(SongActivity.this).load(current.albumCoverWebUrl).into(imageView);
                artistName.setText(current.artistName);
                songName.setText(current.name);
            }

            @Override
            public void onError(Error error) {
                AlertDialog dialog = new AlertDialog.Builder(SongActivity.this).setTitle("Error").
                        setMessage("An error has occurred on playback. Please check your " +
                                "internet connectivity!").create();
                dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                dialog.show();
            }
        }, "spotify:user:allgaminglegend:playlist:7M2BTQqhtBsQ7Ru2ljl7W8", 0, 0);

        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPlayer.getPlaybackState().isPlaying) {
                    mPlayer.pause(null);
                    playPause.setIconResource(R.drawable.ic_play_arrow_white_24dp);
                } else {
                    mPlayer.resume(null);
                    playPause.setIconResource(R.drawable.ic_pause_white_24dp);
                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.skipToPrevious(new Player.OperationCallback() {
                    @Override
                    public void onSuccess() {
                        Metadata.Track current = mPlayer.getMetadata().currentTrack;

                        Picasso.with(SongActivity.this).load(current.albumCoverWebUrl).into(imageView);
                        artistName.setText(current.artistName);
                        songName.setText(current.name);
                    }

                    @Override
                    public void onError(Error error) {

                    }
                });
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.skipToNext(new Player.OperationCallback() {
                    @Override
                    public void onSuccess() {
                        Metadata.Track current = mPlayer.getMetadata().currentTrack;

                        Picasso.with(SongActivity.this).load(current.albumCoverWebUrl).into(imageView);
                        artistName.setText(current.artistName);
                        songName.setText(current.name);
                    }

                    @Override
                    public void onError(Error error) {

                    }
                });
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
