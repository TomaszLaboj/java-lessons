package org.example;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MusicSearch {
    Map<String, Artist> data = new HashMap<>();

    public Song getData(String artistName, String albumName, String songName) {
        Artist artist = data.get(artistName);
        Album album = artist.getAlbum(albumName);

        return album.getSong(songName);
    }

    private static class MusicData {
        Artist artist;
        Album album;
        String song;
    }

    private static class Artist {
        String name;
        Date born;
        String country;

        public Artist(String name, Date born, String country) {
            this.name = name;
            this.born = born;
            this.country = country;
        }

        HashMap<String, Album> albums;

        Album getAlbum(String name) {
            return albums.get(name);
        }

        public void addAlbum(String name, int length, int releaseYear) {
            albums.put(name, new Album(name, length, releaseYear));
        }
    };

    private static class Album {
        String name;
        int length;
        int releaseYear;

        public Album(String name, int length, int releaseYear) {
            this.name = name;
            this.length = length;
        }
        HashMap<String, Song> songs;

        Song getSong(String title) {
            return songs.get(title);
        };

        public void addSong(String title, int trackLength) {
            songs.put(title, new Song(title, trackLength));
        }
    };

    public static class Song {
        String title;
        int trackLength;

        public Song(String title, int trackLength) {
            this.title = title;
            this.trackLength = trackLength;
        }
    };
}
