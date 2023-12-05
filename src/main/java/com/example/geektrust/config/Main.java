package com.example.geektrust.config;

import com.example.geektrust.command.CommandFactory;
import com.example.geektrust.exception.InvalidCommandException;
import com.example.geektrust.utils.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        if (args.length != 1) {
            Utils.printCommand("Command Line File Missing");
        }
        List<String> commandLineInputFile = new LinkedList<>(Arrays.asList(args));
        driver(commandLineInputFile);
    }

    private static void driver(List<String> commandLineInputFile) {
        AppConfig appConfig = new AppConfig();
        CommandFactory commandFactory = appConfig.getCommandFactory();
        String inputFile = commandLineInputFile.get(0);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line = reader.readLine();
            while (line != null) {
                processCommand(commandFactory, line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processCommand(CommandFactory commandFactory, String commandLine) {
        try {
            String[] commandDetails = commandLine.split(" ");
            commandFactory.fireCommand(commandDetails[0], commandDetails);
        } catch (IllegalArgumentException | InvalidCommandException e) {
            Utils.printCommand(e.getMessage());
        }
    }
}
