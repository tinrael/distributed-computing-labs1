package ua.knu.csc.ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MainWindow extends JFrame implements ChangeListener {

    private final JSlider slider = new JSlider();
    private final JLabel label = new JLabel();

    public MainWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JPanel labeledSpinner1 = new JPanel(new FlowLayout());
        labeledSpinner1.add(new JLabel("Thread 1:"));
        labeledSpinner1.add(new JSpinner(new SpinnerNumberModel(Thread.NORM_PRIORITY, Thread.MIN_PRIORITY, Thread.MAX_PRIORITY, 1)));

        JPanel labeledSpinner2 = new JPanel(new FlowLayout());
        labeledSpinner2.add(new JLabel("Thread 2:"));
        labeledSpinner2.add(new JSpinner(new SpinnerNumberModel(Thread.NORM_PRIORITY, Thread.MIN_PRIORITY, Thread.MAX_PRIORITY, 1)));

        Box labeledSpinnersBox = Box.createVerticalBox();
        labeledSpinnersBox.setBorder(new TitledBorder("Priority"));
        labeledSpinnersBox.add(labeledSpinner1);
        labeledSpinnersBox.add(labeledSpinner2);

        Box controlPanelBox = Box.createVerticalBox();
        controlPanelBox.add(labeledSpinnersBox);
        controlPanelBox.add(Box.createVerticalStrut(10));

        JButton buttonStart = new JButton("Start");
        buttonStart.setAlignmentX(Component.CENTER_ALIGNMENT);

        controlPanelBox.add(buttonStart);

        add(controlPanelBox);

        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(10);
        slider.addChangeListener(this);

        label.setText("value: " + slider.getValue());
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        Box box = Box.createVerticalBox();
        box.add(label);
        box.add(Box.createVerticalStrut(10));
        box.add(slider);

        add(box);

        pack();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        label.setText("value: " + slider.getValue());
    }
}