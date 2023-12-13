import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener {
    private final int delay = 50;

    Timer timer = new Timer(delay, this);
    EventSource source;

        public Listener(EventSource source) {
            this.source = source;
        }
    public void actionPerformed(ActionEvent e) {

        for (Vehicles car : Vehicles.cars) {
            car.move();
            source.notifyObsevers(car.getPosition(), car);


        }


    }
}
