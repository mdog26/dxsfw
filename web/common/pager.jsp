<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
var pageNumber = "${pageNo}";
var pageTotal = "${pageTotal}";
$(function() {
    $("#previousBtn").die().live("click",function() {
        $("#pageNo").val((parseInt(pageNumber) - 1));
        $("#pageForm").submit();
    });
        
    $("#nextBtn").die().live("click",function() {
        $("#pageNo").val((parseInt(pageNumber) + 1));
        $("#pageForm").submit();
    });
});

function gotoPage(currPage) {
    $("#pageNo").val(parseInt(currPage));
    $("#pageForm").submit();
}

function tiaozhuan() {
    var inputPageInt = $("#inputPage").val();
    if (inputPageInt == null || inputPageInt == "") {
        return false;
    }
    if (isNaN(inputPageInt)) {
        return false;
    }
    if (inputPageInt < 1) {
        inputPageInt = 1;
    }
    if (parseInt(inputPageInt) > parseInt(pageTotal)) {
        inputPageInt = pageTotal;
    }
    if (inputPageInt == 0) {
        return false;
    }
    $("#pageNo").val(parseInt(inputPageInt));
    $("#pageForm").submit();
}

$(document).keypress(function(e) {
    if (e.which == 13) {
        var inputPageInt = $("#inputPage").val();
        if (inputPageInt == null || inputPageInt == "") {
            return false;
        }
        if (isNaN(inputPageInt)) {
            return false;
        }
        if (inputPageInt < 1) {
            inputPageInt = 1;
        }
        if (parseInt(inputPageInt) > parseInt(pageTotal)) {
            inputPageInt = pageTotal;
        }
        if (inputPageInt == 0) {
            return false;
        }
        $("#pageNo").val(inputPageInt);
    }
}); 
</script>
<div class="yk_panigation_paper">
<c:choose>
  <c:when test="${pageNo < 5}">
    <c:set var="start" value="1"></c:set>
    <c:set var="end" value="5"></c:set>
  </c:when>
  <c:otherwise>
    <c:set var="start" value="${pageNo-2}"></c:set>
    <c:set var="end" value="${pageNo+2}"></c:set>
  </c:otherwise>
</c:choose>
<c:if test="${end > pageTotal}">
  <c:set var="end" value="${pageTotal}"></c:set>
</c:if>
<c:if test="${start < 2}">
  <c:set var="start" value="1"></c:set>
</c:if>

<c:choose>
  <c:when test="${pageNo == 1}">
    <a class="prev_page"></a>
  </c:when>
  <c:otherwise>
    <a id="previousBtn" class="prev_page"></a>
  </c:otherwise>
</c:choose>

<c:if test="${start > 1}">
  <a onclick="gotoPage(1)">1</a>
  <span>...</span>
</c:if>
<c:forEach var="i" begin="${start}" end="${end}" step="1">
  <c:choose>
    <c:when test="${pageNo == i}">
      <a onclick="gotoPage(${1})" class="cur">${i}</a>
    </c:when>
    <c:otherwise>
      <a onclick="gotoPage(${i})">${i}</a>
    </c:otherwise>
  </c:choose>
</c:forEach>
<c:if test="${end < pageTotal}">
  <c:if test="${end != pageTotal-1}">
    <span>...</span>
  </c:if>
  <a onclick="gotoPage(${pageTotal})">${pageTotal}</a>
</c:if>

<c:choose>
  <c:when test="${pageNo >= pageTotal}">
    <a class="next_page"></a>
  </c:when>
  <c:otherwise>
    <a id="nextBtn" class="next_page"></a>
  </c:otherwise>
</c:choose>
<span>跳转至：</span>
<input type="text" id="inputPage" class="yk_paper_input" maxlength="10" value="${pageNo}" />
<input type="button" class="yk_paper_input yk_paper_submit" value="GO" onclick="tiaozhuan()" />
</div>