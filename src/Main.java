import controller.Controller;
import controller.Settings;
import view.Painter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("PadajiciKoule");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
        window.setLocationRelativeTo(null);
        window.setAlwaysOnTop(true);
        window.add(new Painter());
        window.setVisible(true);




        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.startTime();
                window.repaint();
            }
        });
        timer.start();

    }
}
