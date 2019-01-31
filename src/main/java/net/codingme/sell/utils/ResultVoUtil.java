package net.codingme.sell.utils;

import net.codingme.sell.enums.ResultEnum;
import net.codingme.sell.vo.ResultVo;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *
 * @Author niujinpeng
 * @Date 2019/1/30 11:08
 */
public class ResultVoUtil {

    public static ResultVo success(Object data) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ResultEnum.SUCCESS.getCode());
        resultVo.setMsg(ResultEnum.SUCCESS.getMessage());
        if (data == null) {
            return resultVo;
        }
        if (data instanceof List) {
            resultVo.setData(data);
            return resultVo;
        }
        List<Object> objectList = Arrays.asList(data);
        resultVo.setData(objectList);
        return resultVo;
    }

    public static ResultVo success() {
        return success(null);
    }

    public static ResultVo eerror(Integer code, String msg) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;
    }

}
