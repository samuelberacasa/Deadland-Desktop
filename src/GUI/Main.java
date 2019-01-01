package GUI;

import Game.Game;
import Game.GameVariables;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Samuel Beracasa
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame(GameVariables.GameName);
        frame.add(game);
        frame.setSize(GameVariables.FrameWidth, GameVariables.FrameHeight);
        frame.setResizable(true);
        frame.setFocusable(true);
        game.addKeyListener(game);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.err.println("Exiting Game");
                game.stop();
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();
    }

}