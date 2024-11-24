package mk.ukim.finki.wp.lab.model;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Song extends BaseEntity {
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    private List<Artist> performers;
    private Album album;

    public Song(String title, String genre, int releaseYear){
        this.trackId = UUID.randomUUID().toString();
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>();
    }

    public void addPerformer(Artist artist) {
        if (artist != null) {
            performers.add(artist);
        }
    }
    public void addSongToAlbum(Album album) {
        if (album != null) {
            this.album = album;
        }
    }

    @Override
    public String toString() {
        return "Song{" +
                "id='" + getId() + '\'' +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", album='" + getAlbum() + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
