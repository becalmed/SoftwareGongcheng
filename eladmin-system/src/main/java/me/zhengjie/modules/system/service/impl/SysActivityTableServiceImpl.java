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

import me.zhengjie.modules.system.domain.SysActivityTable;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.repository.SysActivityTableRepository;
import me.zhengjie.modules.system.service.SysActivityTableService;
import me.zhengjie.modules.system.service.dto.SysActivityTableDto;
import me.zhengjie.modules.system.service.dto.SysActivityTableQueryCriteria;
import me.zhengjie.modules.system.service.mapstruct.SysActivityTableMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.util.IdUtil;
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
public class SysActivityTableServiceImpl implements SysActivityTableService {

    private final SysActivityTableRepository sysActivityTableRepository;
    private final SysActivityTableMapper sysActivityTableMapper;

    @Override
    public PageResult<SysActivityTableDto> queryAll(SysActivityTableQueryCriteria criteria, Pageable pageable){
        Page<SysActivityTable> page = sysActivityTableRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(sysActivityTableMapper::toDto));
    }

    @Override
    public List<SysActivityTableDto> queryAll(SysActivityTableQueryCriteria criteria){
        return sysActivityTableMapper.toDto(sysActivityTableRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public SysActivityTableDto findById(String date) {
        SysActivityTable sysActivityTable = sysActivityTableRepository.findById(date).orElseGet(SysActivityTable::new);
        ValidationUtil.isNull(sysActivityTable.getDate(),"SysActivityTable","date",date);
        return sysActivityTableMapper.toDto(sysActivityTable);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(SysActivityTable resources) {
        resources.setDate(IdUtil.simpleUUID()); 
        sysActivityTableRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysActivityTable resources) {
        SysActivityTable sysActivityTable = sysActivityTableRepository.findById(resources.getDate()).orElseGet(SysActivityTable::new);
        ValidationUtil.isNull( sysActivityTable.getDate(),"SysActivityTable","id",resources.getDate());
        sysActivityTable.copy(resources);
        sysActivityTableRepository.save(sysActivityTable);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String date : ids) {
            sysActivityTableRepository.deleteById(date);
        }
    }

    @Override
    public void download(List<SysActivityTableDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (SysActivityTableDto sysActivityTable : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("日程8:00-9:00", sysActivityTable.getActivity8to9());
            map.put("日程9:00-10:00", sysActivityTable.getActivity9to10());
            map.put("日程10:00-11:00", sysActivityTable.getActivity10to11());
            map.put("日程11:00-12:00", sysActivityTable.getActivity11to12());
            map.put("日程12:00-13:00", sysActivityTable.getActivity12to13());
            map.put("日程13:00-14:00", sysActivityTable.getActivity13to14());
            map.put("日程14:00-15:00", sysActivityTable.getActivity14to15());
            map.put("日程5:00-16:00", sysActivityTable.getActivity15to16());
            map.put("日程16:00-17:00", sysActivityTable.getActivity16to17());
            map.put("日程17:00-18:00", sysActivityTable.getActivity17to18());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}