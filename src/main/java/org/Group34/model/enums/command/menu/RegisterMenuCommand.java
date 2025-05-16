package org.Group34.model.enums.command.menu;

import org.Group34.model.enums.command.Command;

public enum RegisterMenuCommand implements Command {
    Register ("\\s*register\\s+-u\\s+(?<username>\\S+)\\s+-p\\s+(?<password>\\S+)\\s+(?<passwordConfirm>\\S+)\\s+-n\\s+(?<nickname>\\S+)\\s+-e\\s+(?<email>\\S+)\\s+-g\\s+(?<gender>\\S+)\\s*"),
    RegisterWithRandomPassword ("\\s*register\\s+-u\\s+(?<username>\\S+)\\s+-p\\s+random\\s+-n\\s+(?<nickname>\\S+)\\s+-e\\s+(?<email>\\S+)\\s+-g\\s+(?<gender>\\S+)\\s*"),
    PickQuestion ("\\s*pick\\s+question\\s+-q\\s+(?<questionNumber>[0-9]+)\\s+-a\\s+(?<answer>.+)\\s+-c\\s+(?<answerConfirm>.+)\\s*"),
    GO_TO_LOGIN("\\s*go\\s+to\\s+login\\s*"),

    SHOW_MENU("show\\s+current\\s+menu"),

    EXIT("menu\\s+exit");;

    private final String regex;

    RegisterMenuCommand(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
