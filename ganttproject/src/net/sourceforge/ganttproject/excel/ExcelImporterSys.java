package net.sourceforge.ganttproject.excel;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import net.sourceforge.ganttproject.CustomPropertyHolder;
import net.sourceforge.ganttproject.CustomPropertyManager;
import net.sourceforge.ganttproject.resource.*;
import net.sourceforge.ganttproject.roles.*;
import net.sourceforge.ganttproject.task.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ExcelImporterSys {

    //ERROR MESSAGES
    private static final String FILE_NOT_FOUND = "File not found!";
    private static final String INPUT_ERROR = "Input error!";
    private static final int MAX_COLUMNS = 5;

    /**     Excel workbook      */
    private XSSFWorkbook wb;

    private boolean imported;

    public ExcelImporterSys(String filePath){
        this.imported = false;
        try{
            FileInputStream fp = new FileInputStream(filePath);
            wb = new XSSFWorkbook(fp);
        }catch(FileNotFoundException e){
            JFrame jFrame = new JFrame();
            JOptionPane.showMessageDialog(jFrame, FILE_NOT_FOUND);
        }catch (IOException e){
            JFrame jFrame2 = new JFrame();
            JOptionPane.showMessageDialog(jFrame2, INPUT_ERROR);
        }
    }

    public XSSFWorkbook getBook(){
        return wb;
    }

    public Iterator<Row> getRowIterator(int sheetIndex){
        String sheetName = wb.getSheetName(sheetIndex);
        XSSFSheet sheet = wb.getSheet(sheetName);
        return sheet.rowIterator();
    }

    public ArrayList<Cell> getCellsInRow(Row row){
        ArrayList<Cell> rowCells = new ArrayList<>();
        Cell cell = null;
        int i;
        for(i = 0; i < MAX_COLUMNS; i++){
            cell = row.getCell(i);
            rowCells.add(cell);
        }
        return rowCells;
    }

    /** Creates resources from the excel file */
    public void makeResources(HumanResourceManager myHRManager, RoleManager myRoleManager, int sheetIndex){
        Iterator<Row> it = getRowIterator(sheetIndex);
        ArrayList<Cell> resourceInfo = new ArrayList<>();
        RoleSet projectRoleSet = myRoleManager.getProjectRoleSet();
        while(it.hasNext()){
            Row r = it.next();
            resourceInfo = getCellsInRow(r);
            if(resourceInfo.get(0) != null){
                String name = resourceInfo.get(0).toString();
                String phoneNum = resourceInfo.get(1).toString();
                String email = resourceInfo.get(2).toString();
                String hourlyFee = resourceInfo.get(3).toString();
                String roleName = resourceInfo.get(4).toString();
                HumanResourceManager.ResourceBuilder myResourceBuilder = myHRManager.newResourceBuilder();
                Role newRole = null;
                if(roleDoesntExist(myRoleManager,roleName))
                    newRole = projectRoleSet.createRole(roleName);
                else
                    newRole = myRoleManager.getRoleByName(roleName);

                String persistentID = newRole.getPersistentID();
                HumanResource newResource = myResourceBuilder.withName(name).withID("-1").withPhone(phoneNum)
                        .withEmail(email).withStandardRate(hourlyFee)
                        .withRole(persistentID).build();
                newResource.setRole(newRole);
            }
        }
        this.imported = true;
    }

    public boolean alreadyImported() {
        return this.imported;
    }

    private boolean roleDoesntExist(RoleManager myRoleManager, String roleName){
        return (myRoleManager.getRoleByName(roleName) == null);
    }

}
