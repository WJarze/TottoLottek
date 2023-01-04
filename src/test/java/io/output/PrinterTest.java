package io.output;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

class PrinterTest {

    @Test
    void should_print() {
        Printer mock = mock (Printer.class);
        mock.print ("");
        Mockito.verify(mock, Mockito.times(1)).print ("");
    }
}