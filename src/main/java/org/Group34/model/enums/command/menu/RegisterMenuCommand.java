package org.Group34.model.enums.command.menu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegisterMenuCommand {
    Register ("\\s*register\\s+-u\\s+(?<username>\\S+)\\s+-p\\s+(?<password>\\S+)\\s+(?<passwordConfirm>\\S+)\\s+-n\\s+(?<nickname>\\S+)\\s+-e\\s+(?<email>\\S+)\\s+-g\\s+(?<gender>\\S+)\\s*"),
    RegisterWithRandomPassword ("\\s*register\\s+-u\\s+(?<username>\\S+)\\s+-p\\s+random\\s+-n\\s+(?<nickname>\\S+)\\s+-e\\s+(?<email>\\S+)\\s+-g\\s+(?<gender>\\S+)\\s*"),
    PickQuestion ("\\s*pick\\s+question\\s+-q\\s+(?<questionNumber>[0-9]+)\\s+-a\\s+(?<answer>.+)\\s+-c\\s+(?<answerConfirm>.+)\\s*");

    private String pattern;

    RegisterMenuCommand(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);

        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
