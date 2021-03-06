package master.sheet.mastersheet.excelHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

// import com.mysql.cj.result.Row;

// import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.*;

import master.sheet.mastersheet.Entity.ItemEntity;
import master.sheet.mastersheet.Entity.PoEntity;
import master.sheet.mastersheet.Entity.ProjectEntity;
import master.sheet.mastersheet.Entity.TaskEntity;

public class excelHelper {
    public static Map<String, Object> readFromExcel(String filename,InputStream is) {
        Map<String, Object> data = new HashMap<>();
        try {
            System.out.println("start");
            Workbook wb;
            wb = WorkbookFactory.create(is);
            Sheet sh;
            sh = wb.getSheet("Sheet1");
            int noOfRows = sh.getLastRowNum() + 1;
            System.out.println("number of rows " + filename + ":" + noOfRows);
            // items.xlsx
            // po.xlsx
            // project.xlsx
            // tasks.xlsx
            DataFormatter formatter = new DataFormatter();
            switch (filename) {
                case "items.xlsx":
                    for (int i = 1; i < noOfRows; i++) {

                        ItemEntity items = new ItemEntity();
                        // Timestamp ts = new Timestamp();
                        items.setItem_id(formatter.formatCellValue(sh.getRow(i).getCell(0)));
                        items.setProject_id(formatter.formatCellValue(sh.getRow(i).getCell(1)));
                        items.setItem_name(formatter.formatCellValue(sh.getRow(i).getCell(2)));
                        items.setItem_remarks(formatter.formatCellValue(sh.getRow(i).getCell(3)));
                        items.setItem_type(formatter.formatCellValue(sh.getRow(i).getCell(4)));
                        // items.setStart_date(formatter.formatCellValue(sh.getRow(i).getCell(5)));
                        // new
                        // SimpleDateFormat("MM/dd/yyyy").parse(formatter.formatCellValue(sh.getRow(i).getCell(5)))
                        items.setStart_date(new SimpleDateFormat("MM/dd/yyyy")
                                .parse(formatter.formatCellValue(sh.getRow(i).getCell(5))));
                        items.setEnd_date(new SimpleDateFormat("MM/dd/yyyy")
                                .parse(formatter.formatCellValue(sh.getRow(i).getCell(6))));
                        items.setPo_remarks(formatter.formatCellValue(sh.getRow(i).getCell(7)));
                        items.setPo_no(formatter.formatCellValue(sh.getRow(i).getCell(8)));
                        items.setPo_value(formatter.formatCellValue(sh.getRow(i).getCell(9)));
                        data.put(formatter.formatCellValue(sh.getRow(i).getCell(0)), items);
                    }
                    System.out.println("data: " + data.toString());
                    return data;
                // break;
                case "po.xlsx":
                    for (int i = 1; i < noOfRows; i++) {
                        PoEntity p = new PoEntity();
                        p.setPo_id(formatter.formatCellValue(sh.getRow(i).getCell(0)));
                        p.setStart_date(new SimpleDateFormat("MM/dd/yyyy")
                                .parse(formatter.formatCellValue(sh.getRow(i).getCell(1))));
                        p.setEnd_date(new SimpleDateFormat("MM/dd/yyyy")
                                .parse(formatter.formatCellValue(sh.getRow(i).getCell(2))));
                        data.put(formatter.formatCellValue(sh.getRow(i).getCell(0)), p);
                    }
                    return data;
                // break;
                case "project.xlsx":
                    for (int i = 1; i < noOfRows; i++) {
                        if (formatter.formatCellValue(sh.getRow(i).getCell(0)) != "") {
                            // System.out.println(formatter.formatCellValue(sh.getRow(i).getCell(0)));
                            ProjectEntity project = new ProjectEntity();
                            project.setProject_id(formatter.formatCellValue(sh.getRow(i).getCell(0)));
                            project.setProject_name(formatter.formatCellValue(sh.getRow(i).getCell(1)));
                            project.setStart_date(new SimpleDateFormat("MM/dd/yyyy")
                                    .parse(formatter.formatCellValue(sh.getRow(i).getCell(2))));
                            project.setEnd_date(new SimpleDateFormat("MM/dd/yyyy")
                                    .parse(formatter.formatCellValue(sh.getRow(i).getCell(3))));
                            project.setRemarks(formatter.formatCellValue(sh.getRow(i).getCell(4)));
                            project.setProject_manager(formatter.formatCellValue(sh.getRow(i).getCell(5)));
                            project.setProject_max_amount(formatter.formatCellValue(sh.getRow(i).getCell(6)));
                            project.setProject_type(formatter.formatCellValue(sh.getRow(i).getCell(7)));
                            project.setProject_status(formatter.formatCellValue(sh.getRow(i).getCell(8)));
                            data.put(formatter.formatCellValue(sh.getRow(i).getCell(0)), project);
                        }

                    }
                    return data;
                // break;
                case "tasks.xlsx":
                    for (int i = 1; i < noOfRows; i++) {
                        TaskEntity ta = new TaskEntity();
                        ta.setTask_id(formatter.formatCellValue(sh.getRow(i).getCell(0)));
                        ta.setItem_id(formatter.formatCellValue(sh.getRow(i).getCell(1)));
                        ta.setTask_description(formatter.formatCellValue(sh.getRow(i).getCell(2)));
                        data.put(formatter.formatCellValue(sh.getRow(i).getCell(0)), ta);
                    }
                    return data;
                // break;
            }
            wb.close();
            return data;
        } catch (Exception e) {
            System.out.println("Error:" + e);
            return data;
        }

    }

