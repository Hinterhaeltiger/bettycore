package dev.cnbetty.core.textutility.progressbar;

import dev.cnbetty.core.textutility.stringformatter.StringColor;

public class ProgressBar {
    //TODO: Move to Component-based formatting
    public static String createProgressBar(int x, int max, int length, String begin, String end, String colorbase, String color) {
        String bar = colorbase + begin;
        float xPer = max / length;
        for (int i = 0; i < length; i++) {
            if (x <= xPer * i) {
                bar = bar + color + "|";
            } else {
                bar = bar + colorbase + "|";
            }
        }
        bar = bar + colorbase + end;
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
