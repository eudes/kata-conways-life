package es.eudes.kata.conwayslife;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class GridController {

    @CrossOrigin
    @GetMapping("/init")
    public String init(int x, int y) {
        log.info("Received {}, {}", x, y);
        return "{\"grid\": [[1],[1]]}";
    }

    @CrossOrigin
    @PostMapping("/next")
    public String init(@RequestBody String grid) {
        log.info("Received {}", grid);
        return "{\"grid\": [[1],[1]]}";
    }
}
