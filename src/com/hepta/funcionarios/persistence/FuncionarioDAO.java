package com.hepta.funcionarios.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.hepta.funcionarios.entity.Funcionario;

public class FuncionarioDAO {

	public void salvar(Funcionario funcionario) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(funcionario);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			em.close();
		}
	}

	public Funcionario editar(Funcionario funcionario) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		Funcionario funcionarioEditado = null;
		try {
			em.getTransaction().begin();
			funcionarioEditado = em.merge(funcionario);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			em.close();
		}
		return funcionarioEditado;
	}

	public void deletarPorId(Integer id) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			Funcionario Funcionario = em.find(Funcionario.class, id);
			em.remove(Funcionario);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			em.close();
		}

	}

	public Funcionario buscarPorId(Integer id) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		Funcionario Funcionario = null;
		try {
			Funcionario = em.find(Funcionario.class, id);
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			em.close();
		}
		return Funcionario;
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> buscarTodos() throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		List<Funcionario> Funcionarios = new ArrayList<>();
		try {
			Query query = em.createQuery("FROM Funcionario");
			Funcionarios = query.getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			em.close();
		}
		return Funcionarios;
	}

}
