package org.example;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MusicSearch {
    Map<String, Artist> data = new HashMap<>();

    private Song getData(String artistName, String albumName, String songName) {
        Artist artist = data.get(artistName);
        Album album = artist.getAlbum(albumName);
        return album.getSong(songName);
    }

    private static class Artist {
        String name;
        Date dob;
        HashMap<String, Album> albums;

        Album getAlbum(String name) {
            return albums.get(name);
        }
    };

    private static class Album {
        String name;
        int length;
        HashMap<String, Song> songs;

        Song getSong(String title) {
            return songs.get(title);
        }
    };

    private static class Song {
        String title;
    };
}
