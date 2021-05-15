package Model.Class;


import java.sql.Date;
import java.sql.Time;

public class Employee {

    //<editor-fold desc = "Properties">
    private int id, departmentId, deletedBy, attitude, levelWorkCompletion, modifiedBy;
    private double salary;
    private String name, sex, phone, email, address, position, degree, portrait, advantage, disadvantage, foreignLanguage, note;
    private Date birthDate, timeStart, modifiedDate;
    private boolean deleted;
    //</editor-fold>

    //<editor-fold desc = "Constructors"
    public Employee(){

    }
    public Employee(int id, String name){
        this.id = id;
        this.name = name;
    }
    public Employee(int id, int departmentId, int deletedBy, double salary, String name, String sex, String phone, String email, String address,
                    String position, String degree, String portrait, Date birthDate, boolean deleted, String advantage, String disadvantage,
                    String foreignLanguage, int attitude, int levelWorkCompletion, String note, Date timeStart, int modifiedBy, Date modifiedDate){
        this.id = id;
        this.departmentId = departmentId;
        this.deletedBy = deletedBy;
        this.salary = salary;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.position = position;
        this.degree = degree;
        this.portrait = portrait;
        this.birthDate = birthDate;
        this.deleted = deleted;
        this.advantage = advantage;
        this.disadvantage = disadvantage;
        this.foreignLanguage = foreignLanguage;
        this.attitude = attitude;
        this.levelWorkCompletion = levelWorkCompletion;
        this.note = note;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }
    //</editor-fold>

    //<editor-fold desc = "Get & set properties"
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public int getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(int deletedBy) {
        this.deletedBy = deletedBy;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getAttitude() {
        return attitude;
    }

    public void setAttitude(int attitude) {
        this.attitude = attitude;
    }

    public int getLevelWorkCompletion() {
        return levelWorkCompletion;
    }

    public void setLevelWorkCompletion(int levelWorkCompletion) {
        this.levelWorkCompletion = levelWorkCompletion;
    }

    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }

    public String getDisadvantage() {
        return disadvantage;
    }

    public void setDisadvantage(String disadvantage) {
        this.disadvantage = disadvantage;
    }

    public String getForeignLanguage() {
        return foreignLanguage;
    }

    public void setForeignLanguage(String foreignLanguage) {
        this.foreignLanguage = foreignLanguage;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    //</editor-fold>

}
