<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Document</title>
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
    />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  </head>
  <body>
    <div class="container">
      <a href="/"><h2>새로고침</h2></a>

      <div class="nav__tab">
        <div class="playerJoin">
          <h4>선수 관리</h4>
          <form action="/player" method="POST">
            <input type="text" name="playerName" placeholder="선수이름" />
            <input type="text" name="position" placeholder="포지션" />
            <input type="text" name="teamId" placeholder="팀 번호(id)" />
            <button>확인</button>
          </form>
        </div>
        <h4>팀 관리</h4>
        <div class="teamJoin">
          <form action="/team" method="POST">
            <input type="text" name="teamName" placeholder="팀이름" />
            <input type="text" name="homeGroundId" placeholder="야구장 번호(id)" />
            <button>확인</button>
          </form>
        </div>
        <h4>야구장 관리</h4>
        <div class="groundJoin">
          <form action="/ground" method="POST">
            <input type="text" name="groundName" placeholder="구장이름" />
            <button>확인</button>
          </form>
        </div>
      </div>

      <h4>야구장 관리</h4>
      <table class="table">
        <thead>
          <tr>
            <th>NO</th>
            <th>구장</th>
            <th>팀</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="ground" items="${grounds}">
          <tr id="ground_${ground.id }">
             <td>${ground.id }</td>
             <td>${ground.groundName }</td>
             <td>${ground.team.teamName}</td>
             <td><button class="btn btn-danger" onClick="deleteGroundById(${ground.id})">삭제</button></td>
           </tr>
          </c:forEach>
        </tbody>
      </table>
      <h4>팀 관리</h4>
      <table class="table">
        <thead>
          <tr>
            <th>NO</th>
            <th>팀 이름</th>
            <th>야구장</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="team" items="${teams }">
          <tr id="team_${team.id }">
             <td>${team.id }</td>
             <td>${team.teamName }</td>
             <td>${team.homeGround.groundName }</td>
             <td><button class="btn btn-danger" onClick="deleteTeamById(${team.id})">삭제</button></td>
           </tr>
          </c:forEach>
        </tbody>
      </table>
      <h4>선수 관리</h4>
      <table class="table">
        <thead>
          <tr>
            <th>NO</th>
            <th>선수 이름</th>
            <th>포지션</th>
            <th>팀</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="player" items="${players }">
          <tr id="player_${player.id }">
             <td>${player.id }</td>
             <td>${player.playerName }</td>
             <td>${player.position }</td>
             <td>${player.team.teamName}</td>
             <td><button class="btn btn-danger" onClick="deletePlayerById(${player.id})">삭제</button></td>
           </tr>
          </c:forEach>
        </tbody>
      </table>
      <h4>포지션별 선수 리스트</h4>
      <table class="table">
        <thead>
          <tr>
            <th>NO</th>
            <th>롯데</th>
            <th>삼성</th>
            <th>대구</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="dto" items="${dtos }">
          <tr>
             <td>${dto.position }</td>
             <td>${dto.lotte }</td>
             <td>${dto.samsung }</td>
             <td>${dto.daegu}</td>
           </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </body>

  <script>
  function deleteGroundById(id){
    $.ajax({
     type: "DELETE",
     url: "/ground/"+id,
     dataType: "text"
     }).done(function(result){
     console.log(result);
     if(result === "ok"){
         $("#ground_"+id).remove();
      console.log("성공이요");
     }else{
      alert("삭제에 실패하였습니다. - 야구장을 사용중인 팀이 있습니다");
     }
    });
   }

  function deleteTeamById(id){
	   $.ajax({
	    type: "DELETE",
	    url: "/team/"+id,
	    dataType: "text"
	    }).done(function(result){
	    console.log(result);
	    if(result === "ok"){
	    	$("#team_"+id).remove();
	     console.log("성공이요");
	    }else{
	     alert("삭제에 실패하였습니다. - 팀에 남아있는 선수가 있습니다");
	    }
	   });
	  }

  function deletePlayerById(id){
	   $.ajax({
	    type: "DELETE",
	    url: "/player/"+id,
	    dataType: "text"
	    }).done(function(result){
	    console.log(result);
	    if(result === "ok"){
	    	$("#player_"+id).remove();
	     console.log("성공이요");
	    }else{
	     alert("삭제에 실패하였습니다.");
	    }
	   });
	  }
  </script>
</html>
