/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib.interfaz;

import com.googlecode.jbridges.lib.Jugador;
import com.googlecode.jbridges.lib.PartidaGuardadaData;
import com.googlecode.jbridges.lib.PartidaGuardadaKey;
import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.ClassCatalog;
import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.collections.StoredEntrySet;
import com.sleepycat.collections.StoredMap;
/**
 *
 * @author mdiazoli
 */
public class SampleViews {

    private StoredMap jugadorMap;
    private StoredMap partidaMap;

    public SampleViews(SampleDataBase db){

        ClassCatalog catalog = db.getClassCatalog();

        EntryBinding jugadorKeyBinding = new SerialBinding(catalog, Jugador.class);
        EntryBinding jugadorValueBinding = new SerialBinding(catalog, Jugador.class);

        EntryBinding partidaGuardadaKeyBinding = new SerialBinding(catalog, PartidaGuardadaKey.class);
        EntryBinding partidaGuardadaValueBinding = new SerialBinding(catalog, PartidaGuardadaData.class);

        jugadorMap = new StoredMap(db.getJugadorDatabase(), jugadorKeyBinding, jugadorValueBinding, true);
        partidaMap = new StoredMap(db.getJugadorDatabase(), partidaGuardadaKeyBinding, partidaGuardadaValueBinding, true);
    }

    public final StoredMap getJugadorMap(){
        return jugadorMap;
    }

    public final StoredEntrySet getJugadorEntrySet(){
        return (StoredEntrySet) jugadorMap.entrySet();
    }

    public final StoredMap getPartidaMap(){
        return partidaMap;
    }

    public final StoredEntrySet getPartidaEntrySet(){
        return (StoredEntrySet) partidaMap.entrySet();
    }
}
