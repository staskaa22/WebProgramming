package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet("/listSongs")
@AllArgsConstructor
public class SongListServlet extends HttpServlet {

    private SongService songService;
    private ArtistService artistService;
    private SpringTemplateEngine springTemplateEngine;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(
                JakartaServletWebApplication.buildApplication(getServletContext())
                        .buildExchange(req, resp)
        );
        context.setVariable("songs",songService.findAllSongs());
        context.setVariable("artists",artistService.findAllArtists());
        String errorCtx = (String) req.getSession().getAttribute("errorCtx");

        if (errorCtx != null) {
            req.getSession().removeAttribute("errorCtx");
        }
        if (errorCtx != null) {
            context.setVariable("errorCtx", errorCtx);
        }
        springTemplateEngine.process(
                "listSongs",
                context,
                resp.getWriter()
        );
    }

}