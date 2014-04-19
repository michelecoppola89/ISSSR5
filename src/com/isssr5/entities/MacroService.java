package com.isssr5.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.inference.TestUtils;

import com.isssr5.exceptions.NotExistingMacroServiceException;

@XmlRootElement(name = "macroService")
@Entity
@Table(name = "custommacroservice")
public class MacroService {

	@Id
	@Column(name = "idCustomMacroService")
	private String idCode;

	@Transient
	private ArrayList<Operand> operandList;

	@Column(name = "elementaryServiceList")
	private ArrayList<String> elementaryServices;

	@Column(name = "parameterList")
	private ArrayList<ParameterList> operationOrder;

	@Column(name = "numOperand")
	private int numOperand;

	@Column(name = "description")
	private String description;

	@Column(name = "isPrivate")
	private boolean is_private;

	@Column(name = "keywords")
	private ArrayList<String> keywords;

	@ManyToOne
	@JoinColumn(name = "user", nullable = false)
	private ServiceUser user;

	public MacroService() {
		numOperand = 0;
	}

	public MacroService(String idCode, ArrayList<Operand> operandList,
			ArrayList<String> elementaryServices) {
		this.idCode = idCode;
		this.operandList = operandList;
		this.elementaryServices = elementaryServices;
		numOperand = 0;

	}

	public ArrayList<ParameterList> getOperationOrder() {
		return operationOrder;
	}

	@XmlElement
	public void setOperationOrder(ArrayList<ParameterList> operationOrder) {
		this.operationOrder = operationOrder;
	}

	public int getNumOperand() {
		return numOperand;
	}

	@XmlElement
	public void setNumOperand(int numOperand) {
		this.numOperand = numOperand;
	}

	public String getIdCode() {
		return idCode;
	}

	@XmlElement
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public ArrayList<Operand> getOperandList() {
		return operandList;
	}

	@XmlElement
	public void setOperandList(ArrayList<Operand> operandList) {
		this.operandList = operandList;
	}

	public ArrayList<String> getElementaryServices() {
		return elementaryServices;
	}

	@XmlElement
	public void setElementaryServices(ArrayList<String> elementaryServices) {
		this.elementaryServices = elementaryServices;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getIs_private() {
		return is_private;
	}

	public void setIs_private(boolean is_private) {
		this.is_private = is_private;
	}

	public ArrayList<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}

	@XmlTransient
	public ServiceUser getUser() {
		return user;
	}

	public void setUser(ServiceUser user) {
		this.user = user;
	}

	public void decodeMacroService(DefaultServicesTable table)
			throws NotExistingMacroServiceException {
		MacroService m = table.getTable().get(idCode);
		if (m == null) {
			throw new NotExistingMacroServiceException();
		} else {
			if (m.elementaryServices != null) {
				this.elementaryServices = new ArrayList<String>(
						m.elementaryServices);
				this.operationOrder = new ArrayList<ParameterList>(
						m.operationOrder);
				this.numOperand = m.getNumOperand();
			}
		}

	}

	public String printElementaryService() {
		String output = "";
		for (int i = 0; i < elementaryServices.size(); i++)
			output += elementaryServices.get(i) + " ";
		return output;

	}

	public String printOperandList() {
		String output = "";
		for (int i = 0; i < operandList.size(); i++) {
			output += "Operand " + (i + 1) + ": "
					+ operandList.get(i).PrintOperand() + "\n";
		}

		return output;
	}

	public String printMacroService() {

		String output = "";
		output += "MacroService ID: " + idCode + "\n";

		if (elementaryServices != null) {
			output += "MacroService/Elementary Service list:\n";
			for (int i = 0; i < elementaryServices.size(); i++) {
				output += "MacroService ID: " + elementaryServices.get(i)
						+ "\n";
			}
		}

		output += "Num operand: " + numOperand + "\n";

		if (operationOrder != null) {
			for (int i = 0; i < operationOrder.size(); i++) {
				output += "MacroService " + (i + 1) + " input parameters: ";
				output += operationOrder.get(i).printParameterList();
				output += "\n";
			}
		} else {
			output += "This MacroService is an elementary service\n";
		}
		return output;
	}

	// Hypothesis parametric test

	// return dataSeries array of double from Operand object
	private static double[] getSampleFromOperand(Operand op) {
		ArrayList<String> dataSeries = op.getDataSeries();
		double[] sample = new double[dataSeries.size()];

		for (int i = 0; i < sample.length; i++) {
			sample[i] = Double.parseDouble(dataSeries.get(i));
		}

		return sample;
	}

	// return tTest statistic
	public static double tTestStatistic(Operand op, double mu) {
		double sample[] = getSampleFromOperand(op);
		return TestUtils.t(mu, sample);
	}

