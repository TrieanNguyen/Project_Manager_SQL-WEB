package Model.Class;

import Controller.Utils.MyUtils;

import java.sql.Time;

public class User {

    //<editor-fold desc = "Properties">
    private int employeeId, modifiedBy;
    private String passWord;
    private Time modifiedDate;
    int type;
    //</editor-fold>

    //<editor-fold desc = "Constructors">
    public User()
    {

    }

    public User(int employeeID, String passWord, int modifiedBy, int type){
        setEmployeeId(employeeID);
        setPassWord(passWord);
        setType(type);
        setModifiedBy(modifiedBy);
    }
    //</editor-fold>

    //<editor-fold desc = "Get & set properties">
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Time getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Time modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getType(){
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }
    //</editor-fold>

}
