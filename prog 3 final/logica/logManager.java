package logica;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class logManager {
    private static final String LOG_FILE = "combate_logs.txt";

    public static void guardarLog(String log) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            out.println(log);
        } catch (IOException e) {
            System.out.println("Error al guardar el log: " + e.getMessage());
        }
    }

    public static List<String> leerLogs() {
        List<String> logs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                logs.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error al leer los logs: " + e.getMessage());
        }
        return logs;
    }

    public static void borrarLogs() {
        File file = new File(LOG_FILE);
        if (file.delete()) {
            System.out.println("Archivo de logs borrado exitosamente.");
        } else {
            System.out.println("No se pudo borrar el archivo de logs.");
        }
    }
}
