package mk.ukim.finki.wp.lab.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;
    private final AlbumService albumService;

    @Autowired
    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }


    @PostMapping("/add")
    public String saveOrUpdateSong(@RequestParam(required = false) Long songId,
                                   @RequestParam String title,
                                   @RequestParam String genre,
                                   @RequestParam Long albumId,
                                   @RequestParam int releaseYear, RedirectAttributes redirectAttributes) {
        Song song;

        if (songId != null) {
            Optional<Song> existingSongOptional = songService.findSongById(songId);
            if (existingSongOptional.isPresent()) {
                song = existingSongOptional.get();
                song.setTitle(title);
                song.setGenre(genre);
                song.setReleaseYear(releaseYear);
            } else {
                System.out.println("NONO");
                redirectAttributes.addFlashAttribute("error", "No song found with id " + songId);
                return "redirect:/listSongs";
            }
        } else {
            song = new Song(title, genre, releaseYear);
            songService.addSong(song);
        }

        Optional<Album> albumOptional = albumService.findById(albumId);
        if (albumOptional.isPresent()) {
            Album album = albumOptional.get();
            album.addSong(song);
        }

        return "redirect:/listSongs";
    }

    @GetMapping("/edit-form/{songId}")
    public String editSong(@PathVariable Long songId, Model model, HttpServletRequest req) {
        Optional<Song> songOpt = songService.findSongById(songId);
        List<Album> albums = albumService.findAll();
        if(songOpt.isPresent()){
            songOpt.ifPresent(song -> model.addAttribute("song", song));
            model.addAttribute("albums", albums);
            return "add-song";
        }else{
            req.getSession().setAttribute("errorCtx", "No song with ID " + songId + " was found");
            return "redirect:/listSongs";
        }
    }
    @GetMapping("/add-form")
    public String addSong(Model model) {
        List<Album> albums = albumService.findAll();

        model.addAttribute("albums", albums);

        return "add-song";
    }


    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
        return "redirect:/listSongs";
    }
}
