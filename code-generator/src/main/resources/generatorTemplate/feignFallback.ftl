package ${clientPackage}.feign.fallback;

import ${clientPackage}.feign.${modelNameUpperCamel}ServiceFeign;
import ${clientPackage}.vo.Req${modelNameUpperCamel}VO;
import ${clientPackage}.vo.Res${modelNameUpperCamel}VO;
import ${clientPackage}.feign.${modelNameUpperCamel}ServiceFeign;
import com.zjbdos.springcloud.common.core.vo.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;


/**
 * @package: ${clientPackage}.feign.fallback
 * @description: feign 实现类
 * @author: ${author}
 * @create: ${date}
 **/
 
 public class ${modelNameUpperCamel}ServiceFeignImpl implements ${modelNameUpperCamel}ServiceFeign {
    private Throwable cause;
    private final Logger log = LoggerFactory.getLogger(SerialNumberFeignImpl.class);
	public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }
	
	    @Override
    public ResponseResult<List<Res${modelNameUpperCamel}VO>> getList(Req${modelNameUpperCamel}VO req${modelNameUpperCamel}VO) {
        throw  new MyException("feign调用失败！");
    }

    @Override
    public ResponseResult<List<Res${modelNameUpperCamel}VO>> add(Req${modelNameUpperCamel}VO req${modelNameUpperCamel}VO) {
        throw  new MyException("feign调用失败！");
    }

    @Override
    public ResponseResult<List<Res${modelNameUpperCamel}VO>> update(Req${modelNameUpperCamel}VO req${modelNameUpperCamel}VO) {
        throw  new MyException("feign调用失败！");
    }

    @Override
    public ResponseResult<List<Res${modelNameUpperCamel}VO>> delete(Req${modelNameUpperCamel}VO req${modelNameUpperCamel}VO) {
        throw  new MyException("feign调用失败！");
    }

    @Override
    public ResponseResult<Res${modelNameUpperCamel}VO> getdetail(Req${modelNameUpperCamel}VO req${modelNameUpperCamel}VO) {
        throw  new MyException("feign调用失败！");
    }



}