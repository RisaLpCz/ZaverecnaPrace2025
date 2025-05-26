import controller.Controller;
import controller.Settings;
import view.Painter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("PadajiciKoule");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setLocationRelativeTo(null);
        window.setAlwaysOnTop(true);
        window.setResizable(false);

        Painter painter = new Painter();
        painter.setPreferredSize(new Dimension(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT));
        window.add(painter);
        window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_SPACE && !Controller.spawnCircle.isEmpty()) {
                    Controller.spawnCircle.clear();
                }
            }
        });
        window.pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - window.getWidth()) / 2;
        int y = 0;
        window.setLocation(x, y);

        window.setVisible(true);

        Controller.initialization();

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
