package com.sowell.file.enums;


/**
 * 文件类型
 * @author Xiaojie.Xu
 */
public enum FileType {
    
    IMAGE("image"),
    WORD("word"),
    EXCEL("excel"),
    OTHER("other");
    
    private String name;
    
    private FileType(String name) {
        this.name = name;
    }
    
    /**
     * 根据名称字符串获取文件类型
     * @param name 名称字符串
     * @return 文件类型
     */
    public static FileType getType(String name) {
        for(FileType t : FileType.values()) {
            if(t.name.equals(name)) return t;
        }
        return OTHER;
    }
}
