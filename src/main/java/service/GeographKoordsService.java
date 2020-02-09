package service;

import view.KoordsView;

import java.util.List;

public interface GeographKoordsService {

    List<KoordsView> getAllKoordsViev();
    List<KoordsView> searh(String koords);
    KoordsView searhID(int koordsID);
    void add(KoordsView koords);

}
