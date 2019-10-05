package com.kexinxin.util.crawler;

import java.util.List;

public interface Crawler {
	public void start() throws Exception;
	public List<CrawlerQuestion> getCrawlerQuestionList();
}
