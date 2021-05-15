package Controller.RestServlet;
import Controller.Servlet.SalaryServlet;
import Controller.Utils.DBUtils;
import Controller.Utils.MailUtils;
import Controller.Utils.MyUtils;
import Model.Class.SalaryStatistical;
import Model.Class.User;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ExportExcelServlet" ,urlPatterns = "/ExportExcel")
public class ExportExcelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SalaryStatistical> salaryStatisticals = SalaryServlet.salaryStatisticals;
        for (int i = 0; i < salaryStatisticals.size(); i++) {
            System.out.println(salaryStatisticals.get(i));
        }

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("salary");
        HSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("Mã nhân viên");
        header.createCell(1).setCellValue("Tên phòng ban");
        header.createCell(2).setCellValue("Tên nhân viên");
        header.createCell(3).setCellValue("Chức vụ");
        header.createCell(4).setCellValue("Lương");
        int rowIndex = 1;
        for(int i = 0; i < salaryStatisticals.size(); i++) {
            HSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(salaryStatisticals.get(i).getId());
            row.createCell(1).setCellValue(salaryStatisticals.get(i).getDepartmentName());
            row.createCell(2).setCellValue(salaryStatisticals.get(i).getEmployeeName());
            row.createCell(3).setCellValue(salaryStatisticals.get(i).getPosition());
            row.createCell(4).setCellValue(salaryStatisticals.get(i).getSumSalary());
            rowIndex += 1;
        }

        FileOutputStream outputStream = new FileOutputStream(System.getProperty("user.home") + File.separator + "Downloads" + File.separator + "salary.xls");
        wb.write(outputStream);
        outputStream.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}