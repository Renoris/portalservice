<%@ page import="kr.ac.jejunu.user.data.Comment" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kr.ac.jejunu.user.data.Gallery" %>
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
  <link rel="stylesheet" type="text/css" href="/resources/css/gallery.css">
  <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet"
    crossorigin="anonymous" />
  <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js"
    crossorigin="anonymous"></script>

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
          <a class="dropdown-item" href="/logout">Logout</a>
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
          <div class="card mb-4 card-margin">
            <%
              String ingallery="";
              Gallery post=(Gallery) request.getAttribute("gallery");
              ingallery += "<div class=\"card-header\">";
              ingallery += "<div class=\"title\">"+post.getPosttitle()+"</div>";
              ingallery += "<div class=\"date\">04-21 21:42</div>";
              ingallery += "<div class=\"creater\">test</div>";
              ingallery += "</div>";
              ingallery += "<div class=\"card-body\">";
              ingallery += "<p>";
              ingallery += post.getPostcontent();
              ingallery += "</p>";
              ingallery += "</div>";
            %>
            <%=ingallery%>
            <div class="card-footer">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <%
                    String commentlist = "";
                    ArrayList<Comment> commentArrayList = (ArrayList<Comment>) request.getAttribute("commentlist");
                    for (Comment comment : commentArrayList ) {
                      commentlist += "<tr>";
                      commentlist += "<th style=\"width: 70%; text-align: center;\">"+comment.getComment()+"</th>";
                      commentlist += "<th style=\"width: 15%;\">"+comment.getName()+"</th>";
                      commentlist += "<th style=\"width: 15%; text-align: center;\">"+comment.getOutdate()+"</th>";
                      commentlist += "</tr>";
                    }
                  %>
                  <tbody>
                  <%=commentlist%>

                  </tbody>
                </table>
              </div>
            </div>
          </div>
          <div class="emptycontainer"></div>
          <!--footer위치에 빈공간을 넣을거임-->
        </div>
      </main>
      <footer class="py-4 bg-light mt-auto" style="position: fixed; width:90%; top:90%">
        <div class="container-fluid">
          <form class="form-inline" method="POST">
            <input type="text" class="form-control mb-2 mr-sm-2" name ="comment" id="comment">
            <button type="submit" class="btn btn-primary mb-2">댓글입력</button>
          </form>

        </div>
      </footer>
    </div>

  </div>

  <script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"
    crossorigin="anonymous"></script>
  <script src="/resources/js/scripts.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>

  <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
  <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>

</body>

</html>