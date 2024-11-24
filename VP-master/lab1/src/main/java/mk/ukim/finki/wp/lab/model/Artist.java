package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Artist {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
    private List<Song> songs;
    Random rd = new Random();

    public Artist(String firstName, String lastName, String bio) {
        this.id = Math.abs(rd.nextLong());
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.songs = new ArrayList<>();
    }
    public void addSong(Song song) {
        System.out.println(song);
        if (song != null) {
            songs.add(song);
        }
    }
}
