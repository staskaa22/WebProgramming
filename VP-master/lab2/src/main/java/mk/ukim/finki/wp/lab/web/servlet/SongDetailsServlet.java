package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet("/song/details/*")
@AllArgsConstructor
public class SongDetailsServlet extends HttpServlet {

    private final SpringTemplateEngine engine;
    private final SongService songService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String currentTrackId = pathInfo.substring(1);
        var optionalSong = songService.findSongByTrackId(currentTrackId);
        if (optionalSong.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Song not found.");
            return;
        }

        WebContext context = new WebContext(
                JakartaServletWebApplication.buildApplication(getServletContext())
                        .buildExchange(req, resp)
        );
        context.setVariable("song", optionalSong.get());

        engine.process("songDetails", context, resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentTrackId = req.getParameter("trackId");
        if (currentTrackId == null) {
            resp.sendRedirect("/listSongs");
            return;
        }
        resp.sendRedirect("/artist?trackId=" + currentTrackId);
    }
}
