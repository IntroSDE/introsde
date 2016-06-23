<%@include file="/html/init.jsp"%>

<portlet:defineObjects />

<portlet:renderURL var="viewAccount">

	<portlet:param name="mvcPath" value="/html/create_account/view.jsp"></portlet:param>

</portlet:renderURL>

<portlet:actionURL name="addAccount" var="addAccountURL"></portlet:actionURL>

<aui:form action="<%=addAccountURL%>" name="<portlet:namespace />fm">


	<aui:fieldset>

		<aui:input name="firstName" />
		<aui:input name="familyName" />
		<aui:input name="email" />
		<aui:input name="birthday" />
		<aui:input name="gender" />
		<aui:input name="accountPassword" />
		<aui:input name="maritalStatus" />
		<aui:input name="interests" />
		<aui:input name="educationLevel" />
		<aui:input name="foreignLanguages" />
		<aui:input name="profession" />
		<aui:input name="preferences" />
		
		<aui:input name='accountId' type='hidden'
			value='<%= ParamUtil.getString(renderRequest, "accountId") %>' />

	</aui:fieldset>


	<aui:button-row>
		<aui:button type="submit"></aui:button>
		<aui:button type="cancel"></aui:button>
	</aui:button-row>

</aui:form>