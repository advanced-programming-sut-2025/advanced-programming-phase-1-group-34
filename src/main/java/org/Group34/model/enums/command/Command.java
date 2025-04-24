package main.java.org.Group34.model.enums.command;

/**
 * Each command defines a regular expression pattern used to match user input
 *
 * This interface should be implemented by enums representing commands
 */

public interface Command {
    /**
     * Returns the regular expression pattern associated with the command.
     *
     * @return the regex pattern for matching user input
     */
    String getRegex();
}
