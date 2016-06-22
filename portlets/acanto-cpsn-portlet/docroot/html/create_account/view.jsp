<%@include file="/html/init.jsp"%>

<%
    long accountId = Long.valueOf((Long) renderRequest
            .getAttribute("accountId"));
%>

<aui:nav cssClass="nav-tabs">

    <%
        List<Account> accounts = AccountLocalServiceUtil
                    .getAccounts(scopeGroupId);
            for (int i = 0; i < accounts.size(); i++) {
                Account curAccount = (Account) accounts.get(i);

                String cssClass = StringPool.BLANK;

                if (curAccount.getAccountId() == accountId) {
                    cssClass = "active";
                }
    %>

    <portlet:renderURL var="viewPageURL">
        <portlet:param name="mvcPath" value="/html/create_account/view.jsp" />
        <portlet:param name="accountId"
            value="<%=String.valueOf(curAccount.getAccountId())%>" />
    </portlet:renderURL>

    

    <%
        }
    %>

</aui:nav>


<aui:button-row cssClass="account-buttons">

    <portlet:renderURL var="addAccountURL">
        <portlet:param name="mvcPath"
            value="/html/create_account/edit_account.jsp" />
        <portlet:param name="accountId"
            value="<%=String.valueOf(accountId)%>" />
    </portlet:renderURL>

    <aui:button onClick="<%=addAccountURL.toString()%>"
        value="Add Account" />

</aui:button-row>



<liferay-ui:search-container>

    <liferay-ui:search-container-results
        results="<%=AccountLocalServiceUtil.getAccounts(searchContainer.getStart(),
                        searchContainer.getEnd())%>" />



    <liferay-ui:search-container-row
        className="com.liferay.docs.accounts.model.Account" modelVar="account1">

        <liferay-ui:search-container-column-text property="name" />

        <liferay-ui:search-container-column-text property="password" />

    </liferay-ui:search-container-row>



    <liferay-ui:search-iterator />
    
</liferay-ui:search-container>




