package org.example;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MusicSearch {
    static Map<String, Artist> data = new HashMap<>();

    public static void main(String[] args) {
        addData("Phil Collins",
                new Date(1950, 12, 1),
                "UK",
                "Face value",
                "45:10",
                "1980",
                "In the air tonight",
                "Phil Collins",
                "05:00");


        System.out.println(getData("Phil Collins", "Face value", "In the air tonight"));

    }

    public static String getData(String artistName, String albumName, String songName) {
        Artist artist = data.get(artistName);
        Album album = artist.getAlbum(albumName);
        Song song = album.getSong(songName);
        return new MusicData(
                artist.getArtistName(),
                artist.getBorn(),
                artist.getCountry(),
                album.getAlbumTitle(),
                album.getLength(),
                album.getReleaseYear(),
                song.getSongTitle(),
                song.getMusicAndLyricsAuthor(),
                song.getTrackLength())
                .toString();
    }

    public static void addData(
            String artistName,
            Date born,
            String country,
            String albumName,
            String length,
            String yearOfRelase,
            String songTitle,
            String musicAndLyricsAuthor,
            String trackLength
    ) {
        Artist artist = new Artist(artistName, born, country);
        Album album = new Album(albumName, length, yearOfRelase);
        Song song = new Song(songTitle, musicAndLyricsAuthor, trackLength);

        album.addSong(songTitle, song);
        artist.addAlbum(albumName, album);
        data.put(artistName, artist);
    }

    private static class MusicData {
        String artistName;
        Date born;
        String country;
        String albumTitle;
        String length;
        String yearOfRelease;
        String songTitle;
        String musicAndLyricsAuthor;
        String trackLength;

        public MusicData(String artistName, Date born, String country, String albumTitle, String length, String yearOfRelease, String songTitle, String musicAndLyricsAuthor, String trackLength) {
            this.artistName = artistName;
            this.born = born;
            this.country = country;
            this.albumTitle = albumTitle;
            this.length = length;
            this.yearOfRelease = yearOfRelease;
            this.songTitle = songTitle;
            this.musicAndLyricsAuthor = musicAndLyricsAuthor;
            this.trackLength = trackLength;
        }

        @Override
        public String toString() {
            return "MusicData{" +
                    "artistName='" + artistName + '\'' +
                    ", born=" + born +
                    ", country='" + country + '\'' +
                    ", albumTitle='" + albumTitle + '\'' +
                    ", length='" + length + '\'' +
                    ", yearOfRelease='" + yearOfRelease + '\'' +
                    ", songTitle='" + songTitle + '\'' +
                    ", musicAndLyricsAuthor='" + musicAndLyricsAuthor + '\'' +
                    ", trackLength='" + trackLength + '\'' +
                    '}';
        }
    }

    private static class Artist {
        String artistName;
        Date born;
        String country;

        public Artist(String artistName, Date born, String country) {
            this.artistName = artistName;
            this.born = born;
            this.country = country;
        }

        HashMap<String, Album> albums = new HashMap<>();

        Album getAlbum(String name) {
            return albums.get(name);
        }

        public void addAlbum(String name, Album album) {
            albums.put(name, album);
        }

        public String getArtistName() {
            return artistName;
        }

        public Date getBorn() {
            return born;
        }

        public String getCountry() {
            return country;
        }
    };

    private static class Album {
        String albumTitle;
        String length;
        String releaseYear;

        public Album(String albumTitle, String length, String releaseYear) {
            this.albumTitle = albumTitle;
            this.length = length;
            this.releaseYear = releaseYear;
        }
        HashMap<String, Song> songs = new HashMap<>();

        Song getSong(String title) {
            return songs.get(title);
        };

        public void addSong(String title, Song song) {
            songs.put(title, song);
        }

        public String getAlbumTitle() {
            return albumTitle;
        }

        public String getLength() {
            return length;
        }

        public String getReleaseYear() {
            return releaseYear;
        }
    };

    public static class Song {
        String songTitle;
        String musicAndLyricsAuthor;
        String trackLength;

        public Song(String songTitle, String musicAndLyricsAuthor, String trackLength) {
            this.songTitle = songTitle;
            this.musicAndLyricsAuthor = musicAndLyricsAuthor;
            this.trackLength = trackLength;
        }

        public String getSongTitle() {
            return songTitle;
        }

        public String getMusicAndLyricsAuthor() {
            return musicAndLyricsAuthor;
        }

        public String getTrackLength() {
            return trackLength;
        }
    };
}
