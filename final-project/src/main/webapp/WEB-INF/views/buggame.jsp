<%@ page import="kr.ac.jejunu.user.data.GameScore" %>
<%@ page import="java.util.ArrayList" %>
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
  <link rel="stylesheet" type="text/css" href="/resources/css/buggame.css">
  <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet"
    crossorigin="anonymous" />
  <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js"
    crossorigin="anonymous"></script>
</head>
<%
  String msg=null;
  try{
    msg=request.getAttribute("msg").toString();
  }
  catch (Exception e){
    msg="";
  }
%>
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
          <a class="dropdown-item" href="/admin">관리자</a>
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
              게임
              <div class="sb-sidenav-collapse-arrow">
                <i class="fas fa-angle-down"></i>
              </div>
            </a>
            <div class="collapse" id="collapseLayouts1" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
              <nav class="sb-sidenav-menu-nested nav">
                <a class="nav-link" href="/buggame">곤충잡기</a>
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
                <a class="nav-link" href="/mypicture">사진관</a>
              </nav>
            </div>
          </div>
        </div>
      </nav>
    </div>
    <div id="layoutSidenav_content">
      <main>
        <div class="container-fluid">
          <h1 class="mt-4">bug game!</h1>
        </div>
        <div class="buggame-container">
          <div class="wrap">
            <div>점수 <span id="point">0</span></div>
            <div>생명 <span id="life">10</span></div>
            <div>시간 <span id="timer">10</span></div>
            <div>최종점수 <span id="final-score">0</span></div>
            <div class="box">
              <div id="bug">
              </div>
            </div>
          </div>
          <div calss="info">
            <ul>
              <li>곤충을 클릭하면 시작</li>
              <li>곤충을 클릭하면 점수 +1</li>
              <li>곤충을 시간내에 잡지 못하거나 박스내부를 클릭하면 생명-1</li>
              <li>생명이 0이면 gameover</li>
            </ul>
          </div>
        </div>
        <div class="game-score-table">
          <form action="post">
          </form>
          <table class="table-bordered">
            <tr>
              <thead>
              <%
                String scorelist = "";
                ArrayList<GameScore> scoreList = (ArrayList<GameScore>) request.getAttribute("scorelist");
                for (int i=0; i<10; i++) {
                  try{
                    GameScore score = scoreList.get(i);
                    scorelist += "<tr>";
                    scorelist += "<td style=\"width: 100px; height:30px; text-align: center;\">" + score.getName() + "</td>";
                    scorelist += "<td style=\"width: 50px; height:30px; text-align: center;\">" + score.getScore() + "</td>";
                    scorelist += "<td style=\"width: 150px; height:30px; text-align: center;\">" + score.getDate().toString() + "</td>";
                    scorelist += "</tr>";
                  }catch (Exception e){
                    scorelist += "<tr>";
                    scorelist += "<td style=\"width: 100px; height:30px; text-align: center;\"></td>";
                    scorelist += "<td style=\"width: 50px; height:30px; text-align: center;\"></td>";
                    scorelist += "<td style=\"width: 150px; height:30px; text-align: center;\"></td>";
                    scorelist += "</tr>";
                  }
                }
              %>

                <tr>
                  <td style="width: 100px; height:30px; text-align: center;">유저</td>
                  <td style="width: 50px; height:30px; text-align: center;">점수</td>
                  <td style="width: 150px; height:30px; text-align: center;">날짜</td>
                </tr>
              </thead>

              <tbody>
              <%=scorelist%>




              </tbody>
            </tr>
          </table>
        </div>
      </main>
      <footer class=" py-4 bg-light mt-auto">
        <div class="container-fluid">
          <div class="d-flex align-items-center justify-content-between small">
            <div class="text-muted">bj &middot; PortalService</div>
          </div>
        </div>
      </footer>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
  <script type="text/javascript">
    var f= "<%=msg%>";
    if(!(f=="")){
      alert(f);
    }
  </script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"
    crossorigin="anonymous"></script>
  <script src="/resources/js/buggame.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
  <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
  <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
  <script src="/resources/assets/demo/datatables-demo.js"></script>
  <script src="/resources/js/scripts.js"></script>
</body>

</html>