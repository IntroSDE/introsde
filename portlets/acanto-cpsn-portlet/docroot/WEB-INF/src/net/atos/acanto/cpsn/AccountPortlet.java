package net.atos.acanto.cpsn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ValidatorException;






import com.liferay.docs.accounts.model.Account;
import com.liferay.docs.accounts.service.AccountLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.mysql.jdbc.StringUtils;

/**
 * Portlet implementation class AccountPortlet
 */
public class AccountPortlet extends MVCPortlet {
 
	/*
	public void addAccount(ActionRequest request, ActionResponse response) {


	    try {

	       PortletPreferences prefs = request.getPreferences();

	       String[] storedAccounts = prefs.getValues("accounts", new String[1]);
	       
	       ArrayList<String> accounts = new ArrayList<String>();

	       if (storedAccounts != null) {

	         accounts = new ArrayList<String>(Arrays.asList(prefs.getValues(
	              "accounts", new String[1])));

	       }

	       String userName = ParamUtil.getString(request, "name");
	       String password = ParamUtil.getString(request, "password");
	       String account = userName + "^" + password;

	       accounts.add(account);

	       String[] array = accounts.toArray(new String[accounts.size()]);

	       prefs.setValues("accounts", array);

	       try {

	         prefs.store();

	       } catch (IOException ex) {

	         Logger.getLogger(AccountPortlet.class.getName()).log(
	              Level.SEVERE, null, ex);

	       } catch (ValidatorException ex) {

	         Logger.getLogger(AccountPortlet.class.getName()).log(
	              Level.SEVERE, null, ex);

	       }

	    } catch (ReadOnlyException ex) {

	       Logger.getLogger(AccountPortlet.class.getName()).log(
	          Level.SEVERE, null, ex);

	    }

		
		
	}
	*/
	
	public void addAccount(ActionRequest request, ActionResponse response)
	        throws PortalException, SystemException {

	    ServiceContext serviceContext = ServiceContextFactory.getInstance(
	        Account.class.getName(), request);
	    
		String firstName = ParamUtil.getString(request, "firstName");
		String familyName = ParamUtil.getString(request, "familyName");
		String email = ParamUtil.getString(request, "email");
		String birthday = ParamUtil.getString(request, "birthday");
		String gender = ParamUtil.getString(request, "gender");
		String accountPassword = ParamUtil.getString(request, "accountPassword");
		String maritalStatus = ParamUtil.getString(request, "maritalStatus");
		
		String[] interests = serviceContext.getAssetTagNames();
		
		String educationLevel = ParamUtil.getString(request, "educationLevel");
		String foreignLanguages = ParamUtil.getString(request, "foreignLanguages");
		String profession = ParamUtil.getString(request, "profession");
		
		String preferences = ParamUtil.getString(request, "preferences");

		
	    try {
	        AccountLocalServiceUtil.addAccount(serviceContext.getUserId(),		
	        		firstName, 
	    			familyName, 
	    			email, 
	    			birthday, 
	    			gender, 
	    			accountPassword, 
	    			maritalStatus, 
	    			Arrays.toString(interests),
	    			educationLevel, 
	    			foreignLanguages, 
	    			profession, 
	    			preferences,
	    			
	                serviceContext);

	        SessionMessages.add(request, "accountAdded");

	    } catch (Exception e) {
	        SessionErrors.add(request, e.getClass().getName());

	        response.setRenderParameter("mvcPath",
	            "/html/create_account/edit_account.jsp");
	    }

	}
	
	@Override
	public void render(RenderRequest renderRequest,
	        RenderResponse renderResponse) throws PortletException, IOException {

	    try {
	        ServiceContext serviceContext = ServiceContextFactory.getInstance(
	                Account.class.getName(), renderRequest);

	        long groupId = serviceContext.getScopeGroupId();

	        long accountId = ParamUtil.getLong(renderRequest, "accountId");

	        
	        
	        List<Account> accounts = AccountLocalServiceUtil
	                .getAccounts(groupId);

	        
	        
	        
	        if (accounts.size() == 0) {
	            Account account = AccountLocalServiceUtil.addAccount(
	                    serviceContext.getUserId(), 
	                    "exampleFirstName", 
		    			"exampleFamilyName", 
		    			"exampleEmail", 
		    			"exampleBirthday", 
		    			"exampleGender", 
		    			"exampleAccountPassword", 
		    			"exampleMaritalStatus", 
		    			"exampleInterests", 
		    			"exampleEducationLevel", 
		    			"exampleForeignLanguages", 
		    			"exampleProfession", 
		    			"examplePreferences", 
	                    serviceContext);

	            accountId = account.getAccountId();

	        }
	        

	        
	        
	        
	        if (!(accountId > 0)) {
	            accountId = accounts.get(0).getAccountId();
	        }

	        renderRequest.setAttribute("accountId", accountId);

	    } catch (Exception e) {

	        throw new PortletException(e);
	    }

	    super.render(renderRequest, renderResponse);

	}
	
	
	/*
	private List<Account> parseAccounts (String[] storedAccounts) {

	    ArrayList<Account> accounts = new ArrayList();

	    for (String account : storedAccounts) {
	        String[] parts = account.split("\\^", 2);
	        Account gbAccount = new Account(parts[0], parts[1]);
	        accounts.add(gbAccount);
	    }

	    return accounts;
	}
	*/
	
	
	
}
