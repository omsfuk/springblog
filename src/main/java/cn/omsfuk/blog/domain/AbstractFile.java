package cn.omsfuk.blog.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by omsfuk on 17-5-5.
 */
public class AbstractFile {

    private Integer id;

    private String name = "";

    private String parentPath = "";

    private Boolean directory;

    private String fileType = "markdown";

    private List<AbstractFile> childFiles = new ArrayList<AbstractFile>();

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public Boolean getDirectory() {
        return directory;
    }

    public void setDirectory(Boolean directory) {
        this.directory = directory;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public List<AbstractFile> getChildFiles() {
        return childFiles;
    }

    public void setChildFiles(List<AbstractFile> childFiles) {
        this.childFiles = childFiles;
    }

    public void addChild(AbstractFile file) {
        childFiles.add(file);
    }

    public AbstractFile ensureChild(String name) {
        for (AbstractFile file : childFiles) {
            if(file.name != null && file.name.equals(name)) {
                return file;
            }
        }

        AbstractFile file = new AbstractFile();
        file.setName(name);
        childFiles.add(file);
        return file;
    }

}
