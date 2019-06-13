package com.janeli.pay.utils;

import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.io.FileUtils.openOutputStream;


/**
 * Created by lid on 2018/12/10 0010.
 */
public class JuhePhoneTest {

    public static final String KEY = "82a1916d83e8f1602f6c724d4930e7ab";

    public static void main(String args[]) throws Exception {
        List<String> phones = JuhePhoneTest.getLocalPhones("E:\\phone\\kh.xlsx");
        List<JuhePhoneResultVo> listResult = new ArrayList<JuhePhoneResultVo>();
        for (String phone : phones) {
            JuhePhoneResultVo juhePhoneResultVo = JuhePhoneTest.phoneTest(phone);
            listResult.add(juhePhoneResultVo);
        }
        JuhePhoneTest.writeExcel(listResult);
    }

    public static List<String> getLocalPhones(String fileAddress) throws Exception {
        List<String> listResult = new ArrayList<>();

        File excelFile = new File(fileAddress);
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(excelFile));
        XSSFSheet sheet = wb.getSheetAt(0);

        for (Row row : sheet) {
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        listResult.add(cell.getRichStringCellValue().getString());
                        break;
                    default:
                }
            }
        }
        return listResult;
    }


    public static JuhePhoneResultVo phoneTest(String mobile) {
        //{"reason":"成功","result":{"mobile":"18567994759","realname":"","idcard":"
        // ","province":"福建省","city":"龙岩市","isp":"联通","state":"3","description":
        // "未启用"},"error_code":0}
        JuhePhoneResultVo vo = new JuhePhoneResultVo();
        vo.setPhone(mobile);
        String result = null;
        String url = "http://v.juhe.cn/mobile_status/query";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("mobile", mobile);
        params.put("key", KEY);
        try {
            result = JuhePhoneMessageUtil.net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            System.out.println(object.toString());
            if (object.getInt("error_code") == 0) {
                vo.setDescription(object.getJSONObject("result").getString("description"));
                switch (object.getJSONObject("result").getString("state")) {
                    case "0":
                        vo.setState("正常使用");
                        break;
                    case "1":
                        vo.setState("停机");
                        break;
                    case "2":
                        vo.setState("在网但不可用");
                        break;
                    case "3":
                        vo.setState("不在网、销号、未启用、异常");
                        break;
                    case "4":
                        vo.setState("预销户");
                        break;
                    default:
                        vo.setState("未知状态");
                        break;
                }
                return vo;
            } else {
                vo.setDescription("未知状态");
                vo.setState("未知状态");
                return vo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        vo.setDescription("未知状态");
        vo.setState("未知状态");
        return vo;
    }

    public static void writeExcel(List<JuhePhoneResultVo> list) throws Exception {
        //定义表头
        String[] title = {"电话号码", "状态", "描述"};
        //创建excel工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建工作表sheet
        HSSFSheet sheet = workbook.createSheet();
        //创建第一行
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = null;
        //插入第一行数据的表头
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }
        //写入数据
        for (int i = 1; i <= list.size(); i++) {
            HSSFRow nrow = sheet.createRow(i);
            HSSFCell ncell = nrow.createCell(0);
            ncell.setCellValue(list.get(i - 1).getPhone());
            ncell = nrow.createCell(1);
            ncell.setCellValue(list.get(i - 1).getState());
            ncell = nrow.createCell(2);
            ncell.setCellValue(list.get(i - 1).getDescription());
        }
        //创建excel文件
        String fileName = OrderCodeUtil.getOrderCode();
        File file = new File("e://phone/" + fileName + ".xls");
        try {
            file.createNewFile();
            //将excel写入
            FileOutputStream stream = openOutputStream(file);
            workbook.write(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static class JuhePhoneResultVo {
        private String phone;

        private String state;

        private String description;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
