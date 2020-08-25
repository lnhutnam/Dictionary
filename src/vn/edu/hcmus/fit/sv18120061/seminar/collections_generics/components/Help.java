package vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.components;


import java.awt.*;
import java.net.URI;

/**
 * @vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.components
 * @Author: Le Nhut Nam
 * @Date: 23/05/2020
 * @Organization: VNU - FIT HCMUS
 * @Copyright (c) 2020
 * @LICENSE: MIT LICENSE
 */
public class Help {
    public Help () {
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI("http://www.google.com");
            desktop.browse(oURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
