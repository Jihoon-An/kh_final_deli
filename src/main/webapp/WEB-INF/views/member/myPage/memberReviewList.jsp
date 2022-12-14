<%--
  Created by IntelliJ IDEA.
  User: jaeyoung
  Date: 2022-12-18
  Time: PM 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>딜리 - 내 리뷰 보기</title>
    <%@ include file="/WEB-INF/views/global/m-commonLib.jsp" %>
    <link rel="stylesheet" href="/resources/css/member/myPage/memberReviewList.css">
</head>
<body>

<%@ include file="/WEB-INF/views/customHeader/m_header.jsp" %>
<%@ include file="/WEB-INF/views/customHeader/m_back.jsp" %>
<%@ include file="/WEB-INF/views/customHeader/m_home.jsp" %>

<main id="memberReviewList">
    <hr class="mt55">
    <div class="container">
        <div id="page_title">내 리뷰 보기</div>
        <div class="member_review">내가 쓴 리뷰 총 ${myPageReviewCount}개</div>

        <c:choose>
            <c:when test="${not empty myPageReviewList}">
                <c:forEach var="reviews" items="${myPageReviewList}">
                    <div class="reviews">
                        <div class="store_name">식당명 > ${reviews.store_name}</div>

                        <div id="review_star_wrap">
                        <c:if test="${reviews.rev_star == '1'}">
                            <label for="1-star" id="star_1" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color:rgba(250, 208, 0, 0.99);"></i>
                            </label>
                            <label for="1-star" id="star_2" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color: #ddd;"></i>
                            </label>
                            <label for="1-star" id="star_3" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color: #ddd;"></i>
                            </label>
                            <label for="1-star" id="star_4" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color: #ddd;"></i>
                            </label>
                            <label for="1-star" id="star_5" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color: #ddd;"></i>
                            </label>
                        </c:if>

                        <c:if test="${reviews.rev_star == '2'}">
                            <label for="1-star" id="star_1" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color: rgba(250, 208, 0, 0.99);"></i>
                            </label>
                            <label for="1-star" id="star_2" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color: rgba(250, 208, 0, 0.99);"></i>
                            </label>
                            <label for="1-star" id="star_3" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color: #ddd;"></i>
                            </label>
                            <label for="1-star" id="star_4" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color: #ddd;"></i>
                            </label>
                            <label for="1-star" id="star_5" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color: #ddd;"></i>
                            </label>
                        </c:if>

                        <c:if test="${reviews.rev_star == '3'}">
                            <label for="1-star" id="star_1" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color: rgba(250, 208, 0, 0.99);"></i>
                            </label>
                            <label for="1-star" id="star_2" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color: rgba(250, 208, 0, 0.99);"></i>
                            </label>
                            <label for="1-star" id="star_3" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color: rgba(250, 208, 0, 0.99);"></i>
                            </label>
                            <label for="1-star" id="star_4" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color: #ddd;"></i>
                            </label>
                            <label for="1-star" id="star_5" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color: #ddd;"></i>
                            </label>
                        </c:if>

                        <c:if test="${reviews.rev_star == '4'}">
                            <label for="1-star" id="star_1" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color: rgba(250, 208, 0, 0.99);"></i>
                            </label>
                            <label for="1-star" id="star_2" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color: rgba(250, 208, 0, 0.99);"></i>
                            </label>
                            <label for="1-star" id="star_3" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color: rgba(250, 208, 0, 0.99);"></i>
                            </label>
                            <label for="1-star" id="star_4" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color: rgba(250, 208, 0, 0.99);"></i>
                            </label>
                            <label for="1-star" id="star_5" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color: #ddd;"></i>
                            </label>
                        </c:if>

                        <c:if test="${reviews.rev_star == '5'}">
                            <label for="1-star" id="star_1" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color: rgba(250, 208, 0, 0.99);"></i>
                            </label>
                            <label for="1-star" id="star_2" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color: rgba(250, 208, 0, 0.99);"></i>
                            </label>
                            <label for="1-star" id="star_3" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color: rgba(250, 208, 0, 0.99);"></i>
                            </label>
                            <label for="1-star" id="star_4" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color: rgba(250, 208, 0, 0.99);"></i>
                            </label>
                            <label for="1-star" id="star_5" class="startext">
                                <i class="fa-solid fa-star" style="position:relative;color: rgba(250, 208, 0, 0.99);"></i>
                            </label>
                        </c:if>
                        </div>

                        <div id="write_time">${reviews.rev_writetime}</div>

                        <c:choose>
                            <c:when test="${reviews.flag_udt == 'N'}">
                                <button id="modify_review" name="modify_review" style="display: none">수정</button>
                            </c:when>

                            <c:otherwise>
                                <div id="modify_review_wrap">
                                    <a href="/myPage/review?rev_seq=${reviews.rev_seq}&order_seq=${reviews.order_seq}&store_seq=${reviews.store_seq}">
                                        <button id="psb_modify_review" name="psb_modify_review" revSeq="${reviews.rev_seq}">수정</button>
                                    </a>
                                </div>
                                    <input type="hidden" value="${reviews.rev_seq}">
                                    <input type="hidden" value="${reviews.store_seq}">
                                    <input type="hidden" value="${reviews.order_seq}">

                            </c:otherwise>
                        </c:choose>
                        <div id="delete_review_wrap">
                            <button class="delete_review" name="delete_review" revSeq="${reviews.rev_seq}">삭제</button>
                            <input type="hidden" class="rev_seq" value="${reviews.rev_seq}">
                        </div>
                        <c:choose>
                            <c:when test="${not empty reviews.rev_sysname}">
                                <c:forEach var="reviewImg" items="${reviews.rev_sysname}">
                                    <div id="img_box">
                                        <img src="/resources/img/review/${reviewImg}" style="width: 100%"; height="100%">
                                    </div>
                                </c:forEach>
                            </c:when>

                        </c:choose>

                        <c:choose>
                            <c:when test="${not empty reviews.rev_content}">
                                <div id="review_content">리뷰 내용 > ${reviews.rev_content}</div>
                            </c:when>
                        </c:choose>

                        <c:choose>
                            <c:when test="${not empty reviews.menu}">
                                    <div id="menu_name_wrap">
                                        <c:forEach var="i" items="${reviews.menu}">
                                            <div class="menu_name">${i.menuDTO.menu_name}</div>
                                        </c:forEach>
                                    </div>
                            </c:when>
                        </c:choose>
                </c:forEach>
                </c:when>
                <c:otherwise>
                    <h3>작성한 리뷰가 없습니다.</h3>
                </c:otherwise>
                </c:choose>
            </div>

    <script>
        function onclickDeleteBtn(param) {
            var rev_seq = param.getAttribute('rev_seq');
        }

        $(".delete_review").on("click", function () {

            let revSeq = $(this).closest($(".reviews")).find($(".rev_seq")).val();
            console.log(revSeq);

            if (confirm("정말 삭제하시겠습니까?") == true) {
                $.ajax({
                    url: "/myPage/reviewList/deleteReview",
                    type: "post",
                    data: {'rev_seq': revSeq}
                }).done(function (resp) {
                    location.reload();
                })
            }else{
                return false;
            }
        })
    </script>
</main>
</body>
</html>