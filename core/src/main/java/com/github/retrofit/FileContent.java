/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */

package com.github.retrofit;

import java.util.List;

/**
 * Created by feel on 2016/12/5.
 */
public class FileContent {

    private List<String> file;

    public FileContent() {
    }

    public FileContent(List<String> file) {
        this.file = file;
    }

    public List<String> getFile() {
        return file;
    }

    public void setFile(List<String> file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "FileContent{" +
                "file=" + file +
                '}';
    }
}
