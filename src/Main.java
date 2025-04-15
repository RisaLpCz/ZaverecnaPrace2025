import controller.Settings;
import view.Painter;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("PadajiciKoule");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
        window.setLocationRelativeTo(null);
        window.setAlwaysOnTop(true);
        window.add(new Painter());
        window.setVisible(true);
    }
}
