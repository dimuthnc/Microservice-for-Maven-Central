/*
 * Copyright (c) 2016, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.service;

import org.authentication.UsernamePasswordSecurityInterceptor;
import org.configFileReader.ConfigurationReader;
import org.configFileReader.ConfigFilePOJO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.msf4j.MicroservicesRunner;

/**
 * ApplicationDeployMavenAether entry point.
 *
 * @since 1.0-SNAPSHOT
 */
public class ApplicationDeployMavenAether {

    private final static Logger logger = LoggerFactory.getLogger(ApplicationDeployMavenAether.class);

    public static void main(String[] args) {

        try{
            ConfigurationReader configs = new ConfigurationReader();
            ConfigFilePOJO configurations = configs.getConfigurations();

            if(configurations.getPort() == 0  ||  configurations.getId() == null || configurations.getId().isEmpty() ||
                    configurations.getUrl()== null  || configurations.getUrl().isEmpty()  ||
                     configurations.getName()== null || configurations.getName().isEmpty() ||
                    configurations.getType() == null  || configurations.getName().isEmpty()){
                logger.info("Cannot deploy service.. Check whether port, name, url, id and type fields are available");
            } else {
                new MicroservicesRunner()
                        .addGlobalRequestInterceptor(new UsernamePasswordSecurityInterceptor())
                        .deploy(new ServiceDependencyManager(configurations))

                        .start();

                logger.info("Service for Repository " + configurations.getName() +" Deployed Successfully");
            }
        }catch (Exception e){
            logger.info("Failed to deploy service:",e);
        }
    }
}
