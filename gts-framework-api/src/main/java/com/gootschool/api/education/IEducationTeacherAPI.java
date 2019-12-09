package com.gootschool.api.education;

import com.gootschool.common.response.RevanResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@Api(value = "讲师管理", description = "讲师管理，提供讲师的增、删、改、查")
@RequestMapping("/education/teacher")
public interface IEducationTeacherAPI {

    @ApiOperation("讲师列表")
    @GetMapping("/list")
    RevanResponse list();


    @ApiOperation("讲师分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "个数", required = true, paramType = "path", dataType = "int")
    })
    @GetMapping("/list/{page}/{size}")
    RevanResponse listPage(@PathVariable("page") Integer page,
                           @PathVariable("size") Integer size);

    @ApiOperation("讲师删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "讲师id", required = true, paramType = "path", dataType = "string")
    })
    @DeleteMapping("/delete/{id}")
    RevanResponse removeById(@PathVariable("id") String id);

}
