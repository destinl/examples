package com.ls.typehandler_demo.domain.entity;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/16 14:46
 */
import java.util.List;

public class Tags {
    private int id;
    private List<String> tags;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
