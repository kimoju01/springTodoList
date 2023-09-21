<!-- JSP 설정 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- JSTL 설정 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Bootstrap demo</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
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
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
                                aria-expanded="false" aria-label="Toggle navigation">
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
                        TODO
                    </div>
                    <div class="card-body">
                        <form action="/todo/modify" method="post">
                            <div class="input-group mb-3">
                                <span class="input-group-text">Tno</span>
                                <input type="text" name="tno" class="form-control" placeholder="Title" value=<c:out value="${dto.tno}"></c:out>>
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text">Title</span>
                                <input type="text" name="title" class="form-control" placeholder="Title" value=<c:out value="${dto.title}"></c:out>>
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text">DueDate</span>
                                <input type="date" name="dueDate" class="form-control" placeholder="DueDate" value=<c:out value="${dto.dueDate}"></c:out>>
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text">Writer</span>
                                <input type="text" name="writer" class="form-control" placeholder="Writer" value=<c:out value="${dto.writer}"></c:out> readonly>
                            </div>
                            <div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="finished" ${dto.finished?"checked":""}>
                                    <label class="form-check-label">
                                        Finished
                                    </label>
                                </div>
                            </div>
                            <div class="mb-3">
                                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                    <button type="button" class="btn btn-danger">Remove</button>
                                    <button type="button" class="btn btn-primary">Modify</button>
                                    <button type="button" class="btn btn-secondary">List</button>
                                </div>
                            </div>
                        </form>
                        <script>

                            <!-- Remove 버튼 누를 시 form 태그 action 속성 조정 이벤트 처리 -->
                            const formObj = document.querySelector("form")
                            document.querySelector(".btn-danger").addEventListener("click", function (e) {
                              e.preventDefault()
                              e.stopPropagation()

                              formObj.action = "/todo/remove"
                              formObj.method = "post"

                              formObj.submit()
                            }, false)

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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>
</html>
