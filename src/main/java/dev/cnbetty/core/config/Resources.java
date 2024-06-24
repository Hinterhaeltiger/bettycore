package dev.cnbetty.core.config;

public class Resources {
    private static Resources resources = new Resources();
    private static ClassLoader loader = resources.getClass().getClassLoader();

}
