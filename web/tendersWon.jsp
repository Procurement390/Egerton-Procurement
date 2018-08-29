
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

<c:set var="role" scope="session" value="${user.getRole()}" />
<c:if test="${role == 'DCPO'}">
    <c:import url="/include/sideBar2.jsp" />
</c:if>

<c:if test="${role == 'Supplier'}">
    <c:import url="/include/sideBar.jsp" />
</c:if>  

<div class="container" id="mainbody">

    <h1> Tenders Won </h1>

    <div class="table-responsive">
        <table class="table">
            <thead>
                <tr>
                    <td><strong> Tender Number </strong></td>
                    <td><strong> Tender Description </strong></td>
                    <td><strong> Tender Closing Date </strong></td>
                    <td><strong> Tender Closing Time </strong></td>
                    <td><strong> Bid Amount </strong></td>
                    <td><strong> Tender Status </strong></td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="tenderW" items="${tenderW}" >
                    <tr>
                        <td> ${tenderW.getTenderNumber()}</td>
                        <td> ${tenderW.getDescription()}</td>
                        <td> ${tenderW.getClosingDate()}</td>
                        <td> ${tenderW.getClosingTime()}</td>
                        <td> ${tenderW.getAmount()} </td>
                        <td> ${tenderW.getStatus()}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<c:import url="/include/Footer.jsp" />