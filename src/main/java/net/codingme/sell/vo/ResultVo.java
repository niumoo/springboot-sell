package net.codingme.sell.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.codingme.sell.enums.ResultEnum;

import javax.xml.transform.Result;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * http请求返回的最外层对象
 * 
 * @Author niujinpeng
 * @Date 2019/1/29 21:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVo<T> {

    /** 错误码 */
    private Integer code;

    /** 提示信息 */
    private String msg;

    /** 具体内容 */
    private T data;


}
