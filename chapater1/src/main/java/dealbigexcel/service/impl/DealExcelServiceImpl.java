package dealbigexcel.service.impl;

import dealbigexcel.pojo.PersonBean;
import dealbigexcel.service.DealExcelService;
import dealbigexcel.util.SheetHandler;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

/**
 * @author stuminker
 * @version 1.0
 * @project normal
 * @description
 * @date 2023/2/22 22:21:09
 */
public class DealExcelServiceImpl implements DealExcelService {
    public static void main(String[] args) throws Exception {
        DealExcelServiceImpl dealExcelService = new DealExcelServiceImpl();
        dealExcelService.readBigDataExcel("F:\\java_project\\Excel文件\\test.xlsx");
    }

    @Override
    public void readBigDataExcel(String path) throws Exception {
        // 1.根据excel报表获取OPCpackage
        OPCPackage opcPackage = OPCPackage.open(path, PackageAccess.READ);
        // 2.创建XSSFReader
        XSSFReader xssfReader = new XSSFReader(opcPackage);
        // 3.获取SharedStringTable对象
        SharedStringsTable sharedStringsTable = xssfReader.getSharedStringsTable();
        // 4.获取styleTable对象
        StylesTable stylesTable = xssfReader.getStylesTable();
        // 5.创建sax xmlReader对象
        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        // 6.注册事件驱动处理器
        SheetHandler sheetHandler = new SheetHandler();
        XSSFSheetXMLHandler xssfSheetXMLHandler = new XSSFSheetXMLHandler(stylesTable, sharedStringsTable, sheetHandler, false);
        xmlReader.setContentHandler(xssfSheetXMLHandler);
        // 7.逐行读取
        XSSFReader.SheetIterator sheetIterator = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
        while (sheetIterator.hasNext()) {
            InputStream in = sheetIterator.next();
            InputSource is = new InputSource(in);
            xmlReader.parse(is);
        }
        List<PersonBean> personBeanList = sheetHandler.getPersonBeanList();   // 剩余未插入的数据插入
        System.out.println("最后一次插入数据，本次数据量为" + personBeanList.size());
        System.out.println("共插入" + sheetHandler.getAllCount() + "条数据");
        for (PersonBean personBean : sheetHandler.getPersonBeanList()) {
            System.out.println(Objects.isNull(personBean));
            System.out.println(personBean.toString());
        }
    }
}
