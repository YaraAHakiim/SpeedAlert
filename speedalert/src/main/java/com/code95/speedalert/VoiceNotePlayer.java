package com.code95.speedalert;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by YaRa on 3/4/2018.
 */

public class VoiceNotePlayer {

    private static MediaPlayer mMediaPlayer;

    public enum Mode {
        ResourceId,
        Url
    }

    /**
     *
     * @param context used to create the media player.
     * @param url The Url of th file to be played.
     * @param resId The res id of the file to be played.
     * @param mode Media player mode (Url : Plays file online, ResourceId: Plays an audio file embedded in the project)
     */
    public static void playAudio(Context context, String url, int resId, Mode mode) {
        if(mode != null) {
            switch (mode) {
                case ResourceId:  //raw file
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, resId);
                    }
                    break;
                case Url: //url
                    if (mMediaPlayer == null) {
                        mMediaPlayer = new MediaPlayer();
                    }
                    try {
                        mMediaPlayer.setDataSource(url);
                        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mMediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    mMediaPlayer = new MediaPlayer();
            }
        } else {
            mMediaPlayer = MediaPlayer.create(context, R.raw.alert);
        }

        if (!mMediaPlayer.isPlaying()) {
            mMediaPlayer.start();
        }

    }

}
