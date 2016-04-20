/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIEXmlClasses;

import java.util.List;

/**
 *
 * @author Sumit_Saseendran
 */
public interface PluginType {

    String getCommandFile();

    String getCommandFileForce();

    String getCommandStd();

    String getCommandStdForce();

    String getCopyPayload();

    FeatureSetInfo getFeatureSetInfo();

    String getModules();

    List<Returncodes> getReturnCodeList();

    String getStartFileName();

    String getSystemReboot();

    String getTimeout();

    UnsupportedOSInfo getUnsupportedOSInfo();

    void setCommandFile(String commandFile);

    void setCommandFileForce(String commandFileForce);

    void setCommandStd(String commandStd);

    void setCommandStdForce(String commandStdForce);

    void setCopyPayload(String copyPayload);

    void setModules(String modules);

    void setStartFileName(String startFileName);

    void setSystemReboot(String systemReboot);

    void setTimeout(String timeout);
    
    String getCommandFileName();

    String getCommandFileForceName();
    
}
