package com.isssr5.entities;

import java.util.ArrayList;
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
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.util.FastMath;

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

	// utilizzabile per i dati almeno ordinali
	// esempio di utilizzo:
	// Calculate 95% confidence interval
	// double ci = calcMeanCI(stats, 0.95);
	// System.out.println(String.format("Confidence inteval 95%%: %f", ci);
	Double Confidence_Interval(Operand op, double level) {
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
     * @param distribution reference distribution
     * @param data sample being evaluated
     * @return Kolmogorov-Smirnov statistic \(D_n\)
     * @throws MathIllegalArgumentException if {@code data} does not have length
     *         at least 2
     */
	// cambiare da real distribution a stringa in input
    public double kolmogorovSmirnovStatistic(RealDistribution distribution, double[] data) {
        final int n = data.length;
        final double nd = n;
        final double[] dataCopy = new double[n];
        System.arraycopy(data, 0, dataCopy, 0, n);
        Arrays.sort(dataCopy);
        double d = 0d;
        for (int i = 1; i <= n; i++) {
            final double yi = distribution.cumulativeProbability(dataCopy[i - 1]);
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
     * @param x first sample
     * @param y second sample
     * @return test statistic \(D_n,m\) used to evaluate the null hypothesis
     *         that {@code x} and {@code y} represent samples from the same
     *         underlying distribution
     * @throws MathIllegalArgumentException if either {@code x} or {@code y}
     *         does not have length at least 2.
     */
    public double kolmogorovSmirnovStatistic(double[] x, double[] y) {
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
            final double cdf_y = yIndex >= 0 ? (yIndex + 1d) / m : (-yIndex - 1d) / m;
            final double curD = FastMath.abs(cdf_x - cdf_y);
            if (curD > supD) {
                supD = curD;
            }
        }
        // Now look at y
        for (int i = 0; i < m; i++) {
            final double cdf_y = (i + 1d) / m;
            final int xIndex = Arrays.binarySearch(sx, sy[i]);
            final double cdf_x = xIndex >= 0 ? (xIndex + 1d) / n : (-xIndex - 1d) / n;
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
     * @param x first sample dataset
     * @param y second sample dataset
     * @param exact whether or not the exact distribution of the \(D\( statistic
     *        is used
     * @return p-value associated with the null hypothesis that {@code x} and
     *         {@code y} represent samples from the same distribution
     */


}
