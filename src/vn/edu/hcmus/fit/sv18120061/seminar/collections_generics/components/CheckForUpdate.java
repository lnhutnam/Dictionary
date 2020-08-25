package vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.components
 * @Author: Le Nhut Nam
 * @Date: 23/05/2020
 * @Organization: VNU - FIT HCMUS
 * @Copyright (c) 2020
 * @LICENSE: MIT LICENSE
 */
public class CheckForUpdate extends JFrame {
    private void centerFrame() {

        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();

        int dx = (centerPoint.x - windowSize.width) / 2;
        int dy = (centerPoint.y - windowSize.height) / 2;
        this.setLocation(dx + 300, dy + 200);
        //jFrame.setBounds(dx,dy,windowSize.width + 300,windowSize.height + 200);
    }

    public CheckForUpdate() throws IOException {
        setTitle("Check for a update");
        centerFrame();
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        BufferedImage wPic = ImageIO.read(this.getClass().getResource("/icons/icons8-google-translate-96.png"));
        setIconImage((new ImageIcon(getClass().getResource("/icons/icons8-update-48.png"))).getImage());

        JPanel panel = (JPanel) getContentPane();
        panel.setLayout(null);

        JLabel label = new JLabel(new ImageIcon(wPic));
        panel.add(label);
        Dimension size = label.getPreferredSize();
        label.setBounds(size.width, 0, size.width, size.height);

        label = new JLabel("English Vietnamese Dictionary --version 1.0");
        panel.add(label);
        size = label.getPreferredSize();
        label.setBounds(20, 96, size.width, size.height);

        label = new JLabel("Build: #Dict_052020, built on July, 2020");
        panel.add(label);
        size = label.getPreferredSize();
        label.setBounds(20, 116, size.width, size.height);

        label = new JLabel("Latest version");
        panel.add(label);
        size = label.getPreferredSize();
        label.setBounds(20, 136, size.width, size.height);

        setVisible(true);
    }
}
