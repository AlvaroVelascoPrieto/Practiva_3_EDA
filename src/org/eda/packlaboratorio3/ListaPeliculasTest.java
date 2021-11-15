package org.eda.packlaboratorio1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListaPeliculasTest {

    String direccion;
    String a1;
    String a2;
    String a3;
    String a4;
    String a5;
    String a6;
    String a7;
    String p1;
    String p3;
    String p4;
    String p5;
    ArrayList<Actor> actoresOrdenados;
    ArrayList<Actor> actoresOrdenadosAutomaticamente;
    Map<String, Pelicula> mapaVacio = new HashMap<String, Pelicula>();


    @Before
    public void setUp() throws Exception {

        a1 = "Actor1";
        a2 = "Actor2";
        a3 = "Actor3";
        a4 = "Actor4";
        a5 = "Actor5";
        a6 = "Actor6";
        a7 ="Actor7";
        actoresOrdenados = new ArrayList<Actor>();
        actoresOrdenados.add(new Actor(a1));
        actoresOrdenados.add(new Actor(a2));
        actoresOrdenados.add(new Actor(a3));
        actoresOrdenados.add(new Actor(a4));
        actoresOrdenados.add(new Actor(a5));
        p1 = "Pelicula 1";
        p3 = "Pelicula 3";
        p4 = "Pelicula 4";
        p5 = "Pelicula 5";

        direccion = System.getProperty("user.dir") + "\\src\\org\\eda\\packlaboratorio1\\ListaPeliculasPequeña.txt";
        ListaPeliculas.getListaPeliculas().lectorArchivo(direccion);
    }

    @After
    public void tearDown() throws Exception {
        a1 = null;
        p1 = null;

    }

    @Test
    public void testGetListaPeliculas() {
        assertNotNull(ListaPeliculas.getListaPeliculas());
    }

    @Test
    public void testBuscarActor() {
        assertEquals(ListaPeliculas.getListaPeliculas().buscarActor(a1).getNombre(), a1);
        assertNotEquals(ListaPeliculas.getListaPeliculas().buscarActor(a1).getNombre(), a2);
        assertNull(ListaPeliculas.getListaPeliculas().buscarActor((a7)));// buscar unu actor que no existe
    }

    @Test
    public void testIncrementarRecaudacion() {
        double incremento = 500.00;
        ListaPeliculas.getListaPeliculas().incrementarRecaudacion(p1, incremento);
        assertEquals(ListaPeliculas.getListaPeliculas().getRecaudacion(p1), 0.0, incremento);
    }

    @Test
    public void testInsertarActor() {
        //Insertar un actor que no se encuentra en la lista general de actores en una película que ya está registrada.
        ListaPeliculas.getListaPeliculas().insertarActor(p1,"David");
        assertEquals(ListaPeliculas.getListaPeliculas().devolverPeliculas("David").get(p1).getTitulo(),p1);

        //Insertar un actor que no se encuentra en la lista general de actores en una película que no está registrada.
        ListaPeliculas.getListaPeliculas().insertarActor(p4,"Alvaro");
        assertEquals(ListaPeliculas.getListaPeliculas().devolverPeliculas("Alvaro").get(p4).getTitulo(),p4);

        //Insertar un actor que ya se encuentra en la lista general de actores en una película que ya está registrada.
        ListaPeliculas.getListaPeliculas().insertarActor(p1,a1);
        assertEquals(ListaPeliculas.getListaPeliculas().devolverPeliculas(a1).get(p1).getTitulo(),p1);

        //Insertar un actor que ya se encuentra en la lista general de actores en una película que no está registrada.
        ListaPeliculas.getListaPeliculas().insertarActor(p4,a1);
        assertEquals(ListaPeliculas.getListaPeliculas().devolverPeliculas(a1).get(p4).getTitulo(),p4);



    }

    @Test
    public void testBorrarActor() {
        //Borrar un actor que aparece en el HashMap de actores y está contenido en más de una película.
        assertEquals(ListaPeliculas.getListaPeliculas().devolverPeliculas(a1).get(p1).getTitulo(),p1);
        ListaPeliculas.getListaPeliculas().borrarActor(a1);
        assertNull(ListaPeliculas.getListaPeliculas().devolverPeliculas(a1).get(p1));


        //Borrar un actor que aparece en el HashMap de actores y está contenido en una película.
        assertEquals(ListaPeliculas.getListaPeliculas().devolverPeliculas(a3).get(p1).getTitulo(),p1);
        ListaPeliculas.getListaPeliculas().borrarActor(a3);
        assertNull(ListaPeliculas.getListaPeliculas().devolverPeliculas(a3).get(p1));


        //Borrar un actor que no aparece en el HashMap de actores.
        assertNull(ListaPeliculas.getListaPeliculas().devolverPeliculas(a7).get(p1));
        ListaPeliculas.getListaPeliculas().borrarActor(a7);
        assertNull(ListaPeliculas.getListaPeliculas().devolverPeliculas(a7).get(p1));

    }

    @Test
    public void testDevolverActores() {
        //caso1: Devolver los actores de una película que está guardada y tiene actores.
        assertEquals(ListaPeliculas.getListaPeliculas().devolverActores(p1).get(a1).getNombre(),a1);
        assertNotEquals(ListaPeliculas.getListaPeliculas().devolverActores(p1).get(a1).getNombre(),a5);

        //caso2:Devolver los actores de una película que no está guardada.
        assertEquals(ListaPeliculas.getListaPeliculas().devolverActores(p4), mapaVacio);

        //caso3: Devolver los actores de una película que no tiene actores.
        assertEquals(ListaPeliculas.getListaPeliculas().devolverActores(p5), mapaVacio);

    }

    @Test
    public void testDevolverPeliculas() {
        //Caso1: Devolver las películas de un actor que aparezca en más de una película.
        assertEquals(ListaPeliculas.getListaPeliculas().devolverPeliculas(a1).get(p1).getTitulo(),p1);

        //Caso2: Devolver las películas de un actor que solo aparezca en una película.
        assertEquals(ListaPeliculas.getListaPeliculas().devolverPeliculas(a6).get(p3).getTitulo(),p3);

        //Caso3: Devolver las películas de un actor que no aparezca en ninguna película. (En este caso como indica la postcondición se debería devolver un ArrayList vacío)
        assertEquals(ListaPeliculas.getListaPeliculas().devolverPeliculas(a7),mapaVacio);
    }

    @Test
    public void testDevolverTodosLosActoresOrdenados() {
        //Unico caso hecho
        actoresOrdenadosAutomaticamente = ListaPeliculas.getListaPeliculas().devolverTodosLosActoresOrdenados();
        assertEquals(actoresOrdenadosAutomaticamente.get(0).getNombre(),actoresOrdenados.get(0).getNombre());
        assertEquals(actoresOrdenadosAutomaticamente.get(1).getNombre(),actoresOrdenados.get(1).getNombre());
        assertEquals(actoresOrdenadosAutomaticamente.get(2).getNombre(),actoresOrdenados.get(2).getNombre());
        assertEquals(actoresOrdenadosAutomaticamente.get(3).getNombre(),actoresOrdenados.get(3).getNombre());
        assertEquals(actoresOrdenadosAutomaticamente.get(4).getNombre(),actoresOrdenados.get(4).getNombre());


    }

}
