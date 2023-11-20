<%-- 
    Document   : home
    Created on : Jul 5, 2018, 8:18:21 AM
    Author     : Maxim Chshelokov <schelokov.mv@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="i18n">
<t:generic>
    <jsp:attribute name="content">
        <p><fmt:message key="home.message"/></p>
    </jsp:attribute>
</t:generic>
</fmt:bundle>
