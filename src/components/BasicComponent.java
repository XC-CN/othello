package components;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class BasicComponent extends JComponent {
    public BasicComponent() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                try {
                    onMouseClicked();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public abstract void onMouseClicked() throws InterruptedException;
}
