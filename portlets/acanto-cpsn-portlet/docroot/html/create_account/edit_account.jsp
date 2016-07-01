<%@include file="/html/init.jsp"%>

<portlet:defineObjects />

<portlet:renderURL var="viewAccount">

	<portlet:param name="mvcPath" value="/html/create_account/view.jsp"></portlet:param>

</portlet:renderURL>

<portlet:actionURL name="addAccount" var="addAccountURL"></portlet:actionURL>

<aui:form action="<%=addAccountURL%>" name="<portlet:namespace />fm">


	<aui:fieldset>

		<!--  FIRST NAME = OK -->
		<aui:input name="firstName">
			<aui:validator name="required" />
			<aui:validator name="firstName" />
			
		</aui:input>
<!--  ---------  -->


<!--  SECOND NAME = OK -->
		<aui:input name="familyName">
			<aui:validator name="required" />
			<aui:validator name="familyName" />
			
		</aui:input>
<!--  ---------  -->


<!--  EMAIL = OK -->
		<aui:input name="email">
			<aui:validator name="required" />
			<aui:validator name="email" />
			
		</aui:input>
<!--  ---------  -->


<!--  BIRTHDAY = OK -->
		<aui:input name="birthday" type="Date">
			<aui:validator name="required" />
			<aui:validator name="birthday" />
		</aui:input>
<!--  ---------  -->	
 		
 		<!--  GENDER = OK -->		
		<aui:select name="gender" no-empty-value="true">
			<aui:option label="Female" value="Female" />
            <aui:option label="Male" value="Male" />
        </aui:select>
<!--  ---------  -->
 		
 		
<!--  PASSWORD = OK -->
		<aui:input name="accountPassword" type="password">
			<aui:validator name="required" />
			<aui:validator name="accountPassword" />	
		</aui:input>
<!--  ---------  -->


<!--  MARITAL STATUS = OK -->	
		<aui:select name="maritalStatus" no-empty-value="true">
			<aui:option label="Single" value="Single" />
			<aui:option label="Engaged" value="Engaged" />
            <aui:option label="Married" value="Married" />
            <aui:option label="Divorced" value="Divorced" />
            <aui:option label="Widower" value="Widower" />
        </aui:select>
<!--  ---------  -->


<!--  INTERESTS = ? -->	
		<aui:field-wrapper label="interests-preferences">
		<liferay-ui:asset-tags-selector ></liferay-ui:asset-tags-selector>
		</aui:field-wrapper>
<!--  ---------  -->		
		

<!--  EDUCATION LEVEL = OK -->

		<aui:select name="educationLevel" no-empty-value="true">
			<aui:option label="Primary education" value="Primary education" />
			<aui:option label="Secondary education" value="Secondary education" />
            <aui:option label="Higher education" value="Higher education" />
            <aui:option label="Undergraduate" value="Undergraduate" />
            <aui:option label="Postgraduate" value="Postgraduate" />
        </aui:select>
<!--  ---------  -->


<!--  FOREIGN LANGUAGES = ? -->	
		<aui:input name="foreignLanguages">
		<aui:validator name="required" />
			<aui:validator name="foreignLanguages" />
		</aui:input>
<!--  ---------  -->
		

<!--  PROFESSION = OK -->
		<aui:input name="profession">
			<aui:validator name="required" />
			<aui:validator name="profession" />
		</aui:input>
<!--  ---------  -->


<!--  PREFERENCES = ? -->	
		<aui:input name="preferences">
			<aui:validator name="required" />
			<aui:validator name="preferences" />
		</aui:input>



		<aui:input name='accountId' type='hidden'
			value='<%= ParamUtil.getString(renderRequest, "accountId") %>' />

	</aui:fieldset>


	<aui:button-row>
		<aui:button type="submit"></aui:button>
		<aui:button type="cancel"></aui:button>
	</aui:button-row>

</aui:form>