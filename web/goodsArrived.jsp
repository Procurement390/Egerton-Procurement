
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

<c:import url="/include/sideBar3.jsp" />

<div class="container" id="mainbody">

    <h1> Arrived Goods </h1>

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
               
            </table>
        </div>
</div>

<c:import url="/include/Footer.jsp" />