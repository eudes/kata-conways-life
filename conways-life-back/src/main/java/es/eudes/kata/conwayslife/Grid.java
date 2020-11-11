package es.eudes.kata.conwayslife;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Grid {
    private Boolean[][] grid;

    //"[[0]]"
    public Grid(String seed) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.grid = mapper.readValue(seed, Boolean[][].class);
        } catch (JsonProcessingException e) {
            // Esto está feo
            throw new RuntimeException("Cannot read input grid");
        }
    }

    // 0, 0
    public boolean get(int x, int y) {
        return this.grid[x][y];
    }
}
