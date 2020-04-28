package com.sxgokit.bas.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sxgokit.bas.entity.dto.system.SystemConfigDTO;
import com.sxgokit.bas.mapper.system.SystemConfigMapper;
import com.sxgokit.bas.util.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;

/**
 * @author:Dukang
 * @createDate:2018年10月30日
 * @desc:此类中完成系统启动时需加载的数据
 */
@Configuration
public class StartupConfig implements ApplicationListener<ContextRefreshedEvent> {

	/**
	 * 项目启动加载系统配置表数据到redis的开关
	 */
	@Value("${rdf.loadConfigWhenStart}")
	private boolean loadConfigWhenStart = false;

	@Autowired
	private SystemConfigMapper sysConfigMapper;

	@Autowired
	private RedisUtil redisUtil;

	Logger logger = LoggerFactory.getLogger(StartupConfig.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		if(loadConfigWhenStart){
			logger.info("启动加载了系统配置" + loadSysConfig() + "到redis缓存中。");
		}
	}

	/**
	 * 加载系统配置数据表
	 */
	private int loadSysConfig(){
		List<SystemConfigDTO> models = sysConfigMapper.selectList(new QueryWrapper<>());
		for (SystemConfigDTO model:models) {
			redisUtil.set(model.getConfigKey(), model.getConfigValue());
		}
		return models.size();
	}
}
