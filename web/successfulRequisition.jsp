
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

<c:if test="${role == 'COD'}">
    <c:import url="/include/sideBar3.jsp" />
</c:if>    


<div class="container" id="mainbody">

    <h1> Successful Requisition </h1>

    <div class="table-responsive">
        <table class="table">
            <thead>
                <tr>
                    <td><strong> Requisition id </strong></td>
                    <td><strong> Description </strong></td>
                    <td><strong> Status </strong></td>
                    <td><strong> View </strong></td>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty requisition}"> <h4 class="alert alert-info text-center"> No Successful Requisition </h4>  </c:when>
                    <c:otherwise>
                        <c:forEach var="req" items="${requisition}" >
                            <tr>
                                <td> ${req.getId()}</td>
                                <td> ${req.getFaculty()}</td>
                                <td> ${req.getDepartment()}</td>
                                <td> ${req.getUsername()} </td>
                                <td> <a href="SuccessfulRequisitionItems?reqId=${req.getId()}"> View </a> </td>
                                <c:set var="role" scope="session" value="${user.getRole()}" />
                                <c:if test="${role == 'DCPO'}">
                                    <td><a onClick="publishTender()"> Publish Tender </a></td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>       
    </div>
    <script>
        function publishTender() {
            var conf = confirm("Do you want to publish this requisition?");

            if (conf === false) {
                preventDefault();
            } else {
                window.location.href="tenderCreation?reqId=${req.getId()}";
            }

        }

    </script>
</div>

<c:import url="/include/Footer.jsp" />