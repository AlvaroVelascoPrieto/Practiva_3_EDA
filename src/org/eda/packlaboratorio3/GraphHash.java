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

    public boolean estanConectados(String a1, String a2){
        Stack<String> pila = new Stack<>();
        HashMap<String, String> visitado = new HashMap<String,String>();
        pila.push(a1);
        visitado.put(a1,a1);
        while(!pila.isEmpty()) {
            String aux=pila.pop();
            if(aux.equals(a2)) {
                return true;
            }
            else {
                for (String act : g.get(aux)) {
                    if (!visitado.containsKey(act)) {
                        pila.push(act);
                        visitado.put(act, act);
                    }
                }
            }
        }
        return false;
    }

    public void calcularConexiones(int n){
        Random randomGenerator = new Random();
        Object[] a = g.keySet().toArray();

        for (int i = 1; i <= n; i++){
            int x = randomGenerator.nextInt(g.size());
            int y = randomGenerator.nextInt(g.size());
            System.out.println("Prueba: " + i + " ");
            System.out.println(estanConectados((String) a[x], (String) a[y]));
        }
    }
}