package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataInit;
import mk.ukim.finki.wp.lab.model.Album;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlbumRepository {
    public List<Album> findAll() {
        return DataInit.albums;
    }
    public Optional<Album> findById(Long id) {
        return DataInit.albums.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

}
