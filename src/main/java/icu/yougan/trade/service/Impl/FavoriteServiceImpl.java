package icu.yougan.trade.service.Impl;

import icu.yougan.trade.entity.Favorite;
import icu.yougan.trade.entity.IdleItem;
import icu.yougan.trade.mapper.FavoriteMapper;
import icu.yougan.trade.mapper.IdleItemMapper;
import icu.yougan.trade.service.FavoriteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:油柑
 * @Date:2024/5/21 10:43
 * @Version:v1.0.0
 * @Description:收藏 业务服务实现类
 **/
@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Resource
    private FavoriteMapper favoriteMapper;

    @Resource
    private IdleItemMapper idleItemMapper;

    @Override
    public boolean addFavorite(Favorite favorite) {
        return favoriteMapper.insert(favorite) == 1;
    }

    @Override
    public boolean deleteFavorite(Long id) {
        return favoriteMapper.deleteByPrimaryKey(id) == 1;
    }

    @Override
    public Integer isFavorite(Long userId, Long idleId) {
        return favoriteMapper.checkFavorite(userId, idleId);
    }

    @Override
    public List<Favorite> getAllFavorite(Long userId) {
        List<Favorite> list = favoriteMapper.getMyFavorite(userId);
        if (list.size() > 0) {
            List<Long> idleIdList = new ArrayList<>();
            for (Favorite i : list) {
                idleIdList.add(i.getIdleId());
            }
            List<IdleItem> idleItemList = idleItemMapper.findIdleByList(idleIdList);
            Map<Long, IdleItem> map = new HashMap<>();
            for (IdleItem idle : idleItemList) {
                map.put(idle.getId(), idle);
            }
            for (Favorite i : list) {
                i.setIdleItem(map.get(i.getIdleId()));
            }
        }
        return list;
    }

}
