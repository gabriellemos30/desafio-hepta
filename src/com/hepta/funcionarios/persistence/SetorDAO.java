package com.hepta.funcionarios.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.hepta.funcionarios.entity.Setor;

public class SetorDAO {

		public Setor editar(Setor Setor) throws Exception {
			EntityManager em = HibernateUtil.getEntityManager();
			Setor setorEditado = null;
			try {
				em.getTransaction().begin();
				setorEditado = em.merge(Setor);
				em.getTransaction().commit();
			} catch (Exception e) {
				em.getTransaction().rollback();
				throw new Exception(e);
			} finally {
				em.close();
			}
			return setorEditado;
		}

		public Setor buscarPorId(Integer id) throws Exception {
			EntityManager em = HibernateUtil.getEntityManager();
			Setor Setor = null;
			try {
				Setor = em.find(Setor.class, id);
			} catch (Exception e) {
				em.getTransaction().rollback();
				throw new Exception(e);
			} finally {
				em.close();
			}
			return Setor;
		}

		@SuppressWarnings("unchecked")
		public List<Setor> listarTodos() throws Exception {
			EntityManager em = HibernateUtil.getEntityManager();
			List<Setor> setores = new ArrayList<>();
			try {
				Query query = em.createQuery("FROM Setor");
				setores = query.getResultList();
			} catch (Exception e) {
				em.getTransaction().rollback();
				throw new Exception(e);
			} finally {
				em.close();
			}
			return setores;
		}
		
		public void salvar(Setor Setor) throws Exception {
			EntityManager em = HibernateUtil.getEntityManager();
			try {
				em.getTransaction().begin();
				em.persist(Setor);
				em.getTransaction().commit();
			} catch (Exception e) {
				em.getTransaction().rollback();
				throw new Exception(e);
			} finally {
				em.close();
			}
		
		}	
}
