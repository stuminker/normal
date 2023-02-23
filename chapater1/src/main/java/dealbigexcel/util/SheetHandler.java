package dealbigexcel.util;

import dealbigexcel.pojo.PersonBean;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author stuminker
 * @version 1.0
 * @project normal
 * @description 自定义sheet基于Sax的解析处理器
 * @date 2023/2/22 22:05:01
 */
public class SheetHandler implements XSSFSheetXMLHandler.SheetContentsHandler {
    //实体对象集合
    private final List<PersonBean> personBeanList = new ArrayList<>();
    //封装实体对象
    private PersonBean personBean;
    //第几次插入数据，初始值为1
    private int times = 1;

    //中数据量
    private int allCount = 0;

    @Override
    public void startRow(int i) {
        System.out.println("i=" + i);
        if (0 < i) {
            personBean = new PersonBean();
        }
    }

    /**
     * @param i 行号
     * @return
     * @description 当结束解析某一行的时候出发
     */
    @Override
    public void endRow(int i) {
// System.out.println(employee);
        if (null != personBean) {
            personBeanList.add(personBean);
        }
        //System.out.println(personBean.toString());
        allCount++;
        if (personBeanList.size() == 5) {
            // 假设有一个批量插入
            System.out.println("执行第" + times + "次插入");
            times++;
            personBeanList.clear();
        }
    }

    /**
     * @param cellName    单元格名称
     * @param value       数据
     * @param xssfComment 批注
     * @return
     * @description 对行中的每一个单元格进行处理
     */
    @Override
    public void cell(String cellName, String value, XSSFComment xssfComment) {
        if (personBean != null) {
            String prefix = cellName.substring(0, 1);
            System.out.println(prefix);
            System.out.println(value);
            switch (prefix) {
                case "A":
                    personBean.setName(value);
                    break;
                case "B":
                    personBean.setSex(value);
                    break;
                case "C":
                    personBean.setBirthday(value);
                    break;
                case "D":
                    personBean.setIdType(value);
                    break;
                case "E":
                    personBean.setIdCard(value);
                    break;
                case "F":
                    personBean.setProvince(value);
                    break;
                case "G":
                    personBean.setCity(value);
                    break;
                case "H":
                    personBean.setPicAddress(value);
                    break;
            }
        }
    }

    public List<PersonBean> getPersonBeanList() {
        return personBeanList;
    }

    public int getAllCount() {
        return allCount;
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {
        XSSFSheetXMLHandler.SheetContentsHandler.super.headerFooter(text, isHeader, tagName);
    }

    @Override
    public void endSheet() {
        XSSFSheetXMLHandler.SheetContentsHandler.super.endSheet();
    }
}
