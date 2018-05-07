package cn.bluethink.onegis.resource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.bluethink.onegis.resource.mapper.PluginMapper;
import cn.bluethink.onegis.resource.model.Plugin;
import cn.bluethink.onegis.resource.service.PluginService;

@Service
public class PluginServiceImpl implements PluginService {

    @Autowired
    private PluginMapper pluginMapper;

    @Transactional(rollbackFor=Exception.class)
    @Override
    public boolean save(Plugin plugin) throws Exception {
        int flag = 0;
        if(plugin != null) {
            if(plugin.getId() > 0) {
                flag = pluginMapper.update(plugin);
            } else {
                flag = pluginMapper.insert(plugin);
            }
        }
        return flag > 0;
    }

    @Override
    public Plugin getById(long id) throws Exception {
        return pluginMapper.getById(id);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public boolean delete(long id) throws Exception {
        return pluginMapper.remove(id) > 0;
    }

    @Override
    public PageInfo<Plugin> getForPage(String name, String tags,
                                       int pageNum, Integer pageSize) throws Exception {
        PageInfo<Plugin> pageInfo = PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(()->{
                    try {
                        pluginMapper.getPluginList(name, tags);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        return pageInfo;
    }

}
