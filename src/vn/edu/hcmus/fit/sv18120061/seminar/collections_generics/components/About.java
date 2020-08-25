package vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.components;

import javax.swing.*;
import java.awt.*;

/**
 * @vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.components
 * @Author: Le Nhut Nam
 * @Date: 23/05/2020
 * @Organization: VNU - FIT HCMUS
 * @Copyright (c) 2020
 * @LICENSE: MIT LICENSE
 */
public class About extends JFrame {
    private void centerFrame() {

        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();

        int dx = (centerPoint.x - windowSize.width) / 2;
        int dy = (centerPoint.y - windowSize.height) / 2;
        this.setLocation(dx + 300, dy + 200);
        //jFrame.setBounds(dx,dy,windowSize.width + 300,windowSize.height + 200);
    }

    public About () {
        setTitle("About");
        centerFrame();
        setSize(300, 200);
        // jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // jFrame.setBounds(300, 250, 450, 300);
        setIconImage((new ImageIcon(getClass().getResource("/icons/icons8-about-48.png"))).getImage());

        JPanel panel = (JPanel) getContentPane();
        panel.setLayout(null);

        JLabel label = new JLabel("University of Science, VNU");
        panel.add(label);
        Dimension size = label.getPreferredSize();
        label.setBounds(10, 15, size.width, size.height);

        label = new JLabel("Faculty of Information Technology");
        panel.add(label);
        size = label.getPreferredSize();
        label.setBounds(10, 35, size.width, size.height);

        label = new JLabel("Author: Le Nhut Nam");
        panel.add(label);
        size = label.getPreferredSize();
        label.setBounds(10, 55, size.width, size.height);

        label = new JLabel("Contact: lenam.fithcmus@gmail.com");
        panel.add(label);
        size = label.getPreferredSize();
        label.setBounds(10, 75, size.width, size.height);

        label = new JLabel("Copyright (c) this program - Le Nhut Nam 2020");
        panel.add(label);
        size = label.getPreferredSize();
        label.setBounds(10, 95, size.width, size.height);

        label = new JLabel("MIT License (MIT)");
        panel.add(label);
        size = label.getPreferredSize();
        label.setBounds(10, 115, size.width, size.height);

        setVisible(true);
    }
}
