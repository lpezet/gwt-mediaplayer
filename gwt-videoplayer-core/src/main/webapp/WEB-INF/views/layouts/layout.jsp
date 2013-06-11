<jsp:directive.page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util" %>

<c:set var="mainClass"><tiles:getAsString name="mainClass"/></c:set>
<c:set var="_bodyOnLoad"><tiles:getAsString name="bodyOnLoad"/></c:set>
<c:set var="_bodyOnUnLoad"><tiles:getAsString name="bodyOnUnLoad"/></c:set>
<c:set var="_bodyId"><tiles:getAsString name="bodyId"/></c:set>
<tiles:importAttribute name="h1" toName="h1" scope="request"/>
<tiles:importAttribute name="subNavClass" toName="subNavClass" scope="request"/>
<!doctype html>
<!-- paulirish.com/2008/conditional-stylesheets-vs-css-hacks-answer-neither/ -->
<!--[if lt IE 7 ]> <html class="no-js ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]>    <html class="no-js ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]>    <html class="no-js ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--> <html class="no-js" lang="en"> <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame
       Remove this if you use the .htaccess -->
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title><tiles:getAsString name="title"/></title>
  <meta name="description" content="<tiles:getAsString name="metaDescription"/>"/>
  <meta name="keywords" content="<tiles:getAsString name="metaKeywords"/>"/>

  <!-- Mobile viewport optimized: j.mp/bplateviewport -->
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <!-- Place favicon.ico & apple-touch-icon.png in the root of your domain and delete these references -->
  <link rel="shortcut icon" href="/favicon.ico">
  <link rel="apple-touch-icon" href="/apple-touch-icon.png">

  <!-- CSS: implied media="all" -->
  <link rel="stylesheet" href="/c/style.css?v=2">
  
  <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
  <!-- Uncomment if you are specifically targeting less enabled mobile browsers
  <link rel="stylesheet" media="handheld" href="css/handheld.css?v=2">  -->
  <!-- All JavaScript at the bottom, except for Modernizr which enables HTML5 elements & feature detects -->
  <script src="/j/libs/modernizr-2.0.6.min.js"></script>
  <tiles:insertAttribute name="header"/>
</head>
<body onload="${_bodyOnLoad}" onunload="${_bodyOnUnload}" id="${_bodyId}">
<div id="social-login-box">
    <ul class="social-login">
        <li><a class="login-icon" href="">Login</a></li>
        <li><a class="facebook-icon" href="">Facebook</a></li>
        <li><a class="twitter-icon" href="">Twitter</a></li>
    </ul>
    <a class="toggle" href="">X</a>
</div>
<div id="container">
    <tiles:insertAttribute name="top"/>
	<tiles:insertAttribute name="body"/>
	<tiles:insertAttribute name="bottom"/>
</div>

<tiles:insertAttribute name="footer"/>
<tiles:insertAttribute name="footerScripts"/>

</body>
</html>