package org.configFileReader;

import java.io.IOException;

public class configurationReader {

    public configFilePOJO getConfigurations() throws IOException{

        configFilePOJO repoConfigs = yamlReader.readYaml();
        return repoConfigs;
    }

}
