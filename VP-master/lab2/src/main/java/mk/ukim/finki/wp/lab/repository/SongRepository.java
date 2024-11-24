package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataInit;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SongRepository {
    public List<Song> findAll() {
        return DataInit.songs;
    }
    public Optional<Song> findByTrackId(String id) {
        return DataInit.songs.stream().filter(i -> i.getTrackId().equals(id)).findFirst();
    }
    public Artist addArtistToSong(Artist artist, Song song){
        if (artist != null && song != null) {
            song.addPerformer(artist);
            artist.addSong(song);
            return artist;
        }
        return null;
    }
    public Song addSong(Song newSong) {
        DataInit.songs.add(newSong);

        return newSong;
    }

    public Optional<Song> findSongById(Long id){
        return DataInit.songs.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public void deleteSong(Long id) {
        DataInit.songs.removeIf(song -> song.getId().equals(id));
    }

}
