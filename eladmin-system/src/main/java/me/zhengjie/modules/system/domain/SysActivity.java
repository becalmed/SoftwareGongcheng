/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package me.zhengjie.modules.system.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://eladmin.vip
* @description /
* @author fjw
* @date 2024-05-24
**/
@Entity
@Data
@Table(name="sys_activity")
public class SysActivity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`activity_id`")
    @ApiModelProperty(value = "活动编号")
    private Integer activityId;

    @Column(name = "`activity_name`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "活动名称")
    private String activityName;

    @Column(name = "`activity_progress`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "活动类型")
    private String activityProgress;

    @Column(name = "`create_by`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "创建者")
    private Integer createBy;

    @Column(name = "`update_by`")
    @ApiModelProperty(value = "更新者")
    private Integer updateBy;

    @Column(name = "`create_time`")
    @CreationTimestamp
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    @Column(name = "`update_time`")
    @UpdateTimestamp
    @ApiModelProperty(value = "更新时间")
    private Timestamp updateTime;

    @Column(name = "`start_time`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "开始日期")
    private String startTime;

    @Column(name = "`start_hour`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "开始时刻")
    private String startHour;

    @Column(name = "`end_time`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "结束日期")
    private String endTime;

    @Column(name = "`end_hour`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "结束时刻")
    private String endHour;

    @Column(name = "`participants`")
    @ApiModelProperty(value = "参与者列表")
    private String participants;

    @Column(name = "`resource_requirements`")
    @ApiModelProperty(value = "资源需求列表")
    private String resourceRequirements;

    public void copy(SysActivity source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
