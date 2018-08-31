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

    <h1> Publish Tender </h1>

    <c:if test="${CTMsg != null}">
        <script>
            alert('${CTMsg}');
        </script>
    </c:if>

    <form action="CreateTender" method="POST">

        <div class="table-responsive">
            <table class="table">
                <thead>
                    <tr>
                        <th> Tender Number </th>
                        <th> Faculty </th>
                        <th> Department </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td> ${req.getId()} </td>
                        <td> ${req.getFaculty()} </td>
                        <td> ${req.getDepartment()} </td>
                    </tr>
                </tbody>
            </table>
        </div>



        <div class="table-responsive">
            <table class="table">
                <thead>
                    <tr>
                        <th> Items </th>
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
            
            <div class="card" style="width:50rem">
                <div class="form-group">
                    <label> Closing time </label>
                    <input type="date" name="closingdate" required="" class="form-control">
                </div>
            </div>
            

            <br><br>
            <div class="text-center">
                <input type="submit" value="Publish tender" class="btn btn-primary" >
            </div>
    </form>


</div>

</div>

<c:import url="/include/Footer.jsp" />