	// tTest H0: mu=mu0 ; H1 mu!=mu0
	// return true if null hypothesis can be rejected, false otherwise
	// confidence level 1-alpha
	public static boolean tTestAlphaTwoSided(Operand op, double mu, double alpha) {

		double[] sample = getSampleFromOperand(op);
		return TestUtils.tTest(mu, sample, alpha);
	}

	// tTest H0: mu=mu0 ; H1 mu!=mu0
	// return pValue
	public static double tTestPValueTwoSided(Operand op, double mu) {

		double[] sample = getSampleFromOperand(op);
		return TestUtils.tTest(mu, sample);
	}

	// null hypothesis mu<=mu_0
	// true if rejected null hypothesis false otherwise
	// confidence level 1-alpha
	public static boolean tTestAlphaUnilateralLessEqual(Operand op, double mu,
			double alpha) {

		double[] observed = getSampleFromOperand(op);
		TDistribution t_dist = new TDistribution(observed.length - 1);
		double quant = t_dist.inverseCumulativeProbability(1 - alpha);
		double t_stat = TestUtils.t(mu, observed);
		if (t_stat >= quant)
			return true;
		else
			return false;
	}

	// null hypothesis mu>=mu_0
	// true if rejected null hypothesis false otherwise
	// confidence level 1-alpha
	public static boolean tTestAlphaUnilateralGreaterEqual(Operand op,
			double mu, double alpha) {

		double[] observed = getSampleFromOperand(op);
		TDistribution t_dist = new TDistribution(observed.length - 1);
		double quant = t_dist.inverseCumulativeProbability(alpha);
		double t_stat = TestUtils.t(mu, observed);
		if (t_stat <= quant)
			return true;
		else
			return false;
	}

	// return tTest statistic for two paired sample
	public static double tTestPairedStatistic(Operand op1, Operand op2) {

		double[] observed1 = getSampleFromOperand(op1);
		double[] observed2 = getSampleFromOperand(op2);
		return TestUtils.pairedT(observed1, observed2);
	}

	// return pValue for two paired sample
	// H0: mu1=mu2; H1: mu1!=mu2
	public static double tTestPairedPValue(Operand op1, Operand op2) {

		double[] observed1 = getSampleFromOperand(op1);
		double[] observed2 = getSampleFromOperand(op2);
		return TestUtils.pairedTTest(observed1, observed2);
	}

	// test with confidence level 1-alpha for two paired sample
	// return true if null hypothesis can be rejected, false otherwise
	// H0: mu1=mu2; H1: mu1!=mu2
	// confidence level 1-alpha
	public static boolean tTestPairedAlpha(Operand op1, Operand op2,
			double alpha) {

		double[] observed1 = getSampleFromOperand(op1);
		double[] observed2 = getSampleFromOperand(op2);
		return TestUtils.pairedTTest(observed1, observed2, alpha);
	}

	// return tTest statistic for two unpaired sample with different variance
	public static double tTestStatistic(Operand op1, Operand op2) {

		double[] observed1 = getSampleFromOperand(op1);
		double[] observed2 = getSampleFromOperand(op2);
		return TestUtils.t(observed1, observed2);
	}

	// return pValue for two unpaired sample with different variance
	// H0: mu1=mu2 ; H1: mu1!=mu2
	public static double tTestPValueTwoSided(Operand op1, Operand op2) {

		double[] observed1 = getSampleFromOperand(op1);
		double[] observed2 = getSampleFromOperand(op2);
		return TestUtils.tTest(observed1, observed2);
	}

	// return true if null hypothesis can be rejected for two unpaired sample
	// with different variance
	// H0: mu1=mu2 ; H1: mu1!=mu2
	// confidence level 1-alpha
	public static boolean tTestAlphaTwoSided(Operand op1, Operand op2,
			double alpha) {

		double[] observed1 = getSampleFromOperand(op1);
		double[] observed2 = getSampleFromOperand(op2);
		return TestUtils.tTest(observed1, observed2, alpha);
	}

	// return tTest statistic for two unpaired sample with equal variance
	public static double tTestStatisticTwoSidedEqualVar(Operand op1, Operand op2) {

		double[] observed1 = getSampleFromOperand(op1);
		double[] observed2 = getSampleFromOperand(op2);
		return TestUtils.homoscedasticT(observed1, observed2);
	}

	// return pValue for two unpaired sample with equal variance
	// H0: mu1=mu2 ; H1: mu1!=mu2
	public static double tTestPValueTwoSidedEqualVar(Operand op1, Operand op2) {

		double[] observed1 = getSampleFromOperand(op1);
		double[] observed2 = getSampleFromOperand(op2);
		return TestUtils.homoscedasticTTest(observed1, observed2);
	}

