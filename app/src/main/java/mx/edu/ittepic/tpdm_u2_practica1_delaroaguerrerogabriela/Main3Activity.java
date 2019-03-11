package mx.edu.ittepic.tpdm_u2_practica1_delaroaguerrerogabriela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    EditText descID,descripcion, ubicacion,fecha, presupuesto;
    Button consulta,elimina,modifica,regresa;
    Proyectos proy,proyecto;
    //TextView compDes, compUbi, compFec, compPre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        descID = findViewById(R.id.descId);
        consulta = findViewById(R.id.consulta);
        proy=new Proyectos(this);
        consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String busqueda=descID.getText().toString();
                int id=0;
                try{
                    id=Integer.parseInt(busqueda);
                }catch(NumberFormatException e){

                }
                if (id==0){proyecto=proy.consultar(busqueda);}
                else{proyecto=proy.consultar(id);}
                if (proyecto==null){
                    Toast.makeText(Main3Activity.this, "No existe el registro", Toast.LENGTH_LONG).show();
                }
                else{
                    descripcion.setText(proyecto.getDescripcion());
                    ubicacion.setText(proyecto.getUbicacion());
                    fecha.setText(proyecto.getFecha());
                    presupuesto.setText(proyecto.getPresupuesto()+"");
                }
            }
        });
        descripcion = findViewById(R.id.descripcion);
        ubicacion = findViewById(R.id.ubicacion);
        fecha = findViewById(R.id.fecha);
        presupuesto = findViewById(R.id.presupuesto);
        elimina = findViewById(R.id.elimina);
        modifica = findViewById(R.id.modifica);
        regresa = findViewById(R.id.regresa);
        elimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (proy.eliminar(proyecto)){
                    Toast.makeText(Main3Activity.this, "Proyecto eliminado correctamente", Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Main3Activity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(Main3Activity.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
                }
            }
        });
        modifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (proy.modificar(new Proyectos(proyecto.getId(),descripcion.getText().toString(),ubicacion.getText().toString(),fecha.getText().toString(),Float.parseFloat(presupuesto.getText().toString())))){
                    Toast.makeText(Main3Activity.this,"Proyecto Modificado Exitosamente",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Main3Activity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(Main3Activity.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
                }
            }
        });
        regresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Main3Activity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        /*
        cierre = findViewById(R.id.cerrar);
        compDes = findViewById(R.id.compDes);
        compUbi = findViewById(R.id.compUbi);
        compFec = findViewById(R.id.compFec);
        compPre = findViewById(R.id.compPre);*/
    }


}
