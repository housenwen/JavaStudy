package com.tanhua.dubbo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "sound")
public class Sound implements java.io.Serializable {
    private static final long serialVersionUID = -313673283688565873L;

    private ObjectId id; //主键id
    private Long sid; //自增长id
    private Long userId;//发布者id
    private String soundUrl; //音频文件路径
    private Long created; //创建时间
}
