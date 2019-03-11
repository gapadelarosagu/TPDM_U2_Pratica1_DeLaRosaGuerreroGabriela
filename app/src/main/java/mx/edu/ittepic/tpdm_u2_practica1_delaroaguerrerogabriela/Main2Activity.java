package mx.edu.ittepic.tpdm_u2_practica1_delaroaguerrerogabriela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText descripcion, ubicacion,fecha, presupuesto;
    Button inserta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        descripcion = findViewById(R.id.descripcion);
        ubicacion = findViewById(R.id.ubicacion);
        fecha = findViewById(R.id.fecha);
        presupuesto = findViewById(R.id.presupuesto);
        inserta = findViewById(R.id.inserta);
        inserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Proyectos proyecto=new Proyectos(Main2Activity.this);
                if (proyecto.insertar(new Proyectos(1,descripcion.getText().toString(),ubicacion.getText().toString(),fecha.getText().toString(),Float.parseFloat(presupuesto.getText().toString())))){
                    Toast.makeText(Main2Activity.this,"Proyecto Insertado Exitosamente",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Main2Activity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(Main2Activity.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
