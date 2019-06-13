package com.janeli.pay.dev;

import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

/**
 * Created by helicheng on 2017/7/7.
 */
public class RenameMapperAndPoPlugin extends PluginAdapter {
    private String searchString;
    private String replaceString;
    private String delSuffixString;
    private String delModelSuffixString;

    private Pattern pattern;

    private Pattern suffixPattern;

    private Pattern delModelSuffixPattern;


    public RenameMapperAndPoPlugin() {
    }

    public boolean validate(List<String> warnings) {

        searchString = properties.getProperty("searchString"); //$NON-NLS-1$
        replaceString = properties.getProperty("replaceString"); //$NON-NLS-1$
        delSuffixString = properties.getProperty("delSuffixString");//删除Mapper前缀
        delModelSuffixString = properties.getProperty("delModelSuffixString");//删除Model前缀

        boolean valid = stringHasValue(searchString)
                && stringHasValue(replaceString);
        if (stringHasValue(delSuffixString)) {
            suffixPattern = Pattern.compile(delSuffixString);
        }
        if (stringHasValue(delModelSuffixString)) {
            delModelSuffixPattern = Pattern.compile(delModelSuffixString);
        }
        if (valid) {
            pattern = Pattern.compile(searchString);
        } else {
            if (!stringHasValue(searchString)) {
                warnings.add(getString("ValidationError.18", //$NON-NLS-1$
                        "RenameExampleClassPlugin", //$NON-NLS-1$
                        "searchString")); //$NON-NLS-1$
            }
            if (!stringHasValue(replaceString)) {
                warnings.add(getString("ValidationError.18", //$NON-NLS-1$
                        "RenameExampleClassPlugin", //$NON-NLS-1$
                        "replaceString")); //$NON-NLS-1$
            }
        }

        return valid;
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        String oldType = introspectedTable.getMyBatis3JavaMapperType();
        Matcher matcher = pattern.matcher(oldType);
        oldType = matcher.replaceAll(replaceString);
        if (suffixPattern != null) {
            System.out.println(oldType);
            Matcher suffixmatcher = suffixPattern.matcher(oldType);
            oldType = suffixmatcher.replaceAll("");
        }
        introspectedTable.setMyBatis3JavaMapperType(oldType);
        if (delModelSuffixPattern != null) {
            FullyQualifiedTable fqft = introspectedTable.getFullyQualifiedTable();
            String domainObjectName = introspectedTable.getBaseRecordType();
            Matcher delModelSuffxiMatcher = delModelSuffixPattern.matcher(domainObjectName);
            System.out.println(domainObjectName);
            domainObjectName = delModelSuffxiMatcher.replaceAll("");
            domainObjectName = domainObjectName + "Entity";
            introspectedTable.setBaseRecordType(domainObjectName);
        }


    }

}
