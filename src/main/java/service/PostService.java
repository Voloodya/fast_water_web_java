package service;

import entity.Locality;
import view.PostView;

import java.util.List;

public interface PostService {

    List<PostView> getAllPostView();
    PostView searсhPostViev(int idPost);
    List<PostView> searhPostViev(String namePost);
    List<PostView> searhPostViev(Locality locality);
//    List<PostView> searhPostViev(Geometry koords);

}
