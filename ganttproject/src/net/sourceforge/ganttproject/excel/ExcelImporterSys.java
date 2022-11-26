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

import javax.swing.*;
import net.sourceforge.ganttproject.CustomPropertyHolder;
import net.sourceforge.ganttproject.CustomPropertyManager;
import net.sourceforge.ganttproject.resource.*;
import net.sourceforge.ganttproject.roles.*;
import net.sourceforge.ganttproject.task.*;

public class ExcelImporterSys {

    //ERROR MESSAGES
    private static final String FILE_NOT_FOUND = "File not found!";
    private static final String INPUT_ERROR = "Input error!";

    /**     Excel workbook      */
    private XSSFWorkbook wb;

    /**     CustomPropertyHolder = HumanResource interface      */
    private List<HumanResource> resources;

    public ExcelImporterSys(String filePath){
        this.resources = new ArrayList<>();
        try{
            FileInputStream fp = new FileInputStream(filePath);
            wb = new XSSFWorkbook(fp);
        }catch(FileNotFoundException e){
            JFrame jFrame = new JFrame();
            JOptionPane.showMessageDialog(jFrame, e.getMessage());
        }catch (IOException e){
            JFrame jFrame2 = new JFrame();
            JOptionPane.showMessageDialog(jFrame2, e.getMessage());
        }
    }

    /*public ExcelImporterSys(String fileName){
        this.resources = new ArrayList<>();
        try{
            FileInputStream fp = new FileInputStream(filePath);
            wb = new XSSFWorkbook(fp);
        }catch(FileNotFoundException e){
            JFrame jFrame = new JFrame();
            JOptionPane.showMessageDialog(jFrame, e.getMessage());
        }catch (IOException e){
            JFrame jFrame2 = new JFrame();
            JOptionPane.showMessageDialog(jFrame2, e.getMessage());
        }
    }*/

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
        for(i = 0; i < 3; i++){
            cell = row.getCell(i);
            rowCells.add(cell);
        }
        return rowCells;
    }

    public void makeResources(HumanResourceManager myHRManager, int sheetIndex){
        Iterator<Row> it = getRowIterator(sheetIndex);
        ArrayList<Cell> resourceInfo = new ArrayList<>();
        while(it.hasNext()){
            Row r = it.next();
            resourceInfo = getCellsInRow(r);
            String name = resourceInfo.get(0).toString();
            String hourlyFee = resourceInfo.get(1).toString();
            String role = resourceInfo.get(2).toString();
            HumanResource resource = myHRManager.create(name, -1);
            myHRManager.add(resource);
        }
    }

    /*private boolean checkRoleExistance(Role role){

    }*/

    public void resourceNames(){
        Iterator<HumanResource> it = resources.iterator();
        while(it.hasNext()){
            HumanResource r = it.next();
            System.out.println(r.getName());
        }
    }

}
