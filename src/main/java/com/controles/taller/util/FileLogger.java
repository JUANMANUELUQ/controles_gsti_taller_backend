package com.controles.taller.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileLogger {

    private static final Logger LOG = LoggerFactory.getLogger(FileLogger.class);
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

        // Log via SLF4J so the message is captured by platform log collectors (stdout/stderr)
        LOG.info(logCompleto);

    }

    public static void main(String[] args) {
        log("hola");
    }
}