package view;

import object.Ball;

import javax.swing.*;
import java.awt.*;

public class Painter extends JFrame {

    public Painter() throws HeadlessException {
        setTitle("PadajiciKoule");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 1920);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
