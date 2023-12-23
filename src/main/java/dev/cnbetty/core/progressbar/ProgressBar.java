package dev.cnbetty.core.progressbar;

import dev.cnbetty.core.stringformatter.StringColor;

public class ProgressBar {
    public static String createProgressBar(int x, int max, int length, String begin, String end, String colorBase, String color) {
        String bar = colorBase + begin;
        float xPer = max / length;
        for (int i = 1; i < length + 1; i++) {
            if(x <= xPer * i) {
                bar = bar + color + "|";
            } else {
                bar = bar + colorBase + "|";
            }
        }
        bar = bar + colorBase + end;
        return bar;
    }

    public static String createProgressBar(int x, int max, int length, String colorBase, String color) {
        return createProgressBar(x, max, length, "[", "]", colorBase, color);
    }

    public static String createProgressBar(int x, int max, int length, String color) {
        return createProgressBar(x, max, length, StringColor.WHITE, color);

    }

    public static String createProgressBar(int x, int max, int length, String begin, String end, String color) {
        return createProgressBar(x, max, length, begin, end, StringColor.WHITE, color);
    }

    public static String createProgressBar(int x, int length, String begin, String end, String colorBase, String color) {
        return createProgressBar(x, 100, length, begin, end, colorBase, color);
    }

    public static String createProgressBar(int x, int length, String colorBase, String color) {
        return createProgressBar(x, 100, length, colorBase, color);
    }

    public static String createProgressBar(int x, int length, String color) {
        return createProgressBar(x, 100, length, color);
    }

    public static String createProgressBar(int x, int length, String begin, String end, String color) {
        return createProgressBar(x, 100, length, begin, end, color);
    }
}
