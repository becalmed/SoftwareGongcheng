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
import java.io.Serializable;

/**
* @website https://eladmin.vip
* @description /
* @author fjw
* @date 2024-05-24
**/
@Data
public class SysActivityTableDto implements Serializable {

    /** 日期 */
    private String date;

    /** 日程8:00-9:00 */
    private String activity8to9;

    /** 日程9:00-10:00 */
    private String activity9to10;

    /** 日程10:00-11:00 */
    private String activity10to11;

    /** 日程11:00-12:00 */
    private String activity11to12;

    /** 日程12:00-13:00 */
    private String activity12to13;

    /** 日程13:00-14:00 */
    private String activity13to14;

    /** 日程14:00-15:00 */
    private String activity14to15;

    /** 日程5:00-16:00 */
    private String activity15to16;

    /** 日程16:00-17:00 */
    private String activity16to17;

    /** 日程17:00-18:00 */
    private String activity17to18;
}