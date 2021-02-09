package ua.knu.csc.core;

import javax.swing.*;

public class Slider extends JSlider {

    public Slider() {
        setPaintTicks(true);
        setPaintLabels(true);
        setMajorTickSpacing(10);
    }

    public synchronized void increaseValueByOne() {
        setValue(super.getValue() + 1);
    }

    public synchronized void decreaseValueByOne() {
        setValue(super.getValue() - 1);
    }

    @Override
    public synchronized int getValue() {
        return super.getValue();
    }
    
}
