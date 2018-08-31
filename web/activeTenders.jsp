
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

    ${message}
    <h1> Available Tenders </h1>

    <div class="table-responsive">
        <table class="table">
            <thead>
                <tr>
                    <th>Tender Number</th>
                    <th>Tender Description</th>
                    <th>Status </th>
                </tr>
            </thead>
        </table>
    </div>
</div>   

<c:import url="/include/Footer.jsp" />