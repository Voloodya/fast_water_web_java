package dao;

import entity.Geographkoords;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@SuppressWarnings("unchecked")
@Transactional
@Repository("GeographKoordsDao")
public class GeographKoordsImplDao implements GeographKoordsDao {

    private static final Log logger = LogFactory.getLog(GeographKoordsImplDao.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Geographkoords> getAll() {
        List<Geographkoords> listKoords=sessionFactory.getCurrentSession().createQuery("FROM Geographkoords e",Geographkoords.class).list();
     return listKoords;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Geographkoords> searh(String koords) {
        return null;
    }

    @Override
    public void add(String geogrKoords, String typekoords, int srid, double radiusaction, double heighSeaLevel, String description) {

//        Geometry koords=null;
//        try {
//            koords=wktToGeometry(geogrKoords);
//        } catch (ParseException e) {
//            System.out.println("Неудалось создать объект типа Geometry");
//            e.printStackTrace();
//        }

    Geographkoords newGeographKoords=new Geographkoords();
        newGeographKoords.setKoordinate(geogrKoords);
        newGeographKoords.setTypeKoordinate(typekoords);
        newGeographKoords.setSrid(srid);
        newGeographKoords.setRadiusAction(radiusaction);
        newGeographKoords.setHeighSeaLevel(heighSeaLevel);
        newGeographKoords.setDescription(description);
        sessionFactory.getCurrentSession().save(newGeographKoords);
        logger.info("Geograph koords saved with id"+newGeographKoords.getIdGeographKoords());
        sessionFactory.close();
    }

//    public Geometry wktToGeometry(String wellKnownText)
//            throws ParseException {
//
//        return new WKTReader().read(wellKnownText);
//    }

    @Override
    public void dell(String geographKoords) {
    }

    @Override
    public void dell(int idKoords) {
    }

    @Override
    public void update(String geogrKoords, String typekoords, int srid, double radiusaction, double heighSeaLevel, String description) {
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
