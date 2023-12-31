import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CarView extends JFrame implements SignalObserver{
    private static final int X = 800;
    private static final int Y = 800;

    // The controller member


    DrawPanel drawPanel;

    JPanel controlPanel = new JPanel();

    JPanel gasPanel = new JPanel();
    JSpinner gasSpinner = new JSpinner();
    int gasAmount = 0;
    JLabel gasLabel = new JLabel("Amount of gas");
    JButton addCarButton = new JButton("Add Car");
    JButton removeCarButton = new JButton("Remove Car");

    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Scania Lift Bed");
    JButton lowerBedButton = new JButton("Lower Lift Bed");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");



    // Constructor
    public CarView(String framename){

        initComponents(framename);
    }

    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.drawPanel = new DrawPanel(X, Y -240);
        this.add(drawPanel);

        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(3,4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.setPreferredSize(new Dimension((X/3)+4, 200));

        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(X/7-15,200));
        this.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X/7-15,200));
        this.add(stopButton);

        addCarButton.setBackground(Color.orange);

        addCarButton.setPreferredSize(new Dimension(X/7-15,200));
        this.add(addCarButton);

        removeCarButton.setBackground(Color.red);

        removeCarButton.setPreferredSize(new Dimension(X/7-10,200));
        this.add(removeCarButton);







        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }




    void addGasListener(CarController controller){
        gasButton.addActionListener(controller.createGasListener());
    }
    void addStopListener(CarController controller) {
        stopButton.addActionListener(controller.createStopListener());
    }

    void addStartListener(CarController controller) {
        startButton.addActionListener(controller.createStartListener());

    }
    void addTurboOffListener(CarController controller) {
        turboOffButton.addActionListener(controller.createTurboOffListener());
    }
    void addTurboOnListener(CarController controller) {
        turboOnButton.addActionListener(controller.createTurboOnListener());
    }
    void addliftBedListener(CarController controller) {
        liftBedButton.addActionListener(controller.createLiftBedListener());

    }
    void addlowerBedListener(CarController controller) {
        lowerBedButton.addActionListener(controller.createLowerBedListener());
    }
    void addBrakeListener(CarController controller) {
        brakeButton.addActionListener(controller.createBrakeListener());
    }
    void addAddCarListener(CarController controller) {
        addCarButton.addActionListener(controller.createAddCarListener());
    }
    void addRemoveCarListener(CarController controller) {
        removeCarButton.addActionListener(controller.createRemoveCarListener());
    }

    @Override
    public void actOnSignal(Point position, Vehicles car) {

        drawPanel.updatepanel.moveCar(position,car);
        drawPanel.repaint();

    }

}
