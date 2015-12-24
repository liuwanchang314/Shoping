package com.bean;

/**
 * {
    "api": "APISUCCESS",
    "data": {
        "article_title": "新年快乐",
        "article_content": "<img src=\"/attachment/editor/image/20150210/20150210144221_63898.gif\" alt=\"\" />新年快乐哦~",
        "article_hit": "38",
        "article_time": "2015-02-10 14:42:28"
    },
    "status": 1
}
 * */
public class Dynamicdetailsbean {
	
	private String article_title;
	public String getArticle_title() {
		return article_title;
	}
	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}
	public String getArticle_content() {
		return article_content;
	}
	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}
	public String getArticle_hit() {
		return article_hit;
	}
	public void setArticle_hit(String article_hit) {
		this.article_hit = article_hit;
	}
	public String getArticle_time() {
		return article_time;
	}
	public void setArticle_time(String article_time) {
		this.article_time = article_time;
	}
	private String article_content;
	private String article_hit;
	private String article_time;

}
