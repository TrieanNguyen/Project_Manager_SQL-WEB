package Model.Class;

public class SalaryStatistical {
    private int id;
    private String employeeName, position, departmentName;
    double sumSalary;

    public SalaryStatistical(){
    }
    public SalaryStatistical(int id, String employeeName, String position, String departmentName, double sumSalary){

        this.id = id;
        this.employeeName = employeeName;
        this.position = position;
        this.departmentName = departmentName;
        this.sumSalary = sumSalary;
    }
    public double getSumSalary(){
        return sumSalary;
    }
    public void setSumSalary(double sumSalary){
        this.sumSalary = sumSalary;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
