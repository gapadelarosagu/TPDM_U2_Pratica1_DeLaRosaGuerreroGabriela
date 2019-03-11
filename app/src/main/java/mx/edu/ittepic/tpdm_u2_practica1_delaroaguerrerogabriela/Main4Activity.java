package mx.edu.ittepic.tpdm_u2_practica1_delaroaguerrerogabriela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {
    EditText descripcion, ubicacion,fecha, presupuesto;
    Button elimina,modifica,regresa;
    Proyectos proyecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        descripcion = findViewById(R.id.descripcion);
        ubicacion = findViewById(R.id.ubicacion);
        fecha = findViewById(R.id.fecha);
        presupuesto = findViewById(R.id.presupuesto);
        elimina = findViewById(R.id.elimina);
        modifica = findViewById(R.id.modifica);
        regresa = findViewById(R.id.regresa);
        proyecto=new Proyectos(this);
        elimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Proyectos proy=new Proyectos(Main4Activity.this);
                if (proy.eliminar(proyecto)){
                    Toast.makeText(Main4Activity.this,"Proyecto Eliminado Exitosamente",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Main4Activity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(Main4Activity.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
                }
            }
        });
        modifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Proyectos proy=new Proyectos(Main4Activity.this);
                if (proy.modificar(new Proyectos(proyecto.getId(),descripcion.getText().toString(),ubicacion.getText().toString(),fecha.getText().toString(),Float.parseFloat(presupuesto.getText().toString())))){
                    Toast.makeText(Main4Activity.this,"Proyecto Modificado Exitosamente",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Main4Activity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(Main4Activity.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
                }
            }
        });
        regresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Main4Activity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
    protected void onStart(){
        super.onStart();
        Bundle extras=getIntent().getExtras();
        proyecto=new Proyectos(extras.getInt("id"),extras.getString("des"),extras.getString("ubica"),extras.getString("fecha"),extras.getFloat("presupuesto"));
        descripcion.setText(proyecto.getDescripcion());
        ubicacion.setText(proyecto.getUbicacion());
        fecha.setText(proyecto.getFecha());
        presupuesto.setText(proyecto.getPresupuesto()+"");
    }
}
