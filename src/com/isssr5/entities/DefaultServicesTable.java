package com.isssr5.entities;

import java.util.ArrayList;
import java.util.HashMap;

public class DefaultServicesTable {

	private static DefaultServicesTable instance = null;

	private HashMap<String, MacroService> table;

	private DefaultServicesTable() {

		table = new HashMap<String, MacroService>();

		MacroService avg = new MacroService("AVG", null, null);
		avg.setNumOperand(1);
		avg.setDescription("Calculate mean");
		ArrayList<String> avg_key = new ArrayList<String>();
		avg_key.add("mean");
		avg.setKeywords(avg_key);
		table.put(avg.getIdCode(), avg);

		MacroService var = new MacroService("VAR", null, null);
		var.setNumOperand(1);
		var.setDescription("Calculate variance");
		ArrayList<String> var_key = new ArrayList<String>();
		var_key.add("variance");
		var.setKeywords(var_key);
		table.put(var.getIdCode(), var);

		MacroService avgvar = new MacroService("AVGVAR", null, null);
		avgvar.setNumOperand(1);

		ArrayList<String> ti1 = new ArrayList<String>();
		ti1.add("AVG");
		ti1.add("VAR");
		avgvar.setElementaryServices(ti1);
		avgvar.setDescription("Calculate mean and variance");
		ArrayList<String> avgvar_key = new ArrayList<String>();
		avgvar_key.add("variance");
		avgvar_key.add("mean");
		avgvar.setKeywords(avgvar_key);

		ArrayList<Integer> ti2 = new ArrayList<Integer>();
		ti2.add(1);
		ParameterList pl1 = new ParameterList(ti2);
		ArrayList<Integer> ti3 = new ArrayList<Integer>();
		ti3.add(1);
		ParameterList pl2 = new ParameterList(ti3);
		ArrayList<ParameterList> ti4 = new ArrayList<ParameterList>();
		ti4.add(pl1);
		ti4.add(pl2);
		avgvar.setOperationOrder(ti4);

		table.put(avgvar.getIdCode(), avgvar);

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
				+ " against {mu} which is the second operand\n"
				+ "Usage: GET method\n"
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
				+ " the mean of the population from which sample with {id_op1} is drawn equals {mu0}.\n"
				+ "Returns true iff the null hypothesis can be rejected with confidence 1 - {alpha}\n"
				+ "Usage: GET method\n"
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
				+ " associated with a one-sample, two-tailed t-test comparing the mean of the input array with the constant {mu}.\n"
				+ "Usage: GET method\n"
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
				+ " with confidence level 1-{alpha}.\n"
				+ "Usage: GET method\n"
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
				+ " with confidence level 1-{alpha}.\n"
				+ "Usage: GET method\n"
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
		String description = "Computes a paired, 2-sample t-statistic based on the data in the input arrays.\n"
				+ " The t-statistic returned is equivalent to what would be returned by computing the one-sample t-statistic"
				+ " with mu = 0 and the sample array consisting of the (signed) differences"
				+ " between corresponding entries in sample1 with {id_op1} and sample2 with {id_op2}.\n"
				+ "Usage: GET method\n"
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
				+ " associated with a paired, two-sample, two-tailed t-test based on the data in the input with {id_op1} and {id_op2}.\n"
				+ "Usage: GET method\n"
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
				+ " with confidence level 1-{alpha}\n"
				+ "Usage: GET method\n"
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
		String description = "Computes a 2-sample t-statistic, without the hypothesis of equal subpopulation variances.\n"
				+ " Two sample are specified by {id_op1} and {id_op2}.\n"
				+ "Usage: GET method\n"
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
				+ " associated with a two-sample with {id_op1} and {id_op2}, two-tailed t-test comparing the means of the input arrays.\n"
				+ " The test does not assume that the underlying popuation variances are equal.\n"
				+ "Usage: GET method\n"
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
				+ " populations with the same mean, with significance level {alpha}.\n"
				+ "This test does not assume that the subpopulation variances are equal.\n"
				+ "Usage: GET method\n"
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
				+ " {id_op1} and {id_op2}, under the hypothesis of equal subpopulation variances.\n"
				+ "Usage: GET method\n"
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

}
