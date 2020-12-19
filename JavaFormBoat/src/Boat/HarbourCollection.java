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

    /// Разделитель для записи информации в файл
    private char separator = ':';

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

    public boolean SaveData(String filename) throws IOException {
        FileWriter fs = new FileWriter( filename );
        fs.write( "HarbourCollection" + "\n" );
        for (String level : harbourStages.keySet()) {
            //Начинаем парковку
            fs.write( "Harbour" + separator + level + "\n" );
            Boat boat = null;
            for (int i = 0; (boat = harbourStages.get( level ).get( i )) != null; i++) {
                if (boat != null) {
                    //если место не пустое
                    //Записываем тип лодки
                    if (boat.getClass().toString().equals( "class Boat.Boat" )) {
                        fs.write( "Boat" + separator );
                    }
                    if (boat.getClass().toString().equals( "class Boat.MotorBoat" )) {
                        fs.write( "MotorBoat" + separator );
                    }
                    //Записываемые параметры
                    fs.write( boat.ToString() + "\n" );
                }
            }
        }
        fs.close();
        return true;
    }

    /// Загрузка нформации по лодкам на гаванях из файла
    public boolean LoadData(String filename) throws IOException {
        FileReader fs = new FileReader( filename );
        BufferedReader reader = new BufferedReader( fs );
        String strs = reader.readLine();
        if (!(strs.contains( "HarbourCollection" ))) {
            throw new IllegalArgumentException( "Неверный формат файла" );
        } else {
            Boat boat = null;
            String key = "";
            while ((strs = reader.readLine()) != null) {
                //идем по считанным записям
                if (strs.contains( "Harbour" )) {
                    key = strs.split( String.valueOf( separator ) )[1];
                    harbourStages.put( key, new Harbour<Boat, IAdditional>( pictureWidth, pictureHeight ) );
                } else if (strs.contains( String.valueOf( separator ) )) {
                    if (strs.contains( "Boat" )) {
                        boat = new Boat( strs.split( String.valueOf( separator ) )[1] );
                    }
                    if (strs.contains( "MotorBoat" )) {
                        boat = new MotorBoat( strs.split( String.valueOf( separator ) )[1] );
                    }
                    if (!(harbourStages.get( key ).add( boat ))) {
                        return false;
                    }
                }
            }
            fs.close();
            return true;
        }
    }

    public boolean saveSeparateParking(String filename, String name) throws IOException {
        if (harbourStages.containsKey( name )) {
            FileWriter fw = new FileWriter( filename );
            fw.write( "HarbourCollection\n" );
            Harbour<Boat, IAdditional> level = harbourStages.get( name );
            fw.write( "Harbour" + separator + name + "\n" );
            Boat boat = null;
            for (int i = 0; (boat = level.get( i )) != null; i++) {
                if (boat.getClass().toString().equals( "class Boat.Boat" )) {
                    fw.write( "Boat" + separator );
                }
                if (boat.getClass().toString().equals( "class Boat.MotorBoat" )) {
                    fw.write( "MotorBoat" + separator );
                }
                fw.write( boat.ToString() + "\n" );
            }
            fw.close();
            return true;
        }
        return false;
    }

    public boolean loadSeparateParking(String filename) throws IOException {
        FileReader fr = new FileReader( filename );
        BufferedReader reader = new BufferedReader( fr );
        String str = reader.readLine();
        String key = "";
        if (str.contains( "HarbourCollection" )) {
            Boat boat = null;
            while ((str = reader.readLine()) != null) {
                if (str.contains( "Harbour" )) {
                    key = str.split( String.valueOf( separator ) )[1];
                    if (harbourStages.containsKey( key )) {
                        harbourStages.get( key ).clear();
                    } else {
                        harbourStages.put( key, new Harbour<Boat, IAdditional>( pictureWidth, pictureHeight ) );
                    }
                    str = reader.readLine();
                }
                if (str.contains( "Boat" )) {
                    boat = new Boat( str.split( String.valueOf( separator ) )[1] );
                }
                if (str.contains( "MotorBoat" )) {
                    boat = new MotorBoat( str.split( String.valueOf( separator ) )[1] );
                }
                var result = harbourStages.get( key ).add( boat );
                if (!result) {
                    return false;
                }
            }
            fr.close();
            return true;
        }
        return false;
    }
}
