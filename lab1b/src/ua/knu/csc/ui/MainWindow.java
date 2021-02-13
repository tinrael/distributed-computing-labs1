package ua.knu.csc.ui;

import javax.swing.*;

import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;

public class MainWindow extends JFrame {
    private final JSlider slider = new JSlider();

    private final JButton buttonStart1 = new JButton("Start 1");
    private final JButton buttonStop1 = new JButton("Stop 1");

    private final JButton buttonStart2 = new JButton("Start 2");
    private final JButton buttonStop2 = new JButton("Stop 2");

    private final JLabel statusBarText = new JLabel("ready");

    public MainWindow(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(500, 300);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getSize().width / 2, screenSize.height / 2 - getSize().height / 2);

        JPanel viewPanel = createViewPanel();
        JPanel statusBar = createStatusBar();
        JPanel controlPanel = createControlPanel();

        Container container = Box.createVerticalBox();

        container.add(viewPanel);
        container.add(statusBar);
        container.add(controlPanel);

        add(container);
    }

    private JPanel createViewPanel() {
        JPanel viewPanel = new JPanel();
        viewPanel.setLayout(new FlowLayout());

        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(10);

        Dimension sliderPreferredSize = slider.getPreferredSize();
        slider.setPreferredSize(new Dimension(sliderPreferredSize.width + 100, sliderPreferredSize.height));

        viewPanel.add(slider);

        return viewPanel;
    }

    private JPanel createStatusBar() {
        JPanel statusBar = new JPanel();
        statusBar.setLayout(new FlowLayout());

        statusBar.add(new JLabel("Status:"));
        statusBar.add(statusBarText);

        return statusBar;
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        Box controlBoxThread1 = Box.createVerticalBox();
        controlBoxThread1.setBorder(new CompoundBorder(new TitledBorder("Thread 1"), new EmptyBorder(5, 5, 5, 5)));
        controlBoxThread1.add(buttonStart1);
        controlBoxThread1.add(Box.createVerticalStrut(5));
        controlBoxThread1.add(buttonStop1);

        Box controlBoxThread2 = Box.createVerticalBox();
        controlBoxThread2.setBorder(new CompoundBorder(new TitledBorder("Thread 2"), new EmptyBorder(5, 5, 5, 5)));
        controlBoxThread2.add(buttonStart2);
        controlBoxThread2.add(Box.createVerticalStrut(5));
        controlBoxThread2.add(buttonStop2);

        controlPanel.add(controlBoxThread1);
        controlPanel.add(controlBoxThread2);

        return controlPanel;
    }

}