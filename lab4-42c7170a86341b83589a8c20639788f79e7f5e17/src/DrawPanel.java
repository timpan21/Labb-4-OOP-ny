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

    // Just a single image, TODO: Generalize


    BufferedImage volvoImage;
    BufferedImage scaniaImage;
    BufferedImage saabImage;

    updatePanel updatepanel = new updatePanel();


















    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
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

    // This method is called each time the panel updates/refreshes/repaints itself


    @Override
    protected void paintComponent(Graphics g) {


        super.paintComponent(g);

        g.drawImage(volvoImage, updatepanel.volvoPoint.x, updatepanel.volvoPoint.y, null); // see javadoc for more info on the parameters

        g.drawImage(saabImage, updatepanel.saabPoint.x, updatepanel.saabPoint.y+100, null);
        g.drawImage(scaniaImage, updatepanel.scaniaPoint.x, updatepanel.scaniaPoint.y+200, null);

    }


}
