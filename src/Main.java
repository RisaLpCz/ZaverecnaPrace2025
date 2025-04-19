import controller.Controller;
import controller.Settings;
import view.Painter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("PadajiciKoule");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setLocationRelativeTo(null);
        window.setAlwaysOnTop(true);

        Painter painter = new Painter();
        painter.setPreferredSize(new Dimension(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT));
        window.add(painter);
        window.pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - window.getWidth()) / 2;
        int y = 0;
        window.setLocation(x, y);

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
