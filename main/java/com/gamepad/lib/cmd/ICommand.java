package com.gamepad.lib.cmd;

import org.json.JSONObject;

/**
 * Author: root
 * Date: 07.10.13.
 */
public interface ICommand
{
    public String getCommandString();
    public Boolean runCommand(JSONObject input) throws  Exception;
}
