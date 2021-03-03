package com.hospital.controller.command;

import com.hospital.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private Map<CommandName,Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.LOGINATION, new Logination());
        commands.put(CommandName.REGISTRATION, new Registration());
        commands.put(CommandName.ADDACCOUNT, new AddAccount());
        commands.put(CommandName.LOGOUT, new Logout());
        commands.put(CommandName.GOTOINDEXPAGE,new GoToIndexPage());
        commands.put(CommandName.GOTOMAINPAGE, new GoToMainPage());
        commands.put(CommandName.GOTOMAINPATIENTPAGE, new GoToMainPatientPage());
    }

    public Command takeCommand(String name){

       CommandName commandName = CommandName.valueOf(name.toUpperCase());
       return commands.get(commandName);

    }
}
