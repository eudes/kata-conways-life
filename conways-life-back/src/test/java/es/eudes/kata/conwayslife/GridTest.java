package es.eudes.kata.conwayslife;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class GridTest {


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

//    static Stream<Arguments> cellWithNeighboursStaysAlive() {
//        return Stream.of(
//                Arguments.of("4x4 one cell with 2 neighbours stays alive",
//                        "[" +
//                                "[0,0,0,0]," +
//                                "[0,0,1,0]," +
//                                "[0,0,0,0]," +
//                                "[0,1,0,1]" +
//                                "]",
//                        "[" +
//                                "[0,0,0,0]," +
//                                "[0,0,0,0]," +
//                                "[0,0,1,0]," +
//                                "[0,0,0,0]" +
//                                "]"),
//                );
//    }
//
//    static Stream<Arguments> oneCellAlive() {
//        return Stream.of(
//                Arguments.of("1x1 with alive cell, cell should die", "[[true]]", "[[false]]"),
//                );
//    }
//
//    static Stream<Arguments> oneCellDead() {
//        return Stream.of(
//                Arguments.of("1x1 with dead cell", "stays dead", "[[false]]", "[[false]]")
//        );
//    }
//
//    // TODO explore more tests cases to check if we can define them in a way that
//    //  doesn't force us to test all business rules at once
//    static Stream<Arguments> samples() {
//        return Stream.of(
//                cellWithNeighboursStaysAlive(),
//                ).flatMap(argument -> argument);
//    }

    // Compromise:
    // - we need a grid representation in JSON

    // we couldn't test this part without executing the tested part to generate the examples
//    @Test
//    void givenAnJSONRepresentation_AGridIsCreate(){
//        // given
//        final String giveJson = "[" +
//                "[0,0,0,0]," +
//                "[0,1,0,1]," +
//                "[0,0,1,1]," +
//                "[0,0,1,0]" +
//                "]";
//        // when
//        final Grid actualGrid = new Grid(giveJson);
//
//        // then
//        assertThat(actualGrid, is(expectedGrid));
//    }

    // Check if we can parameterize the test case definition like we can do in Cucumber
    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("samples")
    void shouldCalculateNextGeneration(String caseName, Grid givenGrid, Grid expectedGrid) {
        Grid grid = new Grid(givenGrid);
        Grid next = grid.next();
        boolean cell = grid.get(2, 2);
        assertThat(cell, is(alive));
        assertThat(checkFirstGridContainsLiveCellsFromSecondGrid(next, expectedGrid), is(true));
    }

    private String checkFirstGridContainsLiveCellsFromSecondGrid(Grid next, Grid expectedGrid) {
        return null;
    }

    @DisplayName("4x4 a dead cell with 3 neighbours revives")
    @Test
    void asdf() {
        String given = "[" +
                "[0,0,0,0]," +
                "[0,0,1,0]," +
                "[0,0,0,0]," +
                "[0,1,0,1]" +
                "]";
//        String expected = "[" +
//                        "[0,0,0,0]," +
//                        "[0,0,0,0]," +
//                        "[0,0,1,0]," +
//                        "[0,0,0,0]" +
//                        "]"),
        Grid grid = new Grid(givenGrid);
        Grid next = grid.next();
        boolean cell = grid.get(2, 2);
        assertThat(cell, is(true));
    }

    @DisplayName("4x4 any cell without neighbours dies")
    @Test
    void asdf() {
        String given = "[" +
                "[0,0,0,0]," +
                "[0,0,1,0]," +
                "[0,0,0,0]," +
                "[0,1,0,1]" +
                "]";
//        String expected = "[" +
//                        "[0,0,0,0]," +
//                        "[0,0,0,0]," +
//                        "[0,0,1,0]," +
//                        "[0,0,0,0]" +
//                        "]"),
        Grid grid = new Grid(givenGrid);
        Grid next = grid.next();
        boolean cell21 = grid.get(2, 2);
        boolean cell21 = grid.get(3, 1);
        assertThat(cell, is(true));
    }

    // next week goals:
    // implement properly these two tests
    // make them pass
    // continue using this strategy
}

