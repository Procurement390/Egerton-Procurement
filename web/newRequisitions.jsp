
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

<c:import url="/include/sideBar2.jsp" />

<div class="container" id="mainbody">

    <h1> New Requisitions </h1>

        <div class="table-responsive">
            <table class="table">
                <thead>
                    <tr>
                        <td><strong> Requisition id </strong></td>
                        <td><strong> Faculty </strong></td>
                        <td><strong> Department </strong></td>
                        <td><strong> Username </strong></td>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${empty requisition}"> <tr><td> No item added</td> </tr> </c:when>
                        <c:otherwise>
                            <c:forEach var="req" items="${requisition}" >
                                <tr>
                                    <td> ${req.getId()}</td>
                                    <td> ${req.getFaculty()}</td>
                                    <td> ${req.getDepartment()}</td>
                                    <td> ${req.getUsername()} </td>
                                    <td> <a href="RequisitionApproval?reqId=${req.getId()}"> View </a> </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </tbody>
               
            </table>
        </div>
</div>

<c:import url="/include/Footer.jsp" />