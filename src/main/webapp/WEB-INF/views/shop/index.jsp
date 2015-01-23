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
	<div class="left">
		<div class="article">
			<div style="font:bold 12pt ;">New Books:</div>
			<div>
				<c:forEach items="${newBooks}" var="book">

					<div class="right_articles">
						<p><img src="${ctx}/${book.image}" alt="Image" title="Image"
								class="image"/>
							<b>《${book.name}》</b><br/>
							&nbsp;&nbsp;${book.descn}<br/>
							Price:${book.unitprice}RMB
							<button class="submit" onclick="addToCart(${book.id})">Purchase</button>
						</p>
						<div style="color:red;font-size:8pt" id="addToCartMessage${book.id}"></div>
					</div>

					<hr noshade="true" size="1" style="clear:left"/>

				</c:forEach>
			</div>

			<div>${totalCount} books in total</div>
		</div>
	</div>
</div>
<script src="${ctx}/static/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
	function addToCart(id) {
		var vMessageId = '#addToCartMessage' + id;
		$(vMessageId).hide();
		$.post("order/addToCart/"+id, function(data){
			$(vMessageId).show();
			$(vMessageId).html("<center>" + data + "</center>");
			$(vMessageId).scrollTo();
		});
	}
</script>
<jsp:include page="footer.jsp"/>
</body>
</html>