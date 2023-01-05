package databaseService;

import org.junit.jupiter.api.Assertions;import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


 class ConnectToDatabaseTest {
    ConnectToDatabase connectToDatabase = new ConnectToDatabase ();

    @Test
    void testCollection() {
        ConnectToDatabase connectToDatabaseMock = Mockito.mock ( ConnectToDatabase.class );
        connectToDatabaseMock.collection ( );
        Mockito.verify ( connectToDatabaseMock , Mockito.times ( 1 ) ).collection ( );
    }


    @Test
     void testConnected() {
        ConnectToDatabase connectToDatabaseMock = Mockito.mock ( ConnectToDatabase.class );
        connectToDatabaseMock.connected ( );
        Mockito.verify ( connectToDatabaseMock , Mockito.times ( 1 ) ).connected ( );
        Assertions.assertNotNull ( connectToDatabase.connected () );

    }


    @Test
     void testAccess() {

        ConnectToDatabase connectToDatabaseMock = Mockito.mock ( ConnectToDatabase.class );
        connectToDatabaseMock.access ( );
        Mockito.verify ( connectToDatabaseMock , Mockito.times ( 1 ) ).access ( );
    }
}