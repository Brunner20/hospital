package com.hospital.controller.command;

import com.hospital.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {

    private Map<CommandName,Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.LOGIN, new Login());
        commands.put(CommandName.LOGOUT, new Logout());
        commands.put(CommandName.ADDACCOUNT, new AddAccount());
        commands.put(CommandName.CHANGELOCALE, new ChangeLocale());
        commands.put(CommandName.REGISTRATION, new Registration());
        commands.put(CommandName.GOTOMAINPAGE, new GoToMainPage());
        commands.put(CommandName.GOTOINDEXPAGE, new GoToIndexPage());
        commands.put(CommandName.UPDATEPASSWORD,new UpdatePassword());
        commands.put(CommandName.GOTOPROFILEPAGE, new GoToProfilePage());
        commands.put(CommandName.GOTOPASSWORDUPDATEPAGE, new GoToPasswordUpdatePage());

        commands.put(CommandName.ADDEPICRISIS, new AddEpicrisis());
        commands.put(CommandName.ADDAPPOINTMENT, new AddAppointment());
        commands.put(CommandName.GOTOEPICRISISPAGE,new GoToEpicrisisPage());
        commands.put(CommandName.GOTORECEIPTDATEPAGE,new GoToReceiptDatePage());
        commands.put(CommandName.ADDPATIENTSTODOCTOR, new AddPatientsToDoctor());
        commands.put(CommandName.GOTOFREEPATIENTSPAGE, new GoToFreePatientsPage());
        commands.put(CommandName.GOTOADDAPPOINTMENTPAGE, new GoToAddAppointmentPage());
        commands.put(CommandName.GOTODOCTORSPATIENTSPAGE, new GoToDoctorsPatientsPage());
        commands.put(CommandName.UPDATEAPPOINTMENTSTATUS, new UpdateAppointmentStatus());
        commands.put(CommandName.GOTOADDAPPOINTMENTNEXTPAGE, new GoToAddAppointmentNextPage());
        commands.put(CommandName.GOTOSTAFFAPPOINTMENTLISTPAGE, new GoToStaffAppointmentListPage());

        commands.put(CommandName.GOTOADDSTAFFPAGE, new GoToAddStaffPage());
        commands.put(CommandName.SUBMITAPPLICATION, new SubmitApplication());
        commands.put(CommandName.ADDADDITIONALINFO, new AddAdditionalInfo());
        commands.put(CommandName.GOTOMEDICALHISTORYPAGE,new GoToMedicalHistoryPage());
        commands.put(CommandName.GOTOADDADDITIONALINFOPAGE, new GoToAddAdditionalInfoPage());
        commands.put(CommandName.GOTOPATIENTAPPOINTMENTLISTPAGE, new GoToPatientAppointmentListPage());
    }

    public Command takeCommand(String name){
       CommandName commandName = CommandName.valueOf(name.toUpperCase());
       return commands.get(commandName);

    }
}
