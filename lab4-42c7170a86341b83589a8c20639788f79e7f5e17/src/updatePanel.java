import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;

public class updatePanel {
    // To keep track of a single cars position

    Point volvoPoint = new Point();
    Point saabPoint= new Point();
    Point scaniaPoint = new Point();
    private Map<Class<? extends Vehicles>, Point> pointsMap = new HashMap<>();

    public updatePanel() {
        pointsMap.put(Saab95.class,saabPoint);
        pointsMap.put(Volvo240.class,volvoPoint);
        pointsMap.put(Scania.class,scaniaPoint);
    }
    public void  moveCar(Point position,Vehicles car){


        pointsMap.get(car.getClass()).y = position.y;
        pointsMap.get(car.getClass()).x = position.x;


    }

}
