package urjc.com.wayfindingapp.Activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDeDatos<apiInterface> {
   //  String conexionBD = "jdbc:mysql://localhost:3306/wayfindingBBDD";
    String conexionBD = "jdbc:mysql://10.50.211.100:8080/wayfindingBBDD";
    Connection conexion = null;
    ResultSet rs;
    Statement s;
    SQLiteDatabase db;

    /**
     Método que devuelve los nombres de todos las balizas
     */
    public String[] ObtenerNombreBaliza() {
        int cont = 0;
        String res[] = null;
        String sql = "select name from baliza";
        try {
            rs = s.executeQuery(sql);
            while (rs.next()) {
                res[cont] = rs.getString(1);
                cont++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log( Level.SEVERE, null, ex);
        }
        return res;
    }
    /**
     Método que obtiene el nombre de un lugar en especifico en base a su id
     */
    public String ObtenerLugar(String cod) {
        String res = null;
        String sql = "select name from lugar where idlugar=" + cod;
        try {
            rs = s.executeQuery(sql);
            while (rs.next()) {
                res = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    /**
     Método que obtiene el codigo de una baliza en base a su nombre
     */
    public String ObtenerCodLugar(String baliza) {
        String res = "";
        String sql = "select idlugar from baliza "
                + "where name = '"
                + baliza
                + "'";
        try {
            rs = s.executeQuery(sql);
            while (rs.next()) {
                res = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    /**
     Método que obtiene las balizas asociados a un pasillo en especifico
     */
    public String[] ObtenerBeaconsPasillo(String lugar) {
        String res[] = new String[3];
        int cont = 0;
        String sql = "select name from baliza "
                + "where idlugar="
                + lugar
                + " order by letra asc";
        try {
            rs = s.executeQuery(sql);
            while (rs.next()) {
                res[cont] = rs.getString(1);
                cont++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    /**
     Método que obtiene el literal que identifica a una baliza en especifico
     */
    public String ObtenerLiteral(String baliza) {
        String res = null;
        String sql = "select letra from baliza "
                + "where name = '"
                + baliza
                + "'";
        try {
            rs = s.executeQuery(sql);
            while (rs.next()) {
                res = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    /**
     Método que obtene las coordenadas reales de una baliza
     */
    public String ObtenerCoordendadas(String baliza) {
        String res = null;
        String sql = "select coord_x, coord_y from baliza "
                + "where name = '"
                + baliza
                + "'";
        try {
            rs = s.executeQuery(sql);
            while (rs.next()) {
                res = rs.getString(1) + ";" + rs.getString(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    /**
     Método que obtiene la ruta de donde esta almacenada una imagen dentro del servidor
     en base al lugar al que pertence
     */
    public String ObtenerImgAula(String lugar) {
        String res = null;
        String sql = "select imagen from ubicaciones "
                + "where posicion = '"
                + lugar
                + "'";
        try {
            rs = s.executeQuery(sql);
            while (rs.next()) {
                res = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    /**
     Método que obtiene la ruta de donde esta almacenada una imagen dentro del servidor
     en base al lugar al que pertence
     */
    public String ObtenerImagen(String lugar) {
        String res = null;
        String sql = "select imagen \n"
                + "from lugar \n"
                + "where idlugar=" + lugar;
        try {
            rs = s.executeQuery(sql);
            while (rs.next()) {
                res = rs.getString(1).trim();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    /**
     Método que obtiene la descripcion de un pasillo
     */
    public String ObtenerDescrpPasillo(String pasillo) {
        String res = null;
        String sql = "select t.descripcion\n"
                + "from typelugar t, lugar l\n"
                + "where l.idlugar="+pasillo+"\n"
                + "and l.`idtypelugar`= t.`idtypelugar`";
        try {
            rs = s.executeQuery(sql);
            while (rs.next()) {
                res = rs.getString(1).trim();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    /**
     Método que verifica si el codigo de un lugar es un pasillo
     */
    public boolean VerificarEsPasillo(String cod) {
        boolean res = false;
        String val = null;
        String sql = "select Name\n"
                + "from lugar\n"
                + "where idlugar=" + cod;
        try {
            rs = s.executeQuery(sql);
            while (rs.next()) {
                val = rs.getString(1).trim();
            }
            if (val.equals("PASILLO")) {
                res = true;
            } else {
                res = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    /**
     Método que obtiene los lugares cernado de un pasillo
     */
    public ArrayList ObtenerLugaresPasillo(String pasillo) {
        ArrayList res = new ArrayList();
        String sql = "select l.`name`,t.descripcion\n"
                + "from lugar l, typelugar t, lugarescercanos c\n"
                + "where c.place_principal = " + pasillo + "\n"
                + "and l.idlugar = c.lugar_cercano\n"
                + "and l.`idtype_lugar` = t.`idtype_lugar`";
        try {
            rs = s.executeQuery(sql);
            while (rs.next()) {
                res.add(rs.getString(1).trim() + ";" + rs.getString(2).trim());
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    /**
     Método que obtiene la descripcion de un lugar
     */
    public ArrayList ObtenerDescripcionLugar(String lugar) {
        ArrayList res = new ArrayList();
        String sql = "SELECT l.`name`,f.`name`,l.`descripcion`\n"
                + "from lugar l,lugar f\n"
                + "where l.idlugar=" + lugar + "\n"
                + "and l.`idLugarC` = f.idlugar";
        try {
            rs = s.executeQuery(sql);
            while (rs.next()) {
                String cadena[] = rs.getString(3).split(";");
                String des = "";
                for (int i = 0; i < cadena.length; i++) {
                    des += cadena[i] + "\n";
                }
                res.add(rs.getString(1).trim() + ";" + rs.getString(2).trim() + ";" +
                        des);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    /**
     Método que obtiene la distancia real entre dos beacons
     */
    public double ObtenerDistancia(String a, String b) {
        int cont = 0;
        int bA = 0, bB = 0;
        double res = 0;
        try {
            System.out.println(a + " " + b);
            String sql = "select idbaliza"
                    + " from baliza"
                    + " where name = '" + a
                    + "' or name ='" + b + "'";
            rs = s.executeQuery(sql);
            while (rs.next()) {
                if (cont == 0) {
                    bA = Integer.parseInt(rs.getString(1));
                    rs.getString(1);
                    cont++;
                } else {
                    bB = Integer.parseInt(rs.getString(1));
                    cont = 0;
                }
            }
            sql = "select value"
                    + " from dist_baliza"
                    + " where baliza_a = "
                    + bA
                    + " and baliza_b = "
                    + bB;
            rs = s.executeQuery(sql);
            while (rs.next()) {
                res = Double.parseDouble(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    /**
     Metodo que incluye en la tabla temporal el nombre de la baliza y su distancia al dispositivo
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
     Metodo que limpia la tabla temporal
     */
    public static Cursor CleanTableTemp(DataBaseHelper dataBaseHelper){
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor c = null;
        if (db!=null) {
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
     Metodo que obtiene las baliza y sus distancias
     */
    public static Cursor ConsultaDistancia(DataBaseHelper dataBaseHelper){
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor c = null;
        if (db!=null) {
            String sql = "select Nombre, distancia " +
                    "from Temporal " +
                    "order by distancia ASC";
            c = db.rawQuery(sql, null);
            if (!c.moveToFirst()) {
                c = null;
                Log.i("Error:","error");
            }
        }
        db.close();
        return c;
    }
    /**
     Metodo que obtiene la distancia y nombre de una baliza especifica
     */
    public static Cursor ConsultaEspecifica(DataBaseHelper dataBaseHelper, String nom){
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor c = null;
        if (db!=null) {
            String sql = "select Nombre, distancia " +
                    "from Temporal " +
                    "where Nombre = '"+nom+"'";
            c = db.rawQuery(sql, null);
            if (!c.moveToFirst()) {
                c = null;
            }
        }
        db.close();
        return c;
    }
}