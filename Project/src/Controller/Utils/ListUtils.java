package Controller.Utils;

import Model.Class.Department;
import Model.Class.Employee;

import java.util.List;
import java.util.Map;

public class ListUtils {
    public static void changeLocationEmployee(List<Employee> list, int id){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId() == id)
            {
                Employee employee = list.get(i);
                int index = list.indexOf(employee);
                list.set(index, list.get(0));
                list.set(0, employee);
                break;
            }
        }
    }
    public static void changeLocationDepartment(List<Department> list, int id){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId() == id)
            {
                Department department = list.get(i);
                int index = list.indexOf(department);
                list.set(index, list.get(0));
                list.set(0, department);
                break;
            }
        }
    }
    /*public static void setStyleForDepartment(Map<Department, String> list, int idDepartment){
        for (int i = 0; i < list.size(); i++) {
            if(list.k)
        }
    }*/
}
