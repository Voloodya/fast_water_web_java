
package service;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import view.HydrologyFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ReadExcel {

    @Autowired
    ServletContext servletContext;

    private static String way="E:/Fast_Water_project/FastWaterWeb/Hydrometcentre.xls";

    public List<HydrologyFile> readExcelHydrology (String way, String postName, int yearStart, int monthStart, int dayStart,
                                                   int yearFinish, int monthFinish, int dayFinish){

        List<HydrologyFile> hydrologyList=new ArrayList<HydrologyFile>();
        // Read XSL file
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(way));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Get the workbook instance for XLS file
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Get first sheet from the workbook
        HSSFSheet sheet = workbook.getSheetAt(0);

        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if((row.getCell(0).toString().equals(postName))&&((int)row.getCell(1).getNumericCellValue()>=yearStart && (int)row.getCell(1).getNumericCellValue()<=yearFinish) &&
                    ((int)row.getCell(2).getNumericCellValue()>=monthStart && (int)row.getCell(2).getNumericCellValue()<=monthFinish)&&
                    ((int)row.getCell(3).getNumericCellValue()>=dayStart && (int)row.getCell(3).getNumericCellValue()<=dayFinish)) {

                LocalDate localDate = LocalDate.of((int) row.getCell(1).getNumericCellValue(), (int) row.getCell(2).getNumericCellValue(),
                        (int) row.getCell(3).getNumericCellValue());
                LocalTime localTime = LocalTime.of((int) row.getCell(4).getNumericCellValue(), 00, 00);
                hydrologyList.add(new HydrologyFile(row.getCell(0).toString(),
                        localDate, localTime,
                        row.getCell(7).getNumericCellValue(), row.getCell(9).getNumericCellValue(),
                        row.getCell(10).getNumericCellValue(), row.getCell(19).getNumericCellValue(),
                        row.getCell(11).getNumericCellValue(), row.getCell(17).getNumericCellValue()));
            }


            // Get iterator to all cells of current row
//            Iterator<Cell> cellIterator = row.cellIterator();

//            while (cellIterator.hasNext()) {
//                Cell cell = cellIterator.next();
//
//                // Change to getCellType() if using POI 4.x
//                CellType cellType = cell.getCellTypeEnum();

//                // Alternatively, get the value and format it yourself
//                switch (cell.getCellType()) {
//                    case STRING:
//                        System.out.println(cell.getRichStringCellValue().getString());
//                        break;
//                    case _NONE:
//                        break;
//                    case NUMERIC:
//                        if (DateUtil.isCellDateFormatted(cell)) {
//                            System.out.println(cell.getDateCellValue());
//                        } else {
//                            System.out.println(cell.getNumericCellValue());
//                        }
//                        break;
//                    case BOOLEAN:
//                        System.out.println(cell.getBooleanCellValue());
//                        break;
//                    case FORMULA:
//                        System.out.println(cell.getCellFormula());
//                        break;
//                    case BLANK:
//                        System.out.println();
//                        break;
//                    case ERROR:
//                        break;
//                    default:
//                        System.out.println();
//                }
//                System.out.println("");
//            }
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hydrologyList;
    }

    public List<HydrologyFile> readExcelAll (String nameFile, String postName, int yearStart, int monthStart,int dayStart,
                                               int yearFinish, int monthFinish,int dayFinish)  {

        List<HydrologyFile> hydrologyList = new ArrayList<HydrologyFile>();
        String wayFile="";
        try {
            wayFile =new ClassPathResource(nameFile).getFile().getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Read XSL file
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(wayFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Get the workbook instance for XLS file
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Get first sheet from the workbook
        HSSFSheet sheet = workbook.getSheetAt(0);

        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if ((row.getCell(0).toString().equals(postName)) && ((int) row.getCell(1).getNumericCellValue() >= yearStart && (int) row.getCell(1).getNumericCellValue() <= yearFinish) &&
                    ((int) row.getCell(2).getNumericCellValue() >= monthStart && (int) row.getCell(2).getNumericCellValue() <= monthFinish) &&
                    ((int) row.getCell(3).getNumericCellValue() >= dayStart && (int) row.getCell(3).getNumericCellValue() <= dayFinish)) {

                LocalDate localDate = LocalDate.of((int) row.getCell(1).getNumericCellValue(), (int) row.getCell(2).getNumericCellValue(),
                        (int) row.getCell(3).getNumericCellValue());
                LocalTime localTime = LocalTime.of((int) row.getCell(4).getNumericCellValue(), 00, 00);

                hydrologyList.add(new HydrologyFile(row.getCell(0).toString(),
                        localDate,localTime, (int)row.getCell(5).getNumericCellValue(),
                        row.getCell(6).getNumericCellValue(), row.getCell(7).getNumericCellValue(),
                        row.getCell(8).getNumericCellValue(), row.getCell(9).getNumericCellValue(),
                        row.getCell(10).getNumericCellValue(), row.getCell(13).getNumericCellValue(),
                        row.getCell(12).getNumericCellValue(),row.getCell(14).getNumericCellValue(),
                        row.getCell(15).getNumericCellValue(),row.getCell(16).getNumericCellValue(),
                        row.getCell(11).getNumericCellValue(),row.getCell(17).getNumericCellValue(),
                        row.getCell(18).getNumericCellValue()));
            }
        }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return hydrologyList;
    }

}
