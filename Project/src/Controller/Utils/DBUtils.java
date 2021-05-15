package Controller.Utils;


import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.sql.Date;

import Model.Class.*;
/*import sun.awt.geom.AreaOp;*/
import javax.servlet.http.HttpSession;
import javax.swing.table.TableRowSorter;

public class DBUtils {
    public static User findUser(Connection conn, //
                                       String employeeId, String password) throws SQLException {

        String sql = "exec findLogin ?, ?";

        PreparedStatement pS = conn.prepareStatement(sql);
        pS.setString(1, employeeId);
        pS.setString(2, password);
        ResultSet rs = pS.executeQuery();

        if (rs.next()) {
            Integer id = Integer.valueOf(employeeId);
            User user = new User(id, null,rs.getInt("modifiedBy"), rs.getInt("type"));
            return user;
        }
        return null;
    }
    public static Employee findUser(Connection conn, int employeeId) throws  SQLException {
        String sql = "exec getEmployee ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, employeeId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            int departmentId = resultSet.getInt("DepartmentID");
            Double salary = resultSet.getDouble("Salary");
            String name = resultSet.getString("Name");
            String sex = resultSet.getString("Sex");
            String phone = resultSet.getString("Phone");
            String email = resultSet.getString("Email");
            String address = resultSet.getString("Address");
            String position = resultSet.getString("Position");
            String degree = resultSet.getString("Degree");
            String portrait = resultSet.getString("Portrait");
            Date birthDate = resultSet.getDate("BirthDate");
            String advantage = resultSet.getString("Advantage");
            String disadvantage = resultSet.getString("Disadvantage");
            String foreignLanguage = resultSet.getString("ForeignLanguage");
            int attitude = resultSet.getInt("Attitude");
            int leverWorkCompletion = resultSet.getInt("LevelWorkCompletion");
            String note = resultSet.getString("Note");
            Date timeStart = resultSet.getDate("timeStart");
            int modifiedBy = resultSet.getInt("modifiedBy");
            Date modifiedDate = resultSet.getDate("modifiedDate");
/*(int id, int departmentId,int deletedBy, double salary, String name, String sex, String phone, String email, String address,
                    String position, String degree, String portrait, Time birthDate, boolean deleted, String advantage, String disadvantage,
                    String foreignLanguage, int attitude, int levelWorkCompletion, String note)*/
            Employee employee = new Employee(employeeId, departmentId, 0 ,salary, name, sex, phone, email, address, position, degree
            , portrait, birthDate, false, advantage, disadvantage, foreignLanguage, attitude, leverWorkCompletion, note, timeStart, modifiedBy, modifiedDate);
            return employee;
        }
        return null;
    }
    public static List<Department> getListDepartment(Connection conn) throws SQLException{
        String sql = "Select * from Department       " +
                "     where Deleted = 'False' or Deleted is Null ";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Department> departmentList = new ArrayList<>();
        if(resultSet.next()){
            System.out.println(resultSet.getInt("ID"));
            int iD = resultSet.getInt("ID");
            String name = resultSet.getString("name");
            String roomNumber = resultSet.getString("RoomNumber");
            double budget = resultSet.getDouble("Budget");
            int leader = resultSet.getInt("Leader");
            Department department = new Department(iD, leader, 0, budget, name,
                    roomNumber, null, false);
            departmentList.add(department);
        }
        return departmentList;
    }
    public static Map<Department, String> getListNameLeaderOfDepartment (Connection conn) throws SQLException{
        String sql = "exec getAllDepartmentAndNameLeader";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        Map<Department, String> listNameLeaderOfDepartment = new HashMap<Department, String>();
        while(resultSet.next()){
            int iD = resultSet.getInt("ID");
            String name = resultSet.getString("name");
            String roomNumber = resultSet.getString("RoomNumber");
            double budget = resultSet.getDouble("Budget");
            int leader = resultSet.getInt("Leader");
            Department department = new Department(iD, leader, 0, budget, name,
                    roomNumber, null, false);
            listNameLeaderOfDepartment.put(department, resultSet.getString("leaderName"));
        }
        return  listNameLeaderOfDepartment;
    }
    public static Map<Department, String> getListNameLeaderOfDepartment (Connection conn, int departmentId) throws SQLException{
        String sql = "exec getAllDepartmentAndNameLeader";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        Map<Department, String> listNameLeaderOfDepartment = new HashMap<Department, String>();
        while(resultSet.next()){
            int iD = resultSet.getInt("ID");
            String name = resultSet.getString("name");
            String roomNumber = resultSet.getString("RoomNumber");
            double budget = resultSet.getDouble("Budget");
            int leader = resultSet.getInt("Leader");
            Department department = new Department(iD, leader, 0, budget, name,
                    roomNumber, null, false);
            if(iD != departmentId){
                department.setStyle("display: none");
            }
            listNameLeaderOfDepartment.put(department, resultSet.getString("leaderName"));
        }
        return  listNameLeaderOfDepartment;
    }
    public static Map<Department, String> getListNameLeaderOfDepartmentofLeader (Connection conn, User user) throws SQLException{
        String sql = "exec getAllDepartmentAndNameLeaderOfDepartment ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, user.getEmployeeId());
        ResultSet resultSet = preparedStatement.executeQuery();
        Map<Department, String> listNameLeaderOfDepartment = new HashMap<Department, String>();
        while(resultSet.next()){
            int iD = resultSet.getInt("ID");
            String name = resultSet.getString("name");
            String roomNumber = resultSet.getString("RoomNumber");
            double budget = resultSet.getDouble("Budget");
            int leader = resultSet.getInt("Leader");
            Department department = new Department(iD, leader, 0, budget, name,
                    roomNumber, null, false);
            listNameLeaderOfDepartment.put(department, resultSet.getString("leaderName"));
        }
        return  listNameLeaderOfDepartment;
    }
    public static Map<Department, String> getListNameLeaderOfDepartmentofEmployee (Connection conn, User user) throws SQLException{
        String sql = "exec getAllDepartmentAndNameLeaderOfEmployee ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, user.getEmployeeId());
        ResultSet resultSet = preparedStatement.executeQuery();
        Map<Department, String> listNameLeaderOfDepartment = new HashMap<Department, String>();
        while(resultSet.next()){
            int iD = resultSet.getInt("ID");
            String name = resultSet.getString("name");
            String roomNumber = resultSet.getString("RoomNumber");
            double budget = resultSet.getDouble("Budget");
            int leader = resultSet.getInt("Leader");
            Department department = new Department(iD, leader, 0, budget, name,
                    roomNumber, null, false);
            listNameLeaderOfDepartment.put(department, resultSet.getString("leaderName"));
        }
        return  listNameLeaderOfDepartment;
    }
    public static int countDepartment(Connection conn) throws SQLException{
        String sql = "exec countDepartment";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        int countDepartment = -1;
        if(resultSet.next()){
            countDepartment = resultSet.getInt("COUNTDEPARTMENT");
        }

        return Integer.valueOf(countDepartment);
    }
    public static void insertDepartment(Connection conn, Department department) throws SQLException{
        String sql = "exec insertDepartment ?, ?, ?, ?, ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, department.getId());
        preparedStatement.setString(2, department.getName());
        preparedStatement.setString(3, department.getRoomNumber());
        preparedStatement.setDouble(4, department.getBudget());
        preparedStatement.setInt(5, department.getLeader());
        preparedStatement.executeUpdate();
        /*(iD,name,  roomNumber, budget, leader)*/
    }
    public static Department findDepartment(Connection conn, int departmentId) throws SQLException{
        String sql = "exec findDepartment ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, departmentId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Department department = null;
        if(resultSet.next())
        {
            int leader = resultSet.getInt("leader");
            double budget = resultSet.getDouble("budget");
            String name = resultSet.getString("name");
            String roomNumber = resultSet.getString("roomNumber");
            department = new Department(departmentId, leader, 0, budget, name, roomNumber, null, false);
        }
        return department;

        /*int id, int leader, int deletedBy, double budget
    , String name, String roomNumber, Time deletedDate, boolean deleted)*/

    }
    public static void editDepartment(Connection conn, Department department) throws SQLException{
        String sql = "exec editDepartment ?, ?, ?, ?, ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, department.getId());
        preparedStatement.setString(2, department.getName());
        preparedStatement.setString(3, department.getRoomNumber());
        preparedStatement.setDouble(4, department.getBudget());
        preparedStatement.setInt(5, department.getLeader());
        preparedStatement.executeUpdate();
    }
    public static void deleteDepartment(Connection connection, int idDepartment, HttpSession session) throws  SQLException{
        String sql = "exec deleteDepartment ?, ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idDepartment);
        User user = MyUtils.getLoginedUser(session);
        preparedStatement.setInt(2, user.getEmployeeId());
        preparedStatement.executeUpdate();
    }
    public static Map<Employee, String> getAllEmployeeAndDepartmentName(Connection connection) throws SQLException{
        String sql = "exec getAllEmployeeAndNameDepartment";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        Map<Employee, String> listEmployeeAndDepartmentName = new HashMap<Employee, String>();
        while (resultSet.next()){
            int id = resultSet.getInt("ID");
            int departmentId = resultSet.getInt("DepartmentID");
            Double salary = resultSet.getDouble("Salary");
            String name = resultSet.getString("Name");
            String sex = resultSet.getString("Sex");
            String phone = resultSet.getString("Phone");
            String email = resultSet.getString("Email");
            String address = resultSet.getString("Address");
            String position = resultSet.getString("Position");
            String degree = resultSet.getString("Degree");
            String portrait = resultSet.getString("Portrait");

           /* Date bD = new Date(resultSet.getTimestamp("BirthDate").getTime());*/
            Date birthDate = resultSet.getDate("BirthDate");
            String advantage = resultSet.getString("Advantage");
            String disadvantage = resultSet.getString("Disadvantage");
            String foreignLanguage = resultSet.getString("ForeignLanguage");
            int attitude = resultSet.getInt("Attitude");
            int leverWorkCompletion = resultSet.getInt("LevelWorkCompletion");
            String note = resultSet.getString("Note");
            Date timeStart = resultSet.getDate("timeStart");
            int modifiedBy = resultSet.getInt("modifiedBy");
            Date modifiedDate = resultSet.getDate("modifiedDate");
/*(int id, int departmentId,int deletedBy, double salary, String name, String sex, String phone, String email, String address,
                    String position, String degree, String portrait, Time birthDate, boolean deleted, String advantage, String disadvantage,
                    String foreignLanguage, int attitude, int levelWorkCompletion, String note)*/
            Employee employee = new Employee(id, departmentId, 0 ,salary, name, sex, phone, email, address, position, degree
                    , portrait, birthDate, false, advantage, disadvantage, foreignLanguage, attitude, leverWorkCompletion,
                    note, timeStart, modifiedBy, modifiedDate);

            String departmentName = resultSet.getString("departmentName");
            listEmployeeAndDepartmentName.put(employee, departmentName);
        }
        return listEmployeeAndDepartmentName;
        /*(int id, int departmentId,int deletedBy, double salary, String name, String sex, String phone, String email, String address,
                String position, String degree, String portrait, Time birthDate, boolean deleted)*/
    }
    public static Map<Employee, String> getAllEmployeeAndDepartmentNameOfDepartment(Connection connection, User user) throws SQLException{
        String sql = "exec getAllEmployeeAndNameDepartmentOfDepartment ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user.getEmployeeId());
        ResultSet resultSet = preparedStatement.executeQuery();
        Map<Employee, String> listEmployeeAndDepartmentName = new HashMap<Employee, String>();
        while (resultSet.next()){
            int id = resultSet.getInt("ID");
            int departmentId = resultSet.getInt("DepartmentID");
            Double salary = resultSet.getDouble("Salary");
            String name = resultSet.getString("Name");
            String sex = resultSet.getString("Sex");
            String phone = resultSet.getString("Phone");
            String email = resultSet.getString("Email");
            String address = resultSet.getString("Address");
            String position = resultSet.getString("Position");
            String degree = resultSet.getString("Degree");
            String portrait = resultSet.getString("Portrait");

            /* Date bD = new Date(resultSet.getTimestamp("BirthDate").getTime());*/
            Date birthDate = resultSet.getDate("BirthDate");
            String advantage = resultSet.getString("Advantage");
            String disadvantage = resultSet.getString("Disadvantage");
            String foreignLanguage = resultSet.getString("ForeignLanguage");
            int attitude = resultSet.getInt("Attitude");
            int leverWorkCompletion = resultSet.getInt("LevelWorkCompletion");
            String note = resultSet.getString("Note");
            Date timeStart = resultSet.getDate("timeStart");
            int modifiedBy = resultSet.getInt("modifiedBy");
            Date modifiedDate = resultSet.getDate("modifiedDate");
/*(int id, int departmentId,int deletedBy, double salary, String name, String sex, String phone, String email, String address,
                    String position, String degree, String portrait, Time birthDate, boolean deleted, String advantage, String disadvantage,
                    String foreignLanguage, int attitude, int levelWorkCompletion, String note)*/
            Employee employee = new Employee(id, departmentId, 0 ,salary, name, sex, phone, email, address, position, degree
                    , portrait, birthDate, false, advantage, disadvantage, foreignLanguage, attitude, leverWorkCompletion,
                    note, timeStart, modifiedBy, modifiedDate);

            String departmentName = resultSet.getString("departmentName");
            listEmployeeAndDepartmentName.put(employee, departmentName);
        }
        return listEmployeeAndDepartmentName;
        /*(int id, int departmentId,int deletedBy, double salary, String name, String sex, String phone, String email, String address,
                String position, String degree, String portrait, Time birthDate, boolean deleted)*/
    }
    public static Map<Employee, String> getAllEmployeeAndDepartmentNameOfEmployee(Connection connection, User user) throws SQLException{
        String sql = "exec getAllEmployeeAndNameDepartmentOfEmployee ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user.getEmployeeId());
        ResultSet resultSet = preparedStatement.executeQuery();
        Map<Employee, String> listEmployeeAndDepartmentName = new HashMap<Employee, String>();
        while (resultSet.next()){
            int id = resultSet.getInt("ID");
            int departmentId = resultSet.getInt("DepartmentID");
            Double salary = resultSet.getDouble("Salary");
            String name = resultSet.getString("Name");
            String sex = resultSet.getString("Sex");
            String phone = resultSet.getString("Phone");
            String email = resultSet.getString("Email");
            String address = resultSet.getString("Address");
            String position = resultSet.getString("Position");
            String degree = resultSet.getString("Degree");
            String portrait = resultSet.getString("Portrait");

            /* Date bD = new Date(resultSet.getTimestamp("BirthDate").getTime());*/
            Date birthDate = resultSet.getDate("BirthDate");
            String advantage = resultSet.getString("Advantage");
            String disadvantage = resultSet.getString("Disadvantage");
            String foreignLanguage = resultSet.getString("ForeignLanguage");
            int attitude = resultSet.getInt("Attitude");
            int leverWorkCompletion = resultSet.getInt("LevelWorkCompletion");
            String note = resultSet.getString("Note");
            Date timeStart = resultSet.getDate("timeStart");
            int modifiedBy = resultSet.getInt("modifiedBy");
            Date modifiedDate = resultSet.getDate("modifiedDate");
/*(int id, int departmentId,int deletedBy, double salary, String name, String sex, String phone, String email, String address,
                    String position, String degree, String portrait, Time birthDate, boolean deleted, String advantage, String disadvantage,
                    String foreignLanguage, int attitude, int levelWorkCompletion, String note)*/
            Employee employee = new Employee(id, departmentId, 0 ,salary, name, sex, phone, email, address, position, degree
                    , portrait, birthDate, false, advantage, disadvantage, foreignLanguage, attitude, leverWorkCompletion,
                    note, timeStart, modifiedBy, modifiedDate);

            String departmentName = resultSet.getString("departmentName");
            listEmployeeAndDepartmentName.put(employee, departmentName);
        }
        return listEmployeeAndDepartmentName;
        /*(int id, int departmentId,int deletedBy, double salary, String name, String sex, String phone, String email, String address,
                String position, String degree, String portrait, Time birthDate, boolean deleted)*/
    }
    public static int countEmployee(Connection conn) throws SQLException{
        String sql = "exec countEmployee";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        int countEmployee = -1;
        if(resultSet.next()){
            countEmployee = resultSet.getInt("COUNTEMPLOYEE");
        }

        return Integer.valueOf(countEmployee);
    }
    public static void insertEmployee(Connection connection, Employee employee) throws SQLException{
        String sql = "exec insertEmployee ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, employee.getId());
        preparedStatement.setInt(2, employee.getDepartmentId());
        preparedStatement.setDouble(3, employee.getSalary());
        preparedStatement.setString(4, employee.getName());
        preparedStatement.setString(5, employee.getSex());
        preparedStatement.setString(6, employee.getPhone());
        preparedStatement.setString(7, employee.getEmail());
        preparedStatement.setString(8, employee.getAddress());
        preparedStatement.setString(9, employee.getPosition());
        preparedStatement.setString(10, employee.getDegree());
        preparedStatement.setString(11,employee.getPortrait());
        preparedStatement.setDate(12, employee.getBirthDate());
        preparedStatement.setString(13, employee.getAdvantage());
        preparedStatement.setString(14, employee.getDisadvantage());
        preparedStatement.setString(15, employee.getForeignLanguage());
        preparedStatement.setInt(16, employee.getAttitude());
        preparedStatement.setString(17, employee.getNote());
        preparedStatement.setDate(18, employee.getTimeStart());
        preparedStatement.setInt(19,employee.getModifiedBy());
        preparedStatement.executeUpdate();

        /*insertEmployee(@id int, @departmentId int, @salary real, @name nvarchar(50), @sex nvarchar(50), @phone varchar(50), @email varchar(50)
, @address nvarchar(max), @position nvarchar(50), @degree nvarchar(50), @portrait nvarchar(max), @birthDate datetime, @advantage nvarchar(max),
@disadvantage nvarchar(max), @foreignLanguage nvarchar(50), @attitude int, @note nvarchar(max))*/
    }
    public static void insertUser(Connection connection, User user) throws SQLException{
        String sql = "exec insertUser ?, ?, ?, ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user.getEmployeeId());
        preparedStatement.setString(2,user.getPassWord());
        preparedStatement.setInt(3, user.getModifiedBy());
        preparedStatement.setInt(4, user.getType());
        preparedStatement.executeUpdate();
    }
    public static List<Project> getAllListProjectAndTask(Connection connection) throws SQLException{
        String sql = "exec getAllProject";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Project> listProject = new ArrayList<Project>();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int leader = resultSet.getInt("leader");
            String contract = resultSet.getString("contract");
            Date timeStart = resultSet.getDate("timeStart");
            Date estimatedTimeEnd = resultSet.getDate("estimatedTimeEnd");
            Double progress = resultSet.getDouble("progress");
            int departmentId = resultSet.getInt("departmentId");
            Date modifiedDate = resultSet.getDate("modifiedDate");
            int modifiedBy = resultSet.getInt("modifiedBy");
            Double totalRevenue = resultSet.getDouble("totalRevenue");
            int attitudeLeader = resultSet.getInt("attitudeLeader");
            int levelProjectCompleted = resultSet.getInt("levelProjectCompleted");
            String note = resultSet.getString("note");
            Project project = new Project(id, leader, contract, departmentId, modifiedBy,
                    0, totalRevenue, progress, name, timeStart, estimatedTimeEnd,
                    modifiedDate, null, false, attitudeLeader, levelProjectCompleted, note);
            listProject.add(project);
            /*sql = "exec 
 ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet1 = preparedStatement.executeQuery();
            List<Task> listTask = new ArrayList<Task>();
            while (resultSet1.next()){
                int taskId = resultSet1.getInt("id");
                String taskName = resultSet1.getString("name");
                Date taskTimeStart = resultSet1.getDate("timeStart");
                Date taskEstimatedTimeEnd = resultSet1.getDate("estimatedTimeEnd");
                Date taskModifiedDate = resultSet1.getDate("modifiedDate");
                int taskModifiedBy = resultSet1.getInt("modifiedDate");
                Double taskCoefficient = resultSet1.getDouble("coefficient");
                int taskEmployeeId = resultSet1.getInt("employeeId");
                Boolean taskCompleted = resultSet1.getBoolean("completed");
                int taskDepartmentId = resultSet1.getInt("departmentId");
                String taskLinkCheckTask = resultSet1.getString("linkCheckTask");
                int taskAttitude = resultSet1.getInt("attitude");
                int levelTaskCompleted = resultSet1.getInt("levelTaskCompleted");
                String taskNote = resultSet1.getString("note");
                Task task = new Task(taskId, id, taskModifiedBy, 0, taskEmployeeId, taskCoefficient, taskName, taskModifiedDate,
                        null, false, levelTaskCompleted, taskAttitude, taskLinkCheckTask, taskNote, taskTimeStart, taskEstimatedTimeEnd, taskCompleted);
                listTask.add(task);*/
                        /*Task(int id, int projectId, int modifiedBy, int deletedBy, int employeeId, double coefficient
    , String name, Date modifiedDate, Date deletedDate, Boolean deleted, int levelTaskCompleted, int attitude
    , String linkCheckTask, String note, Date timeStart, Date estimatedTimeEnd, Boolean completed)*/
            /*}*/

            /* Project(int id, int leader, int contract, int departmentId, int modifiedBy,
            int deletedBy, double totalRevenue
            , double progress, String name, Date timeStart, Date estimatedTimeEnd,
             Date modifiedDate, Date deletedDate, Boolean deleted)*/
        }
        return listProject;
    }
    public static List<Project> getAllListProjectAndTaskOfDepartment(Connection connection, User user) throws SQLException{
        String sql = "exec getAllProjectOfDepartment ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user.getEmployeeId());
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Project> listProject = new ArrayList<Project>();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int leader = resultSet.getInt("leader");
            String contract = resultSet.getString("contract");
            Date timeStart = resultSet.getDate("timeStart");
            Date estimatedTimeEnd = resultSet.getDate("estimatedTimeEnd");
            Double progress = resultSet.getDouble("progress");
            int departmentId = resultSet.getInt("departmentId");
            Date modifiedDate = resultSet.getDate("modifiedDate");
            int modifiedBy = resultSet.getInt("modifiedBy");
            Double totalRevenue = resultSet.getDouble("totalRevenue");
            int attitudeLeader = resultSet.getInt("attitudeLeader");
            int levelProjectCompleted = resultSet.getInt("levelProjectCompleted");
            String note = resultSet.getString("note");
            Project project = new Project(id, leader, contract, departmentId, modifiedBy,
                    0, totalRevenue, progress, name, timeStart, estimatedTimeEnd,
                    modifiedDate, null, false, attitudeLeader, levelProjectCompleted, note);
            listProject.add(project);

        }
        return listProject;
    }
    public static List<Project> getAllListProjectAndTaskOfDepartment(Connection connection, int departmentLeaderId, int projectLeaderId) throws SQLException{
        String sql = "exec getAllProjectOfDepartment ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, departmentLeaderId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Project> listProject = new ArrayList<Project>();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int leader = resultSet.getInt("leader");
            String contract = resultSet.getString("contract");
            Date timeStart = resultSet.getDate("timeStart");
            Date estimatedTimeEnd = resultSet.getDate("estimatedTimeEnd");
            Double progress = resultSet.getDouble("progress");
            int departmentId = resultSet.getInt("departmentId");
            Date modifiedDate = resultSet.getDate("modifiedDate");
            int modifiedBy = resultSet.getInt("modifiedBy");
            Double totalRevenue = resultSet.getDouble("totalRevenue");
            int attitudeLeader = resultSet.getInt("attitudeLeader");
            int levelProjectCompleted = resultSet.getInt("levelProjectCompleted");
            String note = resultSet.getString("note");
            Project project = new Project(id, leader, contract, departmentId, modifiedBy,
                    0, totalRevenue, progress, name, timeStart, estimatedTimeEnd,
                    modifiedDate, null, false, attitudeLeader, levelProjectCompleted, note);
            project.setStyle("display: none");
            List<Project> listProjectLeader = DBUtils.getAllListProjectAndTaskOfProject(connection, projectLeaderId);
            for (int i = 0; i < listProjectLeader.size(); i++) {
                if(listProjectLeader.get(i).getId() == id){
                    project.setStyle("");
                    break;
                }
            }
            listProject.add(project);
        }
        return listProject;
    }
    public static List<Project> getAllListProjectAndTaskOfProject(Connection connection, int employeeId) throws SQLException{
        String sql = "exec getAllProjectOfProject ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, employeeId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Project> listProject = new ArrayList<Project>();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int leader = resultSet.getInt("leader");
            String contract = resultSet.getString("contract");
            Date timeStart = resultSet.getDate("timeStart");
            Date estimatedTimeEnd = resultSet.getDate("estimatedTimeEnd");
            Double progress = resultSet.getDouble("progress");
            int departmentId = resultSet.getInt("departmentId");
            Date modifiedDate = resultSet.getDate("modifiedDate");
            int modifiedBy = resultSet.getInt("modifiedBy");
            Double totalRevenue = resultSet.getDouble("totalRevenue");
            int attitudeLeader = resultSet.getInt("attitudeLeader");
            int levelProjectCompleted = resultSet.getInt("levelProjectCompleted");
            String note = resultSet.getString("note");
            Project project = new Project(id, leader, contract, departmentId, modifiedBy,
                    0, totalRevenue, progress, name, timeStart, estimatedTimeEnd,
                    modifiedDate, null, false, attitudeLeader, levelProjectCompleted, note);
            listProject.add(project);

        }
        return listProject;
    }
    public static List<Project> getAllListProjectAndTaskOfEmployee(Connection connection, User user) throws SQLException{
        String sql = "exec getAllProjectOfEmployee ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user.getEmployeeId());
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Project> listProject = new ArrayList<Project>();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int leader = resultSet.getInt("leader");
            String contract = resultSet.getString("contract");
            Date timeStart = resultSet.getDate("timeStart");
            Date estimatedTimeEnd = resultSet.getDate("estimatedTimeEnd");
            Double progress = resultSet.getDouble("progress");
            int departmentId = resultSet.getInt("departmentId");
            Date modifiedDate = resultSet.getDate("modifiedDate");
            int modifiedBy = resultSet.getInt("modifiedBy");
            Double totalRevenue = resultSet.getDouble("totalRevenue");
            int attitudeLeader = resultSet.getInt("attitudeLeader");
            int levelProjectCompleted = resultSet.getInt("levelProjectCompleted");
            String note = resultSet.getString("note");
            Project project = new Project(id, leader, contract, departmentId, modifiedBy,
                    0, totalRevenue, progress, name, timeStart, estimatedTimeEnd,
                    modifiedDate, null, false, attitudeLeader, levelProjectCompleted, note);
            listProject.add(project);

            /* Project(int id, int leader, int contract, int departmentId, int modifiedBy,
            int deletedBy, double totalRevenue
            , double progress, String name, Date timeStart, Date estimatedTimeEnd,
             Date modifiedDate, Date deletedDate, Boolean deleted)*/
        }
        return listProject;
    }

    public static List<Task> getAllTaskByProjectId(Connection connection, int idProject, int employeeId) throws SQLException{
        String sql = "exec getAllTaskByProjectId ?, ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idProject);
        preparedStatement.setInt(2, employeeId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Task> listTask = new ArrayList<Task>();
        while (resultSet.next()){
            int taskId = resultSet.getInt("id");
            String taskName = resultSet.getString("name");
            Date taskTimeStart = resultSet.getDate("timeStart");
            Date taskEstimatedTimeEnd = resultSet.getDate("estimatedTimeEnd");
            Date taskModifiedDate = resultSet.getDate("modifiedDate");
            int taskModifiedBy = resultSet.getInt("modifiedBy");
            float taskCoefficient = resultSet.getFloat("coefficient");
            int taskEmployeeId = resultSet.getInt("employeeId");
            Boolean taskCompleted = resultSet.getBoolean("completed");
            int taskDepartmentId = resultSet.getInt("departmentId");
            String taskLinkCheckTask = resultSet.getString("linkCheckTask");
            int taskAttitude = resultSet.getInt("attitude");
            int levelTaskCompleted = resultSet.getInt("levelTaskCompleted");
            String taskNote = resultSet.getString("note");
            String style = resultSet.getString("style");
            Task task = new Task(taskId, idProject, taskModifiedBy, 0, taskEmployeeId, Math.floor(taskCoefficient * 100)/100, taskName, taskModifiedDate,
                    null, false, levelTaskCompleted, taskAttitude, taskLinkCheckTask, taskNote, taskTimeStart, taskEstimatedTimeEnd, taskCompleted, taskDepartmentId, style);
            listTask.add(task);
        }
        return listTask;
    }
    public static void deleteProject(Connection connection, int idProject, int deletedBy) throws SQLException{
        String sql = "exec deleteProject ?, ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idProject);
        preparedStatement.setInt(2, deletedBy);
        preparedStatement.executeUpdate();
    }
    public static Project findProject(Connection connection, int idProject) throws SQLException{
        String sql = "exec findProject ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idProject);
        ResultSet resultSet = preparedStatement.executeQuery();
        Project project = null;
        if(resultSet.next()){
            int leader = resultSet.getInt("leader");
            String contract = resultSet.getString("contract");
            int departmentId = resultSet.getInt("departmentId");
            int modifiedBy = resultSet.getInt("modifiedBy");
            Date modifiedDate = resultSet.getDate("modifiedDate");
            /*int deletedBy = resultSet.getInt("deletedBy");*/
            double totalRevenue = resultSet.getDouble("totalRevenue");
            double progress = resultSet.getDouble("progress");
            String name = resultSet.getString("name");
            Date timStart = resultSet.getDate("timeStart");
            Date estimatedTimeEnd = resultSet.getDate("estimatedTimeEnd");
/*            Date deletedDate = resultSet.getDate("deletedDate");*/
            int attitudeLeader = resultSet.getInt("attitudeLeader");
            int levelProjectCompleted = resultSet.getInt("levelProjectCompleted");
            String note = resultSet.getString("note");
            project = new Project(idProject, leader, contract, departmentId, modifiedBy, 0, totalRevenue, progress, name, timStart, estimatedTimeEnd,
            modifiedDate, null, false, attitudeLeader, levelProjectCompleted, note);
                    /*Project(int id, int leader, String contract, int departmentId, int modifiedBy, int deletedBy, double totalRevenue
    , double progress, String name, Date timeStart, Date estimatedTimeEnd, Date modifiedDate, Date deletedDate, Boolean deleted
    , int attitudeLeader, int levelProjectCompleted)*/
        }
        return  project;
    }
    public static int countProject(Connection connection)throws SQLException{
        String sql = "exec countProject";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        int id = 0;
        if(resultSet.next())
        {
            id = resultSet.getInt("COUNTPROJECT");
        }
        return id;
    }
    public  static void insertProject(Connection connection, Project project) throws  SQLException{
        String sql = "exec insertProject ?, ?, ?, ?, ?, ?, ?, ?, ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, project.getId());
        preparedStatement.setString(2, project.getName());
        preparedStatement.setInt(3, project.getLeader());
        preparedStatement.setDate(4, project.getTimeStart());
        preparedStatement.setDate(5, project.getEstimatedTimeEnd());
        preparedStatement.setInt(6, project.getDepartmentId());
        preparedStatement.setDouble(7, project.getTotalRevenue());
        preparedStatement.setString(8, project.getNote());
        preparedStatement.setString(9, project.getContract());
        /*insertProject(@id int, @name nvarchar(50), @leader int, @timeStart Date, @estimatedTimeEnd Date, @departmentID int
 , @totalRevenue real, @note nvarchar(MAX), @contract nvarchar(50))*/
        preparedStatement.executeUpdate();
        /**/
    }
    public static void editProject(Connection connection, Project project, User user) throws SQLException{
        String sql = "exec editProject ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, project.getId());
        preparedStatement.setInt(2, project.getLeader());
        preparedStatement.setString(3, project.getName());
        preparedStatement.setDate(4, project.getTimeStart());
        preparedStatement.setDate(5, project.getEstimatedTimeEnd());
        preparedStatement.setInt(6, project.getDepartmentId());
        preparedStatement.setDouble(7, project.getTotalRevenue());
        preparedStatement.setInt(8, project.getAttitudeLeader());
        preparedStatement.setString(9, project.getNote());
        preparedStatement.setInt(10, user.getEmployeeId());
        preparedStatement.executeUpdate();
        /*editProject(@id int, @leader int, @name nvarchar(50), @timeStart Date, @estimatedTimeEnd Date, @departmentId int
 , @totalRevenue real, @attitudeLeader int, @note nvarchar(max))*/
    }
    public static int countTask(Connection connection)throws SQLException{
        String sql = "exec countTask ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        int id = 0;
        if(resultSet.next())
        {
            id = resultSet.getInt("COUNTTASK");
        }
        return id;
    }
    public static void insertTask(Connection connection, Task task)throws SQLException{
        String sql = "exec insertTask ?,?,?,?,?,?,?,?,?,?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, task.getId());
        preparedStatement.setInt(2, task.getProjectId());
        preparedStatement.setString(3, task.getName());
        preparedStatement.setDate(4, task.getTimeStart());
        preparedStatement.setDate(5, task.getEstimatedTimeEnd());
        preparedStatement.setInt(6, task.getModifiedBy());
        preparedStatement.setDouble(7, task.getCoefficient());
        preparedStatement.setInt(8, task.getEmployeeId());
        preparedStatement.setInt(9, task.getDepartmentId());
        preparedStatement.setString(10, task.getNote());
        preparedStatement.executeUpdate();
        /*insertTask(@id int, @projectId int, @name nvarchar(50),@timeStart date, @estimatedTimeEnd date, @modifiedBy int, @coefficient real,
 @employeeId int, @departmentId int, @note nvarchar(MAX))*/
    }
    public static void deleteTask(Connection connection, int idTask, int deleteBy)throws SQLException{
        String sql = "exec deleteTask ?, ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idTask);
        preparedStatement.setInt(2, deleteBy);
        preparedStatement.executeUpdate();
    }
    public static Task findTask(Connection connection, int id) throws SQLException {
        String sql = "exec findTask ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Task task = null;
        if(resultSet.next()){
            String name = resultSet.getString("name");
            int projectId = resultSet.getInt("projectId");
            int levelTaskCompleted = resultSet.getInt("levelTaskCompleted");
            Date timeStart = resultSet.getDate("timeStart");
            Date estimatedTimeEnd = resultSet.getDate("estimatedTimeEnd");
            Double coefficient = resultSet.getDouble("coefficient");
            int employeeId = resultSet.getInt("employeeId");
            Boolean completed = resultSet.getBoolean("completed");
            int departmentId = resultSet.getInt("departmentId");
            String linkCheckTask = resultSet.getString("linkCheckTask");
            int attitude = resultSet.getInt("attitude");
            String note = resultSet.getString("note");
            task = new Task(id, projectId, 0, 0, employeeId, coefficient, name, null, null, false, levelTaskCompleted, attitude,
                    linkCheckTask, note, timeStart, estimatedTimeEnd, completed, departmentId);
            /*public Task(int id, int projectId, int modifiedBy, int deletedBy, int employeeId, double coefficient
    , String name, Date modifiedDate, Date deletedDate, Boolean deleted, int levelTaskCompleted, int attitude
    , String linkCheckTask, String note, Date timeStart, Date estimatedTimeEnd, Boolean completed, int departmentId)*/

        }
              return task;
    }
    public static void editTask(Connection connection, Task task) throws SQLException{
        String sql ="exec editTask ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, task.getId());
        preparedStatement.setString(2, task.getName());
        preparedStatement.setDate(3, task.getTimeStart());
        preparedStatement.setDate(4, task.getEstimatedTimeEnd());
        preparedStatement.setInt(5, task.getModifiedBy());
        preparedStatement.setDouble(6,task.getCoefficient());
        preparedStatement.setInt(7, task.getEmployeeId());
        preparedStatement.setBoolean(8, task.getCompleted());
        preparedStatement.setInt(9, task.getDepartmentId());
        preparedStatement.setString(10, task.getLinkCheckTask());
        preparedStatement.setInt(11, task.getAttitude());
        preparedStatement.setString(12, task.getNote());
        preparedStatement.executeUpdate();
        /*editTask(@id int, @name nvarchar(50), @timeStart date, @estimatedTimeEnd date, @modifiedBy int, @coefficient real, @employeeId int, @completed bit,
@departmentId int, @linkCheckTask nvarchar(MAX), @attitude int, @note nvarchar(max))*/
    }

    public static void changePassWord(Connection connection, User user) throws SQLException{
        String sql = "exec changePassWord ?, ?, ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user.getEmployeeId());
        preparedStatement.setString(2, user.getPassWord());
        preparedStatement.setInt(3, user.getModifiedBy());
        preparedStatement.executeUpdate();
    }
    public static Employee findEmployee(Connection connection, int employeeId) throws SQLException{
        String sql = "exec findEmployee ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, employeeId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Employee employee = null;
        if(resultSet.next()){
            int id = resultSet.getInt("ID");
            int departmentId = resultSet.getInt("DepartmentID");
            Double salary = resultSet.getDouble("Salary");
            String name = resultSet.getString("Name");
            String sex = resultSet.getString("Sex");
            String phone = resultSet.getString("Phone");
            String email = resultSet.getString("Email");
            String address = resultSet.getString("Address");
            String position = resultSet.getString("Position");
            String degree = resultSet.getString("Degree");
            String portrait = resultSet.getString("Portrait");
            /* Date bD = new Date(resultSet.getTimestamp("BirthDate").getTime());*/
            Date birthDate = resultSet.getDate("BirthDate");
            String advantage = resultSet.getString("Advantage");
            String disadvantage = resultSet.getString("Disadvantage");
            String foreignLanguage = resultSet.getString("ForeignLanguage");
            int attitude = resultSet.getInt("Attitude");
            int leverWorkCompletion = resultSet.getInt("LevelWorkCompletion");
            String note = resultSet.getString("Note");
            Date timeStart = resultSet.getDate("timeStart");
            int modifiedBy = resultSet.getInt("modifiedBy");
            Date modifiedDate = resultSet.getDate("modifiedDate");
/*(int id, int departmentId,int deletedBy, double salary, String name, String sex, String phone, String email, String address,
                    String position, String degree, String portrait, Time birthDate, boolean deleted, String advantage, String disadvantage,
                    String foreignLanguage, int attitude, int levelWorkCompletion, String note)*/
            employee = new Employee(id, departmentId, 0 ,salary, name, sex, phone, email, address, position, degree
                    , portrait, birthDate, false, advantage, disadvantage, foreignLanguage, attitude, leverWorkCompletion,
                    note, timeStart, modifiedBy, modifiedDate);
        }
        return employee;
    }
    public static List<SalaryStatistical> salaryStatisticalCompany(Connection connection,int year, int time, Boolean isMonth)throws SQLException{
        String sql = null;
        if(isMonth)
            sql = "exec SumSalary_Month ?, ?";
        else
            sql = "exec Sumsalary_Quarterly ?, ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, time);
        preparedStatement.setInt(2, year);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<SalaryStatistical> salaryStatisticalList = new ArrayList<SalaryStatistical>();
        while (resultSet.next()){
            int id = resultSet.getInt("Employee_id");
            String employeeName = resultSet.getString("employeeName");
            String position = resultSet.getString("position");
            String departmentName = resultSet.getString("departmentName");
            Double sumSalary = resultSet.getDouble("sumSalary");
            SalaryStatistical salaryStatistical = new SalaryStatistical(id, employeeName, position, departmentName, sumSalary);
            /*SalaryStatistical(int id, String employeeName, String position, String departmentName, double sumSalary)*/
            salaryStatisticalList.add(salaryStatistical);
        }
        return salaryStatisticalList;
    }
    public static List<SalaryStatistical> salaryStatisticalCompanyAll(Connection connection,int year)throws SQLException{
        String sql = "exec SumSalary_Year ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, year);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<SalaryStatistical> salaryStatisticalList = new ArrayList<SalaryStatistical>();
        while (resultSet.next()){
            int id = resultSet.getInt("employee_id");
            String employeeName = resultSet.getString("employeeName");
            String position = resultSet.getString("position");
            String departmentName = resultSet.getString("departmentName");
            Double sumSalary = resultSet.getDouble("sumSalary");
            SalaryStatistical salaryStatistical = new SalaryStatistical(id, employeeName, position, departmentName, sumSalary);
            /*SalaryStatistical(int id, String employeeName, String position, String departmentName, double sumSalary)*/
            salaryStatisticalList.add(salaryStatistical);
        }
        return salaryStatisticalList;
    }
    public static List<SalaryStatistical> salaryStatisticalDepartmentAll(Connection connection, int departmentId,int year)throws SQLException{
        String sql = "exec SumSalary_Year_Department ?, ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, departmentId);
        preparedStatement.setInt(2, year);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<SalaryStatistical> salaryStatisticalList = new ArrayList<SalaryStatistical>();
        while (resultSet.next()){
            int id = resultSet.getInt("employee_id");
            String employeeName = resultSet.getString("employeeName");
            String position = resultSet.getString("position");
            String departmentName = resultSet.getString("departmentName");
            Double sumSalary = resultSet.getDouble("sumSalary");
            SalaryStatistical salaryStatistical = new SalaryStatistical(id, employeeName, position, departmentName, sumSalary);
            /*SalaryStatistical(int id, String employeeName, String position, String departmentName, double sumSalary)*/
            salaryStatisticalList.add(salaryStatistical);
        }
        return salaryStatisticalList;
    }
    public static List<SalaryStatistical> salaryStatisticalDepartment(Connection connection, int departmentId, int time, int year, Boolean isMonth)throws SQLException{
        String sql = null;
        if(isMonth)
            sql = "exec SumSalary_Department_Month ?, ?, ?";
        else
            sql= "exec Sumsalary_Department_Quarterly ?, ?, ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, departmentId);
        preparedStatement.setInt(2, time);
        preparedStatement.setInt(3, year);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<SalaryStatistical> salaryStatisticalList = new ArrayList<SalaryStatistical>();
        while (resultSet.next()){
            int id = resultSet.getInt("employee_id");
            String employeeName = resultSet.getString("employeeName");
            String position = resultSet.getString("position");
            String departmentName = resultSet.getString("departmentName");
            Double sumSalary = resultSet.getDouble("sumSalary");
            SalaryStatistical salaryStatistical = new SalaryStatistical(id, employeeName, position, departmentName, sumSalary);
            /*SalaryStatistical(int id, String employeeName, String position, String departmentName, double sumSalary)*/
            salaryStatisticalList.add(salaryStatistical);
        }
        return salaryStatisticalList;
    }
    public static void editEmployee(Connection connection, Employee employee) throws SQLException{
        String sql = "exec editEmloyee ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, employee.getModifiedBy());
        preparedStatement.setInt(2, employee.getId());
        preparedStatement.setString(3, employee.getName());
        preparedStatement.setString(4, employee.getSex());
        preparedStatement.setDate(5, employee.getBirthDate());
        preparedStatement.setString(6, employee.getPhone());
        preparedStatement.setString(7, employee.getEmail());
        preparedStatement.setString(8, employee.getAddress());
        preparedStatement.setString(9, employee.getPosition());
        preparedStatement.setString(10, employee.getDegree());
        preparedStatement.setInt(11, employee.getDepartmentId());
        preparedStatement.setDouble(12,employee.getSalary());
        preparedStatement.setString(13, employee.getAdvantage());
        preparedStatement.setString(14, employee.getDisadvantage());
        preparedStatement.setString(15, employee.getForeignLanguage());
        preparedStatement.setInt(16, employee.getAttitude());
        preparedStatement.setString(17, employee.getNote());
                /*editEmloyee(@modifiedBy int, @id int, @name nvarchar(50), @sex nvarchar(50), @birthDate date
        , @phone nvarchar(50), @email nvarchar(50)
, @address nvarchar(50), @position nvarchar(50), @degree nvarchar(50), @departmentId int, @salary real
, @advantage nvarchar(50)
, @disadvantage nvarchar(50), @foreignLanguage nvarchar(50), @attitude int, @note nvarchar(max))*/
        preparedStatement.executeUpdate();
    }
    public static void deleteEmployee(Connection connection, int employeeId, int deletedBy) throws SQLException{
        String sql = "exec deleteEmployee ?, ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, employeeId);
        preparedStatement.setInt(2, deletedBy);
        preparedStatement.executeUpdate();
    }
    public static List<Employee> getAllIDEmployeeFromDepartment(Connection connection, int departmentID) throws SQLException{
        String sql = "exec getAllIdEmployeeOfDepartment ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, departmentID);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Employee> listID = new ArrayList<>();
        while (resultSet.next()){
            Integer id = resultSet.getInt("ID");
            String name = resultSet.getString("name");
            listID.add(new Employee(id, name));
        }
        return listID;
    }
    public static List<Department> getAllIDDepartment(Connection connection) throws SQLException{
        String sql = "exec getAllIdDepartment";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Department> listID = new ArrayList<>();
        while (resultSet.next()){
            Integer id = resultSet.getInt("ID");
            String name = resultSet.getString("name");
            Department department = new Department(id, name);
            listID.add(department);
        }
        return listID;
    }
    public static int getLeaderFromDepartmentId(Connection connection, int departmentId) throws SQLException{
        String sql = "exec getIdLeader ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, departmentId);
        ResultSet resultSet = preparedStatement.executeQuery();
        int leader = 0;
        if (resultSet.next()){
            leader = resultSet.getInt("leader");
        }
        return leader;
    }
    public static int getDepartmentIdByProjectId(Connection connection, int projectId) throws SQLException{
        String sql = "exec getDepartmentIdByProjectId ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, projectId);
        ResultSet resultSet = preparedStatement.executeQuery();
        int ID = 0;
        if (resultSet.next()){
            ID = resultSet.getInt("leader");
        }
        return ID;
    }
    public static int getIDDepartmentOfLeader(Connection connection, int leader) throws SQLException{
        String sql = "exec getIdDepartmentOfLeader ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, leader);
        ResultSet resultSet = preparedStatement.executeQuery();
        int ID = 0;
        if (resultSet.next()){
            ID = resultSet.getInt("ID");
        }
        return ID;
    }
    public static void updateContract(Connection connection, int projectId, String contract, int modifiedBy) throws SQLException{
        String sql = "exec editContract ?, ?, ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, projectId);
        preparedStatement.setString(2, contract);
        preparedStatement.setInt(3,modifiedBy);
        preparedStatement.executeUpdate();
    }
    public static List<Employee> getAllIDAndNameEmployee(Connection connection)throws SQLException{
        String sql = "exec getAllIDAndNameEmployee";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        List<Employee> employeeList = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int ID = resultSet.getInt("ID");
            String name = resultSet.getString("Name");
            employeeList.add(new Employee(ID, name));
        }
        return employeeList;
    }
    public static void updatePortrait(Connection connection, int employeeId, String portrait, int modifiedBy) throws SQLException{
        String sql = "exec editPortrait ?, ?, ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, employeeId);
        preparedStatement.setInt(2, modifiedBy);
        preparedStatement.setString(3,portrait);
        preparedStatement.executeUpdate();
    }
    public static List<Employee> getAllEmployeeInProject(Connection connection, int projectId)throws SQLException{
        String sql = "exec getAllEmployeeOfProject ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, projectId);
        List<Employee> employeeList = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int ID = resultSet.getInt("ID");
            String name = resultSet.getString("Name");
            employeeList.add(new Employee(ID, name));
        }
        return employeeList;
    }
}
