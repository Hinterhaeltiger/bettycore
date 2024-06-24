package dev.cnbetty.core.config;

import dev.cnbetty.velocitycore.VelocityCore;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class PluginConfigFile {
    private static String configpath;
    private static Logger logger = VelocityCore.getLogger();
    public static void initialize() {
        File jarfile;
        try {
            jarfile = new File(VelocityCore.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        logger.debug("current JAR's location: " + jarfile.getPath());

        String pluginfolder = jarfile.getParent();
        logger.debug("Velocity plugin folder: " + pluginfolder);

        String configdirectory = pluginfolder + "\\VelocityCore";

        // Define the path inside the JAR where the assets folder is located
        String configresourcepath = "config/";

        // Get the JAR file from which the code is running
        String jarPath = VelocityCore.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        try (ZipInputStream zip = new ZipInputStream(new FileInputStream(jarPath))) {
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                // Check if the entry is part of the assets folder
                if (entry.getName().startsWith(configresourcepath) && !entry.isDirectory()) {

                    String fileName = entry.getName().substring(configresourcepath.length());
                    Path outputPath = Path.of(configdirectory).resolve(fileName);

                    if (Files.notExists(outputPath)) {
                        // Ensure the parent directories exist
                        Files.createDirectories(outputPath.getParent());
                        logger.info("Attempting to copy file " + fileName);
                        try (OutputStream out = Files.newOutputStream(outputPath)) {
                            byte[] buffer = new byte[1024];
                            int len;
                            while ((len = zip.read(buffer)) > 0) {
                                out.write(buffer, 0, len);
                            }
                        }
                    } else {
                        logger.info("file " + outputPath + "already exists, no need to copy it.");
                        configpath = outputPath.toString();
                        logger.info("config file location is " + outputPath);
                    }
                }
            }
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }
    public static String getConfigPath() {
        return configpath;
    }
}
