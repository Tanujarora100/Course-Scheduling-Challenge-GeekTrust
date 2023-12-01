package command;

import exception.DataValidationException;
import exception.InvalidCommandException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandInvoker {
    private static final Map<String, ICommand> commandMap = new HashMap<>();

    public void register(String commandName, ICommand command) {
        commandMap.put(commandName, command);

    }

    private ICommand get(String commandName) {
        return commandMap.get(commandName);
    }

    public void executeCommand(String commandName, List<String> params) {
        ICommand command = get(commandName);
        try {
            if (command != null) {
                command.execute(params);
            }
        } catch (InvalidCommandException e) {
            System.out.println("Invalid command");
        } catch (DataValidationException e) {
            System.out.println(e.getMessage());
        }
    }


}
