<%@ page contentType="text/html;charset=UTF-8" %>
<div class="bar">
	<ul>
		<li class="bar_hint"></li>
		<li id="bar_index"><a href="${ctx}/shop">Home</a></li>
		<li id="bar_cart"><a href="${ctx}/order/viewCart">Shopping Cart</a></li>
	</ul>
	<script type="text/javascript">
		$('<%=request.getParameter("active")==null?"bar_index":request.getParameter("active")%>').className = "active";
	</script>
</div>
<div class="search_field">
	<form method="post" action="${ctx}//shop/search.do?method=search">
		<div class="search_form">
			<p>Search books: <input type="text" name="queryString" class="search"/>
				<input type="submit" value="Search" class="submit"/>
				<a class="grey" href="#">Advanced</a>
			</p>
		</div>
	</form>
</div>