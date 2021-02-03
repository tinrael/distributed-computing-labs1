package ua.knu.csc.ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MainWindow extends JFrame implements ChangeListener {

    private JSlider slider = new JSlider();
    private JLabel label = new JLabel();

    public MainWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JButton("Start"));

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