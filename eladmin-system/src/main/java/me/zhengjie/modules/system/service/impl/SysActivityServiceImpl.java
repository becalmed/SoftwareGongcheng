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
package me.zhengjie.modules.system.service.impl;

import me.zhengjie.modules.system.domain.SysActivity;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.repository.SysActivityRepository;
import me.zhengjie.modules.system.service.SysActivityService;
import me.zhengjie.modules.system.service.dto.SysActivityDto;
import me.zhengjie.modules.system.service.dto.SysActivityQueryCriteria;
import me.zhengjie.modules.system.service.mapstruct.SysActivityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import me.zhengjie.utils.PageResult;

/**
* @website https://eladmin.vip
* @description 服务实现
* @author fjw
* @date 2024-05-24
**/
@Service
@RequiredArgsConstructor
public class SysActivityServiceImpl implements SysActivityService {

    private final SysActivityRepository sysActivityRepository;
    private final SysActivityMapper sysActivityMapper;

    @Override
    public PageResult<SysActivityDto> queryAll(SysActivityQueryCriteria criteria, Pageable pageable){
        Page<SysActivity> page = sysActivityRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(sysActivityMapper::toDto));
    }

    @Override
    public List<SysActivityDto> queryAll(SysActivityQueryCriteria criteria){
        return sysActivityMapper.toDto(sysActivityRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public SysActivityDto findById(Integer activityId) {
        SysActivity sysActivity = sysActivityRepository.findById(activityId).orElseGet(SysActivity::new);
        ValidationUtil.isNull(sysActivity.getActivityId(),"SysActivity","activityId",activityId);
        return sysActivityMapper.toDto(sysActivity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(SysActivity resources) {
        sysActivityRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysActivity resources) {
        SysActivity sysActivity = sysActivityRepository.findById(resources.getActivityId()).orElseGet(SysActivity::new);
        ValidationUtil.isNull( sysActivity.getActivityId(),"SysActivity","id",resources.getActivityId());
        sysActivity.copy(resources);
        sysActivityRepository.save(sysActivity);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer activityId : ids) {
            sysActivityRepository.deleteById(activityId);
        }
    }

    @Override
    public void download(List<SysActivityDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (SysActivityDto sysActivity : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("活动名称", sysActivity.getActivityName());
            map.put("活动类型", sysActivity.getActivityProgress());
            map.put("创建者", sysActivity.getCreateBy());
            map.put("更新者", sysActivity.getUpdateBy());
            map.put("创建时间", sysActivity.getCreateTime());
            map.put("更新时间", sysActivity.getUpdateTime());
            map.put("开始日期", sysActivity.getStartTime());
            map.put("开始时刻", sysActivity.getStartHour());
            map.put("结束日期", sysActivity.getEndTime());
            map.put("结束时刻", sysActivity.getEndHour());
            map.put("参与者列表", sysActivity.getParticipants());
            map.put("资源需求列表", sysActivity.getResourceRequirements());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}