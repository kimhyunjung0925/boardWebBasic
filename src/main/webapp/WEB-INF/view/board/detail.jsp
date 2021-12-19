<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/res/css/board/detail.css">
<div>
    <!--작성자랑 로그인정보가 같으면 삭제 수정 할 수 있게-->
    <c:if test="${sessionScope.loginUser.iuser == requestScope.data.writer}">
        <div>
            <a href="/board/del?iboard=${requestScope.data.iboard}"> <button>삭제</button></a>
            <a href="/board/regmod?iboard=${requestScope.data.iboard}"> <button>수정</button></a>
        </div>
    </c:if>

    <!--로그인 되어 있으면 하트 할 수 있게-->
    <c:if test="${sessionScope.loginUser.iuser != null}">
        <div class="fav">
            <c:choose>
                <c:when test="${requestScope.isHeart == 1}">
                <a href="/board/heart?proc=2&iboard=${requestScope.data.iboard}"> <i class="fas fa-heart"></i></a>
                </c:when>
                <c:otherwise>
                    <a href="/board/heart?proc=1&iboard=${requestScope.data.iboard}"><i class="far fa-heart"></i></a>
                </c:otherwise>
            </c:choose>
        </div>
    </c:if>

    <!--로그인 하든 안하든 나타나는 것-->
        <div>NO : ${requestScope.data.iboard} </div>
        <div>글제목 : ${requestScope.data.title} </div>
        <div>조회수 : ${requestScope.data.hit}</div>
        <div>작성자(이름) : ${requestScope.data.writerNm}</div>
        <div>등록일시 : ${requestScope.data.rdt} </div>
        <div> ━━━━━ 《내용》 ━━━━━ </div>
        <div> ${requestScope.data.ctnt} </div>
        <div> ━━━━━━━━━━━━━━ </div>

    <!--로그인 되어 있으면 댓글달 수 있게-->
    <c:if test="${sessionScope.loginUser.iuser != null}">
        <div>
            <form action="/board/cmt/reg" method="post">
                <input type="hidden" name="iboard" value="${requestScope.data.iboard}">
                <input type="text" name="ctnt" placeholder="댓글 내용">
                <input type="submit" value="댓글 등록">
            </form>
        </div>
    </c:if>

    <!------------------------------------------------------------------------------------->
    <!-- 댓글 창 -->
    <div>
        <table>
            <tr>
                <th>내용</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>비고</th>
            </tr>
            <c:forEach items="${requestScope.cmtList}" var="item">
                <tr>
                    <td><c:out value="${item.ctnt}"/> </td>
                    <td>${item.writerNm}</td>
                    <td>${item.rdt}</td>
                    <td>
                        <c:if test="${sessionScope.loginUser.iuser == item.writer}">
                            <button onclick="openModForm(${item.icmt},'${item.ctnt}');">수정</button>
                            <button onclick="isDelCmt(${requestScope.data.iboard},${item.icmt});">삭제</button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>
</div>

<div class="cmtModContainer">
    <div class="cmtModBody">
        <form action="/board/cmt/mod" method="post" id="cmtModFrm">
            <input type="hidden" name="iboard" value="${requestScope.data.iboard}" >
            <input type="hidden" name="icmt" >
            <div><input type="text" name="ctnt" placeholder="댓글내용"></div>
            <div>
                <input type="submit" value="수정">
                <input type="button" value="취소" id="btnCancel">
            </div>
        </form>
    </div>
</div>
<script src="/res/js/board/detail.js?ver=1"></script>

