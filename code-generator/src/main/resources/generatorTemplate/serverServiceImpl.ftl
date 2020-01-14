package ${serverPackage}.service.impl;

import ${serverPackage}.converter.${modelNameUpperCamel}Converter;
import ${serverPackage}.entity.${modelNameUpperCamel};
import ${serverPackage}.service.${modelNameUpperCamel}Service;
import ${serverPackage}.dao.${modelNameUpperCamel}Mapper;
import ${clientPackage}.vo.Res${modelNameUpperCamel}VO;
import ${clientPackage}.vo.Req${modelNameUpperCamel}VO;
import ${clientPackage}.vo.${modelNameUpperCamel}DTO;
import com.zjbdos.springcloud.common.core.vo.ResponseResult;
import com.zjbdos.springcloud.common.core.util.DataUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/**
* @package:${serverPackage}.service.impl
* @description:
* @author: ${author}
* @create: ${date}
**/
@Service
public class ${modelNameUpperCamel}ServiceImpl implements ${modelNameUpperCamel}Service {
    
	@Autowired
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

    @Override
    public ResponseResult<List<Res${modelNameUpperCamel}VO>> getList(Req${modelNameUpperCamel}VO req${modelNameLowerCamel}VO) {
        Example queryExample = new Example(${modelNameUpperCamel}.class);
        List<${modelNameUpperCamel}> results = ${modelNameLowerCamel}Mapper.selectByExample(queryExample);
        return ResponseResult.success(${modelNameUpperCamel}Converter.INSTANCE.${modelNameLowerCamel}s2Res${modelNameUpperCamel}VOs(results));
    }

    @Override
    public ResponseResult<Res${modelNameUpperCamel}VO> getDetail(${modelNameUpperCamel}DTO ${modelNameUpperCamel}DTO) {
        Example queryExample = new Example(${modelNameUpperCamel}.class);
        queryExample.and().andEqualTo("deleted", SysConsts.SYS_NO)
                .andEqualTo("id", ${modelNameUpperCamel}DTO.getId())
                .andEqualTo("organizationEntityId", WorkUtil.getOrgId());
        ${modelNameUpperCamel} result = ${modelNameLowerCamel}Mapper.selectOneByExample(queryExample);
        return ResponseResult.success( ${modelNameUpperCamel}Converter.INSTANCE.${modelNameLowerCamel}2Res${modelNameUpperCamel}VO(result));
    }

	@Override
    public ResponseResult<Res${modelNameUpperCamel}VO> getListPageSize(${modelNameUpperCamel}DTO ${modelNameLowerCamel}DTO) {

		PageHelper.startPage(${modelNameLowerCamel}DTO.getCurrent(), ${modelNameLowerCamel}DTO.getPageSize());
        Example example = new Example(${modelNameUpperCamel}.class);
        Example.Criteria criteria = example.createCriteria();
		List<Res${modelNameUpperCamel}VO> res${modelNameUpperCamel}VOs= ${modelNameUpperCamel}Converter.INSTANCE.${modelNameLowerCamel}s2Res${modelNameUpperCamel}VOs(${modelNameLowerCamel}Mapper.selectByExample(example));
		PageInfo<Res${modelNameUpperCamel}VO> pageInfo = new PageInfo<>(res${modelNameUpperCamel}VOs);
        return new ResponseResult<>(pageInfo);
	}

    @Override
    public ResponseResult add(${modelNameUpperCamel}DTO ${modelNameLowerCamel}DTO) {
        ${modelNameUpperCamel} bean =  ${modelNameUpperCamel}Converter.INSTANCE.${modelNameLowerCamel}DTO2Bean(${modelNameLowerCamel}DTO);
        bean.assignDefault();
        ${modelNameLowerCamel}Mapper.insert(bean);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<Res${modelNameUpperCamel}VO> delete(List<String> ids) {
        if(!DataUtil.isNullOrEmpty(ids)){
            for (String id:ids) {
                ${modelNameUpperCamel} bean = new ${modelNameUpperCamel}();
                bean.setId(id);
                bean.setDeleted(SysConsts.SYS_YES);
                ${modelNameLowerCamel}Mapper.updateByPrimaryKeySelective(bean);
            }
            }



        return ResponseResult.success();

    }

    @Override
    public ResponseResult<Res${modelNameUpperCamel}VO> update(${modelNameUpperCamel}DTO ${modelNameLowerCamel}DTO) {
        Example queryAccount = new Example(${modelNameUpperCamel}.class);
        if (!DataUtil.isNullOrEmpty(${modelNameLowerCamel}DTO.getAccount())) {
            queryAccount.and().andEqualTo("deleted",  SysConsts.SYS_NO)
                    .andEqualTo("account",${modelNameLowerCamel}DTO.getAccount())
                    .andEqualTo("organizationEntityId", WorkUtil.getOrgId())
                    .andNotEqualTo("id",${modelNameLowerCamel}DTO.getId());
            List<${modelNameUpperCamel}>  results = ${modelNameLowerCamel}Mapper.selectByExample(queryAccount);
            if (results.size()>0){
                throw new MyException(${modelNameLowerCamel}DTO, "已经有相同账号的店铺！");
            }
        }

        Example queryNamePlatformId = new Example(${modelNameUpperCamel}.class);
        queryNamePlatformId.and().andEqualTo("deleted",  SysConsts.SYS_NO)
                .andEqualTo("name",${modelNameLowerCamel}DTO.getName())
                .andEqualTo("platformId",${modelNameLowerCamel}DTO.getPlatformId())
                .andNotEqualTo("id",${modelNameLowerCamel}DTO.getId());
        List<${modelNameUpperCamel}> results = ${modelNameLowerCamel}Mapper.selectByExample(queryNamePlatformId);
        if (results.size()>0){
            throw new MyException(${modelNameLowerCamel}DTO, "已经在该平台上有相同名称的店铺！");
        }

        ${modelNameUpperCamel} bean = new ${modelNameUpperCamel}();
        bean.setId(${modelNameLowerCamel}DTO.getId());
        bean.setName(${modelNameLowerCamel}DTO.getName());
        bean.setPlatformId(${modelNameLowerCamel}DTO.getPlatformId());
        bean.setAccount(${modelNameLowerCamel}DTO.getAccount());
        bean.setPassword(${modelNameLowerCamel}DTO.getPassword());
        bean.setComCode(${modelNameLowerCamel}DTO.getComCode());
        ${modelNameLowerCamel}Mapper.updateByPrimaryKeySelective(bean);
        return ResponseResult.success();
    }


}