<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/navbar.jsp"%>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="carousel slide" id="carousel-801771">
				<ol class="carousel-indicators">
					<li data-slide-to="0" data-target="#carousel-801771"></li>
					<li data-slide-to="1" data-target="#carousel-801771" class="active">
					</li>
					<li data-slide-to="2" data-target="#carousel-801771"></li>
				</ol>
				<div class="carousel-inner">
					<div class="item">
						<img alt="" src="../img/home/hadoop.png" />
						<div class="carousel-caption">
							<h4>Hadoop</h4>
							<p>
								Hadoop是一个由Apache基金会所开发的分布式系统基础架构。用户可以在不了解分布式底层细节的情况下，开发分布式程序。充分利用集群的威力进行高速运算和存储。
							</p>
						</div>
					</div>
					<div class="item active">
						<img alt="" src="../img/home/lucene.jpg" />
						<div class="carousel-caption">
							<h4>Lucene</h4>
							<p>
								Lucene是一个开放源代码的全文检索引擎工具包，但它不是一个完整的全文检索引擎，而是一个全文检索引擎的架构，提供了完整的查询引擎和索引引擎。
							</p>
						</div>
					</div>
					<div class="item">
						<img alt="" src="../img/home/crawler.jpg" />
						<div class="carousel-caption">
							<h4>网络爬虫</h4>
							<p>网络爬虫是一种按照一定的规则，自动地抓取万维网信息的程序或者脚本。</p>
						</div>
					</div>
				</div>
				<a data-slide="prev" href="#carousel-801771"
					class="left carousel-control">‹</a> <a data-slide="next"
					href="#carousel-801771" class="right carousel-control">›</a>
			</div>
			<h3>热门试题</h3>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span4">
			<blockquote>
				<p>
					淘宝网（www.taobao.com）与阿里巴巴网（www.alibaba.com）是阿里巴巴集团下的两个独立网站，假设淘宝网每天的独立访客数在亿以上（以IP计），阿里巴巴网每天的独立访客数在千万以上（以IP计）；这两个网站有各自的浏览日志，记录了访客在本网站上的浏览记录，如IP、访问时间、访问的URL等（注：一个IP在某天可能访问多个页面）；现有这两个网站某天的浏览日志文件各一份，要计算在该天既访问过淘宝网又访问过阿里巴巴网站的独立访客数大约是多少，请给出你能想到的方案（可多个）
				</p>
				<small>知识点 <cite>复杂度</cite></small>
			</blockquote>
		</div>
		<div class="span4">
			<blockquote>
				<p>软件开发中的瀑布模型典型的刻画了软件存在周期的阶段划分，与其最相适应的软件开发方法是哪一个？</p>
				<small>知识点 <cite>软件工程</cite></small>
			</blockquote>
		</div>
		<div class="span4">
			<blockquote>
				<p>
					数据库中有学院表和成绩表学院表t_school结构如下:学院ID：school_id,学院名称:school_name成绩表t_score结构如下:学号:id.姓名:name,分数:score,学院ID:school_id请用sql语句查询出学院名称为"计算机系"的分数最高的前20位的学生姓名
				</p>
				<small>知识点<cite>SQL</cite></small>
			</blockquote>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span4">
			<blockquote>
				<p>
					春节期间小明使用微信收到很多个红包，非常开心。在查看领取红包记录时发现，某个红包金额出现的次数超过了红包总数的一半。请帮小明找到该红包金额。写出具体算法思路和代码实现，要求算法尽可能高效。
				</p>
				<small>知识点 <cite>算法基础</cite></small>
			</blockquote>
		</div>
		<div class="span4">
			<blockquote>
				<p>
					在搜索引擎中，会针对每一网站生成一段摘要，并展示在相应query的搜索结果中。给你两个字符串S和T，请设计并实现一个高效的最短摘要生成算法，该算法能找出S中包含所有T中的字符的最短子字符串，即最短摘要，如：S="ADOBECODEBANC"T="ABC"最短摘要结果为"BANC"
				</p>
				<small>知识点 <cite>动态规划</cite></small>
			</blockquote>
		</div>
		<div class="span4">
			<blockquote>
				<p>
					在8X8的棋盘上分布着n个骑士，他们想约在某一个格中聚会。骑士每天可以像国际象棋中的马那样移动一次，可以从中间像8个方向移动（当然
					不能走出棋盘），请计算n个骑士的最早聚会地点和要走多少天。要求尽早聚会，且n个人走的总步数最少，先到聚会地点的骑士可以不再移动等待其他的骑士。
					从键盘输入n（0i,yi（0<=xi,yi<=7）。屏幕输出以空格分隔的三个数，分别为聚会点（x，y）以及走的天数。</p>
				<small>知识点 <cite>动态规划</cite></small>
			</blockquote>
		</div>
	</div>
</div>
<%@include file="../common/footer.jsp"%>