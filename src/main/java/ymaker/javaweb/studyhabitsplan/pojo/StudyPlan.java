package ymaker.javaweb.studyhabitsplan.pojo;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("`study-plans`")
public class StudyPlan {
    @TableId(type = IdType.AUTO)
    private int id;
    private String username;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deadline;
    private String topic;
    private int priority;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private int status;
    private String reminder_time;
    //"yyyy-MM-dd HH:mm//yyyy-MM-dd HH:mm"
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")//2010-10-09 12:11:31
    @TableField(value = "finish_time")
    private Date finishTime;

}
