package br.com.me.mediacontrollercompatreprocase;

import android.media.session.MediaSession;
import android.support.v4.media.session.MediaSessionCompat;

public class MediaSessionSingleton {

    private static MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(ReproCaseApp.getInstance(), "test");
    private static MediaSession mediaSession= new MediaSession(ReproCaseApp.getInstance(), "test");

    public static MediaSessionCompat getMediaSessionCompat() {
        return mediaSessionCompat;
    }

    public static MediaSession getMediaSession() {
        return mediaSession;
    }
}
