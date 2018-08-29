
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/include/Header.jsp"/>

<style>
    body
    {
        background:url(assets/img/procurement2.jpg) no-repeat;
        background-size:cover;
        font-family:Arial,sans-serif;
    }
</style>

<body>
    <div class="login-card"><img src="assets/img/avatar.png" class="profile-img-card">
        <p class="profile-name-card"> ${message} </p>
        <p class="profile-name-card">Register Here</p>
        <form class="form-signin"  action="Registration" method="POST"><span class="reauth-email"> </span>
            <input class="form-control" type="text" required="" placeholder="Full Name" name="name" autofocus="">
            <input class="form-control" type="text" required="" placeholder="Username" name="username">
            <input class="form-control" type="password" required="" placeholder="Password" id="inputPassword" name="password">
            <select class="form-control" name="role" required="">
                <option> Select one ...</option>
                <option value="Supplier"> Supplier </option>
                <option value="COD"> COD </option>
            </select> 
            <div class="checkbox">
                <div class="checkbox">
                    <label>
                        <input type="checkbox">Remember me</label>
                </div>
            </div>
            <button class="btn btn-primary btn-block btn-lg btn-signin" type="submit">Register </button>
        </form><a href="#" class="forgot-password">Already have an account?</a></div>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

<c:import url="/include/Footer.jsp" />