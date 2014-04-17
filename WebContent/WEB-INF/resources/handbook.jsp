<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Statistics 5 Manual</title>

<%@ page isELIgnored="false" %>

<script type="text/javascript">
	function getMacroServicePage() {
		var elem = document.getElementById("sMacroService");
		
		var userstring = '${username}';
		var strUser = elem.options[elem.selectedIndex].value;
		if(strUser == "all_def") {
			window.location.href = "http://localhost:8080/ISSSR5/manual/getAllDefaultElementaryService";
		}
		else if(strUser == "all_priv"){		
			window.location.href = "http://localhost:8080/ISSSR5/manual/"+userstring+"/getAllPrivateCustomizedMacroService";
		}
		else if(strUser == "all_publ") {
			window.location.href = "http://localhost:8080/ISSSR5/manual/getAllPublicCustomizedMacroService";
		}
		
	}
	
	function getMacroServicePageByKeyword() {
		var elem = document.getElementById("keyword");
		var elem_select = document.getElementById("sMacroServiceKey");
		var strUser = elem_select.options[elem_select.selectedIndex].value;
		var k = elem.value;
		var userstring = '${username}';
		if(strUser=="all_def") {
			window.location.href = "http://localhost:8080/ISSSR5/manual/getDefaultKeyword/"+k;
		}
		else if(strUser=="all_priv") {
			window.location.href = "http://localhost:8080/ISSSR5/manual/"+userstring+"/getPrivateKeywords/"+k;
		}
		else if(strUser=="all_publ") {
			window.location.href = "http://localhost:8080/ISSSR5/manual/getPublicByKeywords/"+k;
		}
		
		
	}
</script>

</head>
<body bgcolor="e3d268">


	<H1 align="center">
		Welcome <c:out value="${username}"></c:out> in Statistics 5! <BR> 
		<!-- <IMG SRC="/resources/five.png" BORDER=3
			HEIGHT=200 WIDTH=191 align="middle"> -->
	</H1>
	<HR WIDTH="100%">
	<H2 align="center">
		Get all MacroService <BR> 
		<select id="sMacroService">
			<option value="all_publ">All Public MacroService</option>
			<option value="all_priv">Private customize MacroService</option>
			<option value="all_def">Default Elementary Service</option>
		</select>
		<button id="btMs" onclick="getMacroServicePage()">Get MacroService</button> 
		<BR>
		<H2 align="center">
		Search MacroService by KeyWord <BR> 
		<input id="keyword" type="text" size="25" value="Insert keyword here">
		<select id="sMacroServiceKey">
			<option value="all_publ">All Public MacroService</option>
			<option value="all_priv">Private customize MacroService</option>
			<option value="all_def">Default Elementary Service</option>
		</select>
		<button id="btMsKey" onclick="getMacroServicePageByKeyword()">Get MacroService</button>
		
		
	</H2>
	<HR WIDTH="100%">
	<H1 align="center">
		<A id="1"></A> Handbook
	</H1>
	<UL>
		<LI><A HREF="#1"> Insert a scale</A></LI>
		<LI><A HREF="#2"> Obtain user's scale by ID</A></LI>
		<LI><A HREF="#3"> Obtain all user's scales</A></LI>
		<LI><A HREF="#4"> Create a customized MacroService</A></LI>
		<LI><A HREF="#5"> Obtain a customized MacroService by ID</A></LI>
		<LI><A HREF="#6"> Obtain a all public customized
				MacroServices</A></LI>
		<LI><A HREF="#7"> Obtain all user's customized MacroServices</A></LI>
		<LI><A HREF="#8"> Insert Operand</A></LI>
		<LI><A HREF="#9"> Obtain user's Dataseries by ID</A></LI>
	</UL>

	<HR WIDTH="100%">


	<H1 align="center">
		<A id="1"></A> Insert a scale
	</H1>
	<H2 align="center">Used HTTP methods</H2>


	POST: the client uses post primitive to send Service scale
	informations. These informations are contained in the post body in xml
	form; the informations are:
	<BR>
	<BR>-Type of Domain (enumeral domain or ratio domain )
	<BR>-Scale type (Nominal, Interval, Ordinal, Ratio)
	<BR>-scalePoints (if the type domain is:enumerateDomain) or
	interval ((if the type domain is:intervalDomain))
	<H2 align="center">Example 1</H2>

	insert in the service a scale
	<BR> REQUEST:
	<BR> header: Content-Type application/xml
	<BR> url: http://localhost:8080/ISSSR5/scale/root/nominalScale
	<BR> body:
	<BR>

	<textarea style="color: black; background-color: e3d268;" rows="10"
		cols="40" style="border: none;" disabled="disabled">
