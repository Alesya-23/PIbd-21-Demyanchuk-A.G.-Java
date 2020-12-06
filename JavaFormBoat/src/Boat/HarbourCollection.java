package Boat;

import Logics.IAdditional;

import java.util.HashMap;

public class HarbourCollection {
    public HashMap<String, Harbour<Boat, IAdditional>> harbourStages;

    public String[] keys() {
        return harbourStages.keySet().toArray( new String[harbourStages.keySet().size()] );
    }

    private int pictureWidth;

    private int pictureHeight;

    public HarbourCollection(int pictureWidth, int pictureHeight) {
        harbourStages = new HashMap<String, Harbour<Boat, IAdditional>>();
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
    }

    public void AddHarbour(String name) {
        if (harbourStages.containsKey( name )) {
            return;
        }
        harbourStages.put( name, new Harbour<Boat, IAdditional>( pictureWidth, pictureHeight ) );
    }

    public void DelHarbour(String name) {
        if (harbourStages.containsKey( name )) {
            harbourStages.remove( name );
        }
    }

    public void DelHarbour(int index) {
        if (harbourStages.containsKey( index )) {
            harbourStages.remove( index );
        }
    }

    public Harbour<Boat, IAdditional> get(String ind) {
        if (harbourStages.containsKey( ind )) {
            return harbourStages.get( ind );
        }
        return null;
    }

    public Vehicle getIndex(String ind, int index) {
        if (!harbourStages.containsKey( ind )) {
            return null;
        }
        return harbourStages.get( ind ).get( index );
    }
}
