import controller.Controller;
import controller.Settings;
import view.Painter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The {@code Main} class is the entry point of the application.
 * It sets up the game window, initializes game logic, listens for user input,
 * and starts a repaint loop using a timer to simulate game rendering.
 */
public class Main {
    /**
     * Launches the application and initializes the game window.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("PadajiciKoule");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setAlwaysOnTop(true);
        window.setResizable(false);

        Controller.initialization();

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
