package org.eoa.projectbudget.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: 张骏山
 * @Date: 2023/11/2 17:09
 * @PackageName: org.eoa.projectbudget.mapper
 * @ClassName: FormDMLMapperTest
 * @Description: FormDMLMapper测试工具
 * @Version: 1.0
 **/
@SpringBootTest
class FormDMLMapperTest {

    @Autowired
    FormDMLMapper formDMLMapper;




    @Test
    void getMainFormById() {
        long dataId = 1L;
        System.out.println("formDMLMapper.getMainFormById(1L,FormDDLMapperTest.TEST_MAIN) = " + formDMLMapper.getMainFormById(dataId, FormDDLMapperTest.TEST_MAIN));
    }

    @Test
    void getDetailFormByDataId() {
        long dataId = 1L;
        System.out.println("formDMLMapper.getDetailFormByDataId(dataId,FormDDLMapperTest.TEST_DETAIL) = " + formDMLMapper.getDetailFormByDataId(dataId, FormDDLMapperTest.TEST_DETAIL));
    }

    @Test
    void updateMainForm() {
        long dataId = 1L;
        formDMLMapper.updateMainForm(getColumnDatas((int) (dataId+10)),dataId,FormDDLMapperTest.TEST_MAIN);
        formDMLMapper.getMainFormById(dataId,FormDDLMapperTest.TEST_MAIN);
    }

    @Test
    void updateDetailForm() {
        long dataId = 1L;
        long detailDataId = 2L;
        formDMLMapper.updateDetailForm(getColumnDatas((int) (detailDataId+10)),detailDataId,FormDDLMapperTest.TEST_DETAIL);
        formDMLMapper.getDetailFormByDataId(dataId,FormDDLMapperTest.TEST_DETAIL);
    }

    @Test
    void insertMainForm() {
        HashMap<String, Object> columnDatas = getColumnDatas(5);
        formDMLMapper.insertMainForm(columnDatas,FormDDLMapperTest.TEST_MAIN);
        System.out.println("columnDatas.get(\"dataId\") = " + columnDatas.get("dataId"));

    }

    @Test
    void insertDetailForm() {
        int detailMainId = 2;
        int detailDataId = 3;
        HashMap<String, Object> columnDatas = getColumnDatas(detailDataId);
        columnDatas.put("detailMainId", detailMainId);
        formDMLMapper.insertDetailForm(columnDatas,FormDDLMapperTest.TEST_DETAIL);
        System.out.println("columnDatas.get(\"detailDataId\") = " + columnDatas.get("detailDataId"));
    }

    @Test
    void deleteMainForm() {
        formDMLMapper.deleteMainForm(2L,FormDDLMapperTest.TEST_MAIN);
    }

    @Test
    void deleteDetailForm() {
        formDMLMapper.deleteDetailForm(3L,FormDDLMapperTest.TEST_DETAIL);
    }

    @Test
    void getIdsByMap() {
        formDMLMapper.getIdsByMap(FormDDLMapperTest.TEST_MAIN,getColumnDatas(5));

    }

    private HashMap<String, Object> getColumnDatas(int i) {
        HashMap<String, Object> columnDatas;
        columnDatas = new HashMap<>();
        for (int j = 0; j < FormDDLMapperTest.TEST_COLUMNS.length; j++) {
            columnDatas.put(FormDDLMapperTest.TEST_COLUMNS[j],j+"_"+ i);
        }
        return columnDatas;
    }
}