package com.example.geektrust.command;

import com.example.geektrust.exception.InvalidCommandException;
import com.example.geektrust.utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    //Make it singleton
    private static final CommandFactory INSTANCE = new CommandFactory();

    public static CommandFactory getInstance() {
        return INSTANCE;
    }

    private static final Map<String, ICommand> commandMap = new HashMap<>();

    public void registerNewCommand(String commandName, ICommand commandObject) {
        commandMap.put(commandName, commandObject);
    }

    private ICommand getCommand(String commandName) {
        return commandMap.get(commandName);
    }

    public void fireCommand(String command, String[] commandDetails) {
        ICommand commandObject = getCommand(command);
        if (commandObject == null)
            throw new InvalidCommandException(Utils.INVALID_COMMAND_RESPONSE);
        commandObject.execute(commandDetails);
    }
}
