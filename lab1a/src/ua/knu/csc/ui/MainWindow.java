package ua.knu.csc.ui;

import ua.knu.csc.core.ThreadsRunner;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainWindow extends JFrame implements ChangeListener, ActionListener {
    private final ThreadsRunner threadsRunner;

    private final JSpinner spinnerThread1 = new JSpinner(new SpinnerNumberModel(Thread.NORM_PRIORITY, Thread.MIN_PRIORITY, Thread.MAX_PRIORITY, 1));
    private final JSpinner spinnerThread2 = new JSpinner(new SpinnerNumberModel(Thread.NORM_PRIORITY, Thread.MIN_PRIORITY, Thread.MAX_PRIORITY, 1));

    private final JLabel label = new JLabel();
    private final JSlider slider = new JSlider();

    private final JButton buttonStart = new JButton("Start");

    public MainWindow(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(500, 300);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getSize().width / 2, screenSize.height / 2 - getSize().height / 2);

        JPanel viewPanel = createViewPanel();
        JPanel controlPanel = createControlPanel();

        Container container = Box.createVerticalBox();
        container.add(viewPanel);
        container.add(controlPanel);

        add(container);

        threadsRunner = new ThreadsRunner(this, (int) spinnerThread1.getValue(), (int) spinnerThread2.getValue());
    }

    private JPanel createViewPanel() {
        JPanel viewPanel = new JPanel(new FlowLayout());

        JPanel labeledSpinner1 = new JPanel(new FlowLayout());
        labeledSpinner1.add(new JLabel("Thread 1:"));
        labeledSpinner1.add(spinnerThread1);

        JPanel labeledSpinner2 = new JPanel(new FlowLayout());
        labeledSpinner2.add(new JLabel("Thread 2:"));
        labeledSpinner2.add(spinnerThread2);

        JButton buttonSet = new JButton("Set");
        buttonSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                threadsRunner.setPriorityThread1((int) spinnerThread1.getValue());
                threadsRunner.setPriorityThread2((int) spinnerThread2.getValue());
            }
        });
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

        buttonStart.addActionListener(this);

        controlPanel.add(buttonStart);

        return controlPanel;
    }

    public void increaseSliderValueByOne() {
        slider.setValue(slider.getValue() + 1);
    }

    public void decreaseSliderValueByOne() {
        slider.setValue(slider.getValue() - 1);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        label.setText("value: " + slider.getValue());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        buttonStart.setEnabled(false);

        spinnerThread1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                threadsRunner.setPriorityThread1((int) spinnerThread1.getValue());
            }
        });

        spinnerThread2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                threadsRunner.setPriorityThread2((int) spinnerThread2.getValue());
            }
        });

        threadsRunner.startThreads();
    }
}