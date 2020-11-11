package es.eudes.kata.conwayslife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class GridServiceTest {
    private GridService gridService;


    // Game Of Life:
    // infinite, two-dimensional orthogonal grid of square cells,
    // each of which is in one of two possible states, live or dead

    // - The grid should have a size, since we can't represent an infinite grid
    // - The grid will be cyclic edge by edge
    // - API Contract: The grid is represented as a JSON Object, with a bidimensional array:
    // {\"grid\": [" +
    //                "[0,0,0,0,0,0,0,0]," +
    //                "[0,1,0,1,0,0,0,0]," +
    //                "[0,0,1,1,0,0,0,0]," +
    //                "[0,0,1,0,0,0,0,0]," +
    //                "[0,0,0,0,0,0,0,0]," +
    //                "[0,0,0,0,0,0,0,0]," +
    //                "[0,0,0,0,0,0,0,0]," +
    //                "[0,0,0,0,0,0,0,0]" +
    //                "]}"
    // TODO: formally define the API Contract

    // Rules
    //  - Any live cell with two or three live neighbours survives.
    //  - Any dead cell with three live neighbours becomes a live cell.
    //  - All other live cells die in the next generation. Similarly,
    //    all other dead cells stay dead.

    // Agreements:
    // We'll test outside-in (start with the API)
    // We'll design Object Oriented code, instead of a (Service) Layered approach

    @BeforeEach
    void setup() {
        this.gridService = new GridService();
    }

    @DisplayName("4x4 a dead cell with 3 neighbours revives")
    @Test
    void deadCellShouldRevive_when3NeighboursAlive() {
        // Given
        final String givenGrid = "[" +
                "[0,0,0,0]," +
                "[0,0,1,0]," +
                "[0,0,0,0]," +
                "[0,1,0,1]" +
                "]";

        final Grid grid = new Grid(givenGrid);

        // When
        final Grid next = gridService.next(grid);

        // Then
        final boolean cell = next.get(2, 2);
        assertThat(cell, is(true));
    }

    @DisplayName("4x4 a dead cell with 0 neighbours stays dead")
    @Test
    void deadCellShouldStayDead_when0NeighboursAlive() {
        // Given
        final String givenGrid = "[" +
                "[0,0,0,0]," +
                "[0,0,0,0]," +
                "[0,0,0,0]," +
                "[0,0,0,0]" +
                "]";

        final Grid grid = new Grid(givenGrid);

        // When
        final Grid next = gridService.next(grid);

        // Then
        final boolean cell = next.get(2, 2);
        assertThat(cell, is(false));
    }

//    @DisplayName("4x4 any cell without neighbours dies")
//    @Test
//    void asdf() {
//        String given = "[" +
//                "[0,0,0,0]," +
//                "[0,0,1,0]," +
//                "[0,0,0,0]," +
//                "[0,1,0,1]" +
//                "]";
////        String expected = "[" +
////                        "[0,0,0,0]," +
////                        "[0,0,0,0]," +
////                        "[0,0,1,0]," +
////                        "[0,0,0,0]" +
////                        "]"),
//        Grid grid = new Grid(givenGrid);
//        Grid next = grid.next();
//        boolean cell21 = grid.get(2, 2);
//        boolean cell21 = grid.get(3, 1);
//        assertThat(cell, is(true));
//    }

    // todo tests:
    // grid looping
}

