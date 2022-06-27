package com.example.eventos;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements EventoAdaptador.RecyclerItemClick, SearchView.OnQueryTextListener {
    private RecyclerView Lista;
    private SearchView svSearch;
    private EventoAdaptador adapter;
    private List<Eventos> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initValues();
        initListener();
    }

    private void initViews(){
        Lista = findViewById(R.id.Lista);
       //svSearch = findViewById(R.id.svSearch);
    }

    private void initValues() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //Si quiero en representarlo en celdas
        //GridLayoutManager manager1 = new GridLayoutManager(this);
        Lista.setLayoutManager(manager);

        items = getItems();
        adapter = new EventoAdaptador(items, this);
        Lista.setAdapter(adapter);
    }

    private void initListener() {
        svSearch.setOnQueryTextListener(this);
    }

    private List<Eventos> getItems() {
        List<Eventos> itemLists = new ArrayList<>();
        itemLists.add(new Eventos("Futsal CDU", "Canchas disponibles los viernes, llega con tu equipo.", R.drawable.microfutbol_unicauca));
        itemLists.add(new Eventos("Piscina CDU", "Ven y hechate un chapuson.", R.drawable.natacion));
        itemLists.add(new Eventos("Foros", "Unete a la conferencias de becas en el exterior.", R.drawable.reunion));
        itemLists.add(new Eventos("Rumba", "Ven y comparte con tu parche en esta extraordinaria celebracion de cierre de semestre.", R.drawable.rumbas));
        itemLists.add(new Eventos("Running", "Ven y disfruta los mejores paisajes mientras corres. Hora de salida sabado 6:AM.", R.drawable.runing));
        itemLists.add(new Eventos("Ciclomontañismo", "Ven y demuestra que tan bueno eres para el pedal. Hora de salida sabados. 6:AM.", R.drawable.montar_en_bicicleta));
        itemLists.add(new Eventos("Aprende a nadar", "Clases de natacion, todos los dias 6:PM.", R.drawable.piscina_en_cdu));
        itemLists.add(new Eventos("Sustentaciones", "Quieres aprender como se sustenta un trabajo de grado, ven y aprende. Viernes 7:AM.", R.drawable.sustentacion_de_trabajos));

        return itemLists;
    }

    @Override
    public void itemClick(Eventos item) {
        //Llamamos al intent
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("itemDetail", item);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }
}
