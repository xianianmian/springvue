package com.kuang.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 夏年眠
 * @since 2023-05-08
 */
@Getter
@Setter
  @ApiModel(value = "Course对象", description = "")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("ID")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("课程名称")
      private String name;

      @ApiModelProperty("开课时间")
      private String time;

      @ApiModelProperty("开课状态")
      private Boolean state;

      @ApiModelProperty("分数")
      private Integer score;

      @ApiModelProperty("教师ID")
      private Integer teachId;

      @TableField(exist = false)
      private String teacher;
}
