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
import locadora.model.FilmeBluRay;

/**
 *
 * @author Guilherme
 */
public class FilmeBluRayJpaController implements Serializable {

    public FilmeBluRayJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FilmeBluRay filmeBluRay) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(filmeBluRay);
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

    public void edit(FilmeBluRay filmeBluRay) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            filmeBluRay = em.merge(filmeBluRay);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = filmeBluRay.getId();
                if (findFilmeBluRay(id) == null) {
                    throw new NonexistentEntityException("The filmeBluRay with id " + id + " no longer exists.");
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
            FilmeBluRay filmeBluRay;
            try {
                filmeBluRay = em.getReference(FilmeBluRay.class, id);
                filmeBluRay.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The filmeBluRay with id " + id + " no longer exists.", enfe);
            }
            em.remove(filmeBluRay);
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

    public List<FilmeBluRay> findFilmeBluRayEntities() {
        return findFilmeBluRayEntities(true, -1, -1);
    }

    public List<FilmeBluRay> findFilmeBluRayEntities(int maxResults, int firstResult) {
        return findFilmeBluRayEntities(false, maxResults, firstResult);
    }

    private List<FilmeBluRay> findFilmeBluRayEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FilmeBluRay.class));
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

    public FilmeBluRay findFilmeBluRay(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FilmeBluRay.class, id);
        } finally {
            em.close();
        }
    }

    public int getFilmeBluRayCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FilmeBluRay> rt = cq.from(FilmeBluRay.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