	// return true if null hypothesis can be rejected for two unpaired sample
	// with equal variance
	// H0: mu1=mu2 ; H1: mu1!=mu2
	// confidence level 1-alpha
	public static boolean tTestAlphaTwoSidedEqualVar(Operand op1, Operand op2,
			double alpha) {

		double[] observed1 = getSampleFromOperand(op1);
		double[] observed2 = getSampleFromOperand(op2);
		return TestUtils.homoscedasticTTest(observed1, observed2, alpha);
	}

	// null hypothesis mu1<=mu2 for two unpaired sample with equal variance
	// true if rejected null hypothesis false otherwise
	// confidence level 1-alpha
	public static boolean tTestAlphaUnilateraLessEqualVar(double mu,
			Operand op1, Operand op2, double alpha) {

		double[] observed1 = getSampleFromOperand(op1);
		double[] observed2 = getSampleFromOperand(op2);
		TDistribution t_dist = new TDistribution(observed1.length
				+ observed2.length - 2);
		double quant = t_dist.inverseCumulativeProbability(1 - alpha);
		double t_stat = TestUtils.homoscedasticT(observed1, observed2);
		if (t_stat >= quant)
			return true;
		else
			return false;
	}

	// null hypothesis mu1>=mu2 or two unpaired sample with equal variance
	// true if rejected null hypothesis false otherwise
	// confidence level 1-alpha
	public static boolean tTestAlphaUnilateralGreaterEqualVar(double mu,
			Operand op1, Operand op2, double alpha) {

		double[] observed1 = getSampleFromOperand(op1);
		double[] observed2 = getSampleFromOperand(op2);
		TDistribution t_dist = new TDistribution(observed1.length
				+ observed2.length - 2);
		double quant = t_dist.inverseCumulativeProbability(alpha);
		double t_stat = TestUtils.homoscedasticT(observed1, observed2);
		if (t_stat <= quant)
			return true;
		else
			return false;
	}

	// one way Anova (3 operand) Pvalue
	// H0: mu1=mu2=mu3 ; H1: otherwise
	public static double oneWayAnovaPValue(Operand op1, Operand op2, Operand op3) {

		double[] observed1 = getSampleFromOperand(op1);
		double[] observed2 = getSampleFromOperand(op2);
		double[] observed3 = getSampleFromOperand(op3);
		List<double[]> classes = new ArrayList<double[]>();
		classes.add(observed1);
		classes.add(observed2);
		classes.add(observed3);
		return TestUtils.oneWayAnovaPValue(classes);
	}

	// one way Anova (3 operand) return true if null hypothesis can be rejected
	// confidence level 1-alpha
	// H0: mu1=mu2=mu3 ; H1: otherwise
	public static boolean oneWayAnovaAlpha(Operand op1, Operand op2,
			Operand op3, double alpha) {

		double[] observed1 = getSampleFromOperand(op1);
		double[] observed2 = getSampleFromOperand(op2);
		double[] observed3 = getSampleFromOperand(op3);
		List<double[]> classes = new ArrayList<double[]>();
		classes.add(observed1);
		classes.add(observed2);
		classes.add(observed3);
		return TestUtils.oneWayAnovaTest(classes, alpha);
	}

	// one way Anova (4 operand) Pvalue
	// H0: mu1=mu2=mu3=mu4 ; H1: otherwise
	public static double oneWayAnovaPValue(Operand op1, Operand op2, Operand op3, Operand op4) {

		double[] observed1 = getSampleFromOperand(op1);
		double[] observed2 = getSampleFromOperand(op2);
		double[] observed3 = getSampleFromOperand(op3);
		double[] observed4 = getSampleFromOperand(op4);
		List<double[]> classes = new ArrayList<double[]>();
		classes.add(observed1);
		classes.add(observed2);
		classes.add(observed3);
		classes.add(observed4);
		return TestUtils.oneWayAnovaPValue(classes);
	}

	// one way Anova (4 operand) return true if null hypothesis can be rejected
	// confidence level 1-alpha
	// H0: mu1=mu2=mu3=mu4 ; H1: otherwise
	public static boolean oneWayAnovaAlpha(Operand op1, Operand op2,
			Operand op3,Operand op4, double alpha) {

		double[] observed1 = getSampleFromOperand(op1);
		double[] observed2 = getSampleFromOperand(op2);
		double[] observed3 = getSampleFromOperand(op3);
		double[] observed4 = getSampleFromOperand(op4);
		List<double[]> classes = new ArrayList<double[]>();
		classes.add(observed1);
		classes.add(observed2);
		classes.add(observed3);
		classes.add(observed4);
		return TestUtils.oneWayAnovaTest(classes, alpha);
	}
}
