package br.com.me.mediacontrollercompatreprocase;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button anotherActivity = (Button) findViewById(R.id.button);
        anotherActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAnotherActivity();
            }
        });


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

    private void openAnotherActivity() {
        Intent intent = new Intent(this, AnotherActivity.class);
        startActivity(intent);
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
