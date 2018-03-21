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

import JSONFormats.gaJSON;
import JSONFormats.gavJSON;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.configFileReader.ConfigFilePOJO;
import org.serviceSupportClasses.AvailableVersions;
import org.serviceSupportClasses.DependencyHeirarchy;
import org.serviceSupportClasses.LatestVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This is the Micro-service resource class.
 * See <a href="https://github.com/wso2/msf4j#getting-started">https://github.com/wso2/msf4j#getting-started</a>
 * for the usage of annotations.
 *
 * @since 1.0-SNAPSHOT
 */
@Path("/aethermicroservice")
public class ServiceDependencyManager {

    private final static Logger logger = LoggerFactory.getLogger(ServiceDependencyManager.class);
    private ConfigFilePOJO configurations;

    public ServiceDependencyManager(ConfigFilePOJO configs) {

        this.configurations = configs;
    }

    @POST
    @Path("/getLatest")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLatestVersion(gaJSON jsonObj) {

        logger.info("Finding Latest version for library of group ID:{} and artifact ID:{}"
                , jsonObj.groupID, jsonObj.artifactID);

        long startTime = System.currentTimeMillis();
        String latestVersion = LatestVersion.getVersion(jsonObj.groupID, jsonObj.artifactID, configurations);

        JsonObject reply;
        if (latestVersion.contains("ErrorMsg")) {

            logger.info("Could not Find");
            return Response.status(Response.Status.NOT_FOUND).entity("NotFound").build();
        } else {

            logger.info("Finished retrieving latest version");
            JsonParser parser = new JsonParser();
            reply = parser.parse(latestVersion).getAsJsonObject();
        }
        long endTime = System.currentTimeMillis();
        logger.info("Time taken:{}", (endTime - startTime));
        return Response.ok(reply, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("/getVersions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvailableVersions(gaJSON jsonObj) {

        logger.info("Finding Latest version for library of group ID:{} and artifact ID:{}"
                , jsonObj.groupID, jsonObj.artifactID);

        long startTime = System.currentTimeMillis();
        StringBuilder versionsAvailable = AvailableVersions.getAvailableVersions(jsonObj.groupID, jsonObj.artifactID, configurations);
        String availableVersions = versionsAvailable.toString();
        JsonObject reply;

        if (availableVersions.contains("ErrorMsg")) {
            logger.info("Could not Find");
            return Response.status(Response.Status.NOT_FOUND).entity("NotFound").build();
        } else {

            logger.info("Finished retrieving available versions");
            JsonParser parser = new JsonParser();
            reply = parser.parse(availableVersions).getAsJsonObject();
        }
        long endTime = System.currentTimeMillis();
        logger.info("Time taken:{}", (endTime - startTime));
        return Response.ok(reply, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("/getDHierarchy")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHierarchy(gavJSON jsonObj) {

        logger.info("Finding hierarchy for library of group ID:{} and artifact ID:{} and version: {}"
                , jsonObj.groupID, jsonObj.artifactID, jsonObj.version);

        long startTime = System.currentTimeMillis();
        StringBuilder JsonStringBuilderTree = DependencyHeirarchy.getDependencyHeirarchy(jsonObj.groupID, jsonObj.artifactID, jsonObj.version, configurations);
        String JsonStringTree = JsonStringBuilderTree.toString();

        JsonObject reply;

        if (JsonStringTree.contains("Error")) {
            logger.info("Could not Find");
            return Response.status(Response.Status.NOT_FOUND).entity("NotFound").build();
        } else {
            logger.info("Finished retrieving hierarchy");
            JsonParser parser = new JsonParser();
            reply = parser.parse(JsonStringBuilderTree.deleteCharAt(JsonStringBuilderTree.length() - 1).toString()).getAsJsonObject();

        }
        long endTime = System.currentTimeMillis();
        logger.info("Time taken:" + (endTime - startTime));
        return Response.ok(reply, MediaType.APPLICATION_JSON).build();
    }
}

