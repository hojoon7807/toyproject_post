package com.flab.posttoy.repository.post;

import lombok.*;

@Getter
@Builder
public class PostEntity {
    private Long id;
    private Long userId;
    private String title;
    private String content;

    public void setId(Long id) {
        this.id = id;
    }

    public void changePost(String title, String content) {
        if(isValidData(title)) this.title = title;
        if(isValidData(content)) this.content = content;
    }

    private boolean isValidData(String data){
        return data == null || data.isBlank() ? false : true;
    }
}
