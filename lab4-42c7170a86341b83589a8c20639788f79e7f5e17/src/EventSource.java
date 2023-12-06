
import java.awt.*;
import java.util.*;
import java.util.List;

public class EventSource {
    ArrayList<SignalObserver> observers = new ArrayList<>();
    public void addObserver(SignalObserver observer) {

        observers.add(observer);
        System.out.println(observers.size());
    }
    public void notifyObsevers(Point position, Vehicles car) {

        for (SignalObserver obs: observers) {
            obs.actOnSignal(position,car);
        }




    }


}
