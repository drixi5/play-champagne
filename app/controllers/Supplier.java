package controllers;

import models.User;
import models.Suppliers;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.supplier;
import views.html.addSupplier;
import views.html.modifySupplier;


@Security.Authenticated(Secured.class)
public class Supplier extends Controller {
	
	public static Result index() {
        return ok(supplier.render(User.findByEmail(request().username())));
    }
	public static Result addSupplier(){
		return ok(addSupplier.render(User.findByEmail(request().username())));
	}
	
	public static Result modifySupplier(){
		//return ok(addSupplier.render(User.findByEmail(request().username())));
		return ok(modifySupplier.render(User.findByEmail(request().username()), Suppliers.find.orderBy("id asc").findList()));
	}
	
	public static Result deleteSupplier(){
		return ok(addSupplier.render(User.findByEmail(request().username())));
		//return ok(deleteSupplier.render(User.findByEmail(request().username())));
	}
}
