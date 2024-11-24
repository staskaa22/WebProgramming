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

@WebServlet("/artist/details/*")
@AllArgsConstructor
public class АrtistDetailsServlet extends HttpServlet {

    private SpringTemplateEngine springTemplateEngine;

    private ArtistService artistService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context=new WebContext(
                JakartaServletWebApplication.buildApplication(getServletContext())
                        .buildExchange(req, resp)
        );
        String pathInfo = req.getPathInfo();
        String curArtistId = pathInfo.substring(1);
        Long artistId = Long.parseLong(curArtistId);
        var optionalArtist = artistService.findArtistById(artistId);
        System.out.println(optionalArtist);
        if (optionalArtist.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Song not found.");
            return;
        }
        context.setVariable("artist",optionalArtist.get());
        System.out.println(optionalArtist.get());
        springTemplateEngine.process(
                "artistDetails",
                context,
                resp.getWriter()
        );
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long artistId = Long.parseLong(req.getParameter("artistId"));
        resp.sendRedirect("/artist/details/" + artistId);
    }
}