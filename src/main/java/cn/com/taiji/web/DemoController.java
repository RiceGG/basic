package cn.com.taiji.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class DemoController {
	//访问次数
	@RequestMapping("/xyz")
	public String count() {
		return "access xyz";
	}
	
	@Value("${git.commit.message.short}")
    private String commitMessage;
 
    @Value("${git.branch}")
    private String branch;
 
    @Value("${git.commit.id}")
    private String commitId;
 
    @RequestMapping("/commitId")
    public Map<String, String> getCommitId() {
        Map<String, String> result = new HashMap<>();
        result.put("Commit message",commitMessage);
        result.put("Commit branch", branch);
        result.put("Commit id", commitId);
        return result;
    }
	
}
