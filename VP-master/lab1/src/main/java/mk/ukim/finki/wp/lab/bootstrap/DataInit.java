package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInit {
    public static List<Artist> artists = null;
    public static List<Song> songs = null;

    @PostConstruct
    public void init(){
        artists = new ArrayList<>();
        songs = new ArrayList<>();
        artists.add(new Artist("Boris", "Brejcha", "A techno innovator known for his deep beats and dramatic flair, captivating audiences worldwide."));
        artists.add(new Artist("Argy", "Argy", "A Greek artist blending deep grooves and atmospheric soundscapes, creating a unique take on house and techno."));
        artists.add(new Artist("Anyma", "", "An enigmatic artist who fuses melodic house with ethereal vocals for hauntingly beautiful tracks."));
        artists.add(new Artist("Charlotte de", "Witte", "techno force of nature from Belgium. With her distinct sound, she is at the frontline of the global electronic music scene.\n"));
        artists.add(new Artist("Amelie", "Lens", "A powerhouse DJ and producer, Amelie energizes the dance floor with her intense and melodic techno beats."));
        songs.add(new Song("The Joker", "Techno", 2024));
        songs.add(new Song("Lost in the Groove", "Deep House", 2023));
        songs.add(new Song("Pictures of You", "Melodic Techno", 2022));
        songs.add(new Song("Follow", "Electronic", 2020));
        songs.add(new Song("Doppler", "Techno", 2021));
    }
}
