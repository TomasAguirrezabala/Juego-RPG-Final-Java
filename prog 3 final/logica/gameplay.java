package logica;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.List;


public class gameplay {

    private static Random rand = new Random();
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println(ANSI_YELLOW + "╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║           BIENVENIDO AL JUEGO DE ROL DE CARTAS               ║");
            System.out.println("╚══════════════════════════════════════════════════════════════╝" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "1. Iniciar juego con personajes aleatorios");
            System.out.println("2. Iniciar juego con personajes ingresados manualmente");
            System.out.println("3. Leer logs de partidas anteriores");
            System.out.println("4. Borrar archivo de logs");
            System.out.println("5. Salir" + ANSI_RESET);
            System.out.print(ANSI_GREEN + "Seleccione una opción: " + ANSI_RESET);
            
            int opcion = scanner.nextInt();
            
            switch (opcion) {
                case 1:
                case 2:
                    iniciarJuego(opcion, scanner);
                    break;
                case 3:
                    leerLogs();
                    break;
                case 4:
                    borrarLogs();
                    break;
                case 5:
                    System.out.println("Gracias por jugar. ¡Hasta luego!");
                    return;
                default:
                    System.out.println(ANSI_RED + "Opción no válida." + ANSI_RESET);
            }
        }
    }

    private static void iniciarJuego(int opcion, Scanner scanner) {
        jugador jugador1 = new jugador("Jugador 1");
        jugador jugador2 = new jugador("Jugador 2");

        if (opcion == 1) {
            jugador1.generarPersonajesAleatorios(3);
            jugador2.generarPersonajesAleatorios(3);
            System.out.println(ANSI_PURPLE + "Se generaron personajes aleatorios para cada jugador." + ANSI_RESET);
        } else {
            System.out.println(ANSI_BLUE + "\n┌─────────────────────────────────────────────────────────────┐");
            System.out.println("│ Ingrese los personajes para el Jugador 1:                   │");
            System.out.println("└─────────────────────────────────────────────────────────────┘" + ANSI_RESET);
            ingresarPersonajesManualmente(jugador1, scanner);
            
            System.out.println(ANSI_BLUE + "\n┌─────────────────────────────────────────────────────────────┐");
            System.out.println("│ Ingrese los personajes para el Jugador 2:                   │");
            System.out.println("└─────────────────────────────────────────────────────────────┘" + ANSI_RESET);
            ingresarPersonajesManualmente(jugador2, scanner);
        }

        System.out.println(ANSI_YELLOW + "\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                PERSONAJES DE LOS JUGADORES                   ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝" + ANSI_RESET);
        
        System.out.println(ANSI_CYAN + "Personajes del Jugador 1:" + ANSI_RESET);
        jugador1.getPersonajes().forEach(p -> System.out.println(ANSI_GREEN + p + ANSI_RESET));
        
        System.out.println(ANSI_CYAN + "\nPersonajes del Jugador 2:" + ANSI_RESET);
        jugador2.getPersonajes().forEach(p -> System.out.println(ANSI_GREEN + p + ANSI_RESET));
        
        iniciarCombate(jugador1, jugador2);
    }

    private static void leerLogs() {
        List<String> logs = logManager.leerLogs();
        if (logs.isEmpty()) {
            System.out.println(ANSI_YELLOW + "No hay logs disponibles." + ANSI_RESET);
        } else {
            System.out.println(ANSI_YELLOW + "Logs de partidas anteriores: " + ANSI_RESET);
            for (String log : logs) {
                System.out.println(log);
            }
        }
    }

    private static void borrarLogs() {
        logManager.borrarLogs();
    }

    private static void ingresarPersonajesManualmente(jugador jugador, Scanner scanner) {
        for (int i = 0; i < 3; i++) {
            String nombre = obtenerNombreValido(scanner);
            String apodo = obtenerApodoValido(scanner);
            raza seleccionRaza = obtenerRazaValida(scanner);

            personaje nuevoPersonaje;
            switch (seleccionRaza) {
                case humano:
                    nuevoPersonaje = new humano(nombre, apodo, seleccionRaza);
                    break;
                case orco:
                    nuevoPersonaje = new orco(nombre, apodo, seleccionRaza);
                    break;
                case elfo:
                    nuevoPersonaje = new elfo(nombre, apodo, seleccionRaza);
                    break;
                default:
                    nuevoPersonaje = null;
            }

            if (nuevoPersonaje != null) {
                jugador.agregarPersonaje(nuevoPersonaje);
                System.out.println(ANSI_GREEN + "Personaje agregado: " + nuevoPersonaje.getNombre() + ANSI_RESET);
            }
        }
    }

    private static String obtenerNombreValido(Scanner scanner) {
        String nombre;
        do {
            System.out.print(ANSI_CYAN + "Ingrese el nombre del personaje (solo letras): " + ANSI_RESET);
            nombre = scanner.next();
            if (!nombre.matches("[a-zA-Z]+")) {
                System.out.println(ANSI_RED + "Nombre invalido. Use solo letras." + ANSI_RESET);
            }
        } while (!nombre.matches("[a-zA-Z]+"));
        return nombre;
    }

    private static String obtenerApodoValido(Scanner scanner) {
        String apodo;
        do {
            System.out.print(ANSI_CYAN + "Ingrese el apodo del personaje (letras y numeros): " + ANSI_RESET);
            apodo = scanner.next();
            if (!apodo.matches("[a-zA-Z0-9]+")) {
                System.out.println(ANSI_RED + "Apodo inválido. Use letras y numeros." + ANSI_RESET);
            }
        } while (!apodo.matches("[a-zA-Z0-9]+"));
        return apodo;
    }

    private static raza obtenerRazaValida(Scanner scanner) {
        int razaSeleccionada;
        do {
            System.out.print(ANSI_CYAN + "Seleccione la raza (1. Humano, 2. Orco, 3. Elfo): " + ANSI_RESET);
            while (!scanner.hasNextInt()) {
                System.out.println(ANSI_RED + "Entrada invalida. Ingrese 1, 2 o 3." + ANSI_RESET);
                scanner.next();
            }
            razaSeleccionada = scanner.nextInt();
            if (razaSeleccionada < 1 || razaSeleccionada > 3) {
                System.out.println(ANSI_RED + "Opción inválida. Ingrese 1, 2 o 3." + ANSI_RESET);
            }
        } while (razaSeleccionada < 1 || razaSeleccionada > 3);

        switch (razaSeleccionada) {
            case 1: return logica.raza.humano;
            case 2: return logica.raza.orco;
            case 3: return logica.raza.elfo;
            default: return logica.raza.humano;
        }
    }

    private static void iniciarCombate(jugador jugador1, jugador jugador2) {
        StringBuilder logCombate = new StringBuilder();
        boolean combateTerminado = false;
        jugador jugadorPerdedor = null;
        System.out.println("");
        System.out.println(ANSI_YELLOW + "╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                     INICIO DEL COMBATE                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝" + ANSI_RESET);
        logCombate.append("Combate entre ").append(jugador1.getNombre()).append(" y ").append(jugador2.getNombre()).append("\n");
        
        while (!combateTerminado) {
            personaje personaje1 = seleccionarPersonajeAleatorio(jugador1);
            personaje personaje2 = seleccionarPersonajeAleatorio(jugador2);

            System.out.println(ANSI_BLUE + "\n┌─────────────────────────────────────────────────────────────┐");
            System.out.println("│ Combate entre " + personaje1.getNombre() + " y " + personaje2.getNombre());
            System.out.println("└─────────────────────────────────────────────────────────────┘" + ANSI_RESET);
            logCombate.append("Combate entre ").append(personaje1.getNombre()).append(" y ").append(personaje2.getNombre()).append("\n");

            boolean primerAtaque = rand.nextBoolean();
            personaje atacante = primerAtaque ? personaje1 : personaje2;
            personaje defensor = primerAtaque ? personaje2 : personaje1;

            for (int turno = 1; turno <= 14; turno++) {
                double daño = atacante.ataque(defensor);
                defensor.actualizarVida((int)daño);
                if (daño > 100){daño = daño/10;}
                String mensajeTurno = atacante.getNombre() + " ataca a " + defensor.getNombre() + " causando " + Math.round(daño) + " de daño";
                System.out.println(ANSI_GREEN + atacante.getNombre() + ANSI_RESET + " ataca a " + 
                                ANSI_RED + defensor.getNombre() + ANSI_RESET + 
                                " causando " + Math.round(daño) + " de daño");
                System.out.println("Salud de " + defensor.getNombre() + ": " + defensor.getSalud());
                logCombate.append(mensajeTurno).append("\n");
                logCombate.append("Salud de ").append(defensor.getNombre()).append(": ").append(defensor.getSalud()).append("\n");

                if (defensor.getSalud() <= 0) {
                    System.out.println(ANSI_RED + defensor.getNombre() + " ha sido derrotado!" + ANSI_RESET);
                    logCombate.append(defensor.getNombre()).append(" ha sido derrotado!\n");
                    eliminarPersonaje(defensor == personaje1 ? jugador1 : jugador2, defensor);
                    mejorarPersonaje(atacante);
                    jugadorPerdedor = defensor == personaje1 ? jugador1 : jugador2;
                    break;
                }

                personaje guardarJ = atacante;
                atacante = defensor;
                defensor = guardarJ;
            }

            if (jugador1.getPersonajes().isEmpty() || jugador2.getPersonajes().isEmpty()) {
                combateTerminado = true;
            }
        }

        jugador ganador = jugadorPerdedor == jugador1 ? jugador2 : jugador1;
        System.out.println(ANSI_YELLOW + "╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                     FIN DEL COMBATE                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "¡El ganador es " + ganador.getNombre() + "!" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "¡Eres el Rey de Reyes!" + ANSI_RESET);
        logCombate.append("El ganador es ").append(ganador.getNombre()).append("!\n");
        logCombate.append("¡Eres el Rey de Reyes!\n");
        
        System.out.println(ANSI_CYAN + "\nPersonajes sobrevivientes de " + ganador.getNombre() + ":" + ANSI_RESET);
        logCombate.append("Personajes sobrevivientes de ").append(ganador.getNombre()).append(":\n");
        for (personaje p : ganador.getPersonajes()) {
            System.out.println(ANSI_GREEN + "- " + p.getNombre() + " (" + p.getApodo() + ") - Salud: " + p.getSalud() + ANSI_RESET);
            logCombate.append("- ").append(p.getNombre()).append(" (").append(p.getApodo()).append(") - Salud: ").append(p.getSalud()).append("\n");
        }
        
        logManager.guardarLog(logCombate.toString());
    }

    private static personaje seleccionarPersonajeAleatorio(jugador jugador) {
        ArrayList<personaje> personajes = jugador.getPersonajes();
        return personajes.get(rand.nextInt(personajes.size()));
    }

    private static void eliminarPersonaje(jugador jugador, personaje personaje) {
        jugador.eliminarPersonaje(personaje);
    }

    private static void mejorarPersonaje(personaje personaje) {
        if (rand.nextBoolean()) {
            personaje.actualizarVida(10);
            System.out.println(personaje.getNombre() + " ha ganado 10 puntos de salud!");
        } else {
            System.out.println(personaje.getNombre() + " ha subido de nivel!");
        }
    }
}
