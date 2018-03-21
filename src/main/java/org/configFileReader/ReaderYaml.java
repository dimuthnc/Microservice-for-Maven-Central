package org.configFileReader;

import com.esotericsoftware.yamlbeans.YamlReader;

import java.io.FileReader;
import java.io.IOException;

public class ReaderYaml {
    public static ConfigFilePOJO readYaml() throws IOException{

        com.esotericsoftware.yamlbeans.YamlReader reader = new com.esotericsoftware.yamlbeans.YamlReader(new FileReader("classes/configuration.yml"));

        ConfigFilePOJO remoteRepoConfigurations= reader.read(ConfigFilePOJO.class);

        return remoteRepoConfigurations;
    }

}
