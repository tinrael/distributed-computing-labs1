package ua.knu.csc.core;

import javax.swing.JLabel;

public class JLabelWrapper extends JLabel {
    public JLabelWrapper(String text) {
        super(text);
    }

    @Override
    public void setText(String text) {
        synchronized (this) {
            super.setText(text);
        }
    }
}
