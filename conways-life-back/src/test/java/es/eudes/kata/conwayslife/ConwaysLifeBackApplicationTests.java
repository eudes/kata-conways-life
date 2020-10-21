package es.eudes.kata.conwayslife;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class ConwaysLifeBackApplicationTests {


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

    static Stream<Arguments> samples() {
        return Stream.of(
                Arguments.of("1x1 with live cell", "survives", 1, 1, "[[true]]", "[[true]]"),
                Arguments.of("1x1 with dead cell", "stays dead", 1, 1, "[[false]]", "[[false]]"),
                // TODO explore more tests cases to check if we can define them in a way that
                //  doesn't force us to test all business rules at once
        );
    }

//
//    @DisplayName("There should be a grid of size X by Y")
//    @ParameterizedTest
//    @MethodSource("samples")
//    void test(int sizeX, int sizeY) {
//        Grid grid = new Grid(sizeX, sizeY, seed);
//        grid.next();
//        assertThat(grid.getLengthX(), is(sizeX));
//    }


    // Check if we can parameterize the test case definition like we can do in Cucumber
    @DisplayName("Case: {string} {string}")
    @ParameterizedTest
    @MethodSource("samples")
    void testNext(int sizeX, int sizeY, String seed, String result) {
        Grid grid = new Grid(sizeX, sizeY, seed);
        Grid next = grid.next();
        assertThat(next.toString(), is(result));
    }
//
//    @DisplayName("Case: {string} {string}")
//    @ParameterizedTest
//    @MethodSource("samples")
//    void test2(int sizeX, int sizeY, String seed, String result) {
//        Grid grid = new Grid(sizeX, sizeY, seed);
//        Cell next = grid.get(x, y);
//        assertThat(next.toString(), is(result));
//    }
//

}
