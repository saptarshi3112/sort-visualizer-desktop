package main;

import main.application.*;

import javax.swing.*;
import java.awt.*;

public class Main {

    JFrame frame = new JFrame();

//    SortPanel sort = new SortPanel();
//    SearchPanel search = new SearchPanel();

    private void displayGUI() {
        JFrame frame = new JFrame("Sort - Search Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5));

        contentPane.setLayout(new CardLayout());

        SortPanel sort = new SortPanel(contentPane);
        SearchPanel search = new SearchPanel(contentPane);

        contentPane.add(sort, "Panel 1");
        contentPane.add(search, "Panel 2");

        frame.setContentPane(contentPane);
//        frame.pack();
         frame.setSize(1200, 1000);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().displayGUI();
            }
        });
    }
}
