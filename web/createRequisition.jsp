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


<c:set var="role" scope="session" value="${user.getRole()}" />
<c:if test="${role == 'DCPO'}">
    <c:import url="/include/sideBar2.jsp" />
</c:if>

<c:if test="${role == 'COD'}">
    <c:import url="/include/sideBar3.jsp" />
</c:if>    

<div class="container" id="mainbody">

    <h1> Create Requisition </h1>

    <c:if test="${CTMsg != null}">
        <script>
            alert('${CTMsg}');
        </script>
    </c:if>

    <c:if test="${not empty message}">
        <p style="text-align:center" class="alert alert-success"> ${message} </p>
    </c:if>

    <form action="CreateRequisition" method="POST">
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

            <br><br><br>
            <a href="javaScript:{openPopUp();}" class="btn btn-primary pull-right"> Add new item </a>


            <br><br>
            <div class="text-center">
                <input type="button" value="Submit Requisiton" class="btn btn-primary" onclick="confirmRequisition(this.form)" >
            </div>
    </form>

    <form action="AddItem" method="POST" class="card text-center" style="width: 30rem; margin: 0 auto;" >
        <div id="Adding" style="display:none; background-color: rgba(0,0,0,0.4);  padding: 10px; ">

            <div class="form-group">
                <label> Item </label>
                <input type="text" class="form-control" required="" placeholder="Item" name="item">
            </div>
            <div class="form-group">
                <label> Description </label>
                <textarea class="form-control" rows="3" required="" name="description" placeholder="Description"></textarea>
            </div>
            <div class="form-group">
                <label> Quantity </label>
                <input type="text" placeholder="Quantity" required="" name="quantity">
            </div>

            <input type="button" value="Add" class="btn btn-success" onclick="confirmItems(this.form)"/>

        </div>
    </form>

    <script>
        function openPopUp() {
            $('#Adding').css('display', 'block');
            $('#Adding').dialog();
        }
        
        function confirmItems(form) {
            var conf = confirm("Do you wish to add this item?");

            if (conf === false) {
                preventDefault();
            } else {
                form.submit();
            }

        }

        function confirmRequisition(form) {
            var conf = confirm("Do you wish to submit this requisition?");

            if (conf === false) {
                preventDefault();
            } else {
                form.submit();
            }

        }
    </script>

</div>

</div>

<c:import url="/include/Footer.jsp" />