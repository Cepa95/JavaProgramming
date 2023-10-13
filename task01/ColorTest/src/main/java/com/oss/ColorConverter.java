package com.oss;

import java.util.ArrayList;

public class ColorConverter {

    public static void main(String[] args) {

        String hexColor = "0x1FF0FF";
        Color c = Color.decode(hexColor);

        ArrayList<Float> hsbCode = new ArrayList<>();
        ArrayList<Integer> cmykCode = new ArrayList<>();
        ArrayList<Float> hslCode = new ArrayList<>();

        Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), hsbCode);
        Color.RGBtoHSL(c.getValue(), hslCode);
        Color.RGBtoCMYK(c.getRed(), c.getGreen(), c.getBlue(), cmykCode);


        System.out.println("Boja u HEX formatu: 0x" +
                Integer.toHexString(c.getValue() & 0x00FFFFFF));
        System.out.println("Boja u RGB formatu: " + c.getRed() + ", " +
                c.getGreen() + ", " + c.getBlue());
        System.out.println("Boja u HSB formatu: " + hsbCode.get(0) * 360 + "°, " +
                hsbCode.get(1) * 100 + "%, " + hsbCode.get(2) * 100 + "%");
        System.out.println("Boja u HSL formatu: " + hslCode.get(0) + "°, " +
                hslCode.get(1) + "%, " + hslCode.get(2) + "%");
        System.out.println("Boja u CMYK formatu: " + cmykCode.get(0) + ", " +
                cmykCode.get(1) + ", " + cmykCode.get(2) + ", " + cmykCode.get(3));
    }
}
