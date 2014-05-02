package com.isssr5.entities;

import java.util.ArrayList;
import java.util.HashMap;

public class DefaultServicesTable {

	private static DefaultServicesTable instance = null;

	private HashMap<String, MacroService> table;

	private DefaultServicesTable() {

		table = new HashMap<String, MacroService>();

		// add elementary services for hypothesis parametric test
		add_TTEST_STAT();
		add_TTEST_ALPHA_2SIDED();
		add_TTEST_PVALUE_2SIDED();
		add_TTEST_1SIDED_LESSEQUAL();
		add_TTEST_1SIDED_GREATEREQUAL();
		add_TTEST_PAIRED_STAT();
		add_TTEST_PAIRED_PVALUE();
		add_TTEST_PAIRED_ALPHA();
		add_TTEST_STAT2();
		add_TTEST_PVALUE_2SIDED_2();
		add_TTEST_ALPHA_2SIDED_2();
		add_TTEST_STAT_2SIDED_EQVAR();
		add_TTEST_PVALUE_2SIDED_EQVAR();
		add_TTEST_ALPHA_2SIDED_EQVAR();
		add_TTEST_1SIDED_LESSEQUAL_2_EQVAR();
		add_TTEST_1SIDED_GREATEREQUAL_2_EQVAR();
		add_ONEWAY_ANOVA_PVALUE_3OP();
		add_ONEWAY_ANOVA_ALPHA_3OP();
		add_ONEWAY_ANOVA_PVALUE_4OP();
		add_ONEWAY_ANOVA_ALPHA_4OP();
		
		// non parametric test
		add_ConfidenceInterval();
		add_1SampleKolmogrov();
		add_2SampleKolmogrov();
		
		// add elementary services for descriptive statistic
		add_geometricMean();
		add_maxValue();
		add_mean();
		add_median();
		add_minValue();
		add_stdDeviation();
		add_variance();
		add_frequency();
	}

	public static DefaultServicesTable getInstance() {
		if (instance == null) {
			instance = new DefaultServicesTable();
		}
		return instance;
	}

	public HashMap<String, MacroService> getTable() {
		return table;
	}

	public void setTable(HashMap<String, MacroService> table) {
		this.table = table;
	}

	private void add_TTEST_STAT() {
		MacroService ms = new MacroService();
		String description = "Return t-statistic associated with a one-sample t-test comparing the mean"
				+ " of the observed values with {id_op1}"
				+ " against {mu} which is the second operand<BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/parametricTest/{userid}/TTEST_STAT/{id_op1}/{mu}/";
		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("t-test");
		keywords.add("two-sided");
		keywords.add("t-statistic");
		keywords.add("mean");

		ms.setIdCode("TTEST_STAT");
		ms.setNumOperand(2);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}

