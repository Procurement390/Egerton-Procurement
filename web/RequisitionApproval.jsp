<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<c:import url="/include/Header1.jsp"/>

<c:import url="/include/sideBar2.jsp"/>

<div class="container" id="mainbody">

    <h3 style="text-align:center;"> Requisition items <br> for <br> requisition number ${reqId} </h3>

    <form action="ApproveRequisition" method="POST">
        <div class="table-responsive">
            <table class="table">
                <thead>
                    <tr>
                        <th> Item </th>
                        <th> Description </th>
                        <th> Quantity </th>
                    </tr>
                </thead>
                <tbody>

                    <c:choose>
                        <c:when test="${empty items}"> <tr><td> No item added</td> </tr> </c:when>
                        <c:otherwise>
                            <c:forEach var="item" items="${items}" >
                                <tr>
                                    <td> ${item.getItem()}</td>
                                    <td> ${item.getDescription()}</td>
                                    <td> ${item.getQuantity()}</td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>

                </tbody>
            </table>


            <br><br>
            <div class="text-center">
                <a class="btn btn-danger pull-left" style=" margin-left: 20px;" onClick="rejectRequisition()"> Reject Requisition </a>
                <a class="btn btn-success pull-right" style="margin-right: 20px;" onClick="acceptRequisition()"> Approve Requisition </a>
            </div>
    </form>

    <script>
        function rejectRequisition() {
            var conf = confirm("Do you want to reject this requisition?");

            if (conf === false) {
                preventDefault();
            } else {
                window.location.href="RejectRequisition?reqId=${reqId}";
            }

        }
        
         function acceptRequisition() {
            var conf = confirm("Do you want to accept this requisition?");

            if (conf === false) {
                preventDefault();
            } else {
                window.location.href="ApproveRequisition?reqId=${reqId}"
            }

        }
    </script>

</div>

</div>

<c:import url="/include/Footer.jsp" />