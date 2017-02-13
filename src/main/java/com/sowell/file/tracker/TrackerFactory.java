package com.sowell.file.tracker;

/**
 * tracker工厂类
 * @author Xiaojie.Xu
 */
public class TrackerFactory {
    
    private String server;
    private String webdir;
    private String domain;
    
    public TrackerFactory(String server, String domain) {
        super();
        this.server = server;
        this.domain = domain;
    }
    
    public TrackerFactory(String server, String webdir, String domain) {
        super();
        this.server = server;
        this.webdir = webdir;
        this.domain = domain;
    }

    /**
     * 获取文件交互对象实例
     * @return fileTracker对象
     */
    public FileTracker getFileTracker() {
        return new FileTracker(server, webdir, domain);
    }
    
    /**
     * 获取图片交互对象实例
     * @return imgTracker对象
     */
    public ImageTracker getImageTracker() {
        return new ImageTracker(server, webdir, domain);
    }
}
