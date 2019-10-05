package com.kexinxin.util.crawler;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * Crawling news from hfut news
 *
 * @author hu
 */
public class nowcoderSelectCrawler extends BreadthCrawler implements Crawler {
	List<CrawlerQuestion> crawlerQuestionList = new ArrayList<CrawlerQuestion>();

	public List<CrawlerQuestion> getCrawlerQuestionList() {
		return crawlerQuestionList;
	}

	public void addQuestion(CrawlerQuestion question) {
		crawlerQuestionList.add(question);
	}

	public nowcoderSelectCrawler() {
		super(null, true);
	}

	/**
	 * @param crawlPath
	 *            crawlPath is the path of the directory which maintains
	 *            information of this crawler
	 * @param autoParse
	 *            if autoParse is true,BreadthCrawler will auto extract links
	 *            which match regex rules from pag
	 */
	public nowcoderSelectCrawler(String seed, String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		/* start page */
		// this.addSeed("https://www.nowcoder.com/questionCenter?orderByHotValue=0&questionTypes=001110&mutiTagIds=570&onlyReference=false&page=20");
		this.addSeed(seed);
		/* fetch url like http://news.hfut.edu.cn/show-xxxxxxhtml */
		this.addRegex("https://www.nowcoder.com/questionTerminal/.*");
		/* do not fetch jpg|png|gif */
		this.addRegex("-.*\\.(jpg|png|gif).*");
		/* do not fetch url contains # */
		this.addRegex("-.*#.*");
	}

	public void visit(Page page, CrawlDatums next) {
		/* if page is news page */
		if (page.matchUrl("https://www.nowcoder.com/questionTerminal/.*")) {
			/* we use jsoup to parse page */
			// Document doc = page.doc();

			/* extract title and content of news by css selector */
			String question = page.select(".crumbs-end").first().text();
			question = question.replace("_牛客网", "");
			String type = page.select(".tag-label").first().text();
			String questionType = page.select(".subject-title").first().text();

			String difficul = page.select(".stars-new").get(0).attr("class");
			difficul = difficul.charAt(difficul.length() - 1) + "";

			System.out.println("URL:\n" + page.url());
			System.out.println("title:\n" + question);
			System.out.println("type:\n" + type);
			System.out.println("difficul:" + difficul);
			System.out.println("questionType:" + questionType);

			CrawlerService test = new CrawlerService();

			test.saveQuestion(question, type, difficul);

			// CrawlerQuestion crawlerQuestion=new CrawlerQuestion();
			// crawlerQuestion.setQuestion(question);
			// crawlerQuestion.setType(type);
			// crawlerQuestionList.add(crawlerQuestion);
			try {
				CrawlerLuceneIndexService service = new CrawlerLuceneIndexService();
				//service.add(question, "单选题");
				CrawlerLuceneIndexService.add(question, "单选题");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			/* If you want to add urls to crawl,add them to nextLink */
			/*
			 * WebCollector automatically filters links that have been fetched
			 * before
			 */
			/*
			 * If autoParse is true and the link you add to nextLinks does not
			 * match the regex rules,the link will also been filtered.
			 */
			// next.add("http://xxxxxx.com");
		}
	}

	public void start() throws Exception {
		String url = "https://www.nowcoder.com/questionCenter?orderByHotValue=0&onlyReference=false&questionTypes=100000";
		nowcoderSelectCrawler crawler = new nowcoderSelectCrawler(url, "crawl", true);
		crawler.setThreads(1);
		crawler.setTopN(2);
		crawler.start(2);
	}

	public static void main(String[] args) throws Exception {

	}
}
