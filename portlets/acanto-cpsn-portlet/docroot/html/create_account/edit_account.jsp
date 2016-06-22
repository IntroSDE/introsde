<%@include file="/html/init.jsp"%>

<portlet:defineObjects />

<portlet:renderURL var="viewAccount">

	<portlet:param name="mvcPath" value="/html/create_account/view.jsp"></portlet:param>

</portlet:renderURL>

<portlet:actionURL name="addAccount" var="addAccountURL"></portlet:actionURL>

<aui:form action="<%=addAccountURL%>" name="<portlet:namespace />fm">


	<aui:fieldset>

		<aui:input name="name" />
		<aui:input name="password" />
		<aui:input name='accountId' type='hidden'
			value='<%= ParamUtil.getString(renderRequest, "accountId") %>' />

	</aui:fieldset>


	<aui:button-row>
		<aui:button type="submit"></aui:button>
		<aui:button type="cancel"></aui:button>
	</aui:button-row>

</aui:form>