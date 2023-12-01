package command;

import exception.DataValidationException;

import java.util.List;

public interface ICommand {
    void execute(List<String> params) throws DataValidationException;
}
