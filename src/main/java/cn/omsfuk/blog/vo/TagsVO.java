package cn.omsfuk.blog.vo;

import cn.omsfuk.blog.domain.Tag;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Created by omsfuk on 17-5-14.
 */

@Data
@RequiredArgsConstructor
public class TagsVO {

    @NonNull
    private List<Tag> tags;
}
