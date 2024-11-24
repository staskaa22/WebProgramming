package mk.ukim.finki.wp.lab.model;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Album extends BaseEntity {
    private String title;
    private String genre;
    private int releaseYear;
    private List<Song> songs;

    public Album(String title, String genre, int releaseYear){
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        if (song != null) {
            songs.add(song);
            song.addSongToAlbum(this);
        }
    }

    @Override
    public String toString() {
        return "Album{" +
                "id='" + getId() + '\'' +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
