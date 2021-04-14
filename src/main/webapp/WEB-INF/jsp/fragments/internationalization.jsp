<%@ page contentType="text/html;charset=UTF-8" %>

<script type="text/javascript">
    const i18n = [];
    i18n["addTitle"] = '<spring:message code="${addTitle}" />';
    i18n["editTitle"] = '<spring:message code="${editTitle}"/>';

    <c:forEach var="key" items='<%=new String[]{"common.deleted","common.saved","common.enabled","common.disabled","common.errorStatus","common.confirm"}%>'>
    i18n["${key}"] = "<spring:message code="${key}"/>";
    </c:forEach>
</script>
