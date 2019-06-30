package own.jiang.mall.common.code;

/**
 * @author newjiang
 * @date 2019/6/4
 * @description: 全局唯一标识符
 */
public interface GUID {

    /**
     * 获取下一个ID
     * @return
     */
    String nextId();

}
