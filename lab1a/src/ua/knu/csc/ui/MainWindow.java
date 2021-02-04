package ua.knu.csc.ui;

import java.awt.*;

import javax.swing.*;

import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainWindow extends JFrame implements ChangeListener {

    private final JSlider slider = new JSlider();
    private final JLabel label = new JLabel();

    public MainWindow(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JPanel viewPanel = createViewPanel();
        JPanel controlPanel = createControlPanel();

        Container container = Box.createVerticalBox();
        container.add(viewPanel);
        container.add(controlPanel);

        add(container);
        pack();
    }

    private JPanel createViewPanel() {
        JPanel viewPanel = new JPanel(new FlowLayout());

        JPanel labeledSpinner1 = new JPanel(new FlowLayout());
        labeledSpinner1.add(new JLabel("Thread 1:"));
        labeledSpinner1.add(new JSpinner(new SpinnerNumberModel(Thread.NORM_PRIORITY, Thread.MIN_PRIORITY, Thread.MAX_PRIORITY, 1)));

        JPanel labeledSpinner2 = new JPanel(new FlowLayout());
        labeledSpinner2.add(new JLabel("Thread 2:"));
        labeledSpinner2.add(new JSpinner(new SpinnerNumberModel(Thread.NORM_PRIORITY, Thread.MIN_PRIORITY, Thread.MAX_PRIORITY, 1)));

        JButton buttonSet = new JButton("Set");
        buttonSet.setAlignmentX(Component.CENTER_ALIGNMENT);

        Box priorityBox = Box.createVerticalBox();
        priorityBox.setBorder(new CompoundBorder(new TitledBorder("Priority"), new EmptyBorder(5, 5, 5, 5)));
        priorityBox.add(labeledSpinner1);
        priorityBox.add(labeledSpinner2);
        priorityBox.add(Box.createVerticalStrut(5));
        priorityBox.add(buttonSet);

        label.setText("value: " + slider.getValue());
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(10);
        slider.addChangeListener(this);

        Box sliderBox = Box.createVerticalBox();
        sliderBox.add(label);
        sliderBox.add(Box.createVerticalStrut(10));
        sliderBox.add(slider);

        viewPanel.add(priorityBox);
        viewPanel.add(sliderBox);

        return viewPanel;
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel(new FlowLayout());

        controlPanel.add(new JButton("Start"));

        return controlPanel;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        label.setText("value: " + slider.getValue());
    }
}