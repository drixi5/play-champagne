package controllers;



import models.User;
import models.Stores;
import play.mvc.*;
import play.data.Form;
import views.html.store;
import views.html.addStore;
import views.html.editStore;
import views.html.addAdmin;
import static play.data.Form.form;

@Security.Authenticated(Secured.class)
public class Store extends Controller {
	

	 public static Result index() {
	        return ok(store.render(User.findByEmail(request().username()), Stores.find.orderBy("id asc").findList()));
	    }
	  
	 final static Form<Stores> storeForm = form(Stores.class);
	 
	 public static Result viewAddStore(){
		 return ok(addStore.render(User.findByEmail(request().username()),storeForm));
	 }
	 	 
	 
	 
	 public static Result addStore(){
		 Form<Stores> form = storeForm.bindFromRequest();
		 
		 if (form.hasErrors()){
			 return badRequest(addStore.render(User.findByEmail(request().username()),form));
		 }
		 else
		 { 
			 Stores store= form.get();
			 store.save();
			 //return ok(addAdmin.render(User.findByEmail(request().username())));
			 return index();
		 }
	 } 	 
	 

     public static Result editStore(Long id)  {
        Form<Stores> form = storeForm.bindFromRequest();
        Stores store = Stores.find.byId(id);

       if(form.hasErrors())  {
           
    	   return badRequest(editStore.render(User.findByEmail(request().username()),form, Stores.find.orderBy("id asc").findList()));
       } else {
           store = form.get();
           store.update(id);
           return index();
       }
   }

   public static Result deleteStore(Long id)  {
       Stores store = Stores.find.byId(id);

       if(store == null)  {
           return badRequest("Store not found");
       } else  {
           store.delete();
           return index();
       }
   }
		 
/* A voir avec Xav au niveau du model.user ! 	 
	 public static Result addAdmin(){
		 User admin = Form.form(User.class).bindFromRequest().get();
   	  	 admin.save();
   	     return index();
	 }*/
}
