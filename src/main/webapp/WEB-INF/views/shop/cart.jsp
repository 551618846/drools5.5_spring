<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title> Book Store Demo</title>
	<%@ include file="head.jsp" %>
</head>

<body>
<div class="content">
	<%@ include file="top.jsp" %>
	<div>
		<h2>Shopping Cart</h2>
		<table cellSpacing=0 cellPadding=1 border="1px">
			<tr>
				<th><b>Product Name</b></th>
				<th><b>Amount</b></th>
				<th><b>Price</b></th>
				<th><b>Total Prices</b></th>
			</tr>
			<c:if test="${cart.size==0}">
				<tr>
					<td colspan="4">Your cart is empty</td>
				</tr>
			</c:if>
			<c:forEach items="${cart.items}" var="item" varStatus="var">
				<tr class="${var.index%2==0?'td1':'td2'}">
					<td width="380">
							${item.product.name}
					</td>
					<td width="100">
							${item.quantity}
					</td>
					<td width="100">
						<fmt:formatNumber value="${item.product.unitprice}" pattern="￥#,##0.00"/>
					</td>
					<td width="100">
						<fmt:formatNumber value="${item.totalPrice}" pattern="￥#,##0.00"/>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div align="right">
			Subtotal: you have choose ${cart.size} products, your cost:
			<fmt:formatNumber value="${cart.subTotal}" pattern="￥#,##0.00"/>
			RMB
		</div>
		<div>
			<a href="${ctx}/shop">Continue Shopping</a>&nbsp;&nbsp;
			<c:if test="${cart.size>0}">
				<a href="${ctx}/order/newOrderForm">Place Order</a>
			</c:if>
		</div>
	</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>