<scale>
<enumerateDomain>
<domType>EnumeralDomain</domType>
<scalePoints>Rosso</scalePoints>
<scalePoints>Amaranto</scalePoints>
<scalePoints>Ciclamino</scalePoints>
</enumerateDomain>
<type>NominalScale</type>
</scale>
</textarea>
	<BR> RESPONSE:
	<BR> header:
	<BR> Status Code: 200 OK
	<BR> Content-Length: 0
	<BR> Content-Type: text/html
	<BR> Date: Fri, 11 Apr 2014 11:50:16 GMT
	<BR> Location: /scale/root/getScaleById/2
	<BR> Server: Apache-Coyote/1.1
	<BR>
	<H2 align="center">Monitored Errors</H2>
	-Missing type domain:
	<BR> -Missing scale points (if the type domain is:enumerateDomain)
	or missing interval ((if the type domain is:intervalDomain)
	<BR> -Unknown user name
	<BR>
	<HR WIDTH="100%">
	<H1 align="center">
		<A id="2"></A> Obtain user's scale by ID
	</H1>
	<H2 align="center">Used HTTP methods</H2>
	-GET: the client uses get primitive to ask for a scale by ID. the asked
	informations are: -Type of Domain (enumeral domain or ratio domain )
	<BR> -Scale type (Nominal, Interval, Ordinal, Ratio)
	<BR> -scalePoints (if the type domain is:enumerateDomain) or
	interval ((if the type domain is:intervalDomain))
	<BR>
	<H2 align="center">Example 1</H2>
	obtain information about scale with ID "2" REQUEST: header:
	<BR> Content-Type application/xml url:
	<BR> http://localhost:8080/ISSSR5/scale/root/getScaleById/2
	RESPONSE:
	<BR> header: Status Code: 200 OK Content-Type:
	application/xhtml+xml Date:
	<BR> Fri, 11 Apr 2014 12:03:25 GMT Server: Apache-Coyote/1.1
	<BR> Transfer-Encoding: chunked body:
	<BR>
	<textarea style="color: black; background-color: e3d268;" rows="10"
		cols="40" style="border: none;"  disabled="true">
	 <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    <scale>
    <enumerateDomain>
    <domType>EnumeralDomain</domType>
    <scalePoints>Rosso</scalePoints>
    <scalePoints>Amaranto</scalePoints>
    <scalePoints>Ciclamino</scalePoints>
    </enumerateDomain>
    <id>2</id>
    <type>NominalScale</type>
    </scale>	
	</textarea>
	<H2 align="center">Monitored Errors</H2>
	-Missing user or scale ID
	<BR> -Unknown user name
	<BR> -Unknown scale ID
	<BR>
	<HR WIDTH="100%">

	<H1 align="center">
		<A id="3"></A> Obtain all user's scales
	</H1>
	<H2 align="center">Used HTTP methods</H2>
	-GET: the client uses get primitive to ask for all his scales. the
	<BR> asked informations are: -Type of Domain (enumeral domain or
	ratio
	<BR> domain ) -Scale type (Nominal, Interval, Ordinal, Ratio)
	-scalePoints
	<BR> (if the type domain is:enumerateDomain) or interval ((if the
	type domain is:intervalDomain))
	<BR>
	<H2 align="center">Example 1</H2>
	obtain information about all the scales
	<BR> REQUEST:
	<BR> header: Content-Type application/xml
	<BR> url: http://localhost:8080/ISSSR5/scale/root/getAllScales
	<BR> RESPONSE:
	<BR> header:
	<BR> Status Code: 200 OK
	<BR> Content-Type: application/xhtml+xml
	<BR> Date: Fri, 11 Apr 2014 12:08:25 GMT
	<BR> Server: Apache-Coyote/1.1
	<BR> Transfer-Encoding: chunked
	<BR> body:
	<BR>
	<textarea style="color: black; background-color: e3d268;" rows="10"
		cols="40" style="border: none;" disabled="disabled">
	 <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    <resumeScale>
    <scaleList>
    <enumerateDomain>
    <domType>EnumeralDomain</domType>
    <scalePoints>Rosso</scalePoints>
    <scalePoints>Verde</scalePoints>
    <scalePoints>Amaranto</scalePoints>
    </enumerateDomain>
    <id>1</id>
    <type>NominalScale</type>
    </scaleList>
    <scaleList>
    <enumerateDomain>
    <domType>EnumeralDomain</domType>
    <scalePoints>Rosso</scalePoints>
    <scalePoints>Amaranto</scalePoints>
    <scalePoints>Ciclamino</scalePoints>
    </enumerateDomain>
    <id>2</id>
    <type>NominalScale</type>
    </scaleList>
    </resumeScale>
	</textarea>
	<H2 align="center">Monitored Errors</H2>
	-Missing user
	<BR> -Unknown user name
	<BR>
	<HR WIDTH="100%">
	<H1 align="center">
		<A id="4"></A> Create a customized MacroService
	</H1>
	the client uses post primitive to create and to store a customized
	macro service.
	<BR> these informations are contained in the post body in xml
	form;
	<BR> the informations are:
	<BR> -name of the new customized macro service
	<BR> -macro services and elementary services that compose the
	customized macro service
	<BR> -number of operand that the customized macro service use
	<BR> -which operand uses each macro service or elementary service
	contained in the customized macro service
	<BR> - boolean "is private" that specifies if the customized
	macroservice is available from another users.
	<BR>

	<H2 align="center">Example 1</H2>
	user "root" insert the Customized MacroService
	<BR> REQUEST:
	<BR> header: Content-Type application/xml url:
	http://localhost:8080/ISSSR5/customizedMacroService/root/createCustomizedMacroService
	<BR> body:
	<BR>
	<textarea style="color: black; background-color: e3d268;" rows="10"
		cols="40" style="border: none;" disabled="disabled">
	<macroService>
<elementaryServices>AVG</elementaryServices>
<elementaryServices>VAR</elementaryServices>
<idCode>MyMacroService</idCode>
<numOperand>2</numOperand>
<operationOrder>
<parList>1</parList>
</operationOrder>
<operationOrder>
<parList>2</parList>
</operationOrder>
<is_private>false</is_private>
<description>descrizione</description>
</macroService>
	</textarea>
	RESPONSE:
	<BR> header:
	<BR> Status Code: 200 OK
	<BR> Content-Length: 199
	<BR> Content-Type: text/html
	<BR> Date: Fri, 11 Apr 2014 12:21:30 GMT
	<BR> Location:
	/customizedMacroService/root/getCustomizeMacroServiceById/MyMacroService
	<BR> Server: Apache-Coyote/1.1
	<BR> body:
	<BR>
	<textarea rows="10" cols="40" style="border: none;" disabled="disabled">
	MacroService ID: MyMacroService
	MacroService/Elementary Service list:
	MacroService ID: AVG
	MacroService ID: VAR
	Num operand: 2
	MacroService 1 input parameters: 1 
	MacroService 2 input parameters: 2 
	</textarea>
	<H2 align="center">Monitored Errors</H2>
	-Missing customized service name
	<BR> -Missing macro services or elementary services
	<BR> -Wrong macro services or elementary services
	<BR> -Missing operand number
	<BR> -Client doesn't specify any operands for one of the
	macro/elementary services
	<BR> -Operand specified for an elmentary or macro service doesn't
	exists.
	<BR> -The number of operand specified by client for an elmentary
	or macro service doesn't match the number of operands accepted by that
	macro/elementary service
	<BR> -wrong username
	<BR> -wrong elementaries services
	<BR>
	<HR WIDTH="100%">

	<H1 align="center">
		<A id="5"></A> Obtain a customized MacroService by ID
	</H1>
	<H2 align="center">Used HTTP methods</H2>
	the client uses get primitive obtain information about macro service
	with a defined MacroService ID.
	<BR> the asked informations are:
	<BR> -name of the new customized macro service
	<BR> -macro services and elementary services that compose the
	customized macro service
	<BR> -number of operand that the customized macro service use
	<BR> -which operand uses each macro service or elementary service
	contained in the customized macro service
	<BR> - boolean "is private" that specifies if the customized
	macroservice is available from another users.
	<BR>
	<H2 align="center">Example 1</H2>
	user "root" ask for a cusomized MacroService by ID
	<BR> REQUEST:
	<BR> header: Content-Type application/xml
	<BR> url:
	http://localhost:8080/ISSSR5/customizedMacroService/root/getCustomizeMacroServiceById/MyMacroService
	<BR> RESPONSE:
	<BR> header:
	<BR> Status Code: 200 OK
	<BR> Content-Type: application/xhtml+xml
	<BR> Date: Fri, 11 Apr 2014 12:27:02 GMT
	<BR> Server: Apache-Coyote/1.1
	<BR> Transfer-Encoding: chunked
	<BR> body:
	<BR>
	<textarea style="color: black; background-color: e3d268;" rows="10"
		cols="40" style="border: none;" disabled="disabled">
	<macroService>
<description>my description...</description>
<elementaryServices>AVG</elementaryServices>
<elementaryServices>VAR</elementaryServices>
<idCode>MyMacroService</idCode>
<is_private>false</is_private>
<numOperand>2</numOperand>
<operationOrder>
<parList>1</parList>
</operationOrder>
<operationOrder>
<parList>2</parList>
</operationOrder>
</macroService>
	</textarea>
	<H2 align="center">Example 2</H2>
	user "root2" ask for a private cusomized MacroService (CUST1) and CUST1
	is a private Macro Service of user "root"
	<BR> REQUEST:
	<BR> header: Content-Type application/xml
	<BR> url:
	http://localhost:8080/ISSSR5/customizedMacroService/root2/getCustomizeMacroServiceById/CUST1
	<BR> RESPONSE:
	<BR> header:
	<BR> Status Code: 401 Unauthorized
	<BR> Content-Language: en
	<BR> Content-Length: 1017
	<BR> Content-Type: text/html;charset=utf-8
	<BR> Date: Fri, 11 Apr 2014 12:34:29 GMT
	<BR> Server: Apache-Coyote/1.1
	<BR> body:
	<BR>
	<textarea style="color: black; background-color: e3d268;" rows="10"
		cols="40" style="border: none;" disabled="disabled">
	HTTP Status 401 - Requested MacroService is private

type Status report

message Requested MacroService is private

description This request requires HTTP authentication.
Apache Tomcat/7.0.47
	</textarea>
	<H2 align="center">Monitored Errors</H2>
	-wrong username
	<BR> -wrong MacroService ID
	<BR>
	<HR WIDTH="100%">

	<H1 align="center">
		<A id="6"></A> Obtain a all public customized MacroServices
	</H1>
	<H2 align="center">Used HTTP methods</H2>
	the client uses get primitive obtain information about all customized
	public macro services.
	<BR> the asked informations are:
	<BR> -name of the new customized macro service
	<BR> -macro services and elementary services that compose the
	customized macro service
	<BR> -number of operand that the customized macro service use
	<BR> -which operand uses each macro service or elementary service
	contained in the customized macro service
	<BR> -boolean "is private" that specifies if the customized
	macroservice is available from another users.
	<BR>
	<H2 align="center">Example 1</H2>
	user "root" ask for all public cusomized MacroServices
	<BR> REQUEST:
	<BR> header: Content-Type application/xml
	<BR> url:
	http://localhost:8080/ISSSR5/customizedMacroService/getAllPublicCustomizedMacroService
	<BR> RESPONSE:
	<BR> header:
	<BR> Status Code: 200 OK
	<BR> Content-Type: application/xhtml+xml
	<BR> Date: Fri, 11 Apr 2014 12:43:18 GMT
	<BR> Server: Apache-Coyote/1.1
	<BR> Transfer-Encoding: chunked
	<BR> body:
	<BR>
	<textarea style="color: black; background-color: e3d268;" rows="10"
		cols="40" style="border: none;" disabled="disabled">
	<resumeCustomizedMacroService>
<customizedMacroServiceList>
<description>my description...</description>
<elementaryServices>AVG</elementaryServices>
<elementaryServices>VAR</elementaryServices>
<idCode>MyMacroService</idCode>
<is_private>false</is_private>
<numOperand>2</numOperand>
<operationOrder>
<parList>1</parList>
</operationOrder>
<operationOrder>
<parList>2</parList>
</operationOrder>
</customizedMacroServiceList>
<customizedMacroServiceList>
<description>descrizione</description>
<elementaryServices>AVG</elementaryServices>
<elementaryServices>VAR</elementaryServices>
<idCode>PCUST1</idCode>
<is_private>false</is_private>
<numOperand>2</numOperand>
<operationOrder>
<parList>1</parList>
</operationOrder>
<operationOrder>
<parList>2</parList>
</operationOrder>
</customizedMacroServiceList>
<customizedMacroServiceList>
<description>descrizione</description>
<elementaryServices>AVG</elementaryServices>
<elementaryServices>VAR</elementaryServices>
<idCode>PCUST2</idCode>
<is_private>false</is_private>
<numOperand>2</numOperand>
<operationOrder>
<parList>1</parList>
</operationOrder>
<operationOrder>
<parList>2</parList>
</operationOrder>
</customizedMacroServiceList>
</resumeCustomizedMacroService>
	</textarea>
	<HR WIDTH="100%">
	<H1 align="center">
		<A id="7"></A> Obtain all user's customized MacroServices
	</H1>
	<H2 align="center">Used HTTP methods</H2>
	the client uses get primitive obtain information about all his
	customized macro services.
	<BR> the asked informations are:
	<BR> -name of the new customized macro service
	<BR> -macro services and elementary services that compose the
	customized macro service
	<BR> -number of operand that the customized macro service use
	<BR> -which operand uses each macro service or elementary service
	contained in the customized macro service
	<BR> - boolean "is private" that specifies if the customized
	macroservice is available from another users.
	<BR>
	<H2 align="center">Example 1</H2>
	user "root" ask for all his cusomized MacroServices
	<BR> REQUEST:
	<BR> header: Content-Type application/xml
	<BR> url:
	http://localhost:8080/ISSSR5/customizedMacroService/root/getAllCustomizedMacroService
	<BR> RESPONSE:
	<BR> header:
	<BR> Status Code: 200 OK
	<BR> Content-Type: application/xhtml+xml
	<BR> Date: Fri, 11 Apr 2014 12:46:13 GMT
	<BR> Server: Apache-Coyote/1.1
	<BR> Transfer-Encoding: chunked
	<BR> body:
	<BR>
	<textarea style="color: black; background-color: e3d268;" rows="10"
		cols="40" style="border: none;" disabled="disabled">
	 <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    <resumeCustomizedMacroService>
    <customizedMacroServiceList>
    <description>prova descrizione</description>
    <elementaryServices>AVG</elementaryServices>
    <elementaryServices>VAR</elementaryServices>
    <idCode>CUST1</idCode>
    <is_private>true</is_private>
    <numOperand>2</numOperand>
    <operationOrder>
    <parList>1</parList>
    </operationOrder>
    <operationOrder>
    <parList>2</parList>
    </operationOrder>
    </customizedMacroServiceList>
    <customizedMacroServiceList>
    <description>my description...</description>
    <elementaryServices>AVG</elementaryServices>
    <elementaryServices>VAR</elementaryServices>
    <idCode>MyMacroService</idCode>
    <is_private>false</is_private>
    <numOperand>2</numOperand>
    <operationOrder>
    <parList>1</parList>
    </operationOrder>
    <operationOrder>
    <parList>2</parList>
    </operationOrder>
    </customizedMacroServiceList>
    <customizedMacroServiceList>
    <description>descrizione</description>
    <elementaryServices>AVG</elementaryServices>
    <elementaryServices>VAR</elementaryServices>
    <idCode>PCUST1</idCode>
    <is_private>false</is_private>
    <numOperand>2</numOperand>
    <operationOrder>
    <parList>1</parList>
    </operationOrder>
    <operationOrder>
    <parList>2</parList>
    </operationOrder>
    </customizedMacroServiceList>
    <customizedMacroServiceList>
    <description>descrizione</description>
    <elementaryServices>AVG</elementaryServices>
    <elementaryServices>VAR</elementaryServices>
    <idCode>PCUST2</idCode>
    <is_private>false</is_private>
    <numOperand>2</numOperand>
    <operationOrder>
    <parList>1</parList>
    </operationOrder>
    <operationOrder>
    <parList>2</parList>
    </operationOrder>
    </customizedMacroServiceList>
    </resumeCustomizedMacroService>
	</textarea>

	<H2 align="center">Monitored Errors</H2>
	-wrong username
	<BR> -wrong MacroService ID
	<BR>
	<HR WIDTH="100%">

	<H1 align="center">
		<A id="8"></A> Insert Operand
	</H1>
	<H2 align="center">Used HTTP methods</H2>
	-POST: the client uses post primitive to create and to store a operand;
	the client insrt, in url user name and scale ID.
	<BR> Informations to create a new operand are contained in the
	post body in xml form;
	<BR> the informations are:
	<BR> -type of operand
	<BR> -information relative to operand ( url if operand mode is
	file, user and password if operand mode is DB, enumeration if operand
	mode is enumeration.)
	<BR>
	<H2 align="center">Example 1</H2>
	insert in the service a operand
	<BR> REQUEST:
	<BR> header: Content-Type application/xml
	<BR> url: http://localhost:8080/ISSSR5/scale/root/nominalScale
	<BR> body:
	<BR>
	<textarea style="color: black; background-color: e3d268;" rows="10"
		cols="40" style="border: none;" disabled="disabled">
	<operand>
<dataSeries>3.1</dataSeries>
<dataSeries>4</dataSeries>
<dataSeries>5.5</dataSeries>
<dataSeries>8</dataSeries>
<dataType>Double</dataType>
<operandMode>E</operandMode>
</operand>
	</textarea>
	<BR>RESPONSE:
	<BR> header:
	<BR> Status Code: 200 OK
	<BR> Content-Length: 53
	<BR> Content-Type: text/html
	<BR> Date: Fri, 11 Apr 2014 12:57:08 GMT
	<BR> Location: /dataSeries/root/getOperandById/1
	<BR> Server: Apache-Coyote/1.1
	<BR> body:
	<BR>
	<textarea style="color: black; background-color: e3d268;" rows="10"
		cols="40" style="border: none;" disabled="disabled">
Type: Double OperandMode: E Data Series: 3.1 4 5.5 8 <BR>
</textarea>
	<H2 align="center">Example 2</H2>
	The service read from the file
	<BR> client send a http post message with with file operand mode
	with url: http://localhost:8080/ISSSR5/dataSeries/acquisition
	<BR> The service read from the file (specified with
	url:localhost:8080/ISSSR5/manual/fileExample )archive data series
	<BR> body:
	<BR>

	<textarea style="color: black; background-color: e3d268;" rows="10"
		cols="40" style="border: none;" disabled="disabled">
	<operand>
<dataType>Double</dataType>
<operandMode>F</operandMode>
<url>http://localhost:8080/ISSSR5/manual/fileExample</url>
</operand>
	</textarea>
	<BR>response body:
	<BR>
	<textarea style="color: black; background-color: e3d268;" rows="10"
		cols="40" style="border: none;" disabled="disabled">
	Type: Double
    OperandMode: F
    Data Series: 12 32 44 33 21 23.9 12.7 89 65 3 33 5 6 54 4 56 456 
	</textarea>
	<BR>response header:
	<BR> Status Code: 200 OK
	<BR> Content-Length: 93
	<BR> Content-Type: text/html
	<BR> Date: Fri, 28 Mar 2014 13:45:38 GMT
	<BR> Server: Apache-Coyote/1.1
	<BR>
	<H2 align="center">Example 3</H2>
	The service read from the file
	<BR> client send a http post message with with file operand mode
	with url: http://localhost:8080/ISSSR5/dataSeries/acquisition
	<BR> body:
	<BR>
	<textarea style="color: black; background-color: e3d268;" rows="10"
		cols="40" style="border: none;" disabled="disabled">
	<operand>
<dataType>Double</dataType>
<operandMode>F</operandMode>
<url>http://localhost:URL SBAGLIATO</url>
</operand>
	</textarea>
	<BR>response body:
	<BR> HTTP Status 400 - I/O Exception
	<BR> response header:
	<BR> Status Code: 400 Bad Request
	<BR> Connection: close
	<BR> Content-Language: en
	<BR> Content-Length: 994
	<BR> Content-Type: text/html;charset=utf-8
	<BR> Date: Fri, 28 Mar 2014 13:53:26 GMT
	<BR> Server: Apache-Coyote/1.1
	<BR>
	<H2 align="center">Monitored Errors</H2>
	<HR WIDTH="100%">

	<H1 align="center">
		<A id="9"></A> Obtain user's Dataseries by ID
	</H1>
	<H2 align="center">Used HTTP methods</H2>
	GET: the client uses get primitive to ask for a operand by ID.
	<BR> the asked informations are:
	<BR> -type of operand
	<BR> -information relative to operand ( url if operand mode is
	file, user and password if operand mode is DB, enumeration if operand
	mode is enumeration.)
	<BR>
	<H2 align="center">Example 1</H2>
	obtain information about scale with ID "2"
	<BR> REQUEST:
	<BR> header: Content-Type application/xml
	<BR> url: http://localhost:8080/ISSSR5/scale/root/getScaleById/2
	<BR> RESPONSE:
	<BR> header:
	<BR> Status Code: 200 OK
	<BR> Content-Type: application/xhtml+xml
	<BR> Date: Fri, 11 Apr 2014 13:09:41 GMT
	<BR> Server: Apache-Coyote/1.1
	<BR> Transfer-Encoding: chunked
	<BR> body:
	<BR>
	<textarea rows="10" cols="40" style="border: none;"disabled="disabled">
	  <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    <operand>
    <dataSeries>3.1</dataSeries>
    <dataSeries>4</dataSeries>
    <dataSeries>5.5</dataSeries>
    <dataSeries>8</dataSeries>
    <dataType>Double</dataType>
    <id>1</id>
    <operandMode>E</operandMode>
    <scale>
    <enumerateDomain>
    <domType>EnumeralDomain</domType>
    <scalePoints>Rosso</scalePoints>
    <scalePoints>Verde</scalePoints>
    <scalePoints>Amaranto</scalePoints>
    </enumerateDomain>
    <id>1</id>
    <type>NominalScale</type>
    </scale>
    </operand>
	</textarea>
	<H2 align="center">Monitored Errors</H2>
	-wrong username
	<BR> -wrong operand ID
	<BR>

	<HR WIDTH="100%">
	<P>
</BODY>
</html>