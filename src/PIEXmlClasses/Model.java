/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIEXmlClasses;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sumit_Saseendran
 */
public class Model {

    public Model(String sysId, String sysIdtype, String sysContext, String display) {
        String[] displaylist = display.split(",");
        displayTextList = new ArrayList<>();
        for(String s:displaylist){
            displayTextList.add(new DisplayType(s,"en"));
        }
        this.systemContext=SystemContextEnum.fromValue(sysContext);
        this.systemIdType = SystemIdTypeEnum.fromValue(sysIdtype);
        this.systemID=sysId;
    }

    public String getSystemID() {
        return systemID;
    }

    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }

    public SystemContextEnum getSystemContext() {
        return systemContext;
    }

    public void setSystemContext(SystemContextEnum systemContext) {
        this.systemContext = systemContext;
    }

    public SystemIdTypeEnum getSystemIdType() {
        return systemIdType;
    }

    public void setSystemIdType(SystemIdTypeEnum systemIdType) {
        this.systemIdType = systemIdType;
    }

    public List<DisplayType> getDisplayTextList() {
       
        return displayTextList;
    }


    //model information
    private String systemID;
    private SystemContextEnum systemContext;
    private SystemIdTypeEnum systemIdType;
    private List<DisplayType> displayTextList;
}
