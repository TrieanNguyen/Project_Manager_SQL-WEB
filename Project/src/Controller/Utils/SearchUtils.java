package Controller.Utils;

import Model.Class.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchUtils {
    public static List<Employee> searchEmployee(List<Employee> employeeList, String nameSearch){
        List<Employee> result = new ArrayList<>();
        String nameEmployee;
        nameSearch = nameSearch.toLowerCase(Locale.ROOT);
        for (int i = 0; i < employeeList.size(); i++) {
            nameEmployee = employeeList.get(i).getName().toLowerCase(Locale.ROOT);
            if(nameEmployee.lastIndexOf(nameSearch) != -1){
                result.add(employeeList.get(i));
            }
        }
        return result;
    }
}
