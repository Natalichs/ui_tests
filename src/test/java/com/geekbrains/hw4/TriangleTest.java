package com.geekbrains.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.geekbrains.hw4.Triangle.isTriangle;
import static com.geekbrains.hw4.Triangle.triangleSquare;

public class TriangleTest {

    static Stream ms() {
        return Stream.of(Arguments.of(new Triangle(1,2,3)), Arguments.of(new Triangle(2,3,6)));
    }

    @ParameterizedTest
    @MethodSource("ms")
    void testISTriangleNegative(Triangle t) throws NotTriangleExeption {
        //boolean result = isTriangle(t.getA(), t.getB(), t.getC());
        Assertions.assertThrows(NotTriangleExeption.class, ()-> isTriangle(t.getA(), t.getB(), t.getC()) );
    }
    @Test
    void testISTrianglePositive() throws NotTriangleExeption {
        Assertions.assertTrue(isTriangle(2,3,4));
    }
    @Test
    void testTriangleSquare() throws NotTriangleExeption {
        Assertions.assertEquals(triangleSquare(2,3,4),4.5f);
    }
}
