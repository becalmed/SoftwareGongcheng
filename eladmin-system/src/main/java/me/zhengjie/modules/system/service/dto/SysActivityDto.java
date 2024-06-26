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
package me.zhengjie.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://eladmin.vip
* @description /
* @author fjw
* @date 2024-05-24
**/
@Data
public class SysActivityDto implements Serializable {

    /** 活动编号 */
    private Integer activityId;

    /** 活动名称 */
    private String activityName;

    /** 活动类型 */
    private String activityProgress;

    /** 创建者 */
    private Integer createBy;

    /** 更新者 */
    private Integer updateBy;

    /** 创建时间 */
    private Timestamp createTime;

    /** 更新时间 */
    private Timestamp updateTime;

    /** 开始日期 */
    private String startTime;

    /** 开始时刻 */
    private String startHour;

    /** 结束日期 */
    private String endTime;

    /** 结束时刻 */
    private String endHour;

    /** 参与者列表 */
    private String participants;

    /** 资源需求列表 */
    private String resourceRequirements;
}