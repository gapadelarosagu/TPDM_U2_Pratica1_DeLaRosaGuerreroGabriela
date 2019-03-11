package mx.edu.ittepic.tpdm_u2_practica1_delaroaguerrerogabriela;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;

public class Proyectos {
    private BD_Civil base;
    private int id;
    private String descripcion;
    private String ubicacion;
    private String fecha;
    private float presupuesto;
    protected String error;

    public Proyectos(Activity activity) {
        base = new BD_Civil(activity, "civil", null, 1);
    }

    public Proyectos(int id, String des, String ubi, String fec, float pre) {
        this.id = id;
        this.descripcion = des;
        this.ubicacion = ubi;
        this.fecha = fec;
        this.presupuesto = pre;
    }

    public boolean insertar(Proyectos proyectos) {
        try {
            SQLiteDatabase transaccionInsertar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("DESCRIPCION", proyectos.getDescripcion());
            datos.put("UBICACION", proyectos.getUbicacion());
            datos.put("FECHA", proyectos.getFecha());
            datos.put("PRESUPUESTO", proyectos.getPresupuesto());

            long resultado = transaccionInsertar.insert("PROYECTOS", "IDPROYECTO", datos);
            transaccionInsertar.close();
            if (resultado == -1) {
                return false; //no se pudo hacer el insert
            }
        } catch (SQLiteException e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean eliminar(Proyectos proyectos) {
        try {
            SQLiteDatabase transaccionEliminar = base.getWritableDatabase();
            long resultado = transaccionEliminar.delete("PROYECTOS", "IDPROYECTO=?", new String[]{"" + proyectos.getId()});
            transaccionEliminar.close();

            if (resultado == 0) {
                return false;//no se elimino nada
            }
        } catch (SQLiteException e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean modificar(Proyectos proyectos){
        try{
            SQLiteDatabase transaccionModificar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("DESCRIPCION", proyectos.getDescripcion());
            datos.put("UBICACION", proyectos.getUbicacion());
            datos.put("FECHA", proyectos.getFecha());
            datos.put("PRESUPUESTO", proyectos.getPresupuesto());
            String dato[]={proyectos.getId()+""};
            long resultado = transaccionModificar.update("PROYECTOS",datos,"IDPROYECTO=?",dato);
            transaccionModificar.close();
            if(resultado<0){
                return false; //no se actualizo nada
            }
        }catch(SQLiteException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Proyectos[] consultar(){
        try{
            SQLiteDatabase db=base.getReadableDatabase();
            Cursor c=db.rawQuery("SELECT * FROM PROYECTOS",null);
            if (c.moveToFirst()) {
                Proyectos[] proyectos = new Proyectos[c.getCount()];
                int pos = 0;
                do {
                    proyectos[pos] = new Proyectos(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getFloat(4));
                    pos++;
                } while (c.moveToNext());
                return proyectos;
            }
        }catch(SQLiteException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }
    public Proyectos consultar(Object dato){
        try{
            SQLiteDatabase db=base.getReadableDatabase();
            Cursor c=null;
            if (dato instanceof Integer){
                c=db.rawQuery("SELECT * FROM PROYECTOS WHERE IDPROYECTO=?",new String[]{Integer.parseInt(dato.toString())+""});
            }
            else{
                c=db.rawQuery("SELECT * FROM PROYECTOS WHERE DESCRIPCION=?",new String[]{dato.toString()});
            }

            if (c.moveToFirst()) {
                Proyectos proyecto = new Proyectos(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getFloat(4));
                return proyecto;
            }
        }catch(SQLiteException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(float presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getError(){
        return error;
    }

}

