package org.eclipse.util;

import org.apache.maven.repository.internal.MavenRepositorySystemUtils;
import org.configFileReader.configurationReader;
import org.eclipse.aether.DefaultRepositorySystemSession;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.repository.LocalRepository;
import org.eclipse.aether.repository.RemoteRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.InputStream;

import org.eclipse.configPOJOS.RemoteRepo;
import org.configFileReader.configFilePOJO;
import org.yaml.snakeyaml.Yaml;


/**
 * A helper to boot the repository system and a repository system session.
 */
public class Booter
{

    public static RepositorySystem newRepositorySystem()
    {
        return org.eclipse.manual.ManualRepositorySystemFactory.newRepositorySystem();
    }

    public static DefaultRepositorySystemSession newRepositorySystemSession(RepositorySystem system )
    {
        DefaultRepositorySystemSession session = MavenRepositorySystemUtils.newSession();

        LocalRepository localRepo = new LocalRepository( "target/local-repo" );
        session.setLocalRepositoryManager( system.newLocalRepositoryManager( session, localRepo ) );

        session.setTransferListener( new ConsoleTransferListener() );
        session.setRepositoryListener( new ConsoleRepositoryListener() );

        // uncomment to generate dirty trees
        // session.setDependencyGraphTransformer( null );

        return session;
    }

    public static List<RemoteRepository> newRepositories(RepositorySystem system, RepositorySystemSession session, configFilePOJO remoteRepoInfo)
    {
        return new ArrayList<RemoteRepository>( Arrays.asList( newCentralRepository(remoteRepoInfo) ) );
    }

    private static RemoteRepository newCentralRepository(configFilePOJO remoteRepoInfo)
    {
       //return new RemoteRepository.Builder( "central", "default", "http://dist.wso2.org/maven2/" ).build();
        String url;
        String id;
        String type;
//        RemoteRepo config = null;

//        if(remoteRepoInfo.getUrl() != ""){
            url = remoteRepoInfo.getUrl();
            id = remoteRepoInfo.getId();
            type = remoteRepoInfo.getType();

            return new RemoteRepository.Builder( id, type, url ).build();
//        }

//        Yaml yaml = new Yaml();
//        try(InputStream in = ClassLoader.getSystemResourceAsStream("repoConfiguration.yml")) {
//            config = yaml.loadAs(in, RemoteRepo.class);
//        } catch(Exception ex) {
//            ex.printStackTrace();
//        }
//
//        url =  config.getremoteRepository().get("url");
//        id =  config.getremoteRepository().get("id");
//        type =  config.getremoteRepository().get("type");
//
//        return new RemoteRepository.Builder( id, type, url ).build();
    }

}