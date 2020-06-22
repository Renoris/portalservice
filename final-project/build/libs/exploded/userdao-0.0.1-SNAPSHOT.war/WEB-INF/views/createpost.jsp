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
  <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet"
    crossorigin="anonymous" />
  <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js"
    crossorigin="anonymous"></script>
<%--  <%--%>
<%--    String msg=null;--%>
<%--    try{--%>
<%--      msg=request.getAttribute("msg").toString();--%>
<%--      System.out.println(msg);--%>
<%--    }--%>
<%--    catch (Exception e){--%>
<%--      msg="";--%>
<%--    }--%>
<%--  %>--%>
  <style>
    .title {
      width: 90%;
    }

    .btn-primary {
      float: right;
    }

    textarea.autosize {
      min-height: 50px;
    }

    .content {
      width: 90%;
    }
  </style>
</head>

<body class="sb-nav-fixed">
  <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
    <a class="navbar-brand" href="index.html">BJ-PortalService</a><button class="btn btn-link btn-sm order-1 order-lg-0"
      id="sidebarToggle" href="#">
      <i class="fas fa-bars"></i></button><!-- Navbar Search-->
    <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">

    </form>
    <!-- Navbar-->
    <ul class="navbar-nav ml-auto ml-md-0">
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" id="userDropdown" href="#" role="button" data-toggle="dropdown"
          aria-haspopup="true" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
          <a class="dropdown-item" href="#">Settings</a><a class="dropdown-item" href="#">Activity Log</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="login.html">Logout</a>
        </div>
      </li>
    </ul>
  </nav>

  <div id="layoutSidenav">
    <div id="layoutSidenav_nav">
      <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
          <div class="nav">
            <div class="sb-sidenav-menu-heading">서비스</div>
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseLayouts1"
              aria-expanded="false" aria-controls="collapseLayouts">
              <div class="sb-nav-link-icon">
                <i class="fas fa-columns"></i>
              </div>
              게임들
              <div class="sb-sidenav-collapse-arrow">
                <i class="fas fa-angle-down"></i>
              </div>
            </a>
            <div class="collapse" id="collapseLayouts1" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
              <nav class="sb-sidenav-menu-nested nav">
                <a class="nav-link" href="--게임1링크">파리잡기</a>
                <a class="nav-link" href="--게임2링크">무언가</a>
              </nav>
            </div>
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseLayouts2"
              aria-expanded="false" aria-controls="collapseLayouts">
              <div class="sb-nav-link-icon">
                <i class="fas fa-columns"></i>
              </div>
              게시판
              <div class="sb-sidenav-collapse-arrow">
                <i class="fas fa-angle-down"></i>
              </div>
            </a>
            <div class="collapse" id="collapseLayouts2" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
              <nav class="sb-sidenav-menu-nested nav">
                <a class="nav-link" href="/lobby">자유게시판</a>
                <a class="nav-link" href="/mydaily">나의일정</a>
              </nav>
            </div>
          </div>
        </div>
      </nav>
    </div>
    <div id="layoutSidenav_content">
      <main>
        <div class="container-fluid">
          <h1 class="mt-4">게시글</h1>

          <form method="POST">
            <div class="card mb-4 card-margin">
              <div class="card-header">
                <div class="form-group">
                  <label for="posttitle">게시글 제목</label>
                  <input type="text" class="form-control" name="posttitle" id="posttitle">
                </div>
              </div>
              <div class="card-body">
                <div class="form-group">
                  <label for="postcontent">게시글 내용</label>
                  <textarea class="form-control" rows="15" name="postcontent" id="postcontent"></textarea>
                </div>
              </div>
              <div class="card-footer">
                <button type="submit" class="btn btn-primary">게시글 작성</button>
              </div>
            </div>
          </form>
        </div>
      </main>
      <footer class="py-4 bg-light mt-auto">
        <div class="container-fluid">
          <div class="d-flex align-items-center justify-content-between small">
            <div class="text-muted">bj &middot; PortalService</div>
          </div>
        </div>
      </footer>
    </div>

  </div>
<%--  <script type="text/javascript">--%>
<%--    var f= "<%=msg%>";--%>
<%--    if(!(f=="")){--%>
<%--      alert(f);--%>
<%--    }--%>


<%--  </script>--%>
  <script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"
    crossorigin="anonymous"></script>
  <script src="/resources/js/scripts.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>

  <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
  <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
  <script>

  </script>
</body>

</html>