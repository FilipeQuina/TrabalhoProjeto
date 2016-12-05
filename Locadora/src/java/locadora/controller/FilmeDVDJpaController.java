/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locadora.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import locadora.controller.exceptions.NonexistentEntityException;
import locadora.controller.exceptions.RollbackFailureException;
import locadora.model.FilmeDVD;

/**
 *
 * @author Guilherme
 */
public class FilmeDVDJpaController implements Serializable {

    public FilmeDVDJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FilmeDVD filmeDVD) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(filmeDVD);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FilmeDVD filmeDVD) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            filmeDVD = em.merge(filmeDVD);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = filmeDVD.getId();
                if (findFilmeDVD(id) == null) {
                    throw new NonexistentEntityException("The filmeDVD with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            FilmeDVD filmeDVD;
            try {
                filmeDVD = em.getReference(FilmeDVD.class, id);
                filmeDVD.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The filmeDVD with id " + id + " no longer exists.", enfe);
            }
            em.remove(filmeDVD);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FilmeDVD> findFilmeDVDEntities() {
        return findFilmeDVDEntities(true, -1, -1);
    }

    public List<FilmeDVD> findFilmeDVDEntities(int maxResults, int firstResult) {
        return findFilmeDVDEntities(false, maxResults, firstResult);
    }

    private List<FilmeDVD> findFilmeDVDEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FilmeDVD.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public FilmeDVD findFilmeDVD(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FilmeDVD.class, id);
        } finally {
            em.close();
        }
    }

    public int getFilmeDVDCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FilmeDVD> rt = cq.from(FilmeDVD.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
