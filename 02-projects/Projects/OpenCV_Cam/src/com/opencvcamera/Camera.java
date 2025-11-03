package com.opencvcamera;

import org.opencv.core.Core;

import javax.swing.*;

public class Camera extends JFrame {


    public Camera() {

    }

    public static void main(String[] args) {
   System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
}