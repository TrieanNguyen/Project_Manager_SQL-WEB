package Model.Class;

import java.sql.Date;
import java.sql.Time;

public class Task {

    //<editor-fold desc = "Properties">
    private int id, projectId, modifiedBy, deletedBy, employeeId, levelTaskCompleted, attitude, departmentId;
    private double coefficient;
    private String name, linkCheckTask, note, style;
    private Date modifiedDate, deletedDate, timeStart, estimatedTimeEnd;
    private Boolean deleted, completed;
    //</editor-fold>

    //<editor-fold desc = "Get & set properties">
    public Task(){

    }
    public Task(int id, int projectId, int modifiedBy, int deletedBy, int employeeId, double coefficient
    , String name, Date modifiedDate, Date deletedDate, Boolean deleted, int levelTaskCompleted, int attitude
    , String linkCheckTask, String note, Date timeStart, Date estimatedTimeEnd, Boolean completed, int departmentId){
        this.id = id;
        this.projectId = projectId;
        this.modifiedBy = modifiedBy;
        this.deletedBy = deletedBy;
        this.employeeId = employeeId;
        this.coefficient = employeeId;
        this.coefficient = coefficient;
        this.name = name;
        this.modifiedDate = modifiedDate;
        this.deletedDate = deletedDate;
        this.deleted = deleted;
        this.levelTaskCompleted = levelTaskCompleted;
        this.attitude = attitude;
        this.linkCheckTask = linkCheckTask;
        this.note = note;
        this.timeStart = timeStart;
        this.estimatedTimeEnd = estimatedTimeEnd;
        this.completed = completed;
        this.departmentId = departmentId;
    }
    public Task(int id, int projectId, int modifiedBy, int deletedBy, int employeeId, double coefficient
            , String name, Date modifiedDate, Date deletedDate, Boolean deleted, int levelTaskCompleted, int attitude
            , String linkCheckTask, String note, Date timeStart, Date estimatedTimeEnd, Boolean completed, int departmentId, String style){
        this.id = id;
        this.projectId = projectId;
        this.modifiedBy = modifiedBy;
        this.deletedBy = deletedBy;
        this.employeeId = employeeId;
        this.coefficient = employeeId;
        this.coefficient = coefficient;
        this.name = name;
        this.modifiedDate = modifiedDate;
        this.deletedDate = deletedDate;
        this.deleted = deleted;
        this.levelTaskCompleted = levelTaskCompleted;
        this.attitude = attitude;
        this.linkCheckTask = linkCheckTask;
        this.note = note;
        this.timeStart = timeStart;
        this.estimatedTimeEnd = estimatedTimeEnd;
        this.completed = completed;
        this.departmentId = departmentId;
        this.style = style;
    }
    //</editor-fold>

    //<editor-fold desc = "Get & set properties">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public int getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(int deletedBy) {
        this.deletedBy = deletedBy;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        deleted = deleted;
    }

    public int getLevelTaskCompleted() {
        return levelTaskCompleted;
    }

    public void setLevelTaskCompleted(int levelTaskCompleted) {
        this.levelTaskCompleted = levelTaskCompleted;
    }

    public int getAttitude() {
        return attitude;
    }

    public void setAttitude(int attitude) {
        this.attitude = attitude;
    }

    public String getLinkCheckTask() {
        return linkCheckTask;
    }

    public void setLinkCheckTask(String linkCheckTask) {
        this.linkCheckTask = linkCheckTask;
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

    public Date getEstimatedTimeEnd() {
        return estimatedTimeEnd;
    }

    public void setEstimatedTimeEnd(Date estimatedTimeEnd) {
        this.estimatedTimeEnd = estimatedTimeEnd;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }


    //</editor-fold>

}
