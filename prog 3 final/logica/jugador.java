package logica;

import java.util.ArrayList;
import java.util.Random;

public class jugador {

    private String nombre;
    private ArrayList<personaje> personajes;

    public jugador(String nombre) {
        this.nombre = nombre;
        this.personajes = new ArrayList<personaje>();
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<personaje> getPersonajes() {
        return new ArrayList<personaje>(personajes);
    }

    public void agregarPersonaje(personaje personaje) {
        personajes.add(personaje);
    }

    public void eliminarPersonaje(personaje personaje) {
        personajes.remove(personaje);
    }

    public void limpiarPersonajes() {
        personajes.clear();
    }

    public personaje generarPersonajeAleatorio() {
        Random rand = new Random();
        String[] nombres = {"Elara","Gorlag","Sylvari","Cedric","Grommash","Aelindra","Rowan","Thokk","Faelar","Isolde","Durgash","Elowen","Aldric","Groknak","Thaelar","Aragon", "Legolas", "Gimli", "Gandalf", "Frodo", "Boromir"};
        String[] apodos = {"Flecha Certera","Escudo Inquebrantable","Mente Brillante","Garras Afiladas","Susurro Nocturno","Puño de Piedra","Corazón de Hielo","Espina Venenosa","Trueno Oscuro","Ojo de Águila","Mano de Fuego","Colmillo de Acero","Sombra Veloz","Rompehuesos"};
        raza[] razas = raza.values();
        
        String nombre = nombres[rand.nextInt(nombres.length)];
        String apodo = apodos[rand.nextInt(apodos.length)];
        raza raza = razas[rand.nextInt(razas.length)];

        switch (raza) {
            case humano:
                return new humano(nombre, apodo, raza);
            case orco:
                return new orco(nombre, apodo, raza);
            case elfo:
                return new elfo(nombre, apodo, raza);
            default:
                return null;
        }
    }

    public void generarPersonajesAleatorios(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            personaje p = generarPersonajeAleatorio();
            agregarPersonaje(p);
        }
    }
}

