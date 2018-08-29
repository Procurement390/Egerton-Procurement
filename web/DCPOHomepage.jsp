
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

<c:import url="/include/sideBar2.jsp"/>

<div id="mainbody">

    <c:if test="${not empty message}">
        <h3 style="text-align:center;"> ${message} </h3>
    </c:if>
    <c:if test="${not empty tenderMessage}">
        <h3 style="text-align:center;"> ${tenderMessage} </h3>
    </c:if>


    <h1> Available Tenders </h1>

    <div class="table-responsive">
        <table class="table">
            <thead>
                <tr>
                    <th>Tender Number</th>
                    <th>Tender Description</th>
                    <th>Closing Date</th>
                    <th>Closing Time </th>
                    <th>Status </th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="tenders" items="${tenders}" >
                    <tr>
                        <td> ${tenders.getTenderNumber()}</td>
                        <td> ${tenders.getTenderDescription()}</td>
                        <td> ${tenders.getClosingDate()}</td>
                        <td> ${tenders.getClosingTime()}</td>
                        <td> ${tenders.getStatus()}</td>
                        <td> <a href="TenderApplicants?tender=${tenders.getTenderNumber()}"> Applicants </a> </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>   

<c:import url="/include/Footer.jsp" />