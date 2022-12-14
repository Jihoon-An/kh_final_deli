<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>

    #wrap_host{
        height: 100vh;
        z-index: 2;
    }

    #deli_nav {
        z-index: 3;
        background: #353535;
        min-height: 900px;
        min-width: 250px;
        padding-top: 35px;
    }

    #deli_nav a {
        color: #FFFFFF;
        text-decoration: none;
    }

    #deli_nav a:link {
        color: #FFFFFF;
    }

    #deli_nav a:hover {
        color: #F8F5F2;
    }

    #deli_nav a:active {
        color: #FFFFFF;
    }

    main {
        min-width: 1000px;
        width: 1000px;
        margin: 0 auto;
    }

    #deli_nav h2 {
        font-weight: bold;
        padding-left: 50px;
        margin-bottom: 90px;
    }


    #deli_nav .nav_list{
        width: 100%;
        height: 50px;
        padding-left: 20px;
        font-size: 18px;
        line-height: 48px;
    }

    #deli_nav .nav_list:hover{
        background-color: rgb(26,109,255,0.9);
    }

    #deli_nav .logout{
        margin-top: 100px;
        margin-left: 40px;
        font-size: 13px;
    }

    #deli_nav .icon{
        width: 50px;
        height: 50px;
        text-align: center;
    }

    #deli_nav .icon>i{
        color: #F5F5F5;
        line-height: 48px;
    }


</style>

    <div id="wrap_host" class="d-flex justify-content-start">
        <nav id="deli_nav" class="d-flex flex-column">
            <a class="a_tag" href="/admin">
                <h2>Deli <br> ADMIN</h2></a>

            <a href="/admin/coupon/list"><div class="nav_list d-flex flex-row">
                <div class="icon"><i class="fa-solid fa-ticket"></i></div>
                <div class="title">쿠폰관리</div></div></a>
            <a href="/admin/review"><div class="nav_list d-flex flex-row">
                <div class="icon"><i class="fa-regular fa-pen-to-square"></i></div>
                <div class="title">리뷰관리</div></div></a>
            <a href="/admin/account/list"><div class="nav_list d-flex flex-row">
                <div class="icon"><i class="fa-solid fa-user"></i></div>
                <div class="title">일반회원관리</div></div></a>
            <a href="/admin/owner/list"><div class="nav_list d-flex flex-row">
                <div class="icon"><i class="fa-solid fa-user-tie"></i></div>
                <div class="title">사업자회원관리</div></div></a>
            <a href="/admin/store/list/"><div class="nav_list d-flex flex-row">
                <div class="icon"><i class="fa-solid fa-building"></i></div>
                <div class="title">식당관리</div></div></a>

            <a href="/account/logout"><div class="logout"><i class="fa-solid fa-right-from-bracket"></i> logout</div></a>

        </nav>


        <script>
            window.onload = function (){
                url = window.location.pathname;
                console.log(url)
                if(url == "/admin/coupon/list"){
                    $(".nav_list").eq(0).css("background-color","#1a6dff");
                }
                if(url == "/admin/review"){
                    $(".nav_list").eq(1).css("background-color","#1a6dff");
                }
                if(url == "/admin/account/list"){
                    $(".nav_list").eq(2).css("background-color","#1a6dff");
                }
                if(url == "/admin/owner/list"){
                    $(".nav_list").eq(3).css("background-color","#1a6dff");
                }
                if(url == "/admin/store/list/"){
                    $(".nav_list").eq(4).css("background-color","#1a6dff");
                }
            }
        </script>