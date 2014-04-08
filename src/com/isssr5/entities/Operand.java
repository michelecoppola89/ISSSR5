package com.isssr5.entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.net.URLConnection;
import java.sql.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "operand")
@Entity
@Table(name = "dataseries")
public class Operand {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "iddataSeries", nullable = false)
	private long id;

	@ManyToOne
	@JoinColumn(name = "user", nullable = false)
	private ServiceUser user;

	@Column(name = "operandType")
	private String dataType;// "Double" or "String"

	@Column(name = "dataseries")
	private ArrayList<String> dataSeries;

	@Column(name = "operandMode")
	private String operandMode; // can be E(enumerate),F(file),D(database)

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idScale", nullable = false)
	private Scale scale;

	@Transient
	private String url; // file path

	@Transient
	private ExternalDB eDB;

	public Operand() {

		operandMode = "F";

	}

	public Operand(String dataType, ArrayList<String> dataSeries,
			String operandMode) {
		this.dataType = dataType;
		this.dataSeries = dataSeries;
		this.operandMode = operandMode;
	}

	public String getUrl() {
		return url;
	}

	@XmlElement
	public void setUrl(String url) {
		this.url = url;
	}

	public String getDataType() {
		return dataType;
	}

	@XmlElement
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public ArrayList<String> getDataSeries() {
		return dataSeries;
	}

	@XmlElement
	public void setDataSeries(ArrayList<String> dataSeries) {
		this.dataSeries = dataSeries;
	}

	public String getOperandMode() {
		return operandMode;
	}

	@XmlElement
	public void setOperandMode(String operandMode) {
		this.operandMode = operandMode;
	}

	public ExternalDB geteDB() {
		return eDB;
	}

	@XmlElement
	public void seteDB(ExternalDB eDB) {
		this.eDB = eDB;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@XmlTransient
	public ServiceUser getUser() {
		return user;
	}

	public void setUser(ServiceUser user) {
		this.user = user;
	}

	public Scale getScale() {
		return scale;
	}

	public void setScale(Scale scale) {
		this.scale = scale;
	}

	public String PrintOperand() {

		String output = "";
		output += "Type: " + dataType + "\nOperandMode: " + operandMode + "\n";
		if (dataSeries != null) {
			output += "Data Series: ";
			for (int i = 0; i < dataSeries.size(); i++) {
				output += dataSeries.get(i).toString() + " ";

			}
		}
		return output;

	}

	public void acquisitionFromfile(String urlString) throws IOException,
			NumberFormatException {
		dataSeries = new ArrayList<String>();
		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();

		// open the stream and put it into BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));

		String inputLine;

		while ((inputLine = br.readLine()) != null) {
			dataSeries.add(inputLine);
			if (dataType.equals("Double")) {
				Double.parseDouble(inputLine);
			}

		}

		br.close();

	}

	public void acqusitionFromExternalDB() throws SQLException,
			ClassNotFoundException, NumberFormatException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(eDB.getAddress(),
				eDB.getUsername(), eDB.getPwd());
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(eDB.getQuery());

		dataSeries = new ArrayList<String>();
		while (rs.next()) {
			String toins = rs.getString(1);
			if (dataType.equals("Double")) {
				Double.parseDouble(toins);
			}
			dataSeries.add(toins);

		}

	}
}
