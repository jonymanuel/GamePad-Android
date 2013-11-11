package com.gamepad.lib.cmd;

/**
 * Author: root
 * Date: 07.10.13.
 */
public interface ICommand
{
    public String[] getArguments();
    public String getCommandString();
    public Boolean runCommand();
    public void setArguments();
}
