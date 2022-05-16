import Model.MapModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestBoundaries {
    MapModel mapModel;

    @BeforeEach
    void setUp() {
        App app = new App();
        this.mapModel = app.getMapModel();
        mapModel.setTesting(true);
    }

    @Test
    void testOne(){
        mapModel.restart(0);
        mapModel.movePlayerUp();
        assertEquals( 1,mapModel.getPlayerY());
        assertEquals( 1,mapModel.getPlayerX());
    }

    @Test
    void testTwo(){
        mapModel.restart(1);
        mapModel.movePlayerRight();
        assertEquals( 1,mapModel.getPlayerY());
        assertEquals( 2,mapModel.getPlayerX());
    }

    @Test
    void testThree(){
        mapModel.restart(2);
        mapModel.movePlayerRight();
        assertEquals( 1,mapModel.getPlayerY());
        assertEquals( 1,mapModel.getPlayerX());
    }

    @Test
    void testFour(){
        mapModel.restart(3);
        mapModel.movePlayerRight();
        assertEquals( 1,mapModel.getPlayerY());
        assertEquals( 2,mapModel.getPlayerX());
    }

}
