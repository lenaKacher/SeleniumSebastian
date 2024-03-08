package de.proficom.advantageshop.common;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

public class FileIO {

    /**
     * Load a property file.
     *
     * @param pFilename
     * @return config
     */
    public static Configuration loadConfig(String pFilename) {
        Configurations configs = new Configurations();
        Configuration config = null;

        try {
            config = configs.properties(new File(pFilename));
        } catch (ConfigurationException cex) {
            System.out.println(cex);
        }

        return config;
    }
}