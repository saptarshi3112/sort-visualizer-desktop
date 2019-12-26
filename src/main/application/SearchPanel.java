package main.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.util.ArrayList;
import java.util.HashMap;

import main.search.LinearSearch;
import main.search.BinarySearch;

import main.sort.QuickSort;

import main.utility.RandomArray;

public class SearchPanel extends JPanel implements ActionListener, ItemListener {

    private JComboBox <String> comboBox = new JComboBox<String>();
    private JButton button1 = new JButton("Search");
    private JButton button2 = new JButton("Generate Random Array");
    JTextArea editorPane = new JTextArea("Enter a number to search");

    private ArrayList <Integer> xAxis = new ArrayList<>();

    private String currentSearch = "Linear Search";

    private String searchIndex = "-1";
    private Integer timerIndex = 0;

    private JPanel contentPanel;

    private Timer timer = new Timer(500, this);

    private static final long serialVersionUID = 1L;

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        if (this.xAxis.size() > 0) {
            int space = 5;
            for (int i = 0; i < this.xAxis.size(); i++) {
                int val = this.xAxis.get(i);
                if (i == Integer.parseInt(this.searchIndex)) {
                    g.setColor(Color.RED);
                    g.fillRect(space, 50, 20, val);
                } else {
                    g.setColor(Color.PINK);
                    g.fillRect(space, 50, 20, val);
                }
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(val), space, 45);
                space += 30;
            }
        }
    }

    private void doLinearSearch() {
        HashMap <String, Object> response = new LinearSearch().doSearch(this.xAxis, Integer.parseInt(this.editorPane.getText()));

        ArrayList <Integer> indexes = (ArrayList<Integer>) response.get("index");

        this.timerIndex = 0;
        this.timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timerIndex == indexes.size()-1) {
                    timer.stop();
                }
                searchIndex = String.valueOf(timerIndex);
                repaint();
                timerIndex += 1;
            }
        });

        this.timer.start();

    }

    private void doBinarySearch() {

        HashMap <String, Object> response = new BinarySearch().doSearch(this.xAxis, Integer.parseInt(this.editorPane.getText()));

        ArrayList <Integer> indexes = (ArrayList<Integer>) response.get("index");

        for (Integer idx : indexes) {
            System.out.println(idx);
        }

        this.timerIndex = 0;
        this.timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timerIndex == indexes.size()-1) {
                    timer.stop();
                }
                searchIndex = String.valueOf(indexes.get(timerIndex));
                repaint();
                timerIndex += 1;
            }
        });

        this.timer.start();

    }

    private void generateArrayRandomly() {
        HashMap <String, Object> response = new QuickSort().run(RandomArray.generateRandomArray(39));
        this.xAxis = (ArrayList<Integer>) response.get("result");
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Search")) {
            System.out.println(this.currentSearch);

            switch (this.currentSearch) {
                case ("Linear Search"):
                    this.doLinearSearch();
                    break;
                case ("Binary Search"):
                    this.doBinarySearch();
                    break;
                default:
                    break;
            }

            System.out.println(this.editorPane.getText());
        } else if (e.getActionCommand().equals("Generate Random Array")) {
            this.generateArrayRandomly();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == comboBox) {
            this.currentSearch = (String) comboBox.getSelectedItem();
        }

    }

    public SearchPanel(JPanel panel) {
        this.contentPanel = panel;
        this.setOpaque(true);
        // this.setLayout(null);

//        button1.setBounds(701, 900, 187, 35);
//        button2.setBounds(233, 900, 187, 35);

        JButton b3 = new JButton("Go to sort");
        // b3.setBounds(701, 700, 187, 35);

        button1.addActionListener(this);
        button2.addActionListener(this);

        b3.addActionListener(actionEvent -> {
            CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
            cardLayout.next(contentPanel);
        });

        // editorPane.setBounds(587, 900, 107, 40);

        // comboBox.setBounds(420, 900, 155, 46);

        comboBox.addItem("Linear Search");
        comboBox.addItem("Binary Search");

        comboBox.addItemListener(this);

        this.add(button1);
        this.add(button2);

        this.add(b3);
        this.add(editorPane);
        this.add(comboBox);
    }
}
