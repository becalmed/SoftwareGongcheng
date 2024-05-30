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
import java.io.Serializable;

/**
* @website https://eladmin.vip
* @description /
* @author fjw
* @date 2024-05-24
**/
@Entity
@Data
@Table(name="sys_activity_table")
public class SysActivityTable implements Serializable {

    @Id
    @Column(name = "`date`")
    @ApiModelProperty(value = "日期")
    private String date;

    @Column(name = "`activity8to9`")
    @ApiModelProperty(value = "日程8:00-9:00")
    private String activity8to9;

    @Column(name = "`activity9to10`")
    @ApiModelProperty(value = "日程9:00-10:00")
    private String activity9to10;

    @Column(name = "`activity10to11`")
    @ApiModelProperty(value = "日程10:00-11:00")
    private String activity10to11;

    @Column(name = "`activity11to12`")
    @ApiModelProperty(value = "日程11:00-12:00")
    private String activity11to12;

    @Column(name = "`activity12to13`")
    @ApiModelProperty(value = "日程12:00-13:00")
    private String activity12to13;

    @Column(name = "`activity13to14`")
    @ApiModelProperty(value = "日程13:00-14:00")
    private String activity13to14;

    @Column(name = "`activity14to15`")
    @ApiModelProperty(value = "日程14:00-15:00")
    private String activity14to15;

    @Column(name = "`activity15to16`")
    @ApiModelProperty(value = "日程5:00-16:00")
    private String activity15to16;

    @Column(name = "`activity16to17`")
    @ApiModelProperty(value = "日程16:00-17:00")
    private String activity16to17;

    @Column(name = "`activity17to18`")
    @ApiModelProperty(value = "日程17:00-18:00")
    private String activity17to18;

    public void copy(SysActivityTable source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
