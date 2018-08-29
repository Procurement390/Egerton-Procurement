
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/include/Header1.jsp"/>

<style>    
    body
    {
        background:url(assets/img/procurement2.jpg) no-repeat;
        background-size:cover;
        font-family:Arial,sans-serif;
    }

    footer{
        float: left;
        margin-top: 10px;
    }

</style>

<c:import url="/include/sideBar3.jsp"/>

<div id="mainbody">
    <c:if test="${not empty message}">
        <h3 style="text-align: center" class="alert alert-success"> ${message} </h3>
    </c:if>

</div>   

<c:import url="/include/Footer.jsp" />