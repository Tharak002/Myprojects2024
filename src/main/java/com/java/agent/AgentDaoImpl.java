package com.java.agent;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


public class AgentDaoImpl implements AgentDao{
	
	SessionFactory sessionFactory;
	
	public String changePassword(int agentId, String password, String authCode) {
		sessionFactory = SessionHelper.getSession();
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(AgentAuth.class);
		cr.add(Restrictions.eq("agentId", agentId));
		cr.add(Restrictions.eq("authReason", "Reset Password"));
		cr.add(Restrictions.eq("authCode", authCode));
		AgentAuth auth = (AgentAuth)cr.uniqueResult();
		if (auth!=null) {
			cr = session.createCriteria(Agent.class);
			cr.add(Restrictions.eq("agentId", agentId));
			Agent agent = (Agent)cr.uniqueResult();
			String encr = EncryptPassword.getCode(password);
			agent.setPassword(password);
			Transaction trans = session.beginTransaction();
			session.save(agent);
			trans.commit();
			return "Password Updated Successfully...";
		}
		return "Record Not Found...";
	}
		
		
		public Agent searchByUsername(String username) {

		sessionFactory = SessionHelper.getSession();
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Agent.class);
		cr.add(Restrictions.eq("agentUsername", username));
		Agent agent = (Agent)cr.uniqueResult();
		if (agent!=null) {
			String gencode = GenerateCode.createPassword();
			SendMail.mailSend(agent.getEmail(), "Reset Password Code", gencode);
			AgentAuth auth = new AgentAuth();
			auth.setAgentId(agent.getAgentId());
			auth.setAuthReason("Reason");
			auth.setAuthCode("code");
			Transaction trans = session.beginTransaction();
			session.save(auth);
			trans.commit();
			return agent;
		}
		return null;
	}

	@Override
	public String addAgentDao(Agent agent) {
		AgentAuth agentAuth = new AgentAuth();
		sessionFactory = SessionHelper.getSession();
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		session.save(agent);
		trans.commit();
		session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Agent.class);
		List<Agent> agentList = cr.list();
		System.out.println(agentList.size());
		int id = agentList.get(agentList.size()-1).getAgentId();
		System.out.println(id);
		agentAuth.setAgentId(id);
		agentAuth.setAuthReason("Create Account");
		String code = GenerateCode.createPassword();
		agentAuth.setAuthCode(code);
		session = sessionFactory.openSession();
		trans = session.beginTransaction();
		session.save(agentAuth);
		trans.commit();
		SendMail.mailSend(agent.getEmail(), "One Time Password", code);
		return "Agent Account Created with One Time Password...";
	
	}

	@Override
	public int authenticate(String agentUser, String authCode) {
		sessionFactory = SessionHelper.getSession();
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Agent.class);
		cr.add(Restrictions.eq("username", agentUser));
		Agent agent = (Agent)cr.uniqueResult();
		int agentId = agent.getAgentId();
		cr = session.createCriteria(AgentAuth.class);
		cr.add(Restrictions.eq("agentId", agentId));
		cr.add(Restrictions.eq("authCode", authCode));
		AgentAuth auth = (AgentAuth)cr.uniqueResult();
		if (auth!=null) {
			return 1;
		}
		return 0;
	}

	@Override
	public String updatePassword(String agentUser, String Password) {
		String encr = EncryptPassword.getCode(Password);
		sessionFactory = SessionHelper.getSession();
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Agent.class);
		cr.add(Restrictions.eq("username", agentUser));
		Agent agent = (Agent)cr.uniqueResult();
		agent.setPassword(Password);
		Transaction trans = session.beginTransaction();
		session.saveOrUpdate(agent);
		trans.commit();
		return "Password Updated Successfully...";
	}

	@Override
	public int login(String user, String pwd) {
		String encr = EncryptPassword.getCode(pwd);
		sessionFactory = SessionHelper.getSession();
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Agent.class);
		cr.add(Restrictions.eq("username", user));  
		cr.add(Restrictions.eq("password", pwd));
		Agent agent = (Agent)cr.uniqueResult();
		if (agent!=null) {
			return 1;
		}
		return 0;
	}

}
