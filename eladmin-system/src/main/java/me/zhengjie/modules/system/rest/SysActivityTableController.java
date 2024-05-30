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
package me.zhengjie.modules.system.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.system.domain.SysActivityTable;
import me.zhengjie.modules.system.service.SysActivityTableService;
import me.zhengjie.modules.system.service.dto.SysActivityTableQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import me.zhengjie.utils.PageResult;
import me.zhengjie.modules.system.service.dto.SysActivityTableDto;

/**
* @website https://eladmin.vip
* @author fjw
* @date 2024-05-24
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "api.activityTable管理")
@RequestMapping("/api/sysActivityTable")
public class SysActivityTableController {

    private final SysActivityTableService sysActivityTableService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('sysActivityTable:list')")
    public void exportSysActivityTable(HttpServletResponse response, SysActivityTableQueryCriteria criteria) throws IOException {
        sysActivityTableService.download(sysActivityTableService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询api.activityTable")
    @ApiOperation("查询api.activityTable")
    @PreAuthorize("@el.check('sysActivityTable:list')")
    public ResponseEntity<PageResult<SysActivityTableDto>> querySysActivityTable(SysActivityTableQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(sysActivityTableService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增api.activityTable")
    @ApiOperation("新增api.activityTable")
    @PreAuthorize("@el.check('sysActivityTable:add')")
    public ResponseEntity<Object> createSysActivityTable(@Validated @RequestBody SysActivityTable resources){
        sysActivityTableService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改api.activityTable")
    @ApiOperation("修改api.activityTable")
    @PreAuthorize("@el.check('sysActivityTable:edit')")
    public ResponseEntity<Object> updateSysActivityTable(@Validated @RequestBody SysActivityTable resources){
        sysActivityTableService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除api.activityTable")
    @ApiOperation("删除api.activityTable")
    @PreAuthorize("@el.check('sysActivityTable:del')")
    public ResponseEntity<Object> deleteSysActivityTable(@RequestBody String[] ids) {
        sysActivityTableService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}