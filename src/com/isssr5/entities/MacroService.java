package com.isssr5.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

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

import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.distribution.LogNormalDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.inference.TestUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.rank.Median;

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
	public static double oneWayAnovaPValue(Operand op1, Operand op2,
			Operand op3, Operand op4) {

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
			Operand op3, Operand op4, double alpha) {

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

	// utilizzabile per i dati almeno ordinali
	// esempio di utilizzo:
	// Calculate 95% confidence interval
	// double ci = calcMeanCI(stats, 0.95);
	// System.out.println(String.format("Confidence inteval 95%%: %f", ci);
	public static double Confidence_Interval(Operand op, double level) {
		SummaryStatistics stats = new SummaryStatistics();
		for (int i = 0; i < op.getDataSeries().size(); i++) {
			stats.addValue(Double.parseDouble((op.getDataSeries().get(i))));

		}
		try {
			// Create T Distribution with N-1 degrees of freedom
			TDistribution tDist = new TDistribution(stats.getN() - 1);
			// Calculate critical value
			double critVal = tDist
					.inverseCumulativeProbability(1.0 - (1 - level) / 2);
			// Calculate confidence interval
			return critVal * stats.getStandardDeviation()
					/ Math.sqrt(stats.getN());
		} catch (MathIllegalArgumentException e) {
			return Double.NaN;
		}
	}

	/**
	 * Computes the one-sample Kolmogorov-Smirnov test statistic, \(D_n=\sup_x
	 * |F_n(x)-F(x)|\) where \(F\) is the distribution (cdf) function associated
	 * with {@code distribution}, \(n\) is the length of {@code data} and
	 * \(F_n\) is the empirical distribution that puts mass \(1/n\) at each of
	 * the values in {@code data}.
	 * 
	 * @param distribution
	 *            reference distribution
	 * @param data
	 *            sample being evaluated
	 * @return Kolmogorov-Smirnov statistic \(D_n\)
	 * @throws MathIllegalArgumentException
	 *             if {@code data} does not have length at least 2
	 */
	// cambiare da real distribution a stringa in input
	public static double kolmogorovSmirnovStatistic(String dist, Operand op) {
		double[] data = new double[op.getDataSeries().size()];
		for (int i = 0; i < op.getDataSeries().size(); i++) {
			data[i] = Double.parseDouble((op.getDataSeries().get(i)));
		}

		final int n = data.length;
		final double nd = n;
		final double[] dataCopy = new double[n];
		System.arraycopy(data, 0, dataCopy, 0, n);
		Arrays.sort(dataCopy);
		RealDistribution distribution;
		if (dist.equals("lognormal"))
			distribution = new LogNormalDistribution();
		else
			
		if(dist.equals("normal"))
			distribution = new NormalDistribution();
		else
			distribution = new UniformRealDistribution();
		
		// ---------------------------------------------------------
		double d = 0d;
		for (int i = 1; i <= n; i++) {
			final double yi = distribution
					.cumulativeProbability(dataCopy[i - 1]);
			final double currD = FastMath.max(yi - (i - 1) / nd, i / nd - yi);
			if (currD > d) {
				d = currD;
			}
		}
		return d;
	}

	/**
	 * Computes the two-sample Kolmogorov-Smirnov test statistic, \(D_n,m=\sup_x
	 * |F_n(x)-F_m(x)|\) \(n\) is the length of {@code x}, \(m\) is the length
	 * of {@code y}, \(F_n\) is the empirical distribution that puts mass
	 * \(1/n\) at each of the values in {@code x} and \(F_m\) is the empirical
	 * distribution of the {@code y} values.
	 * 
	 * @param x
	 *            first sample
	 * @param y
	 *            second sample
	 * @return test statistic \(D_n,m\) used to evaluate the null hypothesis
	 *         that {@code x} and {@code y} represent samples from the same
	 *         underlying distribution
	 * @throws MathIllegalArgumentException
	 *             if either {@code x} or {@code y} does not have length at
	 *             least 2.
	 */
	public static double kolmogorovSmirnovStatistic(Operand op1, Operand op2) {
		double[] x = new double[op1.getDataSeries().size()];
		for (int i = 0; i < op1.getDataSeries().size(); i++) {
			x[i] = Double.parseDouble((op1.getDataSeries().get(i)));
		}
		double[] y = new double[op2.getDataSeries().size()];
		for (int i = 0; i < op2.getDataSeries().size(); i++) {
			y[i] = Double.parseDouble((op2.getDataSeries().get(i)));
		}
		// Copy and sort the sample arrays
		final double[] sx = MathArrays.copyOf(x);
		final double[] sy = MathArrays.copyOf(y);
		Arrays.sort(sx);
		Arrays.sort(sy);
		final int n = sx.length;
		final int m = sy.length;
		// Compare empirical distribution cdf values at each (combined) sample
		// point.
		// D_n.m is the max difference.
		// cdf value is (insertion point - 1) / length if not an element;
		// index / n if element is in the array.
		double supD = 0d;
		// First walk x points
		for (int i = 0; i < n; i++) {
			final double cdf_x = (i + 1d) / n;
			final int yIndex = Arrays.binarySearch(sy, sx[i]);
			final double cdf_y = yIndex >= 0 ? (yIndex + 1d) / m
					: (-yIndex - 1d) / m;
			final double curD = FastMath.abs(cdf_x - cdf_y);
			if (curD > supD) {
				supD = curD;
			}
		}
		// Now look at y
		for (int i = 0; i < m; i++) {
			final double cdf_y = (i + 1d) / m;
			final int xIndex = Arrays.binarySearch(sx, sy[i]);
			final double cdf_x = xIndex >= 0 ? (xIndex + 1d) / n
					: (-xIndex - 1d) / n;
			final double curD = FastMath.abs(cdf_x - cdf_y);
			if (curD > supD) {
				supD = curD;
			}
		}
		return supD;
	}

	/**
	 * Computes the <i>p-value</i>, or <i>observed significance level</i>, of a
	 * two-sample <a
	 * href="http://en.wikipedia.org/wiki/Kolmogorov-Smirnov_test">
	 * Kolmogorov-Smirnov test</a> evaluating the null hypothesis that {@code x}
	 * and {@code y} are samples drawn from the same probability distribution.
	 * If {@code exact} is true, the discrete distribution of the test statistic
	 * is computed and used directly; otherwise the asymptotic
	 * (Kolmogorov-Smirnov) distribution is used to estimate the p-value.
	 * 
	 * @param x
	 *            first sample dataset
	 * @param y
	 *            second sample dataset
	 * @param exact
	 *            whether or not the exact distribution of the \(D\( statistic
	 *            is used
	 * @return p-value associated with the null hypothesis that {@code x} and
	 *         {@code y} represent samples from the same distribution
	 */

	/*---------------------------------DESCRIPTIVE STATISTICS---------------------------------------------*/

	public Double compute_mean(Operand op) {

		DescriptiveStatistics stat = new DescriptiveStatistics();
		for (int i = 0; i < op.getDataSeries().size(); i++) {
			stat.addValue(Double.parseDouble(op.getDataSeries().get(i)));
		}

		return stat.getMean();
	}

	public Double compute_variance(Operand op) {

		DescriptiveStatistics stat = new DescriptiveStatistics();
		for (int i = 0; i < op.getDataSeries().size(); i++) {
			stat.addValue(Double.parseDouble(op.getDataSeries().get(i)));
		}

		return stat.getVariance();
	}

	public Double compute_geometricMean(Operand op) {

		DescriptiveStatistics stat = new DescriptiveStatistics();
		for (int i = 0; i < op.getDataSeries().size(); i++) {
			stat.addValue(Double.parseDouble(op.getDataSeries().get(i)));
		}

		return stat.getGeometricMean();
	}

	public Double compute_minValue(Operand op) {

		DescriptiveStatistics stat = new DescriptiveStatistics();
		for (int i = 0; i < op.getDataSeries().size(); i++) {
			stat.addValue(Double.parseDouble(op.getDataSeries().get(i)));
		}

		return stat.getMin();
	}

	public Double compute_maxValue(Operand op) {

		DescriptiveStatistics stat = new DescriptiveStatistics();
		for (int i = 0; i < op.getDataSeries().size(); i++) {
			stat.addValue(Double.parseDouble(op.getDataSeries().get(i)));
		}

		return stat.getMax();
	}

	public Double compute_standardDeviation(Operand op) {

		DescriptiveStatistics stat = new DescriptiveStatistics();
		for (int i = 0; i < op.getDataSeries().size(); i++) {
			stat.addValue(Double.parseDouble(op.getDataSeries().get(i)));
		}

		return stat.getStandardDeviation();
	}

	public Double compute_median(Operand op) {

		double values[] = new double[op.getDataSeries().size()];

		for (int i = 0; i < op.getDataSeries().size(); i++) {
			values[i] = Double.parseDouble(op.getDataSeries().get(i));
		}

		Median median = new Median();

		return median.evaluate(values);

	}

}
