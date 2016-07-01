<%@include file="/html/init.jsp"%>

<%
    long accountId = Long.valueOf((Long) renderRequest
            .getAttribute("accountId"));
%>

<%!
   com.liferay.portal.kernel.dao.search.SearchContainer searchContainer = null;
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

        <liferay-ui:search-container-column-text property="firstName" />
        
        <liferay-ui:search-container-column-text property="familyName" />
        
        <liferay-ui:search-container-column-text property="email" />
        
        <liferay-ui:search-container-column-text property="birthday" />
        
        <liferay-ui:search-container-column-text property="gender" />
        
        <liferay-ui:search-container-column-text property="accountPassword" />
        
        <liferay-ui:search-container-column-text property="maritalStatus" />
        
        <liferay-ui:search-container-column-text property="interests" />
        
        <liferay-ui:search-container-column-text property="educationLevel" />
        
        <liferay-ui:search-container-column-text property="foreignLanguages" />
        
        <liferay-ui:search-container-column-text property="profession" />

        <!--<liferay-ui:search-container-column-text property="preferences" /> -->

    </liferay-ui:search-container-row>



    <liferay-ui:search-iterator />
    
</liferay-ui:search-container>




