/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib.interfaz;

import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import java.io.File;
import java.io.FileNotFoundException;
/**
 *
 * @author mdiazoli
 */
public class SampleDataBase {
    private Environment env;
    private static final String CLASS_CATALOG = "java_class_catalog";

    private StoredClassCatalog javaCatalog;
    
    private static final String JUGADOR_STORE= "jugador_store";
    private static final String PARTIDA_STORE= "partida_store";
    
    private Database jugadorDb;
    private Database partidaDb;

    public SampleDataBase(String homeDirectory) throws DatabaseException, FileNotFoundException {
        System.out.println("Opening enviroment in:" + homeDirectory);

        EnvironmentConfig envConfig = new EnvironmentConfig();
        envConfig.setTransactional(true);
        envConfig.setAllowCreate(true);

        DatabaseConfig dbConfig = new DatabaseConfig();
        dbConfig.setTransactional(true);
        dbConfig.setAllowCreate(true);

        jugadorDb = env.openDatabase(null, JUGADOR_STORE, dbConfig);
        partidaDb = env.openDatabase(null, PARTIDA_STORE, dbConfig);

        Database catalogDb = env.openDatabase(null, CLASS_CATALOG, dbConfig);

        javaCatalog = new StoredClassCatalog(catalogDb);


        env = new Environment(new File(homeDirectory), envConfig);
    }

    public void close() throws DatabaseException {

        jugadorDb.close();
        partidaDb.close();
        javaCatalog.close();
        env.close();
    }

    public final Environment getEnvironment(){
        return env;
    }

    public final StoredClassCatalog getClassCatalog(){
        return javaCatalog;
    }

    public final Database getJugadorDatabase(){
        return jugadorDb;
    }

    public final Database getPartidaDatabase(){
        return partidaDb;
    }
}
