package cn.omsfuk.blog.service.impl;

import cn.omsfuk.blog.model.Picture;
import cn.omsfuk.blog.service.PictureService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by omsfuk on 17-4-24.
 */

@Service
public class PictureServiceImpl implements PictureService {

    @Override
    public List<Picture> getAllPictures(String root) {

        List<Picture> pictures = new LinkedList<Picture>();
        File[] files = new File(root).listFiles();
        for(File file : files) {
            if(file.isFile()) {
                Picture picture = new Picture();
                picture.setFileName(file.getName());
                pictures.add(picture);
            }
        }

        return pictures;
    }
}
