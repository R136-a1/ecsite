package jp.co.internous.ecsite.conrtroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.ecsite.model.dao.UserRepository;
import jp.co.internous.ecsite.model.entity.User;
import jp.co.internous.ecsite.model.form.UserForm;

@Controller 
@RequestMapping("/ecsite/admin/user")
public class UserController {
	@Autowired 
	private UserRepository userRepos;
	
	@RequestMapping("/") 
	public String userIndex() {
		return "register_user";
	}
	
	@RequestMapping("/duplicatedUserName")
	@ResponseBody
	public boolean duplicatedUserName(@RequestBody UserForm f) {

		int count = userRepos.findCountByUserName(f.getUserName());
		return count > 0;
	}
	
	@PostMapping("/registeUser")
	public String registeUserApi(UserForm f) {
		User user = new User();
		user.setUserName(f.getUserName());
		user.setPassword(f.getPassword());
		user.setFullName(f.getFullName());
		userRepos.saveAndFlush(user);
		
		return "adminindex";
	}
	
	
}



