package mk.ukim.finki.wp.lab.repository;
import mk.ukim.finki.wp.lab.bootstrap.DataInit;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArtistRepository {
    public List<Artist> findAll() {
        return DataInit.artists;
    }
    public Optional<Artist> findById(Long id) {
        return DataInit.artists.stream().filter(i -> i.getId().equals(id)).findFirst();
    }
}
