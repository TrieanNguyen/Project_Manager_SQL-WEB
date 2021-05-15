package Model.Class;

import java.sql.Date;
import java.sql.Time;

public class Project {

    //<editor-fold desc = "Properties">
    private int id, leader, departmentId, modifiedBy, deletedBy, attitudeLeader, levelProjectCompleted;
    private double totalRevenue, progress;
    private String name, contract, note, style;
    private Date timeStart, estimatedTimeEnd, modifiedDate, deletedDate;
    private Boolean deleted;
    //</editor-fold>

    //<editor-fold desc = "Properties">
    public Project(){

    }
    public Project(int id, int leader, String contract, int departmentId, int modifiedBy, int deletedBy, double totalRevenue
    , double progress, String name, Date timeStart, Date estimatedTimeEnd, Date modifiedDate, Date deletedDate, Boolean deleted
    , int attitudeLeader, int levelProjectCompleted, String note){
        this.id = id;
        this.leader = leader;
        this.contract = contract;
        this.departmentId = departmentId;
        this.modifiedBy = modifiedBy;
        this.deletedBy = deletedBy;
        this.totalRevenue = totalRevenue;
        this.progress = progress;
        this.name = name;
        this.timeStart = timeStart;
        this.estimatedTimeEnd = estimatedTimeEnd;
        this.modifiedDate = modifiedDate;
        this.deletedDate = deletedDate;
        this.deleted = deleted;
        this.attitudeLeader = attitudeLeader;
        this.levelProjectCompleted = levelProjectCompleted;
        this.note = note;
    }
    //</editor-fold>

    //<editor-fold desc = "Get & set properties">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLeader() {
        return leader;
    }

    public void setLeader(int leader) {
        this.leader = leader;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
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

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.deleted = deleted;
    }

    public int getAttitudeLeader() {
        return attitudeLeader;
    }

    public void setAttitudeLeader(int attitudeLeader) {
        this.attitudeLeader = attitudeLeader;
    }

    public int getLevelProjectCompleted() {
        return levelProjectCompleted;
    }

    public void setLevelProjectCompleted(int levelProjectCompleted) {
        this.levelProjectCompleted = levelProjectCompleted;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
    //</editor-fold>

}
