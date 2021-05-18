package com.tanhua.dubbo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 时间线表，用于存储发布的数据，每一个用户一张表进行存储
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "quanzi_time_line_{userId}")
public class TimeLine implements java.io.Serializable {
    private static final long serialVersionUID = 9096178416317502524L;

    @Id
    private ObjectId id;
    private Long userId; // 用户id
    private ObjectId publishId; //发布id
    private Long date; //发布的时间

}
