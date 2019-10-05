package com.kexinxin.util.classify;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.aliasi.classify.Classification;
import com.aliasi.classify.Classified;
import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.classify.JointClassification;
import com.aliasi.classify.JointClassifier;
import com.aliasi.classify.JointClassifierEvaluator;
import com.aliasi.lm.NGramProcessLM;
import com.aliasi.util.AbstractExternalizable;
import com.kexinxin.bean.Question;
import com.kexinxin.dao.impl.SearchDAOImpl;

public class ClassifyUtil {

	private String[] CATEGORIES = null;

	private int NGRAM_SIZE;

	private List<Question> subjectList = null;

	public String[] getCATEGORIES() {
		return CATEGORIES;
	}

	public void setCATEGORIES(String[] cATEGORIES) {
		CATEGORIES = cATEGORIES;
	}

	public int getNGRAM_SIZE() {
		return NGRAM_SIZE;
	}

	public void setNGRAM_SIZE(int nGRAM_SIZE) {
		NGRAM_SIZE = nGRAM_SIZE;
	}

	public List<Question> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Question> subjectList) {
		this.subjectList = subjectList;
	}

	public ClassifyUtil() {
		subjectList = SearchDAOImpl.getSubjectList();
		HashSet<String> set = new HashSet<String>();
		for (int i = 0; i < subjectList.size(); i++) {
			set.add(subjectList.get(i).getSkillName());
		}
		NGRAM_SIZE = set.size();
		CATEGORIES = new String[NGRAM_SIZE];
		Iterator it = set.iterator();
		int i = 0;
		while (it.hasNext()) {
			String next = (String) it.next();
			CATEGORIES[i] = next;
			i++;
		}
	}

	public String[] getBestCategory(String text) throws ClassNotFoundException, IOException {

		DynamicLMClassifier<NGramProcessLM> classifier = DynamicLMClassifier.createNGramProcess(getCATEGORIES(),
				getNGRAM_SIZE());

		for (Question subject : getSubjectList()) {
			Classification classification = new Classification(subject.getSkillName());
			Classified<CharSequence> classified = new Classified<CharSequence>(subject.getQuestionContent(),
					classification);
			classifier.handle(classified);
		}

		@SuppressWarnings("unchecked") // we created object so know it's safe
		JointClassifier<CharSequence> compiledClassifier = (JointClassifier<CharSequence>) AbstractExternalizable
				.compile(classifier);

		boolean storeCategories = true;
		JointClassifierEvaluator<CharSequence> evaluator = new JointClassifierEvaluator<CharSequence>(
				compiledClassifier, getCATEGORIES(), storeCategories);
		String bestCategory[] = new String[3];
		for (int i = 0; i < getCATEGORIES().length; ++i) {

			Classification classification = new Classification(getCATEGORIES()[i]);
			Classified<CharSequence> classified = new Classified<CharSequence>(text, classification);
			evaluator.handle(classified);
			JointClassification jc = compiledClassifier.classify(text);
			bestCategory[0] = jc.bestCategory();
			bestCategory[1] = jc.category(1);
			bestCategory[2] = jc.category(2);
		}
		return bestCategory;
	}

	public String getBestOneCategory(String text) throws ClassNotFoundException, IOException {

		DynamicLMClassifier<NGramProcessLM> classifier = DynamicLMClassifier.createNGramProcess(getCATEGORIES(),
				getNGRAM_SIZE());

		for (Question subject : getSubjectList()) {
			Classification classification = new Classification(subject.getSkillName());
			Classified<CharSequence> classified = new Classified<CharSequence>(subject.getQuestionContent(),
					classification);
			classifier.handle(classified);
		}

		@SuppressWarnings("unchecked") // we created object so know it's safe
		JointClassifier<CharSequence> compiledClassifier = (JointClassifier<CharSequence>) AbstractExternalizable
				.compile(classifier);

		boolean storeCategories = true;
		JointClassifierEvaluator<CharSequence> evaluator = new JointClassifierEvaluator<CharSequence>(
				compiledClassifier, getCATEGORIES(), storeCategories);
		String bestCategory[] = new String[2];
		for (int i = 0; i < getCATEGORIES().length; ++i) {

			Classification classification = new Classification(getCATEGORIES()[i]);
			Classified<CharSequence> classified = new Classified<CharSequence>(text, classification);
			evaluator.handle(classified);
			JointClassification jc = compiledClassifier.classify(text);
			bestCategory[0] = jc.bestCategory();
			bestCategory[1] = jc.category(1);
		}
		return bestCategory[0];
	}

	// public static void main(String[] args) throws ClassNotFoundException,
	// IOException {
	// ClassifyUtil classify = new ClassifyUtil();
	//
	// DynamicLMClassifier<NGramProcessLM> classifier = DynamicLMClassifier
	// .createNGramProcess(classify.getCATEGORIES(), classify.getNGRAM_SIZE());
	//
	// for (Subject subject : classify.getSubjectList()) {
	// Classification classification = new
	// Classification(subject.getSkillName());
	// Classified<CharSequence> classified = new
	// Classified<CharSequence>(subject.getContent(), classification);
	// classifier.handle(classified);
	// }
	//
	// // System.out.println("Compiling");
	// @SuppressWarnings("unchecked") // we created object so know it's safe
	// JointClassifier<CharSequence> compiledClassifier =
	// (JointClassifier<CharSequence>) AbstractExternalizable
	// .compile(classifier);
	//
	// boolean storeCategories = true;
	// JointClassifierEvaluator<CharSequence> evaluator = new
	// JointClassifierEvaluator<CharSequence>(
	// compiledClassifier, classify.getCATEGORIES(), storeCategories);
	// String bestCategory = "";
	// for (int i = 0; i < classify.getCATEGORIES().length; ++i) {
	// // File classDir = new File(TESTING_DIR, CATEGORIES[i]);
	//
	// String text = "����ʱ�临�Ӷ���͵ķ����ҳ���������ֵ�����������Ԫ�صĲ�ֵ��";
	//
	// // System.out.print("Testing on " + classify.getCATEGORIES()[i]);
	// Classification classification = new
	// Classification(classify.getCATEGORIES()[i]);
	// Classified<CharSequence> classified = new Classified<CharSequence>(text,
	// classification);
	// evaluator.handle(classified);
	// JointClassification jc = compiledClassifier.classify(text);
	// bestCategory = jc.bestCategory();
	// // String details = jc.toString();
	// // System.out.println("Got best category of: " + bestCategory);
	// // System.out.println(jc.toString());
	// // System.out.println("---------------");
	// }
	// System.out.println(bestCategory);
	// // ConfusionMatrix confMatrix = evaluator.confusionMatrix();
	// // System.out.println("Total Accuracy: " + confMatrix.totalAccuracy());
	// //
	// // System.out.println("\nFULL EVAL");
	// // System.out.println(evaluator);
	//
	// }
	// public static void main(String[] args) throws ClassNotFoundException,
	// IOException {
	// String[] a=new
	// ClassifyUtil().bestCategory("����ʱ�临�Ӷ���͵ķ����ҳ���������ֵ�����������Ԫ�صĲ�ֵ��");
	// System.out.println(a[0]+" "+a[1]);
	// }
}
