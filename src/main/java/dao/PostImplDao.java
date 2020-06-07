package dao;

import entity.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@SuppressWarnings("unchecked")
@Transactional
@Repository("PostDao")
public class PostImplDao implements PostDao {

    private static final Log logger = LogFactory.getLog(PostImplDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Post> getAll() {
        List<Post> listPost=sessionFactory.getCurrentSession().createQuery("from Post e",Post.class).list();
        return listPost;
    }

    @Override
    public Post searсh(int idPostSearh) {

        Query query=sessionFactory.getCurrentSession().createQuery("from Post where idPost = :idPostSearh",Post.class);
        query.setParameter("idPostSearh", idPostSearh);
        Post post=(Post)query.getSingleResult();
        return post;
    }

    @Override
    public Post searсh(String namePost) {
        Query query=sessionFactory.getCurrentSession().createQuery("from Post where namePost=:namePost",Post.class);
        query.setParameter("namePost", namePost);
        Post post=(Post)query.getSingleResult();
        return post;
    }

    @Override
    public List<Post> searh(Geographkoords geographkoords) {
        return null;
    }

//    @Override
//    public List<Post> searh(Geometry koords) {
//        return null;
//    }


    @Override
    public void add(String namePost, Double distanceBeetwenSensors, Geographkoords geographkoordsByGeographKoordsId, Basinlocality basinlocalityByBasinLocalityId, Ground groundByGroundId, Locality localityByLocalityId, String description) {

    }

    @Override
    public void dell(Post post) {

    }

    @Override
    public void dell(int idPost) {

    }

    @Override
    public void update(String namePost, Double distanceBeetwenSensors, Geographkoords geographkoordsByGeographKoordsId, Basinlocality basinlocalityByBasinLocalityId, Ground groundByGroundId, Locality localityByLocalityId, String description) {

    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


}
