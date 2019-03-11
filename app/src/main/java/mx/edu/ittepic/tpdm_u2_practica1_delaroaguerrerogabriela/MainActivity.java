package mx.edu.ittepic.tpdm_u2_practica1_delaroaguerrerogabriela;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    Proyectos [] listaProyectos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.listaproyectos);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alerta=new AlertDialog.Builder(MainActivity.this);
                alerta.setTitle("Alerta")
                        .setMessage("¿Desea modificar/eliminar el proyecto seleccionado?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent datos=new Intent(MainActivity.this,Main4Activity.class);
                                datos.putExtra("id",listaProyectos[position].getId());
                                datos.putExtra("des",listaProyectos[position].getDescripcion());
                                datos.putExtra("fecha",listaProyectos[position].getFecha());
                                datos.putExtra("ubica",listaProyectos[position].getUbicacion());
                                datos.putExtra("presupuesto",listaProyectos[position].getPresupuesto());
                                startActivity(datos);
                                finish();
                            }
                        })
                        .setNegativeButton("NO",null).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();//se ejecuta cuando se ve la pantalla
        Proyectos proyecto = new Proyectos(this);
        Proyectos vector[] = proyecto.consultar();
        listaProyectos=vector;
        String[] descripcion = null;

        if(vector==null){
            descripcion = new String[1];
            descripcion[0]= "No hay proyectos aún";
            lista.setEnabled(false);
        }else {
            descripcion= new String[vector.length];
            for (int i=0; i<vector.length; i++){
                Proyectos temp = vector[i];
                descripcion[i] = temp.getDescripcion();// se le pasaran los datos nombre y telefono
            }
            lista.setEnabled(true);
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,descripcion);
        lista.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.insertar:
                Intent inserta = new Intent(this,Main2Activity.class);
                startActivity(inserta);
                finish();
                break;
            case R.id.consultar:
                Intent consulta = new Intent(this,Main3Activity.class);
                startActivity(consulta);
                finish();
                break;
            case R.id.eliminar:
                Intent consulta1 = new Intent(this,Main3Activity.class);
                startActivity(consulta1);
                finish();
                break;
            case R.id.modificar:
                Intent consulta2 = new Intent(this,Main3Activity.class);
                startActivity(consulta2);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
