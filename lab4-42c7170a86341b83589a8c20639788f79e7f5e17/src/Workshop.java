import java.util.ArrayList;

public class Workshop<T extends isCar>  {

    private int maxCarAmount = 5;
    private ArrayList<T> listOfCars = new ArrayList<>();


    public void setMaxCarAmount(int antal) {
        this.maxCarAmount = antal;
    }


    public int getMaxCarAmount() {
        return maxCarAmount;
    }
    public ArrayList<T> getAmountOfCarsInWorkshop(){
        return this.listOfCars;
    }
    public void addCar(T bilTyp) {
        if (maxCarAmount > listOfCars.size()) {
            listOfCars.add(bilTyp);
        }
    }
    public void dispatchCar(T bilTyp) {
        listOfCars.remove(bilTyp);
    }

    public static void main(String[] args) {
        Workshop<Saab95> x = new Workshop<>();
        x.addCar(new Saab95());

    }
}

