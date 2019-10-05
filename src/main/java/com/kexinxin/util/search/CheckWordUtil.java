package com.kexinxin.util.search;

import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import com.kexinxin.bean.Skill;
import com.kexinxin.dao.impl.SearchDAOImpl;

public class CheckWordUtil {
	private static Directory skillDirectory = null;
	private static Directory checkWordDirectory = new RAMDirectory();

	public static String getSuggesion(String str) throws Exception {
		String result = null;
		if (skillDirectory == null) {
			skillDirectory = new RAMDirectory();

			Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_48);
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_48, analyzer);
			IndexWriter writer = new IndexWriter(skillDirectory, config);

			List<Skill> skillList = SearchDAOImpl.getSkillList();
			for (int i = 0; i < skillList.size(); i++) { // 3
				Document doc = new Document();
				doc.add(new Field("skillId", skillList.get(i).getSkillId() + "", Field.Store.YES,
						Field.Index.ANALYZED));
				doc.add(new Field("skillName", skillList.get(i).getSkillName(), Field.Store.YES, Field.Index.ANALYZED));
				doc.add(new Field("fatherSkillId", skillList.get(i).getFatherId() + "", Field.Store.YES,
						Field.Index.ANALYZED));
				writer.addDocument(doc);
			}
			writer.close();
		}


		SpellChecker spell = new SpellChecker(checkWordDirectory); // #A
		IndexReader r = IndexReader.open(skillDirectory);
		//spell.indexDictionary(new LuceneDictionary(r, "skillName"), null, true);

		//spell.setStringDistance(new LevensteinDistance()); // #B

		String wordToRespell = str;
		String[] suggestions = spell.suggestSimilar(wordToRespell, 5); // #C
		if (suggestions.length > 0) {
			result = suggestions[0];
		}
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		SpellChecker spell = new SpellChecker(new RAMDirectory());
		//getSuggesion("SQL");
	}
}
