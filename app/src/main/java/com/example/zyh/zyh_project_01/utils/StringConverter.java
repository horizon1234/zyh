package com.example.zyh.zyh_project_01.utils;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zyh on 2018/6/15.
 */

public class StringConverter implements PropertyConverter<List<String>,String> {
    @Override
    public List<String> convertToEntityProperty(String databaseValue) {
        if(databaseValue == null){
            return null;
        }
        else {
            List<String> list = Arrays.asList(databaseValue.split(","));
            return list;
        }
    }

    @Override
    public String convertToDatabaseValue(List<String> entityProperty) {
        if (entityProperty == null){
            return null;
        }
        else {
            StringBuffer sb = new StringBuffer();
            for(String link : entityProperty){
                sb.append(link);
                sb.append(",");
            }
            return sb.toString();
        }
    }
}
