package com.java.agent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Authorization")
public class AgentAuth {
	
	@Id
	@Column(name="AuthId")
	private int authId;
	@Column(name="AgentId")
	private int agentId;
	@Column(name="AuthCode")
	private String authCode;
	@Column(name="AuthReason")
	private String authReason;
	@Column(name="Status")
	private String status;
	
	
	public int getAuthId() {
		return authId;
	}
	public void setAuthId(int authId) {
		this.authId = authId;
	}
	public int getAgentId() {
		return agentId;
	}
	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getAuthReason() {
		return authReason;
	}
	public void setAuthReason(String authReason) {
		this.authReason = authReason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public AgentAuth() {
		
	}
	
	
	public AgentAuth(int authId, int agentId, String authCode, String authReason, String status) {
		super();
		this.authId = authId;
		this.agentId = agentId;
		this.authCode = authCode;
		this.authReason = authReason;
		this.status = status;
	}
	
	
	
	
	
	
	
	
	

}
