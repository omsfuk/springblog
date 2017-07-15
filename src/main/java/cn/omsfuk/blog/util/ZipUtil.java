package cn.omsfuk.blog.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by omsfuk on 2017/7/14.
 */
public class ZipUtil {

    public static void compress() {

    }

    public static void main(String[] args) throws IOException {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream("compress.zip"));
        out.putNextEntry(new ZipEntry("a/"));
        out.close();
    }
}
