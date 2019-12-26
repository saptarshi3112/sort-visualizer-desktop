package main.application;

import java.awt.*;
import java.util.HashMap;
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import main.utility.RandomArray;
import main.utility.Range;
import main.sort.QuickSort;
import main.sort.HeapSort;
import main.sort.MergeSort;
import main.sort.RandomizedQuickSort;

import javax.swing.*;

public class SortPanel extends JPanel implements ItemListener, ActionListener {

    private JPanel contentPanel;

    private static final long serialVersionUID = 1L;

    private String currentSort = "Merge Sort";

    private JButton button2 = new JButton("Sort");
    private JButton switchButton = new JButton("Go to search");

    private JComboBox <String> comboBox = new JComboBox<String>();

    private ArrayList <Integer> XAxis = new ArrayList <>();

    private Range currentRange = new Range(-1, -1);

    private Timer timer = new Timer(500, this);

    private int timerInterval = 0;

    @SuppressWarnings("unused")
    private static void printArrayList (ArrayList <Integer> list) {
        for (Integer itr : list) {
            System.out.println(itr);
        }
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == comboBox) {
            this.currentSort = (String) comboBox.getSelectedItem();
        }
    }

    public SortPanel(JPanel panel) {

        this.contentPanel = panel;
        this.setOpaque(true);
       // this.setLayout(null);

        button2.setBounds(600, 900, 74, 31);
        switchButton.setBounds(300, 900, 100, 31);

        this.button2.addActionListener(this);

        this.switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                cardLayout.next(contentPanel);
            }
        });


        this.add(button2);
        this.add(switchButton);

        comboBox.setBounds(412, 905, 176, 24);

        comboBox.addItem("Merge Sort");
        comboBox.addItem("Quick Sort");
        comboBox.addItem("Heap Sort");
        comboBox.addItem("Randomized Quick Sort");

        comboBox.addItemListener(this);

        this.add(comboBox);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (this.XAxis.size() > 0) {
            if (this.currentRange.getMaxValue() == -1 && this.currentRange.getMinValue() == -1) {
                int space = 5;
                for (int itr = 0; itr < this.XAxis.size(); itr++) {
                    g.setColor(Color.GREEN);
                    g.fillRect(space, 35, 5, this.XAxis.get(itr));
                    space += 10;
                }
            } else {
                if (this.currentSort.equals("Heap Sort")) {
                    int i = 5;
                    for (int itr = 0; itr < this.XAxis.size(); itr++) {
                        Integer val = this.XAxis.get(itr);
                        if (itr == currentRange.getMinValue() || itr == currentRange.getMaxValue()) {
                            g.setColor(Color.BLUE);
                            g.fillRect(i, 35, 5, val);
                        } else {
                            g.setColor(Color.RED);
                            g.fillRect(i, 35, 5, val);
                        }
                        i+=10;
                    }
                } else {
                    int i = 5;
                    for (int itr = 0; itr < this.XAxis.size(); itr++) {
                        Integer val = this.XAxis.get(itr);
                        if (itr >= currentRange.getMinValue() && itr <= currentRange.getMaxValue()) {
                            g.setColor(Color.BLUE);
                            g.fillRect(i, 35, 5, val);
                        } else {
                            g.setColor(Color.RED);
                            g.fillRect(i, 35, 5, val);
                        }

                        i+=10;
                    }
                }
            }
        }

    }

    @SuppressWarnings("unchecked")
    private void performQuickSort() {
        HashMap <String, Object> quickSortResult = new QuickSort().run(RandomArray.generateRandomArray(119));

        ArrayList <ArrayList <Integer>> arrayStates = (ArrayList<ArrayList<Integer>>) quickSortResult.get("states");
        ArrayList <Range> ranges = (ArrayList<Range>) quickSortResult.get("ranges");

        System.out.println(arrayStates.size());

        // timer.start();
        this.timerInterval = 0;
        this.currentRange = null;
        this.XAxis = null;

        try {
            this.timer = new Timer(300, new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    if (timerInterval == arrayStates.size()-1) {
//                        XAxis = arrayStates.get(timerInterval);
//                        currentRange = ranges.get(timerInterval);
//                        repaint();
                        timer.stop();
                    }
                    XAxis = arrayStates.get(timerInterval);
                    currentRange = ranges.get(timerInterval);
                    repaint();
                    timerInterval += 1;
                }
            });
        } catch(IndexOutOfBoundsException e) {
            this.timer.stop();
        }

        this.timer.start();

    }

    @SuppressWarnings("unchecked")
    private void performRandomizedQuickSort() {
        HashMap <String, Object> quickSortResult = new RandomizedQuickSort().run(RandomArray.generateRandomArray(119));

        ArrayList <ArrayList <Integer>> arrayStates = (ArrayList<ArrayList<Integer>>) quickSortResult.get("states");
        ArrayList <Range> ranges = (ArrayList<Range>) quickSortResult.get("ranges");

        System.out.println(arrayStates.size());

        // timer.start();
        this.timerInterval = 0;
        this.currentRange = null;
        this.XAxis = null;

        try {
            this.timer = new Timer(300, new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    // System.out.println(timerInterval);
                    if (timerInterval == arrayStates.size()-1) {
//                        XAxis = arrayStates.get(timerInterval);
//                        currentRange = ranges.get(timerInterval);
//                        repaint();
//                        timerInterval += 1;
                        timer.stop();
                    }
                    XAxis = arrayStates.get(timerInterval);
                    currentRange = ranges.get(timerInterval);
                    repaint();
                    timerInterval += 1;
                }
            });
        } catch(IndexOutOfBoundsException e) {
            this.timer.stop();
        }

        this.timer.start();
    }

    @SuppressWarnings("unchecked")
    private void performMergeSort() {
        HashMap <String, Object> mergeSortSortResult = new MergeSort().run(RandomArray.generateRandomArray(119));

        ArrayList <ArrayList <Integer>> arrayStates = (ArrayList<ArrayList<Integer>>) mergeSortSortResult.get("states");
        ArrayList <Range> ranges = (ArrayList<Range>) mergeSortSortResult.get("ranges");

        System.out.println(arrayStates.size());

        // timer.start();
        this.timerInterval = 0;
        this.currentRange = null;
        this.XAxis = null;

        try {
            this.timer = new Timer(300, new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    // System.out.println(timerInterval);
                    if (timerInterval == arrayStates.size()-1) {
//                        XAxis = arrayStates.get(timerInterval);
//                        currentRange = ranges.get(timerInterval);
//                        repaint();
//                        timerInterval += 1;
                        timer.stop();
                    }
                    XAxis = arrayStates.get(timerInterval);
                    currentRange = ranges.get(timerInterval);
                    repaint();
                    timerInterval += 1;
                }
            });
        } catch(IndexOutOfBoundsException e) {
            this.timer.stop();
        }

        this.timer.start();

    }

    @SuppressWarnings("unchecked")
    private void performHeapSort() {
        HashMap <String, Object> heapSortSortResult = new HeapSort().run(RandomArray.generateRandomArray(119));

        ArrayList <ArrayList <Integer>> arrayStates = (ArrayList<ArrayList<Integer>>) heapSortSortResult.get("states");
        ArrayList <Range> ranges = (ArrayList<Range>) heapSortSortResult.get("ranges");

        System.out.println(arrayStates.size());

        // timer.start();
        this.timerInterval = 0;
        this.currentRange = null;
        this.XAxis = null;

        try {
            this.timer = new Timer(300, new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    // System.out.println(timerInterval);
                    if (timerInterval == arrayStates.size()-1) {
//                        XAxis = arrayStates.get(timerInterval);
//                        currentRange = ranges.get(timerInterval);
//                        repaint();
//                        timerInterval += 1;
                        timer.stop();
                    }
                    XAxis = arrayStates.get(timerInterval);
                    currentRange = ranges.get(timerInterval);
                    repaint();
                    timerInterval += 1;
                }
            });
        } catch(IndexOutOfBoundsException e) {
            this.timer.stop();
        }

        this.timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Sort")) {
            switch (this.currentSort) {
                case ("Merge Sort"):
                    this.performMergeSort();
                    break;
                case ("Quick Sort"):
                    this.performQuickSort();
                    break;
                case ("Heap Sort"):
                    this.performHeapSort();
                    break;
                case ("Randomized Quick Sort"):
                    this.performRandomizedQuickSort();
                default:
                    break;
            }
        } else if (e.getSource() == timer) {
            System.out.println("repaint");
            repaint();
        }
    }
}
