package org.eda.packlaboratorio3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GraphHash {
    HashMap<String, ArrayList<String>> g;

    public void main(String[] args) {

        this.crearGrafo(ListaPeliculas.getListaPeliculas().devolverTodosLosActores());
    }

    public void crearGrafo(ListaActores lActores) {
    // Post: crea el grafo desde la lista de actores
    // Los nodos son nombres de actores
    // COMPLETAR CÓDIGO
        this.g = new HashMap<String,ArrayList<String>>();
        HashMap<String,Actor> actores = lActores.devolverActores();
        for (Map.Entry<String, Actor> entryAct : actores.entrySet()){
            HashMap<String,String> colegas = new HashMap<String,String>();
            HashMap<String, Pelicula> peliculas = ListaPeliculas.getListaPeliculas().devolverPeliculas(entryAct.getKey());
            for (Map.Entry<String, Pelicula> entryPel : peliculas.entrySet()) {
                HashMap<String, Actor> actoresPeli = entryPel.getValue().devolverActores();
                for (Map.Entry<String, Actor> entryActPel : actoresPeli.entrySet()) {
                    if (!(colegas.containsKey(entryActPel.getKey()))&& !Objects.equals(entryActPel.getKey(), entryAct.getKey())) {
                        colegas.put(entryActPel.getKey(), entryActPel.getKey());
                    }
                }
                g.put(entryAct.getKey(), new ArrayList<String>(colegas.values()));
            }
        }

    }


    public void print(){
        int i = 1;
        for (String s : g.keySet()) {
            System.out.print("Element: " + i++ + " " + s + " --> ");
            for (String k : g.get(s)) {
                System.out.print(k + " ### ");
            }
            System.out.println();
        }
    }

    public boolean estanConectados(String a1, String a2) {
        // COMPLETAR CÓDIGO
        return true;
    }
}