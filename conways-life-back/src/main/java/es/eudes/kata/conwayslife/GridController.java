package es.eudes.kata.conwayslife;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class GridController {

    @CrossOrigin
    @GetMapping("/glider")
    public String glider() {
        log.info("glider");
        return "{\"grid\": [" +
                "[0,0,0,0,0,0,0,0]," +
                "[0,1,0,1,0,0,0,0]," +
                "[0,0,1,1,0,0,0,0]," +
                "[0,0,1,0,0,0,0,0]," +
                "[0,0,0,0,0,0,0,0]," +
                "[0,0,0,0,0,0,0,0]," +
                "[0,0,0,0,0,0,0,0]," +
                "[0,0,0,0,0,0,0,0]" +
                "]}";
    }

    @CrossOrigin
    @GetMapping("/random")
    public String random(int x, int y) throws JSONException {
        log.info("init {}, {}", x, y);
        JSONArray rows = new JSONArray();
        for (int _x = 0; _x < x; _x++) {
            JSONArray cols = new JSONArray();
            for (int _y = 0; _y < y; _y++) {
                cols.put(Math.round(Math.random()));
            }
            rows.put(cols);
        }
        JSONObject response = new JSONObject();
        response.put("grid", rows);
        return response.toString();
    }

    @CrossOrigin
    @PostMapping(value = "/next")
    public String next(@RequestBody String grid) {
        log.info("next {}", grid);
        return grid;
    }
}
