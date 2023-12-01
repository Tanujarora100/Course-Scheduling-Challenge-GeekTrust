package service;

import command.CommandInvoker;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class FileProcessorService {
    private final File file;
    private final BufferedReader reader;
    private final CommandInvoker commandInvoker;

    public FileProcessorService(String filePath, CommandInvoker commandInvoker) throws FileNotFoundException {
        this.file = new File(filePath);
        this.reader = new BufferedReader(new FileReader(file));
        this.commandInvoker = commandInvoker;
    }

    public void processCommands() {
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> tokens = Arrays.asList(line.split(" "));
                commandInvoker.executeCommand(tokens.get(0), tokens);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private void close() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
