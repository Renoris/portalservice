<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>BJ-PortalService</title>

    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js"
        crossorigin="anonymous"></script>
    <script type="text/javascript">
        var message = '${msg}';
        var returnUrl = '${url}';
        alert(mag);
        document.location.href = url;
    </script>
        <style>
            .container {
                margin-top: 5%;
                vertical-align: middle;
            }

            #layoutAuthentication_content {
                background-color: #d9e5ff;
            }

            .btn-primary {
                background-color: #d9e5ff;
                border-color: #d9e5ff;
            }
        </style>
    </head>

    <body class="bg-primary">
    <div id="layoutAuthentication">
        <div id="layoutAuthentication_content">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5">
                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                            <div class="card-header">
                                <h3 class="text-center font-weight-light my-4">Login</h3>
                            </div>
                            <div class="card-body">
                                <form method="POST">
                                    <div class="form-group">
                                        <label class="small mb-1" for="id">Id</label>
                                        <input class="form-control py-4" id="id" name="id" type="text" placeholder="Id를 입력해주세요" />
                                    </div>
                                    <div class="form-group">
                                        <label class="small mb-1" for="password">Password</label>
                                        <input class="form-control py-4" name="password" id="password" type="password" placeholder="패스워드를 입력해주세요" />
                                    </div>

                                    <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                                       <button type="submit" class="btn btn-primary">로그인</button>
                                    </div>
                                </form>
                            </div>
                            <div class="card-footer text-center">
                                <div class="small"><a href="/createaccount">회원 가입</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="layoutAuthentication_footer">
            <footer class="py-4 bg-light mt-auto">
                <div class="container-fluid">
                    <div class="d-flex align-items-center justify-content-between small">
                        <div></div>
                        <div>bj &middot; portalservice project</div>
                        <div></div>
                    </div>
                </div>
            </footer>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
    <script src="/resources/js/scripts.js"></script>
</body>

</html>