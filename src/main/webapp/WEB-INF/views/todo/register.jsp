<%--
  Created by IntelliJ IDEA.
  User: Hyeju
  Date: 2023-09-18
  Time: 오후 8:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Bootstrap demo</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <!-- navbar 시작 -->
        <div class="row">
            <div class="col">
                <nav class="navbar navbar-expand-lg bg-danger-subtle"> <!-- 분홍색 nav 바 -->
                    <!--            <nav class="navbar navbar-expand-lg bg-body-tertiary">-->
                    <div class="container-fluid">
                        <a class="navbar-brand" href="#">Navbar</a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                            <div class="navbar-nav">
                                <a class="nav-link active" aria-current="page" href="#">Home</a>
                                <a class="nav-link" href="#">Features</a>
                                <a class="nav-link" href="#">Pricing</a>
                                <a class="nav-link disabled" aria-disabled="true">Disabled</a>
                            </div>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
        <!-- navbar 끝 -->
        <!-- Card 시작 -->
        <div class="row content"> <!-- class 명 띄어쓰기 => 클래스가 여러 개 라는 뜻. row 클래스이자 content 클래스이다 -->
            <div class="col">
                <div class="card">
                    <div class="card-header">
                        Featured
                    </div>
                    <div class="card-body">
                        <form action="/todo/register" method="post">
                            <div class="input-group mb-3">
                                <span class="input-group-text">Title</span>
                                <input type="text" name="title" class="form-control" placeholder="Title">
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text">DueDate</span>
                                <input type="date" name="dueDate" class="form-control" placeholder="DueDate">
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text">Writer</span>
                                <input type="text" name="writer" class="form-control" placeholder="Writer">
                            </div>
                            <div class="mb-3">
                                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                    <button type="submit" class="btn btn-primary">Submit</button>
                                    <button type="reset" class="btn btn-secondary">Reset</button>
                                </div>
                            </div>
                        </form>
                        <!-- 클라이언트 측에서 서버에서 발생한 유효성 검사 오류 확인하고 처리할 수 있다 -->
                        <script>
                            <!-- serverValidResult라는 비어있는 객체 선언 -->
                            const serverValidResult = {}

                            <!-- JSTL을 사용해 서버 측에서 전달된 'errors'라는 컬렉션 순회. 'error'는 현재 요소를 나타냄 -->
                            <c:forEach items = "${errors}" var="error">
                            <!-- 각 반복에서 현재 'error' 객체의 필드, 메시지 가져와서 serverValidResult 객체에 필드=>이름, 메시지=>값으로 가지는 속성을 추가 -->
                            serverValidResult['${error.getField()}'] = '${error.defaultMessage}'
                            </c:forEach>

                            console.log(serverValidResult)
                        </script>
                    </div>
                </div>
            </div>
        </div>
        <!-- Card 끝 -->
    </div>

    <div class="row content">
<%--        <h1>content</h1>--%>
    </div>

    <!-- Footer 시작 -->
    <div class="row footer">
        <div class="row fixed-bottom" style="z-index: -100">
            <footer class="py-1 my-1">
                <p class="text-center text-muted">Footer</p>
            </footer>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
