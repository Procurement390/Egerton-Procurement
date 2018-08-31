
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

<c:import url="/include/sideBar3.jsp"/>

<div id="mainbody">

    <form class="card text-center" style="width:50rem; padding: 10px; margin: 0 auto;" action="Profile" method="POST">

        <div class="form-group">
            <label> Name </label>
            <input type="text" class="form-control" name="name" value=${cod.getName()} >
        </div>
        <div class="form-group">
            <label> Username </label>
            <input type="text" class="form-control" name="username" value=${cod.getUsername()}>
        </div>
        <fieldset>
            <legend> Update Password </legend>
            <div class="row">
                <div class="col-sm-4 form-group">
                    <label> Current Password </label>
                    <input type="password" placeholder="Current Password" name="currentPassword" class="form-control">
                </div>
                <div class="col-sm-4 form-group">
                    <label> New Password </label>
                    <input type="password" placeholder="New Password" name="newPassword" class="form-control">
                </div>
                <div class="col-sm-4 form-group">
                    <label> Repeat Password </label>
                    <input type="password" placeholder="Repeat Password" name="repeatPassword" class="form-control">
                </div>
            </div>
        </fieldset>
        <div class="form-group">
            <label >Role</label>
            <input type="text" class="form-control" name="role" value=${cod.getRole()}>
        </div>
        <div class="form-group">
            <label>Faculty</label>
            <input type="text" class="form-control" name="faculty" value=${cod.getFaculty()}>
        </div>
        <div class="form-group">
            <label>Department</label>
            <input type="text" class="form-control" name="department" value=${cod.getDepartment()}>
        </div>
        <button type="submit" class="btn btn-primary"> Update Profile </button>
    </form>

</div>   

<c:import url="/include/Footer.jsp" />