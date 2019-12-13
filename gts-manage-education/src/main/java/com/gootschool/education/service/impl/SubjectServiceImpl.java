package com.gootschool.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gootschool.common.code.RevanCodeEnum;
import com.gootschool.common.handler.RevanException;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.common.utils.ExcelImportHSSFUtil;
import com.gootschool.education.mapper.ISubjectMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gootschool.education.service.ISubjectService;
import com.gootschool.pojo.education.Subject;
import com.gootschool.pojo.education.SubjectNestedVO;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程科目 服务实现类
 *
 * @author Revan Wang
 * @since 2019-12-12
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<ISubjectMapper, Subject> implements ISubjectService {


    @Transactional
    @Override
    public RevanResponse importExcel(MultipartFile file) {

        // 提示信息
        List<String> msgs = new ArrayList<>();

        try {
            InputStream inputStream = file.getInputStream();

            ExcelImportHSSFUtil excelHSSFUtil = new ExcelImportHSSFUtil(file.getInputStream());
            HSSFSheet sheet = excelHSSFUtil.getSheet();

            int rowCount = sheet.getPhysicalNumberOfRows();
            if (rowCount < 2) {
                msgs.add("Excel数据为空");
                return RevanResponse.error().data("msgs", msgs);
            }

            // 遍历数据
            for (int rowNum = 1; rowNum < rowCount; rowNum++) {
                // 获取行
                HSSFRow rowData = sheet.getRow(rowNum);
                // 行不为空
                if (rowData != null) {
                    // 一级(列)分类名称
                    String levelOneValue = "";
                    HSSFCell levelOnecell = rowData.getCell(0);
                    if (levelOnecell != null) {
                        levelOneValue = excelHSSFUtil.getCellValue(levelOnecell, Cell.CELL_TYPE_STRING);
                        if (StringUtils.isBlank(levelOneValue)) {
                            msgs.add("第" + rowNum + "行一级分类为空");
                            continue;
                        }
                    }

                    // 保存一级目录
                    String parentId = this.saveSubject(levelOneValue, "0");

                    // 二级(列)分类名称
                    String levelTwoValue = "";
                    HSSFCell levelTwoCell = rowData.getCell(1);
                    if (levelTwoCell != null) {
                        levelTwoValue = excelHSSFUtil.getCellValue(levelTwoCell, Cell.CELL_TYPE_STRING);
                        if (StringUtils.isBlank(levelTwoValue)) {
                            msgs.add("第" + rowNum + "行二级分类为空");
                            continue;
                        }
                    }

                    // 保存二级目录
                    this.saveSubject(levelTwoValue, parentId);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RevanException(RevanCodeEnum.UPLOAD_EXCEL_FAIL);
        }
        return RevanResponse.ok().data("msgs", msgs);
    }

    @Override
    public RevanResponse nestedList() {
        // 最终得到数据列表
        List<SubjectNestedVO> items = new ArrayList<>();

        // 1.获取一级分类数据
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        queryWrapper.orderByAsc("sort", "id");
        List<Subject> subjects = baseMapper.selectList(queryWrapper);

        // 2.获取二级分类数据
        QueryWrapper<Subject> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.ne("parent_id", 0);
        queryWrapper2.orderByAsc("sort", "id");
        List<Subject> subjects2 = baseMapper.selectList(queryWrapper2);

        // 3.填充一级分类数据
        for (Subject subject : subjects) {
            SubjectNestedVO nestedVO = new SubjectNestedVO();
            BeanUtils.copyProperties(subject, nestedVO);
            items.add(nestedVO);

            // 4.填充二级分类数据
            List<SubjectNestedVO> nestedVOS2 = new ArrayList<>();
            for (Subject subject2 : subjects2) {
                if (subject.getId().equals(subject2.getParentId())) {
                    // 创建二级类别VO对象
                    SubjectNestedVO subjectVO2 = new SubjectNestedVO();
                    BeanUtils.copyProperties(subject2, subjectVO2);
                    nestedVOS2.add(subjectVO2);
                }
            }
            nestedVO.setChildren(nestedVOS2);
        }

        return RevanResponse.ok().data("items", items);
    }


    /**
     * 保存 subject
     *
     * @param name     科目名称
     * @param parentId 父id
     * @return
     */
    private String saveSubject(String name, String parentId) {
        // 查询是否已经存储到数据库中
        Subject subject = this.getSubByTitle(name, parentId);

        if (subject == null) {
            // 新增
            subject = new Subject();
            subject.setTitle(name);
            subject.setSort(0);
            subject.setParentId(parentId);
            baseMapper.insert(subject);
        }

        return subject.getId();
    }


    /**
     * 根据分类名称和父id查询
     *
     * @param title
     * @param parentId
     * @return
     */
    private Subject getSubByTitle(String title, String parentId) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title)
                .eq("parent_id", parentId);
        return baseMapper.selectOne(queryWrapper);
    }

}
