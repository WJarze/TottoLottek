package io;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataReaderTest extends DataReader {


    @Test
    void testGetInt() {
        DataReader io = new DataReader ();
        String data = "1";
        System.setIn ( new ByteArrayInputStream ( data.getBytes ( ) ) );
        assertEquals("1", Integer.toString ( io.getOption (new Scanner ( System.in )) ));
    }
}