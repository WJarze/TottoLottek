package io.output;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

 
 class PrinterTest {

    @Test
    void should_print() {
        Printer mock = Mockito.mock(Printer.class);
        mock.print ("");
        Mockito.verify(mock, Mockito.times(1)).print ("");
    }

 

}