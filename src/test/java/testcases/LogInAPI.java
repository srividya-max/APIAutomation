package testcases;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LogInAPI  extends BaseTest{
	
	String sURL=null;
	String sToken;
	
	/*URL-->Header-->Body-->post
	 *Rest Assured works on given/when and then concept
	 */
	
	@BeforeMethod
	public void settingDependencyBeforeEveryMethod() {
		sURL=null;
	}

	@BeforeTest 
	public void loginAPI() throws IOException{

		sURL= oDataUtils.ReadPageURLproperties("Techarch.LoginURL");
		
		RestAssured.baseURI = sURL;
		System.out.println(RestAssured.baseURI);
		
		JsonObject userCreds = new JsonObject();
		userCreds.addProperty("username", oDataUtils.ReadConstantsproperties("user.name"));
	    userCreds.addProperty("password", oDataUtils.ReadConstantsproperties("user.pwd"));
	    System.out.println(oDataUtils.ReadConstantsproperties("user.name")+" "+oDataUtils.ReadConstantsproperties("user.pwd"));
		
	    Response res=RestAssured.given().contentType("application/json").body(userCreds).post();
	    
	    System.out.println("The status code in loginAPI is:"+ res.getStatusCode());
	    System.out.println(res.asPrettyString());
				
		sToken = res.jsonPath().getString("token[0]");
		System.out.println("the token is:"+sToken);

	}
	@Test(priority=1)
	public void addDataAPI() throws IOException { 
		
		sURL = oDataUtils.ReadPageURLproperties("Techarch.AddDataURL");
		
		Map<String,String> map = new HashMap();
		map.put("token", sToken);
		System.out.println("token in add data is:"+ sToken);
		
		RestAssured.baseURI = sURL;
		
		JsonObject userData = new JsonObject();
		userData.addProperty("accountno", oDataUtils.ReadConstantsproperties("account.no"));
		userData.addProperty("departmentno",oDataUtils.ReadConstantsproperties("dept.no"));
		userData.addProperty("salary", oDataUtils.ReadConstantsproperties("salary"));
		userData.addProperty("pincode", oDataUtils.ReadConstantsproperties("pincode"));
		
		Response res=RestAssured.given().contentType("application/json").headers(map).body(userData).post();
		
		System.out.println(res.asString());
		System.out.println("The status code in addDataAPI is:"+ res.getStatusCode());
		res.prettyPrint();

	}
	
	@Test(priority = 2)
	public void getDataAPI() throws IOException{

		sURL = oDataUtils.ReadPageURLproperties("Techarch.GetDataURL");
		
		Map<String,String> map = new HashMap();
		map.put("token", sToken);
		System.out.println("token in get data is:"+ sToken);
		
		RestAssured.baseURI = sURL;

		Response res=RestAssured.given().contentType("application/json").headers(map).get();
		
		System.out.println(res.asString());
		System.out.println("The status code in getDataAPI is:"+ res.getStatusCode());
		res.prettyPrint();
//		System.out.println(res.jsonPath().getString("accountno").toString());
//		System.out.println(res.jsonPath().getString("accountno[2]"));
//				List <Object> liAcc = res.jsonPath().getList("accountno");
//				for(Object s: liAcc) {
//					System.out.println(s);
//				}
	}
	
	@Test(priority = 3)
	public void editDataAPI() throws IOException {
		
		sURL = oDataUtils.ReadPageURLproperties("Techarch.EditDataURL");
		
		Map<String,String> map = new HashMap();
		map.put("token", sToken);
		System.out.println("Tokenin edit data:"+ sToken);
	
		RestAssured.baseURI = sURL;
		
		JsonObject editData = new JsonObject();
		editData.addProperty("accountno", oDataUtils.ReadConstantsproperties("edit.actno"));
		editData.addProperty("departmentno",oDataUtils.ReadConstantsproperties("newdept.no"));
		editData.addProperty("id",oDataUtils.ReadConstantsproperties("edit.id"));
		editData.addProperty("salary", oDataUtils.ReadConstantsproperties("salary"));
		editData.addProperty("pincode", oDataUtils.ReadConstantsproperties("pincode"));
		editData.addProperty("userid", oDataUtils.ReadConstantsproperties("edit.userid"));
		
		Response res=RestAssured.given().contentType("application/json").headers(map).body(editData).put();		

		System.out.println(res.asString());
		System.out.println("The status code in editDataAPI is:"+ res.getStatusCode());
		res.prettyPrint();

	}
	
	@Test(priority = 4)
	public void deleteDataAPI() throws IOException {
		
		sURL = oDataUtils.ReadPageURLproperties("Techarch.DeleteDataURL");
		
		Map<String,String> map = new HashMap();
		map.put("token", sToken);
		System.out.println("Tokenin delete data:"+ sToken);
	
		RestAssured.baseURI = sURL;
		
		JsonObject deleteData = new JsonObject();
		deleteData.addProperty("accountno", oDataUtils.ReadConstantsproperties("delete.id"));
		deleteData.addProperty("departmentno",oDataUtils.ReadConstantsproperties("delete.userid"));
		
		Response res=RestAssured.given().contentType("application/json").headers(map).body(deleteData).delete();	
		
		System.out.println(res.asString());
		System.out.println("The status code in deleteDataAPI is:"+ res.getStatusCode());
		res.prettyPrint();

	}

	@Test(priority = 5)
	public void logOutAPI() throws IOException {
		
		sURL = oDataUtils.ReadPageURLproperties("Techarch.LogoutURL");
		
		Map<String,String> map = new HashMap();
		map.put("token", sToken);
		System.out.println("Tokenin delete data:"+ sToken);
		
		RestAssured.baseURI = sURL;
		Response res=RestAssured.given().contentType("application/json").headers(map).post();
		System.out.println(res.getStatusCode());
		res.prettyPrint();

		
	}
}
