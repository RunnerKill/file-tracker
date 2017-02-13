//package com.sowell.file.tracker;
//
//import static org.junit.Assert.*;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import com.sowell.file.model.FileDTO;
//
//import junit.framework.Assert;
//
//public class FileTrackerTest {
//
//    private FileTracker ft;
//
//    @Before
//    public void before() {
//        String server = "http://192.168.1.149:8780";
//        String domain = "fs";
//        TrackerFactory trackerFactory = new TrackerFactory(server, domain);
//        ft = trackerFactory.getFileTracker();
//    }
//
//    @Test
//    public void testUpload() throws Exception {
//        File f1 = new File("D:/pictures/1.jpg");
//        File f2 = new File("D:/pictures/中文图片abc.png");
//        List<File> files = new ArrayList<File>();
//        files.add(f1);
//        files.add(f2);
//        List<FileDTO> f = ft.upload(files, "ftt", "xxj");
//        System.out.println(f);
//    }
//    
//    @Test
//    public void testGetById() throws Exception {
//        FileDTO f = ft.getById("45685348c9ef4057b954ca6e9c03d796");
//        System.out.println(f);
//        Assert.assertNotNull(f);
//    }
//
//    @Test
//    public void testGetByIds() throws Exception {
//        List<String> idArray = new ArrayList<String>();
//        idArray.add("45685348c9ef4057b954ca6e9c03d796");
//        idArray.add("1382cde623c249ac918627c0281b813a");
//        List<FileDTO> f = ft.getByIds(idArray);
//        System.out.println(f);
//        Assert.assertNotNull(f);
//    }
//    
//    @Test
//    public void testDeleteById() throws Exception {
//        boolean del = ft.deleteById("122b16169bad47789fa7f0e1df587246");
//        System.out.println(del);
//    }
//    
//    @Test
//    public void testDeleteByIds() throws Exception {
//        List<String> idArray = new ArrayList<String>();
//        idArray.add("1382cde623c249ac918627c0281b813a");
//        idArray.add("2e5282e7bca74ce392aff5ba5e729009");
//        boolean del = ft.deleteByIds(idArray);
//        System.out.println(del);
//    }
//    
//    @Test
//    public void testDownload() throws Exception {
//        File f = ft.download("51dc738021194b8aba011e86ca578198", "D:/t.jpg");
//        System.out.println(f);
//    }
//
//}
