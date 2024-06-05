package com.java.agent;


public interface AgentDao {
	
	String addAgentDao(Agent agent);
	int authenticate(String agentUser, String authCode);
	String updatePassword(String customerUser, String Password);
	int login(String user,String pwd);

}
