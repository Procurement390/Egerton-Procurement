<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/include/Header1.jsp"/>

<c:if test="user == null">
    <c:redirect url="/login.jsp"/>
</c:if>

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

<c:import url="/include/sideBar.jsp"/>

<div class="container" id="mainbody">

    <c:if test="${not empty message}">
        <h3 style="text-align:center" class="alert alert-success"> ${message} </h3>
    </c:if>


    <h1> Available Tenders </h1>

    <div class="table-responsive">
        <table class="table">
            <thead>
                <tr>
                    <th>Tender Number</th>
                    <th>Tender Description</th>
                    <th>Closing Date</th>
                    <th>ClosingTime </th>
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
                        <td><form action="tenderApplication" method="POST">
                                <input type="hidden" name="count" value="${tenders.getCount()}">
                                <input type="hidden" name="reqId" value="${tenders.getTenderNumber()}">
                                <input type="submit" value="Apply"> 
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>   

<c:import url="/include/Footer.jsp" />