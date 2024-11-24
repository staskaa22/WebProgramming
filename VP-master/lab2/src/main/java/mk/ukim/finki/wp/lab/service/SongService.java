package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> findAllSongs() {
        return songRepository.findAll();
    }

    public Optional<Song> findSongByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }
    public Optional<Song> findSongById(Long id) {
        return songRepository.findSongById(id);
    }


    public Artist addArtistToSong(Artist artist, String trackId) {
        Optional<Song> optionalSong = songRepository.findByTrackId(trackId);
        if (optionalSong.isPresent() && artist != null) {
            Song song = optionalSong.get();
            songRepository.addArtistToSong(artist, song);
            return artist;
        }
        return null;
    }

    public Song addSong(Song newSong) {
        songRepository.addSong(newSong);
        return newSong;
    }

    public boolean deleteSong(Long id) {
        Optional<Song> song = songRepository.findSongById(id);
        if (song.isPresent()) {
            songRepository.deleteSong(id);
            return true;
        }
        return false;
    }
}
