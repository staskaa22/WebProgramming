package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/artist")
@AllArgsConstructor
public class АrtistServlet extends HttpServlet {

    private SpringTemplateEngine springTemplateEngine;

    private ArtistService artistService;
    private SongService songService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context=new WebContext(
                JakartaServletWebApplication.buildApplication(getServletContext())
                        .buildExchange(req, resp)
        );
        String currentTrackId = req.getParameter("trackId");
        context.setVariable("artists",artistService.findAllArtists());
        context.setVariable("trackId",currentTrackId);

        springTemplateEngine.process(
                "artistsList",
                context,
                resp.getWriter()
        );
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long artistId = Long.parseLong(req.getParameter("artistId"));
        String currentTrackId = req.getParameter("trackId");
        Optional<Artist> optionalArtist = artistService.findArtistById(artistId);
        if (optionalArtist.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Artist not found.");
            return;
        }

        Artist artist = optionalArtist.get();
        songService.addArtistToSong(artist, currentTrackId);
        resp.sendRedirect("/song/details/" + currentTrackId);
    }
}