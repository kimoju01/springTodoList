<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                        Featured
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">Todo List</h5>
                        <!-- list table 시작 -->
                        <table class="table">
                            <thead>
                            <tr>  <!-- table row -->
                                <th scope="col">Tno</th>
                                <th scope="col">Title</th>
                                <th scope="col">Writer</th>
                                <th scope="col">DueDate</th>
                                <th scope="col">Finished</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${responseDTO.dtoList}" var="dto">
                            <tr>  <!-- table row -->
                                <!-- c:out 태그로 특수 문자 이스케이프해서 안전하게 출력 -->
                                <th scope="row"><c:out value="${dto.tno}"/></th>
                                <!-- 조회 페이지로 이동할 때 tno와 함께 page, size 값이 포함된 getLink() 결과값도 넘겨준다. -->
                                <td><a href="/todo/read?tno=${dto.tno}&${pageRequestDTO.link}" class="text-decoration-none"><c:out value="${dto.title}"/></a></td>  <!-- table data -->
                                <td><c:out value="${dto.writer}"/></td>
                                <td><c:out value="${dto.dueDate}"/></td>
                                <td><c:out value="${dto.finished}"/></td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <!-- list table 끝 -->
                        <!-- 페이지 번호 시작 -->
                        <div>
                            <ul class="pagination justify-content-end">
                                <!-- test 속성의 조건식이 true면 코드 실행 -->
                                <c:if test="${responseDTO.prev}">
                                    <li class="page-item">
                                        <!-- 링크 이동을 ul 태그에 한 번에 이벤트 처리하기 위해 data-num으로 속성 넣기 -->
                                        <a class="page-link" data-num="${responseDTO.start-1}">◀️</a>
                                    </li>
                                </c:if>
                                <c:forEach begin="${responseDTO.start}" end="${responseDTO.end}" var="num">
                                    <!-- 현재 페이지 번호면 버튼 활성화(active) -->
                                    <li class="page-item ${responseDTO.page == num ? "active" : ""}">
                                        <a class="page-link" data-num="${num}">${num}</a>
                                    </li>
                                </c:forEach>
                                <!-- test 속성의 조건식이 true면 코드 실행 -->
                                <c:if test="${responseDTO.next}">
                                    <li class="page-item">
                                        <a class="page-link" data-num="${responseDTO.end+1}">▶️</a>
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                        <!-- 페이지 번호 끝 -->
                        <script>
                            document.querySelector(".pagination").addEventListener("click", function (e) {
                                e.preventDefault()
                                e.stopPropagation()

                                // 클릭 이벤트 발생한 요소를 target에 저장
                                const target = e.target

                                // 클릭된 요소가 a 태그가 아닌 경우 함수 종료
                                if(target.tagName !== 'A') {
                                    return
                                }

                                // 클릭된 요소의 data-num 속성 가져와서 num에 저장
                                const num = target.getAttribute("data-num")
                                // 백틱(` `)을 이용해서 처리 (템플릿 리터럴) => 문자열 결합을 간편하게 할 수 있다.
                                // 여기서 num은 자바스크립트 변수 num이다. 즉, 클릭된 요소의 data-num 속성 값 = 클릭된 페이지 번호.
                                // num의 $ 표시 앞에 \ 를 넣는 이유는 EL 표현이 아니라는 걸 표시하기 위함.
                                self.location = `/todo/list?page=\${num}`
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