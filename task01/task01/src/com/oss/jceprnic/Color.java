package com.oss.jceprnic;
import java.util.ArrayList;

public class Color {

    private int red;
    private int green;
    private int blue;
    private int value;

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public Color(int red, int green, int blue){
        this.red=red;
        this.green=green;
        this.blue=blue;
        value = ((red & 0xFF) << 16) | ((green & 0xFF) << 8)  | ((blue & 0xFF) << 0);
    }

    public static ArrayList<Float> RGBtoHSB(int r, int g, int b, ArrayList<Float> hsbCode) {
        float hue, saturation, brightness;

        int cmax = (r > g) ? r : g;
        if (b > cmax) cmax = b;
        int cmin = (r < g) ? r : g;
        if (b < cmin) cmin = b;

        brightness = ((float) cmax) / 255.0f;
        if (cmax != 0)
            saturation = ((float) (cmax - cmin)) / ((float) cmax);
        else
            saturation = 0;
        if (saturation == 0)
            hue = 0;
        else {
            float redc = ((float) (cmax - r)) / ((float) (cmax - cmin));
            float greenc = ((float) (cmax - g)) / ((float) (cmax - cmin));
            float bluec = ((float) (cmax - b)) / ((float) (cmax - cmin));
            if (r == cmax)
                hue = bluec - greenc;
            else if (g == cmax)
                hue = 2.0f + redc - bluec;
            else
                hue = 4.0f + greenc - redc;
            hue = hue / 6.0f;
            if (hue < 0)
                hue = hue + 1.0f;
        }
        hsbCode.add(hue);
        hsbCode.add(saturation);
        hsbCode.add(brightness);
        return hsbCode;
    }

    public static ArrayList<Float> RGBtoHSL(int rgb, ArrayList<Float> hslCode ) {
        float r = ((0x00ff0000 & rgb) >> 16) / 255.f;
        float g = ((0x0000ff00 & rgb) >> 8) / 255.f;
        float b = ((0x000000ff & rgb)) / 255.f;
        float max = Math.max(Math.max(r, g), b);
        float min = Math.min(Math.min(r, g), b);
        float c = max - min;

        float h_ = 0.f;
        if (c == 0) {
            h_ = 0;
        } else if (max == r) {
            h_ = (float)(g-b) / c;
            if (h_ < 0) h_ += 6.f;
        } else if (max == g) {
            h_ = (float)(b-r) / c + 2.f;
        } else if (max == b) {
            h_ = (float)(r-g) / c + 4.f;
        }
        float h = 60.f * h_;

        float l = (max + min) * 0.5f;

        float s;
        if (c == 0) {
            s = 0.f;
        } else {
            s = c / (1 - Math.abs(2.f * l - 1.f));
        }

        hslCode.add(h);
        hslCode.add(s*100);
        hslCode.add(l*100);
        return hslCode;
    }
    public static ArrayList<Integer> RGBtoCMYK(int r, int g, int b, ArrayList<Integer> cmyk) {
        double percentageR = r / 255.0 * 100;
        double percentageG = g / 255.0 * 100;
        double percentageB = b / 255.0 * 100;

        double k = 100 - Math.max(Math.max(percentageR, percentageG), percentageB);

        if (k == 100) {
            cmyk.add(0);
            cmyk.add(0);
            cmyk.add(0);
            cmyk.add(100);
        } else {
            int c = (int) ((100 - percentageR - k) / (100 - k) * 100);
            int m = (int) ((100 - percentageG - k) / (100 - k) * 100);
            int y = (int) ((100 - percentageB - k) / (100 - k) * 100);
            cmyk.add(c);
            cmyk.add(m);
            cmyk.add(y);
            cmyk.add((int) k);
        }
        return cmyk;
    }

    public static Color decode(String str){
        Integer intval = Integer.decode(str);
        int i = intval.intValue();
        return new Color((i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF);
    }

    public static void main(String[] args) {

        String hexColor = "0x1FF0FF";
        Color c = Color.decode(hexColor);

        ArrayList<Float> hsbCode = new ArrayList<>();
        ArrayList<Integer> cmykCode = new ArrayList<>();
        ArrayList<Float> hslCode = new ArrayList<>();

        Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), hsbCode);
        Color.RGBtoHSL(c.getValue(),hslCode);
        Color.RGBtoCMYK(c.getRed(), c.getGreen(), c.getBlue(), cmykCode);


        System.out.println("Boja u HEX formatu: 0x" +
                Integer.toHexString(c.getValue() & 0x00FFFFFF));
        System.out.println("Boja u RGB formatu: " + c.getRed() + ", " +
                c.getGreen() + ", " + c.getBlue());
        System.out.println("Boja u HSB formatu: " + hsbCode.get(0) * 360 + "°, " +
                hsbCode.get(1) * 100 + "%, " + hsbCode.get(2) * 100 + "%");
        System.out.println("Boja u HSL formatu: " + hslCode.get(0) + "°, " +
                hslCode.get(1)  + "%, " + hslCode.get(2)  + "%");
        System.out.println("Boja u CMYK formatu: " + cmykCode.get(0) + ", " +
                cmykCode.get(1) + ", " + cmykCode.get(2) + ", " + cmykCode.get(3));
    }
}


