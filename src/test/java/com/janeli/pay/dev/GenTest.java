package com.janeli.pay.dev;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by helicheng on 2017/7/7.
 */
public class GenTest {

    private File configFile;

    public void before() {
        //读取mybatis参数
        configFile = new File("D:\\YTH\\Cardpay\\src\\test\\resources\\generatorConfig.xml");
//        configFile = new File("E:\\ideaProject\\party-build\\pary-build-web\\src\\test\\resources\\generatorConfig.xml");

    }


    public static void main(String[] args) throws Exception {
        File configFile = new File("D:\\YTH\\Cardpay\\src\\test\\resources\\generatorConfig.xml");
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
