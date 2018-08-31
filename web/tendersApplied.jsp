
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

<c:import url="/include/sideBar.jsp" />

<div class="container" id="mainbody">

    <h1> Tenders Applied </h1>

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
                    <c:forEach var="tenderA" items="${tenderA}" >
                        <tr>
                            <td> ${tenderA.getTenderNumber()}</td>
                            <td> ${tenderA.getDescription()}</td>
                            <td> ${tenderA.getClosingDate()}</td>
                            <td> ${tenderA.getClosingTime()}</td>
                            <td> ${tenderA.getAmount()} </td>
                            <td> ${tenderA.getStatus()}</td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
</div>

<c:import url="/include/Footer.jsp" />