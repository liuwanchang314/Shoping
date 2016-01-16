package com.bean;
/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2016-1-16 下午6:51:24 
 * 类说明 
 * 
 * {
    "api": "APISUCCESS",
    "data": {
        "spec_name": [
            {
                "id": 11,
                "name": "颜色分类"
            },
            {
                "id": 10,
                "name": "适用床尺寸"
            }
        ],
        "spec_price": "182.00",
        "spec_storage": "60",
        "spec_salenum": "0",
        "spec_main": [
            {
                "id": 68,
                "key": "酒红色"
            },
            {
                "id": 41,
                "key": "1.8m（6英尺）床"
            }
        ]
    },
    "status": 1
}
 */
public class SpecBean {
	
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
}
