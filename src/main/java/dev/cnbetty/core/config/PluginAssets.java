package dev.cnbetty.core.config;

import dev.cnbetty.core.Core;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class PluginAssets {

    private static Logger logger = Core.logger;

    public static void initialize() {
        // Create a new single-threaded executor
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.submit(() -> {
            try {
                //I have no fucking idea what this code does and how it works but it does so DON'T TOUCH IT
                //assets will be copied asynchronously because of larger file size
                File jarfile = new File(Core.class.getProtectionDomain().getCodeSource().getLocation().toURI());
                logger.info("current JAR's location: " + jarfile.getPath());

                String pluginfolder = jarfile.getParent();
                logger.info("Velocity plugin folder: " + pluginfolder);

                String assetsdirectory = pluginfolder + "\\Core\\assets";

                String assetsFolderPath = "assets/";
                String jarPath = Core.class.getProtectionDomain().getCodeSource().getLocation().getPath();

                try (ZipInputStream zip = new ZipInputStream(new FileInputStream(jarPath))) {
                    ZipEntry entry;
                    while ((entry = zip.getNextEntry()) != null) {
                        // Check if the entry is part of the assets folder
                        if (entry.getName().startsWith(assetsFolderPath) && !entry.isDirectory()) {
                            String fileName = entry.getName().substring(assetsFolderPath.length());
                            Path outputPath = Path.of(assetsdirectory).resolve(fileName);

                            if (Files.notExists(outputPath)) {
                                try {
                                    Files.createDirectories(outputPath.getParent());
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                                logger.info("Attempting to copy file " + fileName);
                                try (OutputStream out = Files.newOutputStream(outputPath)) {
                                    byte[] buffer = new byte[1024];
                                    int len;
                                    while ((len = zip.read(buffer)) > 0) {
                                        out.write(buffer, 0, len);
                                    }
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            } else {
                                logger.info("File " + outputPath + " already exists, no need to copy it.");
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } finally {
                executor.shutdown();
                try {
                    if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                        executor.shutdownNow();
                    }
                } catch (InterruptedException e) {
                    executor.shutdownNow();
                }
            }
        });
    }
}