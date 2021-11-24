package org.eda.packlaboratorio3;

import java.util.*;

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
        HashMap<String,String> visitados = new HashMap<String, String>();
        Queue<String> cola = new LinkedList<String>();
        cola.add(a1);
        while (!cola.isEmpty()){
            ArrayList<String> adyacentes = g.get(cola.peek());
            Iterator<String> itr = adyacentes.iterator();
            while (itr.hasNext()){
                String act = itr.next();
                if (a2.equals(act)){
                    return true;
                }
                else if(!visitados.containsKey(act)){
                    cola.add(act);
                }
            }
            visitados.put(cola.peek(),cola.poll());
        }
        return false;
    }
}