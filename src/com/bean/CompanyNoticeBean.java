package com.bean;
/**
 * {
    "api": "APISUCCESS",
    "data": [
        {
            "article_title": "新年快乐",
            "article_hit": "38",
            "article_id": "209",
            "article_time": "2015-02-10 14:42:28"
        },
        {
            "article_title": "公司公告",
            "article_hit": "60",
            "article_id": "208",
            "article_time": "2015-02-10 14:40:11"
        }
    ],
    "status": 1
}
 * */
public class CompanyNoticeBean {

	private String article_title;
	public String getArticle_title() {
		return article_title;
	}
	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}
	public String getArticle_hit() {
		return article_hit;
	}
	public void setArticle_hit(String article_hit) {
		this.article_hit = article_hit;
	}
	public String getArticle_id() {
		return article_id;
	}
	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}
	public String getArticle_time() {
		return article_time;
	}
	public void setArticle_time(String article_time) {
		this.article_time = article_time;
	}
	private String article_hit;
	private String article_id;
	private String article_time;
}
