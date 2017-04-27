package cn.omsfuk.blog.service;

import cn.omsfuk.blog.model.Picture;

import java.util.List;

/**
 * Created by omsfuk on 17-4-24.
 */
public interface PictureService {

    List<Picture> getAllPictures(String root);

}
