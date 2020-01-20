package configuration;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private static final String RESOURCES_PATH = "src\\main\\resources\\";
    private static Properties properties;

    public static void readProperties() {
        if (properties == null) {
            properties = new Properties();
        }
        try {
            properties.load(new FileReader(new File(RESOURCES_PATH, "api.properties")));
            properties.load(new FileReader(new File(RESOURCES_PATH, "mail.properties")));
            properties.load(new FileReader(new File(RESOURCES_PATH, "applitools.properties")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getAccessToken() {
        initializeProperties();
        return properties.getProperty("vkAccessToken");
    }

    public static String getOwnerId() {
        initializeProperties();
        return properties.getProperty("ownerId");
    }

    public static String getMessageText() {
        initializeProperties();
        return properties.getProperty("vkMessage");
    }

    public static String getEditedMessageText() {
        initializeProperties();
        return properties.getProperty("vkEditedMessage");
    }

    public static String getVersion() {
        initializeProperties();
        return properties.getProperty("vkVersion");
    }

    public static String getMainUrl() {
        initializeProperties();
        return properties.getProperty("mainUrl");
    }

    public static String getApiKey() {
        initializeProperties();
        return properties.getProperty("apiKey");
    }

    private static void initializeProperties() {
        if (properties == null) {
            readProperties();
        }
    }
}