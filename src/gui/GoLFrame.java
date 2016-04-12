/***************************************
 * Yossi Silberhaft - 210028924
 * Binyamin Greenberg - 200220671
 **************************************/
package gui;
import java.awt.*;

import javax.swing.JFrame;

public class GoLFrame extends JFrame {
    private static final long serialVersionUID = 498792054583252572L;
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 1000;

    public GoLFrame() {
        //Sets the frame to open at the center of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2,
                dim.height / 2 - this.getSize().height / 2);
        this.setTitle("Game of Life");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.getContentPane().add(new GoLPanel(this));
        this.setResizable(false);
        this.setResizable(true);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}