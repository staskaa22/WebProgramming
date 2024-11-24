package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInit {
    public static List<Artist> artists = null;
    public static List<Song> songs = null;
    public static List<Album> albums = null;

    @PostConstruct
    public void init(){
        artists = new ArrayList<>();
        songs = new ArrayList<>();
        albums = new ArrayList<>();

        // Initializing Artists
        artists.add(new Artist("Boris", "Brejcha", "A techno innovator known for his deep beats and dramatic flair, captivating audiences worldwide."));
        artists.add(new Artist("Argy", "Argy", "A Greek artist blending deep grooves and atmospheric soundscapes, creating a unique take on house and techno."));
        artists.add(new Artist("Anyma", "", "An enigmatic artist who fuses melodic house with ethereal vocals for hauntingly beautiful tracks."));
        artists.add(new Artist("Charlotte de", "Witte", "techno force of nature from Belgium. With her distinct sound, she is at the frontline of the global electronic music scene."));
        artists.add(new Artist("Amelie", "Lens", "A powerhouse DJ and producer, Amelie energizes the dance floor with her intense and melodic techno beats."));

        // Initializing Songs
        songs.add(new Song("The Joker", "Techno", 2024));
        songs.add(new Song("Lost in the Groove", "Deep House", 2023));
        songs.add(new Song("Pictures of You", "Melodic Techno", 2022));
        songs.add(new Song("Follow", "Electronic", 2020));
        songs.add(new Song("Doppler", "Techno", 2021));

        // Initializing Albums
        albums.add(new Album("Techno Dreams", "Techno", 2024));
        albums.add(new Album("Groove Chronicles", "Deep House", 2023));
        albums.add(new Album("Mystic Waves", "Melodic Techno", 2022));
        albums.add(new Album("Electronic Pulse", "Electronic", 2021));
        albums.add(new Album("Deep Connections", "Techno", 2023));

        albums.get(0).addSong(songs.get(0));
        albums.get(1).addSong(songs.get(1));
        albums.get(2).addSong(songs.get(2));
        albums.get(3).addSong(songs.get(3));
        albums.get(4).addSong(songs.get(4));
    }
}
