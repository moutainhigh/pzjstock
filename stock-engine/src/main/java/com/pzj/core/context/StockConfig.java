package com.pzj.core.context;

import com.pzj.commons.utils.PropertyLoader;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * Created by Administrator on 2016-12-22.
 */
public class StockConfig {
	@Resource(name = "propertyLoader")
	private PropertyLoader propertyLoader;

	private Properties configs;

	public void setConfigs(Properties configs) {
		this.configs = configs;
	}

	public String getConfig(String key) {
		return configs.getProperty(key);
	}

	public void setConfig(String key, String value) {
		configs.setProperty(key, value);
	}

	public Boolean getEnableDock() {
        String property = null;
        if (propertyLoader != null){
            property = propertyLoader.getProperty("stock_conf", "stock.dock", "false");
        }
        if (property == null){
            property = configs.getProperty("stock.dock");
        }
		return property != null && property.equals("true");
	}

	public Boolean getEnableDockPreemption() {
		String property = null;
		if (propertyLoader != null){
			property = propertyLoader.getProperty("stock_conf", "stock.dock.preemption", "false");
		}
		if (property == null){
			property = configs.getProperty("stock.dock.preemption");
		}
		return property != null && property.equals("true");
	}
}
