<%-- 
    Document   : index
    Created on : Jan 24, 2021, 4:23:56 PM
    Author     : MONS
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Car Rental</title>

        <!-- load stylesheets -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700"/>
        <!-- Google web font "Open Sans" -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
        <!-- Font Awesome -->
        <link rel="stylesheet"  href="css/bootstrap.min.css"/> <!-- Bootstrap style -->
        <link rel="stylesheet"  href="css/datepicker.css" />
        <link rel="stylesheet"  href="slick/slick.css" />
        <link rel="stylesheet"  href="slick/slick-theme.css" />
        <link rel="stylesheet"  href="css/style.css"/> <!-- Templatemo style -->

    </head>

    <body>
        <div class="tm-main-content" id="top">
            <div class="tm-top-bar-bg"></div>
            <div class="tm-top-bar" id="tm-top-bar">
                <div class="container">
                    <div class="row">
                        <nav class="navbar navbar-expand-lg narbar-light">
                            <a class="navbar-brand mr-auto" href="loadProducts">
                                <img src="https://i.ibb.co/M2SRKkD/logo.png" alt="Site logo">
                                CAR RENTAL
                            </a>
                            <button type="button" id="nav-toggle" class="navbar-toggler collapsed" data-toggle="collapse"
                                    data-target="#mainNav" aria-expanded="false" aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <div id="mainNav" class="collapse navbar-collapse tm-bg-white">
                                <ul class="navbar-nav ml-auto">
                                    <li class="nav-item">
                                        <a class="nav-link active" href="#top">Home <span
                                                class="sr-only">(current)</span></a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#tm-section-2">Top Category</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#tm-section-3" id="tm-section">Rent</a>
                                    </li>
                                    <c:if test="${empty sessionScope.USER}">
                                        <li class="nav-item loginform">
                                            <button class="btn btn-primary" data-toggle="modal" data-target="#loginModal">
                                                Login</button>
                                            <!-- LoginForm Modal -->
                                            <div class="modal fade login" id="loginModal">
                                                <div class="modal-dialog login animated">
                                                    <div class="modal-content">

                                                        <div class="modal-body">
                                                            <div class="form-structor">
                                                                <div class="signup">
                                                                    <h2 class="form-title" id="signup"><span>or</span>Log in</h2>
                                                                    <form action="login" method="post">
                                                                        <div class="form-holder">
                                                                            <input name="txtUsername" value="${requestScope.TXTUSER}" class="input" placeholder="User ID or Email" />
                                                                            <input name="txtPassword" type="password" class="input" placeholder="Password" />
                                                                        </div>
                                                                        <h5 id="INVALID">${requestScope.ID_INVALID}</h5>
                                                                        <div class="g-recaptcha" data-sitekey="6LfXLmIaAAAAAKHSr2vKlsgT1UBxwp1LMJ067vRK"></div>
                                                                        <button type="submit"  class="submit-btn">Log in</button>
                                                                        <h5 id="SUCCESS">${requestScope.SHOW}</h5> 

                                                                    </form>
                                                                </div>
                                                                <div class="login slide-up">
                                                                    <div class="center">
                                                                        <h2 class="form-title" id="login"><span>or</span>Sign up</h2>
                                                                        <form action="signup" method="post">
                                                                            <c:set var="errors" value="${requestScope.INSERTERROR}"/>
                                                                            <div class="form-holder">
                                                                                <input required name="txtUsername" value="${requestScope.TXTUSER_SIGNUP}" type="email" class="input" placeholder="Email" />
                                                                                <c:if test="${not empty errors.usernameLengthError}"><h6 id="INVALID">${errors.usernameLengthError}</h6></c:if>
                                                                                    <input required name="txtFullName" type="text" class="input" placeholder="Full Name" />
                                                                                <c:if test="${not empty errors.fullnameLengthError}"><h6 id="INVALID">${errors.fullnameLengthError}</h6></c:if>
                                                                                    <input required name="txtPassword" type="password" class="input" placeholder="Password" />
                                                                                <c:if test="${not empty errors.passwordLengthError}"><h6 id="INVALID">${errors.passwordLengthError}</h6></c:if>
                                                                                    <input required name="txtPhone" type="text" class="input" placeholder="Phone" maxlength="10" minlength="10" />
                                                                                    <input required name="txtAddress" type="text" class="input" placeholder="Address" />
                                                                                </div>
                                                                                <button type="submit" value="login" name="btAction" class="submit-btn">Sign up</button>
                                                                            <c:if test="${not empty errors.usernameIsExist}"><h6 id="INVALID">${errors.usernameIsExist}</h6></c:if>
                                                                            </form>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>

                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- LoginForm Modal -->
                                            </li>
                                    </c:if>
                                    <c:if test="${not empty sessionScope.USER}">
                                        <c:if test="${sessionScope.USER.isAdmin eq false}">
                                            <li class="nav-item">
                                                <a  id="edit"class="nav-link waves-effect" data-toggle="modal" data-target="#cartModal">
                                                    <i class="fas fa-shopping-cart"></i>
                                                    <span class="clearfix d-none d-sm-inline-block"> Cart </span>
                                                </a>
                                                <!-- CartForm Modal -->
                                                <div class="modal fade cart" id="cartModal">
                                                    <div class="modal-dialog cart animated">
                                                        <div class="modal-content cart">

                                                            <div class="modal-body ">

                                                                <div class="container">
                                                                    <div class="row">
                                                                        <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

                                                                            <!-- Shopping cart table -->
                                                                            <div class="table-responsive">
                                                                                <table class="table">
                                                                                    <thead>
                                                                                        <tr>
                                                                                            <th scope="col" class="border-0 bg-light">
                                                                                                <div class="p-2 px-3 text-uppercase">Product</div>
                                                                                            </th>
                                                                                            <th scope="col" class="border-0 bg-light">
                                                                                                <div class="py-2 text-uppercase">Rental Date</div>
                                                                                            </th>
                                                                                            <th scope="col" class="border-0 bg-light">
                                                                                                <div class="py-2 text-uppercase">Return Date</div>
                                                                                            </th>
                                                                                            <th scope="col" class="border-0 bg-light">
                                                                                                <div class="py-2 text-uppercase">Price</div>
                                                                                            </th>
                                                                                            <th scope="col" class="border-0 bg-light">
                                                                                                <div class="py-2 text-uppercase">Quantity</div>
                                                                                            </th>
                                                                                            <th scope="col" class="border-0 bg-light">
                                                                                                <div class="py-2 text-uppercase">Edit</div>
                                                                                            </th>
                                                                                        </tr>
                                                                                    </thead>
                                                                                    <tbody>
                                                                                        <c:set var="list" value="${sessionScope.CARTCUSTOMER.getCartProduct()}" />
                                                                                        <c:set var="total" value="0"/>
                                                                                        <c:forEach var="dto" items="${list}" >

                                                                                            <tr>
                                                                                                <th scope="row" class="border-0">
                                                                                                    <div class="p-2">

                                                                                                        <img src="${dto.value.productImage}" alt="" width="70" class="img-fluid rounded shadow-sm">
                                                                                                        <div class="ml-3 d-inline-block align-middle">
                                                                                                            <h5 class="mb-0"> <a href="#" class="text-dark d-inline-block align-middle">${dto.value.productName}</a></h5><span class="text-muted font-weight-normal font-italic d-block">${dto.value.category}</span>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </th>

                                                                                        <form action="updateProduct"> 
                                                                                            <td class="border-0 align-middle">${dto.value.rentalDate}</td>
                                                                                            <td class="border-0 align-middle">${dto.value.returnDate}</td>
                                                                                            <td class="border-0 align-middle"><strong>$ ${dto.value.productPrice} (x ${dto.value.days} Day)</strong></td>
                                                                                            <fmt:parseNumber var="days" value="${dto.value.days}" integerOnly="true"/>
                                                                                            <c:set var="total" value="${dto.value.productPrice*dto.value.quancart*days+total}"/>
                                                                                            <td class="border-0 align-middle"><strong> <input onkeydown="return false" type="number" min="0"  max="${dto.value.quantity}" name="quanCart" value="${dto.value.quancart}" /> </strong></td>
                                                                                            <td class="border-0 align-middle"> 
                                                                                                <input type="hidden" name="productID" value="${dto.value.productID}" />
                                                                                                <button id="updateCart" type="submit" class="text-dark"><i class="fa fa-edit"></i></button> 
                                                                                        </form>
                                                                                        <form action="deleteProduct" onsubmit="return confirm('Are you sure to delete?')">
                                                                                            <input type="hidden" name="productID" value="${dto.value.productID}" />

                                                                                            <button type="submit" class="text-dark"><i class="fa fa-trash"></i></button>
                                                                                        </form></td>
                                                                                        </tr>
                                                                                        </form>
                                                                                    </c:forEach>

                                                                                    </tbody>
                                                                                </table>
                                                                            </div>
                                                                            <!-- End -->
                                                                        </div>
                                                                    </div>

                                                                    <div class="row py-5 p-4 bg-white rounded shadow-sm">
                                                                        <div class="col-lg-6">
                                                                            <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Coupon code</div>
                                                                            <div class="p-4">
                                                                                <p class="font-italic mb-4">If you have a coupon code, please enter it in the box below</p>
                                                                                <div class="input-group mb-4 border rounded-pill p-2">
                                                                                    <c:set value="0" var="valueDiscount" />
                                                                                    <form action="discount">
                                                                                        <input type="text" name="txtDiscountCode" value="${param.discountCode}" placeholder="Apply coupon" aria-describedby="button-addon3" class="form-control border-0">

                                                                                        <c:set value="${sessionScope.discountValue}" var="map" />
                                                                                        <c:if test="${not empty map}">
                                                                                            <c:set value="${map}" var="valueDiscount" />
                                                                                            <input type="hidden" name="valueDiscount" value="${valueDiscount}" />
                                                                                        </c:if>
                                                                                        <div class="input-group-append border-0">
                                                                                            <button id="button-addon3" type="submit" class="btn btn-dark px-4 rounded-pill"><i class="fa fa-gift mr-2"></i>Apply coupon</button>
                                                                                        </div>
                                                                                    </form>

                                                                                </div>
                                                                            </div>
                                                                            <div class=" px-4 py-3 text-uppercase "></div>
                                                                            <div class="p-4">

                                                                            </div>
                                                                        </div>
                                                                        <div class="col-lg-6">
                                                                            <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Order summary </div>
                                                                            <div class="p-4">
                                                                                <form action="checkout">
                                                                                    <ul class="list-unstyled mb-4">
                                                                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Order Subtotal </strong><strong>$ ${total}</strong></li>
                                                                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Discount</strong><strong>- $ ${valueDiscount}</strong></li>
                                                                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total</strong>
                                                                                            <c:if test="${total - valueDiscount<0}">
                                                                                                <h5 class="font-weight-bold">$ 0</h5>
                                                                                            </c:if>
                                                                                            <input type="hidden" name="userBuyProduct" value="${sessionScope.USER.username}" />
                                                                                            <input type="hidden" name="totalPrice" value="${total - valueDiscount}" />
                                                                                            <c:if test="${total - valueDiscount>0}">
                                                                                                <h5 class="font-weight-bold">$ ${total - valueDiscount}</h5>
                                                                                            </c:if>

                                                                                        </li>
                                                                                    </ul><button type="submit" class="btn btn-dark rounded-pill py-2 btn-block">Procceed to checkout</button>
                                                                                </form>
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                </div>


                                                            </div>

                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- CartForm Modal -->
                                            </li>
                                        </c:if>
                                        <li class="nav-item user">
                                            <c:set var="currentUserID" value="${sessionScope.USER.username}" />
                                        <li class="nav-item dropdown">
                                            <a class="nav-link dropdown-toggle" id="dropdownId" data-toggle="dropdown"
                                               aria-haspopup="true" aria-expanded="false"> <i class="fas fa-user    "></i>
                                                Welcome, ${sessionScope.USER.fullName}</a>

                                            <div class="dropdown-menu" aria-labelledby="dropdownId">
                                                <c:if test="${sessionScope.USER.isAdmin eq true}">
                                                </c:if>
                                                <c:if test="${sessionScope.USER.isAdmin eq false}">
                                                    <a class="dropdown-item" href="orderHistory" data-toggle="modal" data-target="#orderHistoryModal">Order history</a>
                                                </c:if>
                                                <form action="logout" >
                                                    <input class="dropdown-item" type="submit" value="Sign Out" />
                                                </form>

                                            </div>
                                        </li>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>
                        </nav>
                    </div> <!-- row -->
                </div> <!-- container -->
            </div> <!-- .tm-top-bar -->



            <!--orderHistoryForm Modal -->
            <div class="modal fade cart" id="orderHistoryModal">
                <div class="modal-dialog cart animated">
                    <div class="modal-content cart">

                        <div class="modal-body orderr">
                            <section class="p-5 tm-container-outer titleOrder">
                                <div class="container">
                                    <div class="row">
                                        <div class="col-xs-12 mx-auto tm-about-text-wrap text-center">
                                            <h2 class="text-uppercase mb-4">ORDERS HISTORY</h2>
                                        </div>
                                    </div>
                                </div>
                            </section>
                            <div class="container">
                                <div class="row">
                                    <form action="searchNameOrder">
                                    <div class="col search1">

                                        <div class="form-group">
                                            <label for="">Search By Name: </label>
                                            <input type="text" class="form-control" name="searchName_Order" id="" aria-describedby="helpId" placeholder="">
                                        </div>
                                        <button type="submit" class="btn btn-primary">Search</button>

                                    </div>
                                    </form>
                                    <form action="searchDateOrder">
                                    <div class="col search2">

                                        <div class="form-group">
                                            <label for="">Search By Order Date: </label>
                                            <input class="datepicker" id="depart" name="searchDate_Order" value="${requestScope.searchDate}" type="text" placeholder="2021-03-01" />
                                        </div>
                                        <button type="submit" class="btn btn-primary">Search</button>

                                    </div>
                                    </form>
                                </div>
                            </div>
                            <div class="container">
                                <div class="row">
                                    <div class="col">
                                        <c:if test="${sessionScope.LISTORDERS.size() == 0}">
                                            <p class="noOrder"><strong id="caption">No order history!!!</strong></p>
                                        </c:if>
                                        <c:if test="${sessionScope.LISTORDERS.size() > 0}">
                                            <c:forEach var="listOrders" items="${sessionScope.LISTORDERS}">
                                                <form action="deleteOrderHistory" onsubmit="return confirm('Are you sure to delete?')">
                                                    <input type="hidden" name="orderID" value="${listOrders.id}" />
                                                    <div class="orderHistory">
                                                        <div class="container ">
                                                            <div class="row">
                                                                <div class="col">
                                                                    <p><Strong id="caption">Date: </Strong>  ${listOrders.dateOrder}</p>
                                                                </div>
                                                                <c:set var="totalDiscounts" value="0"/>
                                                                <c:forEach var="Discount" items="${sessionScope.LISTDISCOUNTSDETAIL}">
                                                                    <c:if test="${Discount.key==listOrders.id}">
                                                                        <c:forEach var="discountList" items="${Discount.value}">
                                                                            <c:set var="totalDiscounts" value="${totalDiscounts+discountList.value}"/>
                                                                        </c:forEach>
                                                                    </c:if>
                                                                </c:forEach>
                                                                <div class="col">
                                                                    <p><Strong id="caption">Discount: </Strong>   $ ${totalDiscounts}</p>
                                                                </div>
                                                                <div class="col">
                                                                    <p><Strong id="caption">Total Price: </Strong>  ${listOrders.totalPrice}</p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="container">
                                                            <div class="row">
                                                                <div class="col">
                                                                    <c:forEach var="cars" items="${sessionScope.LISTORDERSDETAIL}">
                                                                        <c:if test="${listOrders.id==cars.key}">

                                                                            <c:forEach var="carsList" items="${cars.value}">
                                                                                <div class="list-groupp">
                                                                                    <div class="tm-recommended-place carHistory">
                                                                                        <img src="${carsList.image}" alt="Image" id="order1" class="img-fluid tm-recommended-img">
                                                                                        <div class="tm-recommended-description-box">

                                                                                            <h3 class="tm-recommended-title" id="order2"><strong>Name: </strong>   ${carsList.nameCar}</h3>
                                                                                            <p class="tm-category-highlight" id="order2"><strong>Category: </strong>   ${carsList.nameCategory}</p>
                                                                                            <p class="tm-text-gray" id="order2"><strong>Rental Date: </strong>   ${carsList.rentalDate}</p>
                                                                                            <p class="tm-text-gray" id="order2"><strong>Return Date: </strong>   ${carsList.returnDate}</p>
                                                                                            <p class="tm-text-gray" id="order2"><strong>Amount: </strong>   ${carsList.quantity}</p>
                                                                                            <p class="tm-text-gray" id="order2"><strong>Price: </strong>   ${carsList.price}</p>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                            <button type="submit" class="btn btn-primary orderrr">Delete</button>

                                                                        </c:if>


                                                                    </c:forEach>

                                                                </div>
                                                            </div>
                                                        </div>    



                                                    </div>
                                                </form>
                                            </c:forEach>
                                        </c:if>


                                    </div>
                                </div>
                            </div>


                        </div>

                    </div>
                </div>
            </div>
            <!--orderHistoryForm Modal -->



            <div class="tm-page-wrap mx-auto">
                <section class="tm-banner">
                    <div class="tm-container-outer tm-banner-bg">
                        <div class="container">

                            <div class="row tm-banner-row tm-banner-row-header">
                                <div class="col-xs-12">
                                    <div class="tm-banner-header">
                                        <h1 class="text-uppercase tm-banner-title">Let's begin</h1>
                                        <img src="https://i.ibb.co/GkLtbDg/dots-3.png" alt="Dots">
                                        <c:if test="${not empty sessionScope.VERIFY}">
                                            <form action="checkMail">
                                                <div class="form-group">
                                                    <label for="">Please verify email: </label>
                                                    <input type="text" class="form-control" name="txtMailCode" id="" aria-describedby="helpId" placeholder="">
                                                    <small id="helpId" class="form-text text-muted">Get code in your email!</small>
                                                    <h6 id="INVALID">${requestScope.FAILVERIFY}</h6>
                                                    <button type="submit" class="btn btn-primary">Check</button>
                                                </div>
                                            </form>
                                        </c:if>
                                        <p class="tm-banner-subtitle">We assist you to choose the best.</p>
                                        <a href="javascript:void(0)" class="tm-down-arrow-link"><i
                                                class="fa fa-2x fa-angle-down tm-down-arrow"></i></a>
                                    </div>
                                </div> <!-- col-xs-12 -->
                            </div> <!-- row -->
                            <div class="row tm-banner-row" id="tm-section-search">

                                <div class="s002">
                                    <form>
                                        <div class="inner-form">
                                            <div class="input-field first-wrap">
                                                <div class="icon-wrap">
                                                    <i class="fa fa-car" aria-hidden="true"></i>
                                                </div>
                                                <input id="search" type="text" placeholder="Car Name?" />
                                            </div>

                                            <div class="input-field second-wrap">
                                                <div class="icon-wrap">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                                                    <path d="M17 12h-5v5h5v-5zM16 1v2H8V1H6v2H5c-1.11 0-1.99.9-1.99 2L3 19c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2h-1V1h-2zm3 18H5V8h14v11z"></path>
                                                    </svg>
                                                </div>
                                                <input class="datepicker" id="depart" type="text" placeholder="2021-03-01" />
                                            </div>
                                            <div class="input-field third-wrap">
                                                <div class="icon-wrap">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                                                    <path d="M17 12h-5v5h5v-5zM16 1v2H8V1H6v2H5c-1.11 0-1.99.9-1.99 2L3 19c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2h-1V1h-2zm3 18H5V8h14v11z"></path>
                                                    </svg>
                                                </div>
                                                <input class="datepicker" id="return" type="text" placeholder="2021-03-02" />
                                            </div>
                                            <div class="input-field fouth-wrap">
                                                <div class="icon-wrap">
                                                    <i class="fa fa-list-alt" aria-hidden="true"></i>
                                                </div>
                                                <select data-trigger="" name="choices-single-defaul">
                                                    <option placeholder="">Category </option>
                                                    <option>Luxury</option>

                                                </select>
                                            </div>
                                            <div class="input-field last-wrap">
                                                <div class="icon-wrap">
                                                    <i class="fa fa-caret-square-o-right" aria-hidden="true"></i>
                                                </div>
                                                <input id="search" type="number" placeholder="Car Number?" />
                                            </div>
                                            <div class="input-field fifth-wrap">
                                                <button class="btn-search" type="button">SEARCH</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>

                            </div> <!-- row -->
                            <div class="tm-banner-overlay"></div>
                        </div> <!-- .container -->
                    </div> <!-- .tm-container-outer -->
                </section>

                <section class="p-5 tm-container-outer tm-bg-gray">
                    <div class="container">
                        <div class="row">
                            <div class="col-xs-12 mx-auto tm-about-text-wrap text-center">
                                <h2 class="text-uppercase mb-4">TODAY’S <strong>ALTERNATIVE</strong> TO LEASING </h2>
                                <p class="mb-4">Our car subscription service is the solution for those who want more flexibility than you get with car leasing. 
                                    Instead of investing money in new cars that go down in value, save your money for vacations or other expenses.</p>
                            </div>
                        </div>
                    </div>
                </section>

                <div class="tm-container-outer" id="tm-section-2">
                    <section class="tm-slideshow-section">
                        <div class="tm-slideshow">
                            <img src="https://cars.usnews.com/static/images/Auto/izmo/i159614257/2021_acura_ilx_angularfront.jpg" alt="Image">
                            <img src="https://cars.usnews.com/static/images/Auto/izmo/i159614387/2021_alfa_romeo_stelvio_angularfront.jpg" alt="Image">
                            <img src="https://cars.usnews.com/static/images/Auto/izmo/i159614315/2021_audi_a5_coupe_angularfront.jpg" alt="Image">
                        </div>
                        <div class="tm-slideshow-description tm-bg-primary">
                            <h2 class="">LUXURY CAR</h2>
                            <p>A luxury car or a luxury car is a vehicle that aims to provide passengers with greater comfort, 
                                higher equipment levels and perceived quality than conventional cars at a more expensive price.</p>
                            <a href="#" class="text-uppercase tm-btn tm-btn-white tm-btn-white-primary">Rent</a>
                        </div>
                    </section>
                    <section class="clearfix tm-slideshow-section tm-slideshow-section-reverse">

                        <div class="tm-right tm-slideshow tm-slideshow-highlight">
                            <img src="https://cars.usnews.com/static/images/Auto/izmo/i159614367/2021_honda_accord_sedan_angularfront.jpg" alt="Image">
                            <img src="https://cars.usnews.com/static/images/Auto/izmo/i159614077/2021_honda_clarity_angularfront.jpg" alt="Image">
                            <img src="https://cars.usnews.com/static/images/Auto/izmo/i159614250/2021_hyundai_sonata_hybrid_angularfront.jpg" alt="Image">
                        </div>

                        <div class="tm-slideshow-description tm-slideshow-description-left tm-bg-highlight">
                            <h2 class="">INTERMEDIATE CAR</h2>
                            <p>An intermediate car rental provides some more room for passengers and luggage than most smaller cars. 
                                Reserve now and get low rates on an intermediate car rental from Enterprise Rent-A-Car.</p>
                            <a href="#" class="text-uppercase tm-btn tm-btn-white tm-btn-white-highlight">Rent</a>
                        </div>

                    </section>
                    <section class="tm-slideshow-section">
                        <div class="tm-slideshow">
                            <img src="https://cars.usnews.com/static/images/Auto/izmo/i159614324/2021_mazda_mazda_3_angularfront.jpg" alt="Image">
                            <img src="https://cars.usnews.com/static/images/Auto/izmo/i159614281/2021_honda_civic_sedan_angularfront.jpg" alt="Image">
                            <img src="https://cars.usnews.com/static/images/Auto/izmo/i159614227/2021_kia_forte_angularfront.jpg" alt="Image">
                        </div>
                        <div class="tm-slideshow-description tm-bg-primary">
                            <h2 class="">COMPACT CAR</h2>
                            <p> A compact car is a kind of vehicle size - mainly used in North America - between a subcompact and a midsize car. </p>
                            <a href="#" class="text-uppercase tm-btn tm-btn-white tm-btn-white-primary">Rent</a>
                        </div>
                    </section>
                </div>
                <div class="tm-container-outer" id="tm-section-3">
                    <ul class="nav nav-pills tm-tabs-links">
                        <li class="tm-tab-link-li" >
                            <a href="#aa" data-toggle="tab" class="tm-tab-link all" id="showAll">
                                SHOW ALL
                            </a>
                        </li>
                        <li class="tm-tab-link-li">
                            <a href="#aa" data-toggle="tab" class="tm-tab-link">
                                <img src="https://cars.usnews.com/static/images/Auto/izmo/i159614382/2021_mercedes_benz_e_class_sedan_angularfront.jpg" alt="Image" class="img-fluid">
                                LUXURY CAR
                            </a>
                        </li>
                        <li class="tm-tab-link-li">
                            <a href="#aa" data-toggle="tab" class="tm-tab-link">
                                <img src="https://cars.usnews.com/static/images/Auto/izmo/i159613939/2020_dodge_grand_caravan_angularfront.jpg" alt="Image" class="img-fluid">
                                INTERMEDIATE CAR
                            </a>
                        </li>
                        <li class="tm-tab-link-li">
                            <a href="#aa" data-toggle="tab" class="tm-tab-link">
                                <img src="https://cars.usnews.com/static/images/Auto/izmo/i159614115/2021_bmw_2_series_angularfront.jpg" alt="Image" class="img-fluid">
                                COMPACT CAR
                            </a>
                        </li>

                    </ul>
                    <div class="tab-content clearfix">

                        <!-- Tab product-->
                        <div class="tab-pane fade" id="aa">
                            <div class="tm-recommended-place-wrap">

                                <c:set var="listItems" value="${sessionScope.ITEMSLIST}" />

                                <c:if test="${sessionScope.USER.isAdmin eq false || empty sessionScope.USER}">
                                    <c:if test="${not empty listItems}">


                                        <div class="s002">
                                            <form action="search">
                                                <div class="inner-form">
                                                    <div class="input-field first-wrap">
                                                        <div class="icon-wrap">
                                                            <i class="fa fa-car" aria-hidden="true"></i>
                                                        </div>
                                                        <input id="search" type="text" name="searchValueName" value="${requestScope.searchValueName}" placeholder="Car Name?" />
                                                    </div>

                                                    <div class="input-field second-wrap">
                                                        <div class="icon-wrap">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                                                            <path d="M17 12h-5v5h5v-5zM16 1v2H8V1H6v2H5c-1.11 0-1.99.9-1.99 2L3 19c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2h-1V1h-2zm3 18H5V8h14v11z"></path>
                                                            </svg>
                                                        </div>
                                                        <input class="datepicker" id="depart" name="rentalDate" value="${requestScope.rentalDate}" type="text" placeholder="2021-03-01" />
                                                    </div>
                                                    <div class="input-field third-wrap">
                                                        <div class="icon-wrap">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                                                            <path d="M17 12h-5v5h5v-5zM16 1v2H8V1H6v2H5c-1.11 0-1.99.9-1.99 2L3 19c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2h-1V1h-2zm3 18H5V8h14v11z"></path>
                                                            </svg>
                                                        </div>
                                                        <input class="datepicker" id="return"   name="returnDate" value="${requestScope.returnDate}" type="text" placeholder="2021-03-02" />
                                                    </div>
                                                    <div class="input-field fouth-wrap">
                                                        <div class="icon-wrap">
                                                            <i class="fa fa-list-alt" aria-hidden="true"></i>
                                                        </div>
                                                        <select data-trigger="" name="categorySearchValue">
                                                            <option placeholder="">Category </option>
                                                        </select>
                                                    </div>
                                                    <div class="input-field last-wrap">
                                                        <div class="icon-wrap">
                                                            <i class="fa fa-caret-square-o-right" aria-hidden="true"></i>
                                                        </div>
                                                        <input id="search" name="searchValueQuantity" value="${requestScope.searchValueQuantity}" type="number" placeholder="Car Number?" />
                                                    </div>
                                                    <div class="input-field fifth-wrap">
                                                        <button class="btn-search" type="submit">SEARCH</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                        <!-- Paging -->
                                        <nav aria-label="Page navigation">
                                            <ul class="pagination">
                                            </ul>
                                        </nav>
                                        <div id="loopp">
                                            <!-- end paging -->
                                            <c:forEach var="dto" items="${listItems}" varStatus="counter">
                                                <div class="list-groupp">
                                                    <form action="AddProduct">
                                                        <input type="hidden" name="userBuyProduct" value="${sessionScope.USER.username}" />
                                                        <input type="hidden" name="btActionSearch" value="${requestScope.ACTION}" />               
                                                        <input type="hidden" name="searchValueQuantity"value="${param.searchValueQuantity}" />
                                                        <input type="hidden" name="searchValueName" value="${param.searchValueName}" />
                                                        <input type="hidden" name="rentalDate" value="${param.rentalDate}" />
                                                        <input type="hidden" name="returnDate" value="${param.returnDate}" />
                                                        <input type="hidden" name="categorySearchValue" value="${param.categorySearchValue}" />

                                                        <!-- product -->
                                                        <div class="tm-recommended-place">
                                                            <img src="${dto.productImage}" alt="Image" class="img-fluid tm-recommended-img">
                                                            <input type="hidden" name="image" value="${dto.productImage}" />
                                                            <input type="hidden" name="productID" value="${dto.productID}" />
                                                            <input type="hidden" name="productName" value="${dto.productName}" />
                                                            <input type="hidden" name="productCategory" value="${dto.category}" />
                                                            <input type="hidden" name="quantity" value="${dto.quantity}" />
                                                            <div class="tm-recommended-description-box">
                                                                <div class="container">
                                                                    <div class="row">
                                                                        <div class="col picker1">
                                                                            <h3 class="tm-recommended-title">${dto.productName}</h3>

                                                                            <p class="tm-category-highlight">${dto.category}</p>

                                                                            <p class="tm-text-gray">Color: ${dto.productColor}</p>
                                                                            <p class="tm-text-gray">Year: ${dto.year}</p>
                                                                            <p class="tm-text-gray">Quantity: ${dto.quantity}</p>
                                                                        </div>
                                                                        <div class="col picker2">
                                                                            <p><strong>Rental Date: </strong></p>
                                                                            <div class="input-field second-wrap" id="rentalPickDate">


                                                                                <input class="datepicker" id="rentalPickDate2" name="rentalDay" value="2021-03-01" type="text" placeholder="2021-03-01" />
                                                                            </div>
                                                                            <p><strong>Return Date: </strong></p>
                                                                            <div class="input-field third-wrap"id="returnPickDate">


                                                                                <input class="datepicker" id="returnPickDate2"   name="returnDay" value="2021-03-02" type="text" placeholder="2021-03-02" />
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>


                                                            </div>
                                                            <c:if test="${not empty sessionScope.USER}">
                                                                <button type="submit" class="tm-recommended-price-box">
                                                                    <p class="tm-recommended-price">$ ${dto.productPrice}</p>
                                                                    <input type="hidden" name="productPrice" id="productPrice1" value="${dto.productPrice}" />
                                                                    <p class="tm-recommended-price-link">+ Add to cart</p>
                                                                </button>

                                                            </c:if>

                                                            <c:if test="${empty sessionScope.USER}">
                                                                <a data-toggle="modal" data-target="#loginModal" class="tm-recommended-price-box">
                                                                    <p class="tm-recommended-price">$ ${dto.productPrice} /Day</p>
                                                                    <p class="tm-recommended-price-link">+ Add to cart</p>
                                                                </a>

                                                            </c:if>
                                                        </div>
                                                    </form>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </c:if>
                                    <c:if test="${empty listItems && empty requestScope.ERROR}">
                                        <h1 class="error-list" style="color:red">List Products Are Empty!!!</h1>
                                    </c:if>
                                </c:if>
                                <c:if test="${sessionScope.USER.isAdmin eq true }">
                                    <c:if test="${not empty listItems}">


                                        <div class="s002">
                                            <form action="search">
                                                <div class="inner-form">
                                                    <div class="input-field first-wrap">
                                                        <div class="icon-wrap">
                                                            <i class="fa fa-car" aria-hidden="true"></i>
                                                        </div>
                                                        <input id="search" type="text" name="searchValueName" value="${param.searchValueName}" placeholder="Car Name?" />
                                                    </div>

                                                    <div class="input-field second-wrap">
                                                        <div class="icon-wrap">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                                                            <path d="M17 12h-5v5h5v-5zM16 1v2H8V1H6v2H5c-1.11 0-1.99.9-1.99 2L3 19c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2h-1V1h-2zm3 18H5V8h14v11z"></path>
                                                            </svg>
                                                        </div>
                                                        <input class="datepicker" id="depart" name="rentalDate" value="${param.rentalDate}" type="text" placeholder="2021-03-01" />
                                                    </div>
                                                    <div class="input-field third-wrap">
                                                        <div class="icon-wrap">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                                                            <path d="M17 12h-5v5h5v-5zM16 1v2H8V1H6v2H5c-1.11 0-1.99.9-1.99 2L3 19c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2h-1V1h-2zm3 18H5V8h14v11z"></path>
                                                            </svg>
                                                        </div>
                                                        <input class="datepicker" id="return"   name="returnDate" value="${param.returnDate}" type="text" placeholder="2021-03-02" />
                                                    </div>
                                                    <div class="input-field fouth-wrap">
                                                        <div class="icon-wrap">
                                                            <i class="fa fa-list-alt" aria-hidden="true"></i>
                                                        </div>
                                                        <select data-trigger="" name="categorySearchValue">
                                                            <option placeholder="">Category </option>
                                                            <option>Luxury</option>
                                                        </select>
                                                    </div>
                                                    <div class="input-field last-wrap">
                                                        <div class="icon-wrap">
                                                            <i class="fa fa-caret-square-o-right" aria-hidden="true"></i>
                                                        </div>
                                                        <input id="search" name="searchValueQuantity" value="${param.searchValueQuantity}" type="number" placeholder="Car Number?" />
                                                    </div>
                                                    <div class="input-field fifth-wrap">
                                                        <button class="btn-search" type="submit">SEARCH</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                        <!-- Paging -->
                                        <nav aria-label="Page navigation">
                                            <ul class="pagination">
                                            </ul>
                                        </nav>
                                        <div id="loopp">
                                            <!-- end paging -->
                                            <c:forEach var="dto" items="${listItems}">
                                                <div class="list-groupp">
                                                    <!-- product -->
                                                    <div class="tm-recommended-place">
                                                        <img src="${dto.productImage}" alt="Image" class="img-fluid tm-recommended-img">
                                                        <div class="tm-recommended-description-box">
                                                            <h3 class="tm-recommended-title">${dto.productName}</h3>
                                                            <p class="tm-category-highlight">${dto.category}</p>
                                                            <p class="tm-text-gray">Color: ${dto.productColor}</p>
                                                            <p class="tm-text-gray">Year: ${dto.year}</p>
                                                            <p class="tm-text-gray">Quantity: ${dto.quantity}</p>
                                                            <p class="tm-text-gray">Status: ${dto.status}</p>
                                                        </div>
                                                        <a href="javascript:void(0)" class="tm-recommended-price-box">
                                                            <p class="tm-recommended-price">$ ${dto.productPrice} /Day</p>
                                                        </a>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </c:if>
                                    <c:if test="${empty listItems && empty requestScope.ERROR}">
                                        <h1 class="error-list" style="color:red"> <strong> List Products Are Empty!!!</strong></h1>
                                    </c:if>
                                </c:if>


                            </div>
                        </div> <!-- tab-pane -->
                    </div>
                </div>

                <!-- .tm-container-outer -->


                <footer class="tm-container-outer">
                    <p class="mb-0">Copyright © <span class="tm-current-year">2018</span> Tks for <a rel="nofollow"
                                                                                                     href="http://www.google.com/+templatemo" target="_parent">Template Free</a></p>
                </footer>
            </div>
        </div> <!-- .main-content -->

        <!-- load JS files -->
        <script src="js/jquery-1.11.3.min.js"></script> <!-- jQuery (https://jquery.com/download/) -->

        <script src="js/popper.min.js"></script> <!-- https://popper.js.org/ -->
        <script src="js/bootstrap.min.js"></script> <!-- https://getbootstrap.com/ -->
        <script src="js/datepicker.min.js"></script> <!-- https://github.com/qodesmith/datepicker -->
        <script src="js/jquery.singlePageNav.min.js"></script>
        <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
        <script src="slick/slick.min.js"></script> <!-- http://kenwheeler.github.io/slick/ -->
        <script src="js/jquery.scrollTo.min.js"></script> <!-- https://github.com/flesler/jquery.scrollTo -->

        <script src="https://www.google.com/recaptcha/api.js"></script>

        <script>

                                                    'use strict';
                                                    var numberOfItems = $("#loopp .list-groupp").length;
                                                    var limitPerPage = 5;
                                                    $("#loopp .list-groupp:gt(" + (limitPerPage - 1) + ")").hide();
                                                    var totalPages = Math.ceil(numberOfItems / limitPerPage);
                                                    $(".pagination").append("<li class='page-item disabled' id='previous-page'><a class='page-link' href='javascript:void(0)' aria-label='Previous'><span aria-hidden='true'>&laquo;</span><span class='sr-only'>Previous</span></a></li>");

                                                    $(".pagination").append("<li class='current-page active'><a class='page-link' href='javascript:void(0)'>" + 1 + "</a></li>");

                                                    for (var i = 2; i <= totalPages; i++) {
                                                        $(".pagination").append("<li class='current-page '><a class='page-link' href='javascript:void(0)'>" + i + "</a></li>");
                                                    }

                                                    $(".pagination").append("<li class='page-item' id='next-page'><a class='page-link' href='javascript:void(0)' aria-label='Next'><span aria-hidden='true'>&raquo;</span><span class='sr-only'>Next</span></a></li>");

                                                    $(".pagination li.current-page").on("click", function () {
                                                        if ($(this).index === 1)
                                                            $("#previous-page").addClass("disabled");
                                                        else
                                                            $("#previous-page").removeClass("disabled");

                                                        if ($(this).hasClass("active")) {
                                                            return false;
                                                        } else {
                                                            var currentPage = $(this).index();
                                                            //active
                                                            $(".pagination li").removeClass("active");
                                                            $(this).addClass("active");
                                                            //paging
                                                            $("#loopp .list-groupp").hide();
                                                            var grandTotal = limitPerPage * currentPage;
                                                            for (var i = grandTotal - limitPerPage; i < grandTotal; i++) {
                                                                $("#loopp .list-groupp:eq(" + i + ")").show();
                                                            }
                                                        }


                                                    });

                                                    $("#next-page").on("click", function () {
                                                        var currentPage = $(".pagination li.active").index();
                                                        if (currentPage + 1 === 1)
                                                            $("#previous-page").addClass("disabled");

                                                        else
                                                            $("#previous-page").removeClass("disabled");
                                                        if (currentPage === totalPages) {
                                                            return false;
                                                        } else {
                                                            currentPage++;
                                                            $(".pagination li").removeClass("active");
                                                            $("#loopp .list-groupp").hide();
                                                            var grandTotal = limitPerPage * currentPage;
                                                            for (var i = grandTotal - limitPerPage; i < grandTotal; i++) {
                                                                $("#loopp .list-groupp:eq(" + i + ")").show();
                                                            }
                                                            $(".pagination li.current-page:eq(" + (currentPage - 1) + ")").addClass("active");//index in array start from 0
                                                        }
                                                    });

                                                    $("#previous-page").on("click", function () {
                                                        var currentPage = $(".pagination li.active").index();
                                                        if (currentPage - 1 === 1)
                                                            $("#previous-page").addClass("disabled");

                                                        else
                                                            $("#previous-page").removeClass("disabled");
                                                        if (currentPage === 1) {
                                                            return false;
                                                        } else {
                                                            currentPage--;
                                                            $(".pagination li").removeClass("active");
                                                            $("#loopp .list-groupp").hide();
                                                            var grandTotal = limitPerPage * currentPage;
                                                            for (var i = grandTotal - limitPerPage; i < grandTotal; i++) {
                                                                $("#loopp .list-groupp:eq(" + i + ")").show();
                                                            }
                                                            $(".pagination li.current-page:eq(" + (currentPage - 1) + ")").addClass("active");//index in array start from 0
                                                        }
                                                    });

                                                    /* DOM is ready
                                                     ------------------------------------------------*/
                                                    $(function () {

                                                        // Change top navbar on scroll
                                                        $(window).on("scroll", function () {
                                                            if ($(window).scrollTop() > 100) {
                                                                $(".tm-top-bar").addClass("active");
                                                            } else {
                                                                $(".tm-top-bar").removeClass("active");
                                                            }
                                                        });

                                                        // Smooth scroll to search form
                                                        $('.tm-down-arrow-link').click(function () {
                                                            $.scrollTo('#tm-section-search', 300, {easing: 'linear'});
                                                        });


                                                        // Update nav links on scroll
                                                        $('#tm-top-bar').singlePageNav({
                                                            currentClass: 'active',
                                                            offset: 60
                                                        });

                                                        // Close navbar after clicked
                                                        $('.nav-link').click(function () {
                                                            $('#mainNav').removeClass('show');
                                                        });

                                                        // Slick Carousel
                                                        $('.tm-slideshow').slick({
                                                            infinite: true,
                                                            arrows: true,
                                                            slidesToShow: 1,
                                                            slidesToScroll: 1
                                                        });


                                                    });




        </script>

        <c:if test="${not empty requestScope.ID_INVALID}">
            <script>
                $('#loginModal').modal('show');
            </script>
        </c:if>
        <c:if test="${not empty requestScope.SHOW}">
            <script>
                $('#loginModal').modal('show');
            </script>
        </c:if>



        <c:if test="${not empty requestScope.INSERTERROR}">
            <script>
                $('#loginModal').modal('show');
                $(document).ready(function () {
                    $('#login').trigger('click');
                });
            </script>


        </c:if>
        <script>
            $('#tm-section').click(function () {
                $('#showAll').trigger('click');
            });
        </script>
        <c:if test="${not empty requestScope.renting}">
            <script>
                $(document).ready(function () {
                    $('#tm-section').trigger('click');
                });

            </script>
        </c:if>

        <c:if test="${not empty requestScope.orderHistoryModal}">
            <script>
                $(document).ready(function () {
                    $('#orderHistoryModal').trigger('click');
                });

            </script>
        </c:if>
        <c:if test="${not empty requestScope.edit}">
            <script>
                $(document).ready(function () {
                    $('#edit').trigger('click');
                });

            </script>
        </c:if>



        <!-- form search -->
        <script src="js/choices.js"></script>
        <script src="js/flatpickr.js"></script>
        <script>
                flatpickr(".datepicker",
                        {});

        </script>
        <script>
            const choices = new Choices('[data-trigger]',
                    {
                        searchEnabled: false,
                        itemSelectText: ''
                    });

        </script>

        <script>
            const loginBtn = document.getElementById('login');
            const signupBtn = document.getElementById('signup');

            loginBtn.addEventListener('click', (e) => {
                let parent = e.target.parentNode.parentNode;
                Array.from(e.target.parentNode.parentNode.classList).find((element) => {
                    if (element !== "slide-up") {
                        parent.classList.add('slide-up');
                    } else {
                        signupBtn.parentNode.classList.add('slide-up');
                        parent.classList.remove('slide-up');
                    }
                });
            });

            signupBtn.addEventListener('click', (e) => {
                let parent = e.target.parentNode;
                Array.from(e.target.parentNode.classList).find((element) => {
                    if (element !== "slide-up") {
                        parent.classList.add('slide-up');
                    } else {
                        loginBtn.parentNode.parentNode.classList.add('slide-up');
                        parent.classList.remove('slide-up');
                    }
                });
            });
        </script>

    </body>

</html>
