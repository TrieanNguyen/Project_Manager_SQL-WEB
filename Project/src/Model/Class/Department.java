package Model.Class;

import java.sql.Time;

public class Department {

    //<editor-fold desc = "Properties">
    private int id, leader, deletedBy;
    private double budget;
    private String name, roomNumber, style;
    private Time deletedDate;
    private boolean deleted;
    //</editor-fold>

    //<editor-fold desc = "Constructors">
    public Department(){

    }
    public Department(int id, String name){
        this.id = id;
        this.name = name;
    }
    public Department(int id, int leader, int deletedBy, double budget
    , String name, String roomNumber, Time deletedDate, boolean deleted){
        this.id = id;
        this.leader = leader;
        this.budget = budget;
        this.name = name;
        this.roomNumber = roomNumber;
        if(deleted)
        {
            this.deleted = deleted;
            this.deletedBy = deletedBy;
            this.deletedDate = deletedDate;
        }
        else
            this.deleted = !deleted;
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

    public int getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(int deletedBy) {
        this.deletedBy = deletedBy;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Time getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Time deletedDate) {
        this.deletedDate = deletedDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
    //</editor-fold>

}
