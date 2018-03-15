package org.serviceSupportClasses;

import org.configFileReader.ConfigFilePOJO;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.resolution.VersionRangeRequest;
import org.eclipse.aether.resolution.VersionRangeResult;
import org.eclipse.aether.version.Version;
import org.eclipse.util.Booter;

public class LatestVersion {

    //return the latest Version of the given Artifact
    public static String getVersion(String groupID, String artifactID, ConfigFilePOJO configurations) {

        RepositorySystem system = Booter.newRepositorySystem();

        RepositorySystemSession session = Booter.newRepositorySystemSession(system);

        String artifactRef = groupID + ":" + artifactID + ":[0,)";

        Artifact artifact = new DefaultArtifact(artifactRef);

        VersionRangeRequest rangeRequest = new VersionRangeRequest();
        rangeRequest.setArtifact(artifact);
        rangeRequest.setRepositories(Booter.newRepositories(system, session, configurations));

        String ans;
        ans = "{\"ErrorMsg\":\"NotFound\"}";
        try {
            VersionRangeResult rangeResult = system.resolveVersionRange(session, rangeRequest);

            Version newestVersion = rangeResult.getHighestVersion();

            ans = "{\"GroupID\":\"" + groupID + "\",\"ArtifactID\":\"" + artifactID + "\",";

            if (newestVersion != null) {
                ans = ans.concat("\"NewestVersion\":\"" + newestVersion.toString() + "\"}");
            } else {
                ans = ans.concat("\"ErrorMsg\":\"NotFound\"}");
            }

        } catch (Exception e) {

            ans = "{\"ErrorMsg\":\"" + e.getMessage() + "\"}";
            return ans;
        }

        return ans;
    }

}
