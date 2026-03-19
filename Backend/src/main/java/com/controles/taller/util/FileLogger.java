package com.controles.taller.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger {

    private static final String LOG_PATH = "logs.txt";

    // Formato de fecha y hora
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Metodo para escribir logs
     */
    public static void log(String mensaje) {
        String fechaHora = LocalDateTime.now().format(FORMATTER);
        String logCompleto = fechaHora + " - " + mensaje;

        try (FileWriter fw = new FileWriter(LOG_PATH, true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(logCompleto);

        } catch (IOException e) {
            System.err.println("Error escribiendo log: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        log("hola");
    }
}