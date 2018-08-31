<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/include/Header.jsp" />

<style>
    body
    {
        background:url(assets/img/procurement2.jpg) no-repeat;
        background-size:cover;
        font-family:Arial,sans-serif;
    }
</style>

<div class="container">
    <div class="login-card"><img src="assets/img/avatar.png" class="profile-img-card">
        <p class="profile-name-card">Login Here</p>
        <c:if test="${not empty message}">
            <p style="text-align:center" class="alert alert-success"> ${message} </p>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <p style="text-align:center" class="alert alert-danger"> ${errorMessage} </p>
        </c:if>


        <form class="form-signin" action="login" method="POST"><span class="reauth-email"> </span>
            <input class="form-control" type="text" required="" placeholder="Username" autofocus="" name="username">
            <input class="form-control" type="password" required="" placeholder="Password" id="inputPassword" name="password">
            <div class="checkbox">
                <div class="checkbox">
                    <label>
                        <input type="checkbox">Remember me</label>
                </div>
            </div>
            <button class="btn btn-primary btn-block btn-lg btn-signin" type="submit">Sign in</button>
        </form><a href="#" class="forgot-password">Forgot your password?</a><a href="#" class="forgot-password"> Don't have an Account? </a></div>
</div>

<c:import url="/include/Footer.jsp" />
