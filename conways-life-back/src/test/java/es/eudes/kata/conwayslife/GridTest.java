package es.eudes.kata.conwayslife;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GridTest {

    @Test
    @DisplayName("Given a 1x1 seed with alive cell, get(0,0) should return true")
    void givenA1x1SeedWithAliveCell_get00ShouldReturnTrue() {
        // Given
        final String seed = "[[1]]";
        final Grid grid = new Grid(seed);

        // When
        final boolean cell = grid.get(0, 0);

        // Then
        assertThat(cell, is(true));
    }

    @Test
    @DisplayName("Given a 1x1 seed with dead cell, get(0,0) should return false")
    void givenA1x1SeedWithDeadCell_get00ShouldReturnFalse() {
        // Given
        final String seed = "[[0]]";
        final Grid grid = new Grid(seed);

        // When
        final boolean cell = grid.get(0, 0);

        // Then
        assertThat(cell, is(false));
    }

    @Test
    @DisplayName("Given a 2x2 seed with multiple states cell, get should return same state as input")
    void givenA2x2SeedWithMultipleStates_getShouldReturnSameStateAsInput() {
        // Given
        final String seed = "[" +
                "[0,1]," +
                "[1,0]" +
                "]";
        final Grid grid = new Grid(seed);

        // When
        final boolean cell00 = grid.get(0, 0);
        final boolean cell01 = grid.get(0, 1);
        final boolean cell10 = grid.get(1, 0);
        final boolean cell11 = grid.get(1, 1);

        // Then
        assertThat("cell 0,0", cell00, is(false));
        assertThat("cell 0,1", cell01, is(true));
        assertThat("cell 1,0", cell10, is(true));
        assertThat("cell 1,1", cell11, is(false));
    }

    @Test
    @DisplayName("Given a malformed seed, it should throw exception")
    void givenAMalformedSeed_newGridThrowsException() {
        // Given
        final String seed = "[asdfasdf]";

        // When/Then
        assertThrows(RuntimeException.class, () -> {
            new Grid(seed);
        });
    }
}
