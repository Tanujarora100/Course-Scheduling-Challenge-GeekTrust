package com.geektrust.backend;

import com.geektrust.backend.config.ObjectPool;
import command.CommandInvoker;
import service.FileProcessorService;

import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <file_path>");
            System.exit(1);
        }

        String filePath = args[0];

        ObjectPool objectPool = new ObjectPool();
        CommandInvoker commandInvoker = objectPool.getCommandInvoker();

        FileProcessorService fileProcessor = null;
        try {
            fileProcessor = new FileProcessorService(filePath, commandInvoker);
            fileProcessor.processCommands();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
}


