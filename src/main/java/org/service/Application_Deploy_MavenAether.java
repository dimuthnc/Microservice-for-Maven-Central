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

import org.configFileReader.configurationReader;
import org.configFileReader.configFilePOJO;

import java.util.logging.Logger;
import org.wso2.msf4j.MicroservicesRunner;

/**
 * Application_Deploy_MavenAether entry point.
 *
 * @since 1.0-SNAPSHOT
 */
public class Application_Deploy_MavenAether {

    private final static Logger LOGGERMAIN = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args) {

        try{
            configurationReader configs = new configurationReader();
            configFilePOJO configurations = configs.getConfigurations();

            new MicroservicesRunner(configurations.getPort())
                    .deploy(new Service_DependencyManagerOfMaven(configurations))
                    .start();

            LOGGERMAIN.info("Service Deployed Successfully");

        }catch (Exception e){
            LOGGERMAIN.info("Failed to deploy service");
            LOGGERMAIN.info("Error:"+ e.getMessage());
        }
    }
}
