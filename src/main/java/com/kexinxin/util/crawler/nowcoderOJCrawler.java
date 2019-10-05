package com.kexinxin.util.crawler;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;

/**
 * Crawling news from hfut news
 *
 * @author hu
 */
public class nowcoderOJCrawler extends BreadthCrawler implements Crawler {

	List<CrawlerQuestion> crawlerQuestionList = new ArrayList<CrawlerQuestion>();

	public List<CrawlerQuestion> getCrawlerQuestionList() {
		return crawlerQuestionList;
	}

	public void addQuestion(CrawlerQuestion question) {
		crawlerQuestionList.add(question);
	}

	public nowcoderOJCrawler() {
		super(null, true);
	};

	/**
	 * @param crawlPath
	 *            crawlPath is the path of the directory which maintains
	 *            information of this crawler
	 * @param autoParse
	 *            if autoParse is true,BreadthCrawler will auto extract links
	 *            which match regex rules from pag
	 */
	public nowcoderOJCrawler(String seed, String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		/* start page */
		// this.addSeed("https://www.nowcoder.com/questionCenter?orderByHotValue=0&questionTypes=001110&mutiTagIds=570&onlyReference=false&page=20");
		this.addSeed(seed);
		/* fetch url like http://news.hfut.edu.cn/show-xxxxxxhtml */
		this.addRegex("https://www.nowcoder.com/practice/.*");
		/* do not fetch jpg|png|gif */
		this.addRegex("-.*\\.(jpg|png|gif).*");
		/* do not fetch url contains # */
		this.addRegex("-.*#.*");
	}

	public void visit(Page page, CrawlDatums next) {
		/* if page is news page */
		if (page.matchUrl("https://www.nowcoder.com/practice/.*")) {
			String question = page.select(".terminal-topic-title").first().text();

			System.out.println("URL:\n" + page.url());
			System.out.println("title:\n" + question);

			CrawlerService test = new CrawlerService();
			String content = page.select(".subject-describe").text();
			System.out.println("content:\n" + content);
			test.saveOJProgrammingQuestion(question, "[编程题]", content);

			// CrawlerQuestion crawlerQuestion=new CrawlerQuestion();
			// crawlerQuestion.setQuestion(question);
			// crawlerQuestion.setType("编程题");
			// crawlerQuestionList.add(crawlerQuestion);
			try {
				CrawlerLuceneIndexService service = new CrawlerLuceneIndexService();
				//service.add(question, "编程题");
				CrawlerLuceneIndexService.add(question, "编程题");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void start() throws Exception {
		String url = "https://www.nowcoder.com/activity/oj?title=&tags=&order=&asc=false&page=1";
		nowcoderOJCrawler crawler = new nowcoderOJCrawler(url, "crawl", true);
		crawler.setThreads(1);
		crawler.setTopN(2);
		crawler.start(2);
	}

	public static void main(String[] args) throws Exception {

	}
}
