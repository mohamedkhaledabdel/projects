package lab4;

import java.util.ArrayList;

public class Rule {
	Head headOfRule;
	ArrayList<Body> bodyOfRule;

	
	public Rule(Head headOfRule, ArrayList<Body> bodyOfRule) {
		this.headOfRule = headOfRule;
		this.bodyOfRule = bodyOfRule;
	}
	public Rule() {
		
	}

	public Head getHeadOfRule() {
		return headOfRule;
	}
	
	public void setRule(Rule rule){
		this.headOfRule = rule.headOfRule;
		this.bodyOfRule = rule.bodyOfRule;
	}
	
	public void setHeadOfRule(Head headOfRule) {
		this.headOfRule = headOfRule;
	}

}
