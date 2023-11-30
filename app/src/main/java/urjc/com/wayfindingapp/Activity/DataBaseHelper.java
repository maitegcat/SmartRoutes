package urjc.com.wayfindingapp.Activity;

import static android.content.ContentValues.TAG;
import static urjc.com.wayfindingapp.Activity.IndoorScan.dataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import urjc.com.wayfindingapp.R;

public class DataBaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    String temporal = "CREATE TABLE Temporal (Nombre TEXT, distancia DOUBLE)";

    String Baliza = "CREATE TABLE Baliza (IDbaliza INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT NOT NULL,  Coord_x INTEGER NOT NULL, Coord_y INTEGER NOT NULL,Codletra TEXT NOT NULL, IDlugar INTEGER )";
    String Lugar = "CREATE TABLE Lugar (IDLugar INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT NOT NULL, Descripcion TEXT NOT NULL, Coordenada_x INTEGER NOT NULL, Coordenada_y INTEGER NOT NULL, imagen TEXT,IDtype_place INTEGER, IDlugares_prox INTEGER)";
    String Typeplace = "CREATE TABLE Typeplace (IDTypeplace INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT NOT NULL,DESCRIPCION TEXT NOT NULL)";
    String Dis_baliza = "CREATE TABLE Dis_baliza (IDdistancia_baliza INTEGER PRIMARY KEY AUTOINCREMENT,Value TEXT NOT NULL,Baliza_a INTEGER NOT NULL,Baliza_b INTEGER NOT NULL, Baliza_c INTEGER NOT NULL)";
    String Lugares_prox = "CREATE TABLE Lugares_prox (IDLugares_prox INTEGER PRIMARY KEY AUTOINCREMENT,Lugar_prox INTEGER NOT NULL, Lugar_prin INTEGER NOT NULL)";
    String User = "CREATE TABLE User (IDUser INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT NOT NULL,Password TEXT NOT NULL)";

    /*
    constructor de la clase
    */
    DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory
            factory, int version) {
        super(context, name, factory, version);
    }

    /*
    Metodo que crea la base de datos
    */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(temporal);
        db.execSQL("DROP TABLE IF EXISTS Baliza");
        db.execSQL("DROP TABLE IF EXISTS Lugar");
        db.execSQL("DROP TABLE IF EXISTS Dis_baliza");
        db.execSQL("DROP TABLE IF EXISTS Typeplace");
        db.execSQL("DROP TABLE IF EXISTS Lugares_prox");
        db.execSQL("DROP TABLE IF EXISTS User");

        db.execSQL(Baliza);
        db.execSQL(Lugar);
        db.execSQL(Typeplace);
        db.execSQL(Dis_baliza);
        db.execSQL(Lugares_prox);
        db.execSQL(User);

        //Tabla Balizas mis Balizas Kontakt
        db.execSQL("INSERT INTO Baliza (IDbaliza , Name, Coord_x, Coord_y, Codletra,IDlugar) VALUES(?,'Kontakt DH3v 1', 0, 0,'A',1)");
        db.execSQL("INSERT INTO Baliza (IDbaliza , Name, Coord_x, Coord_y, Codletra,IDlugar) VALUES(?,'Kontakt OLtx 2', 5, 12,'B',2)");
        db.execSQL("INSERT INTO Baliza (IDbaliza , Name, Coord_x, Coord_y, Codletra,IDlugar) VALUES(?,'Kontakt Pu2z 3', 13, 0,'C',3)");

        //Tabla Balizas Kontakt sin incluir el nombre del fabricante y el numero de baliza para pruebas en URJC se debe ncluir numero de la baliza para posicionarla segun plano
        db.execSQL("INSERT INTO Baliza (IDbaliza , Name, Coord_x, Coord_y, Codletra,IDlugar) VALUES(?,'Kontakt 61Lf 1', 0, 0,'A',4)");
        db.execSQL("INSERT INTO Baliza (IDbaliza , Name, Coord_x, Coord_y, Codletra,IDlugar) VALUES(?,'Kontakt 9ctc 2', 39, 34,'B',5)");
        db.execSQL("INSERT INTO Baliza (IDbaliza , Name, Coord_x, Coord_y, Codletra,IDlugar) VALUES(?,'Kontakt DCpM 3', 57, 0,'C',6)");

        db.execSQL("INSERT INTO Baliza (IDbaliza , Name, Coord_x, Coord_y, Codletra,IDlugar) VALUES(?,'Kontakt KDHT 4', 13, 0,'C',7)");
        db.execSQL("INSERT INTO Baliza (IDbaliza , Name, Coord_x, Coord_y, Codletra,IDlugar) VALUES(?,'Kontakt MNpi 5', 5, 12,'B',8)");

        //Tablas Balizas Kontakt sin incluir el nombre del fabricante y el numero de baliza resto de balizas hasta 11 para pruebas en FJ23 hay que incluir numero de la baliza para posicionarla segun plano

        db.execSQL("INSERT INTO Baliza (IDbaliza , Name, Coord_x, Coord_y, Codletra,IDlugar) VALUES(?,'Kontakt axuY 6', 0, 10,'B',9)");
        db.execSQL("INSERT INTO Baliza (IDbaliza , Name, Coord_x, Coord_y, Codletra,IDlugar) VALUES(?,'Kontakt ilky 7', 11, 33,'A',10)");
        db.execSQL("INSERT INTO Baliza (IDbaliza , Name, Coord_x, Coord_y, Codletra,IDlugar) VALUES(?,'Kontakt syEJ 8', 10, 0,'C',11)");

        db.execSQL("INSERT INTO Baliza (IDbaliza , Name, Coord_x, Coord_y, Codletra,IDlugar) VALUES(?,'Kontakt tn25 9', 11, 35,'A',12)");
        db.execSQL("INSERT INTO Baliza (IDbaliza , Name, Coord_x, Coord_y, Codletra,IDlugar) VALUES(?,'Kontakt x66C 10', 10, 0,'B',13)");

        //Tabla Balizas Kontakt sin incluir el nombre del fabricante y el numero de baliza para pruebas en FJ23 del grupo de 13 balizas e-mail del 21/9/2023 Marcos
        db.execSQL("INSERT INTO Baliza (IDbaliza , Name, Coord_x, Coord_y, Codletra,IDlugar) VALUES(?,'Kontakt HuF59h 11', 0, 10,'C',14)");
        db.execSQL("INSERT INTO Baliza (IDbaliza , Name, Coord_x, Coord_y, Codletra,IDlugar) VALUES(?,'Kontakt HuFc2H 12', 0, 10,'C',15)");


        /*******************************************************************************************/
        //Tabla lugar planos Casa
        db.execSQL("INSERT INTO Lugar (IDLugar , Name, Descripcion, Coordenada_x, Coordenada_y, imagen,IDtype_place,IDlugares_prox ) VALUES(?,'CASA','BALIZA DH3v 1', 0, 0, " + R.drawable.piso1 + ", 1,1)");
        db.execSQL("INSERT INTO Lugar (IDLugar , Name, Descripcion, Coordenada_x, Coordenada_y, imagen,IDtype_place,IDlugares_prox ) VALUES(?,'CASA','BALIZA OLtx 2', 0, 0, " + R.drawable.piso1 + ", 2,2)");
        db.execSQL("INSERT INTO Lugar (IDLugar , Name, Descripcion, Coordenada_x, Coordenada_y, imagen,IDtype_place,IDlugares_prox ) VALUES(?,'CASA','BALIZA Pu2z 3', 0, 0," + R.drawable.piso1 + ", 3,3)");

        /*******************************************************************************************/
        //Tabla lugar planos URJC laboratorio III
        db.execSQL("INSERT INTO Lugar (IDLugar , Name, Descripcion, Coordenada_x, Coordenada_y, imagen,IDtype_place,IDlugares_prox ) VALUES(?,'ACCESO','Vertibulo', 0, 0, " + R.drawable.urjcplanopasillo + ", 4,4)");
        db.execSQL("INSERT INTO Lugar (IDLugar , Name, Descripcion, Coordenada_x, Coordenada_y, imagen,IDtype_place,IDlugares_prox ) VALUES(?,'ACCESO','Vertibulo', 0, 0, " + R.drawable.urjcplanopasillo + ", 5,5)");
        db.execSQL("INSERT INTO Lugar (IDLugar , Name, Descripcion, Coordenada_x, Coordenada_y, imagen,IDtype_place,IDlugares_prox ) VALUES(?,'ACESSO','Vertibulo', 0, 0," + R.drawable.urjcplanopasillo + ", 6,6)");
        db.execSQL("INSERT INTO Lugar (IDLugar , Name, Descripcion, Coordenada_x, Coordenada_y, imagen,IDtype_place,IDlugares_prox ) VALUES(?,'LOCAL 3','Local 3 P6', 0, 0, " + R.drawable.urjcplanol3 + ", 7,7)");
        db.execSQL("INSERT INTO Lugar (IDLugar , Name, Descripcion, Coordenada_x, Coordenada_y, imagen,IDtype_place,IDlugares_prox ) VALUES(?,'LOCAL 4','Local 4 P6', 0, 0, " + R.drawable.urjcplanol4 + ", 8,8)");

        //Tabla lugar planos FJ23
        db.execSQL("INSERT INTO Lugar (IDLugar , Name, Descripcion, Coordenada_x, Coordenada_y, imagen,IDtype_place,IDlugares_prox ) VALUES(?,'SALA1','Sala de estimulacion cognitiva', 0, 0, " + R.drawable.fundjuan1b1 + ", 9,9)");
        db.execSQL("INSERT INTO Lugar (IDLugar , Name, Descripcion, Coordenada_x, Coordenada_y, imagen,IDtype_place,IDlugares_prox ) VALUES(?,'SALA2','Sala de estimulacion cognitiva', 0, 0, " + R.drawable.fundjuan1b2 + ", 10,10)");
        db.execSQL("INSERT INTO Lugar (IDLugar , Name, Descripcion, Coordenada_x, Coordenada_y, imagen,IDtype_place,IDlugares_prox ) VALUES(?,'SALA3','Sala pequeña', 0, 0," + R.drawable.fundjuan1b3 + ", 11,11)");
        db.execSQL("INSERT INTO Lugar (IDLugar , Name, Descripcion, Coordenada_x, Coordenada_y, imagen,IDtype_place,IDlugares_prox ) VALUES(?,'SALA4','Sala grupal', 0, 0, " + R.drawable.fundjuan1b4 + ", 12,12)");
        db.execSQL("INSERT INTO Lugar (IDLugar , Name, Descripcion, Coordenada_x, Coordenada_y, imagen,IDtype_place,IDlugares_prox ) VALUES(?,'SALA5','Sala grupal', 0, 0,  " + R.drawable.fundjuan1b5 + ", 13,13)");
        db.execSQL("INSERT INTO Lugar (IDLugar , Name, Descripcion, Coordenada_x, Coordenada_y, imagen,IDtype_place,IDlugares_prox ) VALUES(?,'ACCESO','Acceso planta 1', 0, 0,  " + R.drawable.fundjuan1b6 + ", 14,14)");
        db.execSQL("INSERT INTO Lugar (IDLugar , Name, Descripcion, Coordenada_x, Coordenada_y, imagen,IDtype_place,IDlugares_prox ) VALUES(?,'ACCESO','Acceso planta 1', 0, 0,  " + R.drawable.fundjuan1b6 + ", 15,15)");
        db.execSQL("INSERT INTO Lugar (IDLugar , Name, Descripcion, Coordenada_x, Coordenada_y, imagen,IDtype_place,IDlugares_prox ) VALUES(?,'ACCESO','Acceso planta 1', 0, 0,  " + R.drawable.fundjuan1b6 + ", 16,16)");
        db.execSQL("INSERT INTO Lugar (IDLugar , Name, Descripcion, Coordenada_x, Coordenada_y, imagen,IDtype_place,IDlugares_prox ) VALUES(?,'ACCESO','Acceso planta 2', 0, 0,  " + R.drawable.fundjuan2b9 + ", 17,17)");
        db.execSQL("INSERT INTO Lugar (IDLugar , Name, Descripcion, Coordenada_x, Coordenada_y, imagen,IDtype_place,IDlugares_prox ) VALUES(?,'ACCESO','Acceso planta 2', 0, 0,  " + R.drawable.fundjuan2b9 + ", 18,18)");
        db.execSQL("INSERT INTO Lugar (IDLugar , Name, Descripcion, Coordenada_x, Coordenada_y, imagen,IDtype_place,IDlugares_prox ) VALUES(?,'ACCESO','Acceso planta 2', 0, 0,  " + R.drawable.fundjuan2b9 + ", 19,19)");
        db.execSQL("INSERT INTO Lugar (IDLugar , Name, Descripcion, Coordenada_x, Coordenada_y, imagen,IDtype_place,IDlugares_prox ) VALUES(?,'SALA6','Sala grupal planta 2', 0, 0,  " + R.drawable.fundjuan2b12 + ", 20,20)");

        /*******************************************************************************************/
        //Tabla Tipo de lugar
        db.execSQL("INSERT INTO Typeplace (IDTypeplace , Name, Descripcion ) VALUES(?,'SALA1','Sala de estimulacion cognitiva')");
        db.execSQL("INSERT INTO Typeplace (IDTypeplace , Name, Descripcion ) VALUES(?,'SALA2','Sala de estimulacion cognitiva')");
        db.execSQL("INSERT INTO Typeplace (IDTypeplace , Name, Descripcion ) VALUES(?,'SALA3','Sala pequeña')");
        db.execSQL("INSERT INTO Typeplace (IDTypeplace , Name, Descripcion ) VALUES(?,'SALA4','Sala grupal')");
        db.execSQL("INSERT INTO Typeplace (IDTypeplace , Name, Descripcion ) VALUES(?,'SALA5','Sala grupal')");
        db.execSQL("INSERT INTO Typeplace (IDTypeplace , Name, Descripcion ) VALUES(?,'ACCESO','Acceso planta 1')");
        db.execSQL("INSERT INTO Typeplace (IDTypeplace , Name, Descripcion ) VALUES(?,'ACCESO','Acceso planta 1')");
        db.execSQL("INSERT INTO Typeplace (IDTypeplace , Name, Descripcion ) VALUES(?,'ACCESO','Acceso planta 1')");
        db.execSQL("INSERT INTO Typeplace (IDTypeplace , Name, Descripcion ) VALUES(?,'ACCESO','Acceso planta 2')");
        db.execSQL("INSERT INTO Typeplace (IDTypeplace , Name, Descripcion ) VALUES(?,'ACCESO','Acceso planta 2')");
        db.execSQL("INSERT INTO Typeplace (IDTypeplace , Name, Descripcion ) VALUES(?,'ACCESO','Acceso planta 2')");
        db.execSQL("INSERT INTO Typeplace (IDTypeplace , Name, Descripcion ) VALUES(?,'SALA6','Sala grupal planta 2')");

        /*******************************************************************************************/
        db.execSQL("INSERT INTO Dis_baliza (IDdistancia_baliza , Value, Baliza_a, Baliza_b, Baliza_c) VALUES(?,4.0,1,2,0)");
        db.execSQL("INSERT INTO Dis_baliza (IDdistancia_baliza , Value, Baliza_a, Baliza_b, Baliza_c) VALUES(?,8.0,1,3,0)");
        db.execSQL("INSERT INTO Dis_baliza (IDdistancia_baliza , Value, Baliza_a, Baliza_b, Baliza_c) VALUES(?,4.0,2,1,0)");
        db.execSQL("INSERT INTO Dis_baliza (IDdistancia_baliza , Value, Baliza_a, Baliza_b, Baliza_c) VALUES(?,8.0,2,3,0)");
        db.execSQL("INSERT INTO Dis_baliza (IDdistancia_baliza , Value, Baliza_a, Baliza_b, Baliza_c) VALUES(?,4.0,3,1,0)");
        db.execSQL("INSERT INTO Dis_baliza (IDdistancia_baliza , Value, Baliza_a, Baliza_b, Baliza_c) VALUES(?,8.0,3,2,0)");
        db.execSQL("INSERT INTO Dis_baliza (IDdistancia_baliza , Value, Baliza_a, Baliza_b, Baliza_c) VALUES(?,4.0,3,1,0)");
        db.execSQL("INSERT INTO Dis_baliza (IDdistancia_baliza , Value, Baliza_a, Baliza_b, Baliza_c) VALUES(?,8.0,3,2,0)");

        /*******************************************************************************************/
        db.execSQL("INSERT INTO Lugares_prox (IDLugares_prox,Lugar_prox, Lugar_prin) VALUES(?,1,1)");
        db.execSQL("INSERT INTO Lugares_prox (IDLugares_prox,Lugar_prox, Lugar_prin) VALUES(?,2,2)");
        db.execSQL("INSERT INTO Lugares_prox (IDLugares_prox,Lugar_prox, Lugar_prin) VALUES(?,3,3)");
        db.execSQL("INSERT INTO Lugares_prox (IDLugares_prox,Lugar_prox, Lugar_prin) VALUES(?,4,4)");
        db.execSQL("INSERT INTO Lugares_prox (IDLugares_prox,Lugar_prox, Lugar_prin) VALUES(?,5,5)");
        db.execSQL("INSERT INTO Lugares_prox (IDLugares_prox,Lugar_prox, Lugar_prin) VALUES(?,6,6)");
        db.execSQL("INSERT INTO Lugares_prox (IDLugares_prox,Lugar_prox, Lugar_prin) VALUES(?,7,7)");
        db.execSQL("INSERT INTO Lugares_prox (IDLugares_prox,Lugar_prox, Lugar_prin) VALUES(?,8,8)");

        /*******************************************************************************************/
        db.execSQL("INSERT INTO User (IDUser,Name,Password) VALUES(?,'admin','123')");

        System.out.println("HA CARGADO");


    }

    /**
     * Método que devuelve los nombres de todos las balizas
     */
    public String[] ObtenerNombresBaliza() {
        String[] nombres = null;
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        try {
            Cursor cursor = db.rawQuery("SELECT name FROM Baliza", null);

            if (cursor != null) {
                int count = cursor.getCount();
                nombres = new String[count];

                int i = 0;
                while (cursor.moveToNext()) {
                    nombres[i] = cursor.getString(cursor.getColumnIndex("name"));
                    i++;
                }

                cursor.close();
            }
        } catch (SQLException e) {
            Log.e(TAG, "Error al obtener nombres de balizas: " + e.getMessage());
        } finally {
            db.close();
        }

        return nombres;
    }

    /**
     * Método que obtiene el nombre de un lugar en especifico en base a su id
     */
    public String ObtenerLugar(String cod) {
        String res = null;
        String sql = "SELECT name FROM lugar WHERE idlugar = ?";
        try {
            // Utilizamos una consulta parametrizada para evitar problemas de inyección SQL
            Cursor cursor = db.rawQuery(sql, new String[]{cod});
            if (cursor != null && cursor.moveToFirst()) {
                res = cursor.getString(cursor.getColumnIndex("name"));
            }
            // Cerramos el cursor para liberar recursos
            if (cursor != null) {
                cursor.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    /**
     * Método que obtiene el codigo de una baliza en base a su nombre
     */
    public String ObtenerCodLugar(String baliza) {
        String res = "";
        String sql = "SELECT idlugar FROM baliza WHERE name = ?";
        String[] parametros = {baliza};

        try {
            // Utilizamos una consulta parametrizada para evitar problemas de inyección SQL
            Cursor cursor = db.rawQuery(sql, parametros);
            if (cursor != null && cursor.moveToFirst()) {
                res = cursor.getString(cursor.getColumnIndex("idlugar"));
            }
            // Cerramos el cursor para liberar recursos
            if (cursor != null) {
                cursor.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }

    /**
     * Método que obtiene las balizas asociados a un pasillo en especifico
     */
    public String[] ObtenerBeaconsPasillo(String lugar) {
        String[] res = new String[3];
        int cont = 0;
        String sql = "SELECT name FROM baliza WHERE idlugar = ? ORDER BY codletra ASC";
        String[] parametros = {lugar};

        try {
            // Utilizamos una consulta parametrizada para evitar problemas de inyección SQL
            Cursor cursor = db.rawQuery(sql, parametros);
            while (cursor != null && cursor.moveToNext() && cont < 3) {
                res[cont] = cursor.getString(cursor.getColumnIndex("name"));
                cont++;
            }
            // Cerramos el cursor para liberar recursos
            if (cursor != null) {
                cursor.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }

    /**
     * Método que obtiene el literal que identifica a una baliza en especifico
     */
    public String ObtenerLiteral(String baliza) {
        String res = null;
        String sql = "SELECT codletra FROM baliza WHERE name = ?";
        String[] parametros = {baliza};

        try {
            // Utilizamos una consulta parametrizada para evitar problemas de inyección SQL
            Cursor cursor = db.rawQuery(sql, parametros);
            if (cursor != null && cursor.moveToFirst()) {
                res = cursor.getString(cursor.getColumnIndex("codletra"));
            }
            // Cerramos el cursor para liberar recursos
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(DataBaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }

    /**
     * Método que obtene las coordenadas reales de una baliza
     */
    public String ObtenerCoordendadas(String baliza) {
        String res = null;
        String sql = "SELECT coord_x, coord_y FROM baliza WHERE name = ?";
        String[] parametros = {baliza};

        try {
            // Utilizamos una consulta parametrizada para evitar problemas de inyección SQL
            Cursor cursor = db.rawQuery(sql, parametros);
            if (cursor != null && cursor.moveToFirst()) {
                String coord_x = cursor.getString(cursor.getColumnIndex("coord_x"));
                String coord_y = cursor.getString(cursor.getColumnIndex("coord_y"));
                res = coord_x + ";" + coord_y;
            }
            // Cerramos el cursor para liberar recursos
            if (cursor != null) {
                cursor.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }

    /**
     * Método que obtiene la ruta de donde esta almacenada una imagen dentro del servidor
     * en base al lugar al que pertence
     */

    public String ObtenerImagen(String lugar) {
        String res = null;
        String sql = "SELECT imagen FROM lugar WHERE idlugar = ?";
        String[] parametros = {lugar};

        try {
            // Utilizamos una consulta parametrizada para evitar problemas de inyección SQL
            Cursor cursor = db.rawQuery(sql, parametros);
            if (cursor != null && cursor.moveToFirst()) {
                res = cursor.getString(cursor.getColumnIndex("imagen")).trim();
            }
            // Cerramos el cursor para liberar recursos
            if (cursor != null) {
                cursor.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }

    /**
     * Método que obtiene la descripcion de un pasillo
     */
    public String ObtenerDescrpPasillo(String pasillo) {
        String res = null;
        String sql = "SELECT t.descripcion " +
                "FROM typeplace t " +
                "INNER JOIN lugar l ON l.idlugar = t.idtypeplace " +
                "WHERE l.idlugar = ?";
        String[] parametros = {pasillo};

        try {
            // Utilizamos una consulta parametrizada para evitar problemas de inyección SQL
            Cursor cursor = db.rawQuery(sql, parametros);
            if (cursor != null && cursor.moveToFirst()) {
                res = cursor.getString(cursor.getColumnIndex("descripcion")).trim();
            }
            // Cerramos el cursor para liberar recursos
            if (cursor != null) {
                cursor.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }

    /**
     * Método que verifica si el codigo de un lugar es un pasillo
     */
    public boolean VerificarEsPasillo(String cod) {
        boolean res = false;
        String sql = "SELECT Name FROM lugar WHERE idlugar = ?";
        String[] parametros = {cod};

        try {
            // Utilizamos una consulta parametrizada para evitar problemas de inyección SQL
            Cursor cursor = db.rawQuery(sql, parametros);
            if (cursor != null && cursor.moveToFirst()) {
                String nombreLugar = cursor.getString(cursor.getColumnIndex("Name")).trim();
                res = nombreLugar.equals("PASILLO");
            }
            // Cerramos el cursor para liberar recursos
            if (cursor != null) {
                cursor.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }

    /**
     * Método que obtiene los lugares cernado de un pasillo
     */
    public ArrayList<String> ObtenerLugaresPasillo(String pasillo) {
        ArrayList<String> res = new ArrayList<>();

        String sql = "SELECT l.name, t.descripcion " +
                "FROM lugar l, typeplace t, lugares_prox c " +
                "WHERE c.lugar_prin = ? " +
                "AND l.idlugar = c.lugar_prox " +
                "AND l.idlugar = t.idtypeplace";
        String[] parametros = {pasillo};

        try {
            // Utilizamos una consulta parametrizada para evitar problemas de inyección SQL
            Cursor cursor = db.rawQuery(sql, parametros);
            while (cursor != null && cursor.moveToNext()) {
                String nombreLugar = cursor.getString(cursor.getColumnIndex("name")).trim();
                String descripcion = cursor.getString(cursor.getColumnIndex("descripcion")).trim();
                res.add(nombreLugar + ";" + descripcion);
            }
            // Cerramos el cursor para liberar recursos
            if (cursor != null) {
                cursor.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }

    /**
     * Método que obtiene la descripcion de un lugar
     */
    public ArrayList<String> ObtenerDescripcionLugar(String lugar) {
        ArrayList<String> res = new ArrayList<>();

        String sql = "SELECT l.name, f.name, l.descripcion " +
                "FROM lugar l, lugar f " +
                "WHERE l.idlugar = ? " +
                "AND l.idlugares_prox = f.idlugar";
        String[] parametros = {lugar};

        try {
            // Utilizamos una consulta parametrizada para evitar problemas de inyección SQL
            Cursor cursor = db.rawQuery(sql, parametros);
            while (cursor != null && cursor.moveToNext()) {
                String cadena[] = cursor.getString(cursor.getColumnIndex("descripcion")).split(";");
                String des = "";
                for (int i = 0; i < cadena.length; i++) {
                    des += cadena[i] + "\n";
                }
                res.add(cursor.getString(cursor.getColumnIndex("name")).trim() + ";" +
                        cursor.getString(cursor.getColumnIndex("name")).trim() + ";" +
                        des);
            }
            // Cerramos el cursor para liberar recursos
            if (cursor != null) {
                cursor.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }

    /**
     * Método que obtiene la distancia real entre dos beacons
     */
    public double ObtenerDistancia(String a, String b) {
        int cont = 0;
        int bA = 0, bB = 0;
        double res = 0;

        try {
            String sql = "SELECT idbaliza FROM baliza WHERE name = ? OR name = ?";
            String[] parametros = {a, b};

            // Buscamos los IDs de las balizas a y b utilizando una consulta parametrizada
            Cursor cursor = db.rawQuery(sql, parametros);
            if (cursor != null && cursor.moveToFirst()) {
                bA = Integer.parseInt(cursor.getString(0));
                cursor.moveToNext();
                bB = Integer.parseInt(cursor.getString(0));
                cursor.close();
            }

            // Obtenemos la distancia de la tabla Dis_baliza utilizando los IDs de las balizas
            sql = "SELECT value FROM Dis_baliza WHERE baliza_a = ? AND baliza_b = ?";
            String[] parametrosDistancia = {String.valueOf(bA), String.valueOf(bB)};
            cursor = db.rawQuery(sql, parametrosDistancia);
            if (cursor != null && cursor.moveToFirst()) {
                res = Double.parseDouble(cursor.getString(0));
                cursor.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }

    /**
     * Metodo que incluye en la tabla temporal el nombre de la baliza y su distancia al dispositivo
     */
    public static boolean IntroTemp(DataBaseHelper dataBaseHelper, String nom, double
            dist) {
        boolean res;
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        if (db != null) {
            if (db != null) {
                ContentValues values = new ContentValues();
                values.put("nombre", nom);
                values.put("distancia", dist);
                long i = db.insert("Temporal", null, values);
                if (i > 0) {
                    res = true;
                } else
                    res = false;
            } else
                res = false;
        } else
            res = false;
        db.close();
        return res;
    }

    /**
     * Metodo que limpia la tabla temporal
     */
    public static Cursor CleanTableTemp(DataBaseHelper dataBaseHelper) {
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor c = null;
        if (db != null) {
            String sql = "delete from Temporal";
            //     c = db.rawQuery(sql, null);
            //     if (!c.moveToFirst()) {
            c = null;
        }
        //   }
        db.close();
        return c;
    }

    /**
     * Metodo que obtiene las baliza y sus distancias
     */
    public static Cursor ConsultaDistancia(DataBaseHelper dataBaseHelper) {
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor c = null;
        if (db != null) {
            String sql = "select Nombre, distancia " +
                    "from Temporal " +
                    "order by distancia ASC";
            c = db.rawQuery(sql, null);
            if (!c.moveToFirst()) {
                c = null;
                Log.i("Error:", "error");
            }
        }
        db.close();
        return c;
    }

    /**
     * Metodo que obtiene la distancia y nombre de una baliza especifica
     */
    public static Cursor ConsultaEspecifica(DataBaseHelper dataBaseHelper, String nom) {
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor c = null;
        if (db != null) {
            String sql = "select Nombre, distancia " +
                    "from Temporal " +
                    "where Nombre = '" + nom + "'";
            c = db.rawQuery(sql, null);
            if (!c.moveToFirst()) {
                c = null;
            }
        }
        db.close();
        return c;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
