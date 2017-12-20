package org.wg.webuploader.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.wg.webuploader.mapper.ItemsMapper;
import org.wg.webuploader.service.ItemsService;

/**
 * <p>Title: ItemsServiceImpl</p>
 * <p>Description: 商品管理</p>
 */
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsMapper itemsMapper;

//    @Transactional(isolation = Isolation.REPEATABLE_READ)
//    @Override
/*    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)
            throws Exception {
        //通过ItemsMapperCustom查询数据库
        return itemsMapper.findItemsList(itemsQueryVo);
    }*/
}
