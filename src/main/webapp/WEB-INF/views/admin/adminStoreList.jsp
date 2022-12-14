<%--
  Created by IntelliJ IDEA.
  User: HYB
  Date: 2022-12-22
  Time: 오후 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>딜리 - 운영자 식당관리</title>
    <%@ include file="/WEB-INF/views/global/pc-commonLib.jsp" %>

    <%--data tables--%>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.js"></script>

    <link rel="stylesheet" href="/resources/css/admin/storeList.css">
</head>


<%@ include file="/WEB-INF/views/customHeader/admin_nav.jsp" %>

<main id="adminStore">

    <div id="container">

        <table id="myTable">
            <thead>
            <tr>
                <th class="seq">번호</th>
                <th class="sName">상호명</th>
                <th class="category">업종</th>
                <th class="phone">연락처</th>
                <th class="add_detail1">주소</th>
                <th class="oNum">사업번호</th>
                <th class="oName">대표자명</th>
                <th class="display">공개</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${not empty list}">
                    <c:forEach var="store" items="${list}">
                        <tr class="store">
                            <td class="store_seq">${store.store_seq}</td>
                            <td class="store_name">${store.store_name}</td>
                            <td class="store_category">${store.store_category}</td>
                            <td class="store_phone">${store.store_phone}</td>
                            <td class="store_add_detail1">${store.store_add_detail1}, ${store.store_add_detail2}</td>
                            <td class="owner_num">${store.owner_num}</td>
                            <td class="owner_name">${store.owner_name}</td>
                            <td class="store_display">${store.store_display}</td>
                        </tr>
                    </c:forEach>
                </c:when>
            </c:choose>
            </tbody>
        </table>
    </div>


    <!-- 모달 -->
    <div class="modal">
        <div class="modal_content">
            <div class="closeBtn"><i class="fa-solid fa-xmark fa-2xl"></i></div>
            <div class="modalInfo">
                <span class="modalTitle">식당번호</span>
                <input type="text" placeholder="식당번호" disabled id="store_seq">
            </div>
            <div class="modalInfo">
                <span class="modalTitle">상호명</span>
                <input type="text" placeholder="상호명" disabled id="store_name">
            </div>
            <div class="modalInfo">
                <span class="modalTitle">업종</span>
                <input type="text" placeholder="업종" disabled id="store_category">
            </div>
            <div class="modalInfo">
                <span class="modalTitle">연락처</span>
                <input type="text" placeholder="연락처" disabled id="store_phone">
            </div>
            <div class="modalInfo">
                <span class="modalTitle">주소</span>
                <input type="text" placeholder="주소" disabled id="store_add_detail1">
            </div>
            <div class="modalInfo">
                <span class="modalTitle">사업자 등록번호</span>
                <input type="text" placeholder="사업자 등록번호" disabled id="owner_num">
            </div>
            <div class="modalInfo">
                <span class="modalTitle">대표자명</span>
                <input type="text" placeholder="대표자명" disabled id="owner_name">
            </div>
            <div class="modalInfo">
                <span class="modalTitle">공개여부</span>
                <input type="text" placeholder="공개여부" disabled id="store_display">
            </div>

            <div class="btnBox">
                <button type="button" id="deleteBtn" class="deli_btn">삭제</button>
                <button type="button" id="statusBtn" class="deli_btn">비공개</button>
            </div>
        </div>
    </div>

</main>

<script src="/resources/js/admin/storeList.js"></script>
</div>
</body>
</html>
