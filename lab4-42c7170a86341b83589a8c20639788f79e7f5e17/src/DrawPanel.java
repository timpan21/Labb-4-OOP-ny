    import sun.misc.Signal;

    import java.awt.*;
    import java.awt.image.BufferedImage;
    import java.io.File;
    import java.io.IOException;
    import java.util.LinkedList;
    import java.util.Objects;
    import javax.imageio.ImageIO;
    import javax.imageio.stream.ImageInputStream;
    import javax.swing.*;
    import java.util.ArrayList;

    // This panel represent the animated part of the view with the car images.

    public class DrawPanel extends JPanel{
        private CarController carController;

        // Just a single image, TODO: Generalize


        BufferedImage volvoImage;
        BufferedImage scaniaImage;
        BufferedImage saabImage;

        updatePanel updatepanel = new updatePanel();


        // Initializes the panel and reads the images
        public DrawPanel(int x, int y, CarController carController) {
            this.carController = carController;
            this.setDoubleBuffered(true);
            this.setPreferredSize(new Dimension(x, y));
            this.setBackground(Color.green);
            // Print an error message in case file is not found with a try/catch block
            try {
                volvoImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
                scaniaImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));
                saabImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }


        }

        private BufferedImage getImageForVehicle(Vehicles car) {
            if (car instanceof Saab95) {
                return saabImage;
            } else if (car instanceof Volvo240) {
                return volvoImage;
            } else if (car instanceof Scania) {
                return scaniaImage;
            }
            return null;
        }


        // This method is called each time the panel updates/refreshes/repaints itself


        @Override
        protected void paintComponent(Graphics g) {
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


