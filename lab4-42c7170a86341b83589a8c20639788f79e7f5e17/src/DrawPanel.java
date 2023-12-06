    import sun.misc.Signal;

    import java.awt.*;
    import java.awt.image.BufferedImage;
    import java.io.File;
    import java.io.IOException;
    import java.util.*;
    import javax.imageio.ImageIO;
    import javax.imageio.stream.ImageInputStream;
    import javax.swing.*;

    // This panel represent the animated part of the view with the car images.

    public class DrawPanel extends JPanel{
        private CarController carController;

        // Just a single image, TODO: Generalize


        BufferedImage volvoImage;
        BufferedImage scaniaImage;
        BufferedImage saabImage;

        updatePanel updatepanel = new updatePanel();






        private final Map<Class<? extends Vehicles>, BufferedImage> test = new HashMap<>();



        // Initializes the panel and reads the images
        public DrawPanel(int x, int y, CarController carController) {
            this.carController = carController;



            this.setDoubleBuffered(true);
            this.setPreferredSize(new Dimension(x, y));
            this.setBackground(Color.green);
            // Print an error message in case file is not found with a try/catch block
            try {
                // You can remove the "pics" part if running outside of IntelliJ and
                // everything is in the same main folder.
                // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

                // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
                // if you are starting in IntelliJ.
                volvoImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
                scaniaImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));
                saabImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }


        }

        private BufferedImage getImageForVehicle(Vehicles car) {

            return test.get(car.getClass());
        }


        // This method is called each time the panel updates/refreshes/repaints itself


        @Override
        protected void paintComponent(Graphics g) {
            test.put(Saab95.class, saabImage);
            test.put(Volvo240.class, volvoImage);
            test.put(Scania.class, scaniaImage);
            super.paintComponent(g);
            int deltaY = 100; // Vertical offset between cars
            int yOffset = 0;  // Starting offset

            for (Vehicles car : carController.getVehicles()) {

                BufferedImage image = getImageForVehicle(car);

                int x = car.getPosition().x;
                int y = car.getPosition().y + yOffset; // Apply the offset

                g.drawImage(image, x, y, null); // Draw the car at the adjusted position

                yOffset += deltaY; // Increase offset for the next car
            }
        }
    }



