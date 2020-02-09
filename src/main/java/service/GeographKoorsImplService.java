package service;

import dao.GeographKoordsDao;
import entity.Geographkoords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import view.KoordsView;

import java.util.ArrayList;
import java.util.List;

@Service
@Repository("GeographKoordsService")
public class GeographKoorsImplService implements GeographKoordsService {

    @Qualifier("GeographKoordsDao")
    @Autowired
    private GeographKoordsDao geographKoordsDao;

    @Override
    public List<KoordsView> getAllKoordsViev() {

        List<Geographkoords> listGeographKoords=geographKoordsDao.getAll();
        List<KoordsView> koordslist=new ArrayList<KoordsView>();

        for(Geographkoords x : listGeographKoords){
            koordslist.add(new KoordsView(x.getIdGeographKoords(),x.getTypeKoordinate(),x.getSrid(),
                    x.getRadiusAction(),x.getHeighSeaLevel(),x.getKoordinate(),x.getDescription()));
        }
        return koordslist;
    }

    @Override
    public List<KoordsView> searh(String koords) {
        return null;
    }

    @Override
    public KoordsView searhID(int koordsID) {
        return null;
    }

    @Override
    public void add(KoordsView koords) {
        geographKoordsDao.add(koords.getKoordinateTxt(),koords.getTypeKoordinate(),koords.getSrid(),
                koords.getRadiusAction(),koords.getHeighSeaLevel(),koords.getDescription());
    }
}