	private void add_TTEST_ALPHA_2SIDED() {
		MacroService ms = new MacroService();
		String description = "Performs a two-sided t-test evaluating the null hypothesis that"
				+ " the mean of the population from which sample with {id_op1} is drawn equals {mu0}.<BR>"
				+ "Returns true iff the null hypothesis can be rejected with confidence 1 - {alpha}<BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/parametricTest/{userid}/TTEST_ALPHA_2SIDED/{id_op1}/{mu0}/{alpha}/";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("t-test");
		keywords.add("two-sided");
		keywords.add("test");
		keywords.add("mean");

		ms.setIdCode("TTEST_ALPHA_2SIDED");
		ms.setNumOperand(3);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	private void add_TTEST_PVALUE_2SIDED() {
		MacroService ms = new MacroService();
		String description = "Performs a two-sided t-test evaluating the null hypothesis that"
				+ " associated with a one-sample, two-tailed t-test comparing the mean of the input array with the constant {mu}.<BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/parametricTest/{userid}/TTEST_PVALUE_2SIDED/{id_op1}/{mu}/";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("t-test");
		keywords.add("two-sided");
		keywords.add("p-value");
		keywords.add("mean");

		ms.setIdCode("TTEST_PVALUE_2SIDED");
		ms.setNumOperand(2);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	private void add_TTEST_1SIDED_LESSEQUAL() {
		MacroService ms = new MacroService();
		String description = "Performs a one-sided t-test evaluating the null hypothesis that,"
				+ " the mean of the population from which sample with {id_op1} is drawn, is less equal {mu0}"
				+ " with confidence level 1-{alpha}.<BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/parametricTest/{userid}/TTEST_1SIDED_LESSEQUAL/{id_op1}/{mu0}/{alpha}/";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("t-test");
		keywords.add("one-sided");
		keywords.add("test");
		keywords.add("mean");

		ms.setIdCode("TTEST_1SIDED_LESSEQUAL");
		ms.setNumOperand(3);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	private void add_TTEST_1SIDED_GREATEREQUAL() {
		MacroService ms = new MacroService();
		String description = "Performs a one-sided t-test evaluating the null hypothesis that,"
				+ " the mean of the population from which sample with {id_op1} is drawn, is greater equal {mu0}"
				+ " with confidence level 1-{alpha}.<BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/parametricTest/{userid}/TTEST_1SIDED_GREATEREQUAL/{id_op1}/{mu0}/{alpha}/";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("t-test");
		keywords.add("one-sided");
		keywords.add("test");
		keywords.add("mean");

		ms.setIdCode("TTEST_1SIDED_GREATEREQUAL");
		ms.setNumOperand(3);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	private void add_TTEST_PAIRED_STAT() {
		MacroService ms = new MacroService();
		String description = "Computes a paired, 2-sample t-statistic based on the data in the input arrays.<BR>"
				+ " The t-statistic returned is equivalent to what would be returned by computing the one-sample t-statistic"
				+ " with mu = 0 and the sample array consisting of the (signed) differences"
				+ " between corresponding entries in sample1 with {id_op1} and sample2 with {id_op2}.<BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/parametricTest/root/TTEST_PAIRED_STAT/{id_op1}/{id_op2}/";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("t-test");
		keywords.add("two-sided");
		keywords.add("t-statistic");
		keywords.add("paired");

		ms.setIdCode("TTEST_PAIRED_STAT");
		ms.setNumOperand(2);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	private void add_TTEST_PAIRED_PVALUE() {
		MacroService ms = new MacroService();
		String description = "Returns the observed significance level, or p-value,"
				+ " associated with a paired, two-sample, two-tailed t-test based on the data in the input with {id_op1} and {id_op2}.<BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/parametricTest/{userid}/TTEST_PAIRED_PVALUE/{id_op1}/{id_op2}/";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("t-test");
		keywords.add("two-sided");
		keywords.add("p-value");
		keywords.add("paired");

		ms.setIdCode("TTEST_PAIRED_PVALUE");
		ms.setNumOperand(2);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	private void add_TTEST_PAIRED_ALPHA() {
		MacroService ms = new MacroService();
		String description = "Performs a paired t-test evaluating the null hypothesis"
				+ " that the mean of the paired differences between sample1 with {id_op1} and sample2 with {id_op2} is 0"
				+ " with confidence level 1-{alpha}<BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/parametricTest/{userid}/TTEST_PAIRED_ALPHA/{id_op1}/{id_op2}/{alpha}/";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("t-test");
		keywords.add("two-sided");
		keywords.add("test");
		keywords.add("paired");
		keywords.add("mean");

		ms.setIdCode("TTEST_PAIRED_ALPHA");
		ms.setNumOperand(3);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	private void add_TTEST_STAT2() {
		MacroService ms = new MacroService();
		String description = "Computes a 2-sample t-statistic, without the hypothesis of equal subpopulation variances.<BR>"
				+ " Two sample are specified by {id_op1} and {id_op2}.<BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/parametricTest/{userid}/TTEST_STAT2/{id_op1}/{id_op2}/";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("t-test");
		keywords.add("t-statistic");
		keywords.add("different variance");

		ms.setIdCode("TTEST_STAT2");
		ms.setNumOperand(2);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	private void add_TTEST_PVALUE_2SIDED_2() {
		MacroService ms = new MacroService();
		String description = "Returns the observed significance level, or p-value,"
				+ " associated with a two-sample with {id_op1} and {id_op2}, two-tailed t-test comparing the means of the input arrays.<BR>"
				+ " The test does not assume that the underlying popuation variances are equal.<BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/parametricTest/{userid}/TTEST_PVALUE_2SIDED_2/{id_op1}/{id_op2}/";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("t-test");
		keywords.add("p-value");
		keywords.add("different variance");
		keywords.add("two-sided");
		keywords.add("mean");

		ms.setIdCode("TTEST_PVALUE_2SIDED_2");
		ms.setNumOperand(2);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	private void add_TTEST_ALPHA_2SIDED_2() {
		MacroService ms = new MacroService();
		String description = "Performs a two-sided t-test evaluating the null hypothesis"
				+ " that sample1 with {id_op1} and sample2 with {id_op2} are drawn from"
				+ " populations with the same mean, with significance level {alpha}.<BR>"
				+ "This test does not assume that the subpopulation variances are equal.<BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/parametricTest/{userid}/TTEST_ALPHA_2SIDED_2/{id_op1}/{id_op2}/{alpha}/";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("t-test");
		keywords.add("test");
		keywords.add("different variance");
		keywords.add("mean");
		keywords.add("two-sided");

		ms.setIdCode("TTEST_ALPHA_2SIDED_2");
		ms.setNumOperand(3);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	private void add_TTEST_STAT_2SIDED_EQVAR() {
		MacroService ms = new MacroService();
		String description = "Computes a 2-sample t statistic for two sample with"
				+ " {id_op1} and {id_op2}, under the hypothesis of equal subpopulation variances.<BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/parametricTest/{userid}/TTEST_STAT_2SIDED_EQVAR/{id_op1}/{id_op2}/";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("t-test");
		keywords.add("t-statistic");
		keywords.add("equal variance");
		keywords.add("mean");
		keywords.add("two-sided");

		ms.setIdCode("TTEST_STAT_2SIDED_EQVAR");
		ms.setNumOperand(2);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	private void add_TTEST_PVALUE_2SIDED_EQVAR() {
		MacroService ms = new MacroService();
		String description = "Returns the observed significance level, or p-value,"
				+ " associated with a two-sample with {id_op1} and {id_op2}, two-tailed t-test comparing the means of the input arrays.<BR>"
				+ " The test assume that the underlying popuation variances are equal.<BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/parametricTest/{userid}/TTEST_PVALUE_2SIDED_EQVAR/{id_op1}/{id_op2}/";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("t-test");
		keywords.add("p-value");
		keywords.add("equal variance");
		keywords.add("two-sided");
		keywords.add("mean");

		ms.setIdCode("TTEST_PVALUE_2SIDED_EQVAR");
		ms.setNumOperand(2);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	private void add_TTEST_ALPHA_2SIDED_EQVAR() {
		MacroService ms = new MacroService();
		String description = "Performs a two-sided t-test evaluating the null hypothesis"
				+ " that sample1 with {id_op1} and sample2 with {id_op2} are drawn from"
				+ " populations with the same mean, with significance level {alpha}.<BR>"
				+ "This test assume that the subpopulation variances are equal.<BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/parametricTest/{userid}/TTEST_ALPHA_2SIDED_2_EQVAR/{id_op1}/{id_op2}/{alpha}/";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("t-test");
		keywords.add("test");
		keywords.add("equal variance");
		keywords.add("mean");
		keywords.add("two-sided");

		ms.setIdCode("TTEST_ALPHA_2SIDED_EQVAR");
		ms.setNumOperand(3);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	private void add_TTEST_1SIDED_LESSEQUAL_2_EQVAR() {
		MacroService ms = new MacroService();
		String description = "Performs a one-sided t-test evaluating the null hypothesis that,"
				+ " the mean of sample1 with {id_op1} is less equal the mean of sample 2 with {id_op2}"
				+ " with confidence level 1-{alpha}.<BR>"
				+ "This test assume that the subpopulation variances are equal.<BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/parametricTest/{userid}/TTEST_1SIDED_LESSEQUAL_2_EQVAR/{id_op1}/{id_op2}/{alpha}/";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("t-test");
		keywords.add("one-sided");
		keywords.add("test");
		keywords.add("mean");
		keywords.add("equal variance");

		ms.setIdCode("TTEST_1SIDED_LESSEQUAL_2_EQVAR");
		ms.setNumOperand(3);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	private void add_TTEST_1SIDED_GREATEREQUAL_2_EQVAR() {
		MacroService ms = new MacroService();
		String description = "Performs a one-sided t-test evaluating the null hypothesis that,"
				+ " the mean of sample1 with {id_op1} is greater equal the mean of sample 2 with {id_op2}"
				+ " with confidence level 1-{alpha}.<BR>"
				+ "This test assume that the subpopulation variances are equal.<BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/parametricTest/{userid}/TTEST_1SIDED_GREATEREQUAL_2_EQVAR/{id_op1}/{id_op2}/{alpha}/";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("t-test");
		keywords.add("one-sided");
		keywords.add("test");
		keywords.add("mean");
		keywords.add("equal variance");

		ms.setIdCode("TTEST_1SIDED_GREATEREQUAL_2_EQVAR");
		ms.setNumOperand(3);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	private void add_ONEWAY_ANOVA_PVALUE_3OP() {
		MacroService ms = new MacroService();
		String description = "Compute ANOVA p-value, evaluating the null hypothesis that there"
				+ " is no difference among the means of 3 data sample with {id_op1}, {id_op2} and {id_op3}.<BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/parametricTest/{userid}/ONEWAY_ANOVA_PVALUE_3OP/{id_op1}/{id_op2}/{id_op3}/";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("ANOVA");
		keywords.add("p-value");
		keywords.add("mean");

		ms.setIdCode("ONEWAY_ANOVA_PVALUE_3OP");
		ms.setNumOperand(3);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	private void add_ONEWAY_ANOVA_ALPHA_3OP() {
		MacroService ms = new MacroService();
		String description = "Performs an ANOVA test, evaluating the null hypothesis that there"
				+ " is no difference among the means of 3 data sample with {id_op1}, {id_op2} and {id_op3}.<BR>"
				+ "The confidence level is 1-{alpha}"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/parametricTest/{userid}/ONEWAY_ANOVA_ALPHA_3OP/{id_op1}/{id_op2}/{id_op3}/{alpha}/";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("ANOVA");
		keywords.add("test");
		keywords.add("mean");

		ms.setIdCode("ONEWAY_ANOVA_ALPHA_3OP");
		ms.setNumOperand(4);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	private void add_ONEWAY_ANOVA_PVALUE_4OP() {
		MacroService ms = new MacroService();
		String description = "Compute ANOVA p-value, evaluating the null hypothesis that there"
				+ " is no difference among the means of 4 data sample with {id_op1}, {id_op2}, {id_op3} and {id_op4}.<BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/parametricTest/root/ONEWAY_ANOVA_PVALUE_4OP/{id_op1}/{id_op2}/{id_op3}/{id_op4}/";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("ANOVA");
		keywords.add("p-value");
		keywords.add("mean");

		ms.setIdCode("ONEWAY_ANOVA_PVALUE_4OP");
		ms.setNumOperand(4);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	private void add_ONEWAY_ANOVA_ALPHA_4OP() {
		MacroService ms = new MacroService();
		String description = "Performs an ANOVA test, evaluating the null hypothesis that there"
				+ " is no difference among the means of 4 data sample with {id_op1}, {id_op2}, {id_op3} and {id_op4}.<BR>"
				+ "The confidence level is 1-{alpha}<BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/parametricTest/{userid}/ONEWAY_ANOVA_ALPHA_3OP/{id_op1}/{id_op2}/{id_op3}/{id_op4}/{alpha}/";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("ANOVA");
		keywords.add("test");
		keywords.add("mean");

		ms.setIdCode("ONEWAY_ANOVA_ALPHA_4OP");
		ms.setNumOperand(5);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	
	private void add_mean() {
		MacroService ms = new MacroService();
		String description = "Performs mean on a data sample <BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/descriptiveStatistic/{user}/mean/{id1}";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("mean");
		keywords.add("sample");
		

		ms.setIdCode("mean");
		ms.setNumOperand(1);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}

	
	private void add_variance() {
		MacroService ms = new MacroService();
		String description = "Performs variance on a data sample <BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/descriptiveStatistic/{user}/variance/{id1}";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("variance");
		keywords.add("sample");
		

		ms.setIdCode("variance");
		ms.setNumOperand(1);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}

	private void add_geometricMean() {
		MacroService ms = new MacroService();
		String description = "Performs geometric mean on a data sample <BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/descriptiveStatistic/{user}/geometricMean/{id1}";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("geometric");
		keywords.add("sample");
		keywords.add("mean");
		

		ms.setIdCode("geometricMean");
		ms.setNumOperand(1);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	
	private void add_minValue() {
		MacroService ms = new MacroService();
		String description = "Performs minimum value on a data sample <BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/descriptiveStatistic/{user}/min/{id1}";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("min");
		keywords.add("minimum");
		keywords.add("value");
		

		ms.setIdCode("min");
		ms.setNumOperand(1);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	
	private void add_maxValue() {
		MacroService ms = new MacroService();
		String description = "Performs maximum value on a data sample <BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/descriptiveStatistic/{user}/max/{id1}";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("max");
		keywords.add("maximum");
		keywords.add("value");
		

		ms.setIdCode("max");
		ms.setNumOperand(1);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	
	private void add_stdDeviation() {
		MacroService ms = new MacroService();
		String description = "Performs standard deviation on a data sample <BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/descriptiveStatistic/{user}/max/{id1}";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("standard");
		keywords.add("deviation");
		keywords.add("sample");
		

		ms.setIdCode("stdDeviation");
		ms.setNumOperand(1);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	private void add_median() {
		MacroService ms = new MacroService();
		String description = "Performs median value on a data sample <BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/descriptiveStatistic/{user}/median/{id1}";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("median");
		keywords.add("sample");
	
		

		ms.setIdCode("median");
		ms.setNumOperand(1);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	private void add_frequency() {
		MacroService ms = new MacroService();
		String description = "Performs frequency for data sample with ordinal or nominal scale <BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/descriptiveStatistic/{user}/frequency/{id1}";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("frequency");
		keywords.add("ordinal");
		keywords.add("nominal");
	
		

		ms.setIdCode("frequency");
		ms.setNumOperand(1);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}

	
	
	
	private void add_ConfidenceInterval() {
		MacroService ms = new MacroService();
		String description = "Performs confidence interval on a data sample <BR>"
				+ "Return half width of confidence interval centered on sample mean <BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/ConfidenceInterval/{user}/{opId}/{level}";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("confidence interval");
		keywords.add("mean");
	
		

		ms.setIdCode("ConfidenceInterval");
		ms.setNumOperand(2);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	private void add_1SampleKolmogrov() {
		MacroService ms = new MacroService();
		String description = "Return statistic for Kolmogorov-Smironov test <BR>"
				+ "{distribution} should be uniform, normal or lognormal."
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/1SampleKolmogrov/{user}/{opId}/{distribution}";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("kolmogorov");
		keywords.add("distribution");
	
		

		ms.setIdCode("1SampleKolmogrov");
		ms.setNumOperand(2);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}
	
	private void add_2SampleKolmogrov() {
		MacroService ms = new MacroService();
		String description = "Return statistic for Kolmogorov-Smironov test to verify null hypothesis"
				+ " that 2 samples have the same distribution <BR>"
				+ "Usage: GET method<BR>"
				+ "http://localhost:8080/ISSSR5/2SampleKolmogrov/{user}/{msId}/{op1Id}/{op2Id}";

		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("kolmogorov");
		keywords.add("distribution");
	
		

		ms.setIdCode("2SampleKolmogrov");
		ms.setNumOperand(2);
		ms.setDescription(description);
		ms.setIs_private(false);
		ms.setKeywords(keywords);

		table.put(ms.getIdCode(), ms);
	}



	
	
	

}
