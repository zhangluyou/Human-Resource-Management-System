package com.jointem.hrm.utils;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by dartagnan on 17/8/1.
 */
public class ReadPropertesUtil {

    public static String getValue(String key) throws Exception{
        Properties pps = new Properties();
//        pps.load(new FileInputStream("src/main/resources/hrm.properties"));
        pps.load(ReadPropertesUtil.class.getClassLoader().getResourceAsStream("hrm.properties"));
        String value = pps.getProperty(key);
        return value;
    }

    public static void main(String[] args) {
        try {
            String value = getValue("filePath");
            System.out.println(value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
