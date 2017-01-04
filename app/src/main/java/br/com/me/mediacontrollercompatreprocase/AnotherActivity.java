package br.com.me.mediacontrollercompatreprocase;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;

import timber.log.Timber;

public class AnotherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        Timber.d("onCreate");

//        MediaSession mediaSession = MediaSessionSingleton.getMediaSession();
//        MediaController mediaController = new MediaController(this, mediaSession.getSessionToken());
//
//        setMediaController(mediaController);

        MediaSessionCompat mediaSession = MediaSessionSingleton.getMediaSessionCompat();
        Timber.d("token = %s", mediaSession.getSessionToken());

        MediaControllerCompat mediaControllerCompat = null;
        try {
            mediaControllerCompat = new MediaControllerCompat(this, mediaSession.getSessionToken());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (mediaControllerCompat != null) {

            MediaControllerCompat.setMediaController(this, mediaControllerCompat);
        }

        Timber.i("mediaController = %s", MediaControllerCompat.getMediaController(this));
    }

    @Override
    protected void onStop() {
        Timber.d("onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Timber.d("onDestroy");
        super.onDestroy();
    }
}
