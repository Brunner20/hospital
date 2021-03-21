package com.hospital.controller.command;

import com.hospital.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {

    private Map<CommandName,Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.LOGIN, new Login());
        commands.put(CommandName.REGISTRATION, new Registration());
        commands.put(CommandName.ADDACCOUNT, new AddAccount());
        commands.put(CommandName.LOGOUT, new Logout());
        commands.put(CommandName.GOTOINDEXPAGE, new GoToIndexPage());
        commands.put(CommandName.ADDADDITIONALINFOPAGE, new AddAdditionalInfoPage());
        commands.put(CommandName.GOTOMAINSTAFFPAGE, new GoToMainStaffPage());
        commands.put(CommandName.GOTOMAINPATIENTPAGE, new GoToMainPatientPage());
        commands.put(CommandName.CHANGELOCALE, new ChangeLocale());
        commands.put(CommandName.GOTODOCTORSPATIENTSPAGE, new GoToDoctorsPatientsPage());
        commands.put(CommandName.GOTOFREEPATIENTSPAGE, new GoToFreePatientsPage());
        commands.put(CommandName.ADDPATIENTSTODOCTOR, new AddPatientsToDoctor());
        commands.put(CommandName.GOTOPROFILEPAGE, new GoToProfilePage());
    }

    public Command takeCommand(String name){

       CommandName commandName = CommandName.valueOf(name.toUpperCase());
       return commands.get(commandName);

    }
}
