package com.gootschool.api.education;

import com.gootschool.common.response.RevanResponse;
import com.gootschool.pojo.education.Teacher;
import com.gootschool.pojo.education.request.TeacherQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@Api(value = "讲师管理", description = "讲师管理，提供讲师的增、删、改、查")
@RequestMapping("/education/teacher")
@CrossOrigin
public interface IEducationTeacherAPI {

    @ApiOperation("讲师列表")
    @GetMapping("/list")
    RevanResponse list();


    /**
     * RequestBody json类型数据必须使用 POST
     *
     * @param page
     * @param size
     * @param teacherQuery
     * @return
     */
    @ApiOperation("讲师分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "个数", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "teacherQuery", value = "查询条件", required = false),
    })
    @PostMapping("/list/{page}/{size}")
    RevanResponse listPage(@PathVariable("page") Integer page,
                           @PathVariable("size") Integer size,
                           @RequestBody(required = false) TeacherQuery teacherQuery);

    @ApiOperation("讲师删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "讲师id", required = true, paramType = "path", dataType = "string")
    })
    @DeleteMapping("/delete/{id}")
    RevanResponse removeById(@PathVariable("id") String id);


    @ApiOperation("讲师新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teacher", value = "讲师对象", required = false),
    })
    @PostMapping("/saveOrUpdate")
    RevanResponse saveOrUpdateTeacher(@RequestBody Teacher teacher);


    @ApiOperation("根据id查询讲师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "讲师id", required = true, paramType = "path", dataType = "string")
    })
    @GetMapping("/{id}")
    RevanResponse queryById(@PathVariable("id") String id);


    @ApiOperation("管理员登录")
    @PostMapping("/login")
    RevanResponse login();


    @ApiOperation("管理员登录")
    @GetMapping("/userinfo")
    RevanResponse userinfo();
}
