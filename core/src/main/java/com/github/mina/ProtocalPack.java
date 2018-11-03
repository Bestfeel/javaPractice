package com.github.mina;

import java.io.Serializable;

/**
 * Created by feel on 16/5/15.
 */
public class ProtocalPack implements Serializable {


    private int length;
    private byte flag;
    private String content;

    public ProtocalPack(String content) {

        this.content = content;
        this.length = content == null ? 0 : 5 + content.getBytes().length;
    }

    public ProtocalPack(int length, byte flag, String content) {
        this.length = length;
        this.flag = flag;
        this.content = content;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ProtocalPack{" +
                "length=" + length +
                ", flag=" + flag +
                ", content='" + content + '\'' +
                '}';
    }
}

