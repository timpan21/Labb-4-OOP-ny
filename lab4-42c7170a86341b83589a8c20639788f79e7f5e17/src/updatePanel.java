import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;

public class updatePanel extends JPanel{





    // To keep track of a single cars position

    Point volvoPoint = new Point();
    Point saabPoint= new Point();
    Point scaniaPoint = new Point();


    public void  moveCar(Point position,Vehicles car){



        if (car instanceof Saab95) {
            saabPoint.y =  position.y;
            saabPoint.x =  car.getPosition().x;
        }

        else if (car instanceof Volvo240) {
            volvoPoint.y = position.y;
            volvoPoint.x = position.x;
        }

        else if (car instanceof Scania) {
            scaniaPoint.x = position.x;
            scaniaPoint.y = position.y;
        }


    }




}
