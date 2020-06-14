package app.service;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/ses")
public class SessionController {

    private final String JSESSIONID = "JSESSIONID";
    private final String NO_COOKIE_JSESSIONID = String.format("No cookie %s found", JSESSIONID);

    private final CalculatorHistoryService calculatorHistory;
    public SessionController(CalculatorHistoryService calculatorHistory) {
        this.calculatorHistory = calculatorHistory;
    }

    private Stream<Cookie> cookieStream(Cookie[] cookies) {
        return cookies == null ? Stream.empty() : Arrays.stream(cookies);
    }

    private Optional<String> cookie(Cookie[] cookies) {
        return cookieStream(cookies)
                .filter(c -> c.getName().equals(JSESSIONID))
                .map(Cookie::getValue)
                .findFirst();
    }

    /**
     * http://localhost:8088/ses/a
     * Session from Cookie "JSESSIONID"
     * approach 1
     * it will not create the session it will only access it
     * because Session ins't mentioned, only Cookie referred
     *
     * can produce NPX => Error 500
     * so we DO need an extra code to handle that
     */

    @GetMapping("a")
    public String handle_a(HttpServletRequest req) {
        return cookie(req.getCookies())
                .map(n -> String.format("Cookie %s via req.getCookies: %s", JSESSIONID, n))
                .orElse(NO_COOKIE_JSESSIONID);
    }

    /**
     * http://localhost:8088/ses/b
     * Session from Cookie "JSESSIONID"
     * approach 2
     * it will not create the session it will only access it
     * because Session ins't mentioned, only Cookie referred
     *
     * can produce Error 400 because of cookie absence
     * so we DO need an extra code to handle that
     */

    @GetMapping("b")
    public String handle_b(@CookieValue(JSESSIONID) Optional<String> sess_id) {
        return sess_id
                .map(n -> String.format("Cookie %s via @CookieValue: %s", JSESSIONID, n))
                .orElse(NO_COOKIE_JSESSIONID);
    }

    /**
     * http://localhost:8088/ses/c
     * Session from HttpServletRequest.getSession()
     * approach 1
     *
     * it will be created it if it doesn't exist or invalidated
     * so we DO NOT need an extra code to handle the absence fact
     */

    @GetMapping("c")
    public String handle_c(HttpServletRequest req) {
        final HttpSession session = req.getSession();
        final String sess_id = session.getId();
        return String.format("Session ID via HttpServletRequest.getSession() (w1): %s", sess_id);
    }

    /**
     * http://localhost:8088/ses/d
     * Session from HttpSession
     * approach 2
     *
     * it will be created it if it doesn't exist or invalidated
     * so we DO NOT need an extra code to handle the absence fact
     */



    @GetMapping("d")
    public String handle_d(HttpSession session) {
        final String id = session.getId();
        calculatorHistory.put(id, new Operation() {});
        return String.format("Session ID via HttpSession (w2): %s", id);
    }

    /**
     * http://localhost:8088/ses/x
     * Invalidating session
     *
     * Session becomes invalidated, but Cookie still contains old value !!!
     * new Session will be created once accessed
     */

    @GetMapping("x")
    public String handle_x(HttpSession session) {
        final String id = session.getId();
        session.invalidate();
        return String.format("Session Invalidated: %s", id);
    }
}
