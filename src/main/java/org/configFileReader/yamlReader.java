package org.configFileReader;

import com.esotericsoftware.yamlbeans.YamlReader;

import java.io.FileReader;
import java.io.IOException;

public class yamlReader {
    public static configFilePOJO readYaml() throws IOException{

        YamlReader reader = new YamlReader(new FileReader("/home/jayathma/WSO2_Intern_Local/Copies/config/repoConfiguration.yml"));

        configFilePOJO remoteRepoConfigurations= reader.read(configFilePOJO.class);

        return remoteRepoConfigurations;
    }

}
