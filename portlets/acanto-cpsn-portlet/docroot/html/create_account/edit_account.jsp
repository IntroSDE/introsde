<%@include file="/html/init.jsp"%>

<portlet:defineObjects />

<portlet:renderURL var="viewAccount">

	<portlet:param name="mvcPath" value="/html/create_account/view.jsp"></portlet:param>

</portlet:renderURL>

<portlet:actionURL name="addAccount" var="addAccountURL"></portlet:actionURL>

<aui:form action="<%=addAccountURL%>" name="<portlet:namespace />fm">


	<aui:fieldset>

<!--  FIRST NAME = OK -->
		<aui:input name="first name">
			<aui:validator name="firstName" />
			<aui:validator name="required" />
		</aui:input>
<!--  ---------  -->


<!--  SECOND NAME = OK -->
		<aui:input name="family name">
			<aui:validator name="familyName" />
			<aui:validator name="required" />
		</aui:input>
<!--  ---------  -->


<!--  EMAIL = OK -->
		<aui:input name="email">
			<aui:validator name="email" />
			<aui:validator name="required" />
		</aui:input>
<!--  ---------  -->


<!--  BIRTHDAY = OK -->
		<aui:input name="birthday" type="Date">
			<aui:validator name="birthday" />
			<aui:validator name="required" />
		</aui:input>
<!--  ---------  -->		


<!--  GENDER = OK -->		
		<aui:select name="gender" no-empty-value="true">
		<aui:option>Female</aui:option>
            <aui:option>Male</aui:option>
        </aui:select>
<!--  ---------  -->


<!--  PASSWORD = OK -->
		<aui:input name="password" type="password">
			<aui:validator name="accountPassword" />
			<aui:validator name="required" />
		</aui:input>
<!--  ---------  -->


<!--  MARITAL STATUS = OK -->	
		<aui:select name="marital status" no-empty-value="true">
		<aui:option>Single</aui:option>
		<aui:option>Engaged</aui:option>
            <aui:option>Married</aui:option>
            <aui:option>Divorced</aui:option>
            <aui:option>Widower</aui:option>
        </aui:select>
<!--  ---------  -->


<!--  INTERESTS = OK -->
		<aui:field-wrapper label="interests">
		<liferay-ui:asset-tags-selector ></liferay-ui:asset-tags-selector>
		</aui:field-wrapper>
<!--  ---------  -->


<!--  EDUCATION LEVEL = OK -->
		<aui:select name="education level" no-empty-value="true">
		<aui:option>Primary education</aui:option>
		<aui:option>Secondary education</aui:option>
            <aui:option>Higher education</aui:option>
            <aui:option>Undergraduate</aui:option>
            <aui:option>Postgraduate</aui:option>
        </aui:select>
<!--  ---------  -->


<!--  FOREIGN LANGUAGES = OK -->
		<aui:field-wrapper label="foreign languages">
		<liferay-ui:asset-tags-selector ></liferay-ui:asset-tags-selector>
		</aui:field-wrapper>
<!--  ---------  -->


<!--  PROFESSION = OK -->
		<aui:field-wrapper label="profession">
		<liferay-ui:asset-tags-selector ></liferay-ui:asset-tags-selector>
		</aui:field-wrapper>
<!--  ---------  -->


<!--  PREFERENCES = OK -->
		<aui:field-wrapper label="preferences">
		<liferay-ui:asset-tags-selector ></liferay-ui:asset-tags-selector>
		</aui:field-wrapper>
<!--  ---------  -->




		<aui:input name='accountId' type='hidden'
			value='<%= ParamUtil.getString(renderRequest, "accountId") %>' />

	</aui:fieldset>


	<aui:button-row>
		<aui:button type="submit"></aui:button>
		<aui:button type="cancel"></aui:button>
	</aui:button-row>

</aui:form>