    // Map<String,project> project,Map<String,item> item,Map<String,po>
    // po,Map<String,task> task
    public static void wrtieExcelFile(List<ProjectEntity> project, List<ItemEntity> item, List<PoEntity> po,
            List<TaskEntity> task) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("MasterSheet");
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        // set data in tree
        // for project
        // for item
        // for task
        List<String> taskList = new ArrayList<String>();
        for(TaskEntity te: task)
                taskList.add(te.getItem_id());
        int counter =0;
        for(String tl:taskList)
            if (Collections.frequency(taskList, tl)>counter)
                counter=Collections.frequency(taskList, tl);
        String arrayString = "item id,item name,item type,item start date,item end date,item remarks,"+
                    "po no,po start date,po end date,po value,project id,project name,"+
                    "project start date,project end date,project remarks,project manager,project type,project stauts";
        for(int i=0;i<counter;i++)
            arrayString+=",Task"+(i+1)+",Task description"+(i+1);
        System.out.println(arrayString);
        String[] strArr = arrayString.split(",");
        System.out.println("########################################################################");
        for(String str:strArr)
        System.out.print(str+" ");
        
        data.put("1",strArr);
        for (ProjectEntity pe : project) {
            for (ItemEntity ie : item) {
                if (ie.getItem_id().equals(pe.getProject_id())) {
                    
                    PoEntity poE = new PoEntity();
                    for (PoEntity poee : po) {
                        if (poee.getPo_id().equals(ie.getPo_no())) {
                            poE = poee;
                            break;
                        }
                    }
                    System.out.println(poE.getPo_id()+" "+poE.getStart_date());
                    // L
                    List<Object> rowList = new ArrayList<Object>();
                    Object[] obj = { ie.getItem_id(), ie.getItem_name(), ie.getItem_type(), ie.getStart_date().toString(),
                        ie.getEnd_date().toString(), ie.getItem_remarks(), ie.getPo_no(), poE.getStart_date().toString(),
                        poE.getEnd_date().toString(), ie.getPo_value(), ie.getProject_id(), pe.getProject_name(),
                        pe.getStart_date().toString(), pe.getEnd_date().toString(), pe.getRemarks(), pe.getProject_manager(),
                        pe.getProject_type(),pe.getProject_status() };
                    for(Object ob:obj){
                        rowList.add(ob);
                    }
                    for (TaskEntity te : task) {
                        if (te.getItem_id().equals(ie.getItem_id())){
                            rowList.add(te.getTask_id());
                            rowList.add(te.getTask_description());
                        } 
                    }
                    obj = rowList.toArray();
                    data.put(ie.getItem_id(),obj);
                }
            }
        }
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                // cell.setCellValue((String)obj);
                if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer) obj);
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(new File("testDemo1.xlsx"));
            workbook.write(out);
            out.close();
            workbook.close();
            System.out.println("Done");
        } catch (Exception e) {
            // workbook.close();
            System.out.println(e);
            // TODO: handle exception
        }
    }
}
